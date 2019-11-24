package lib.test.io

import lib.control.ManagedValue
import lib.control.on
import lib.test.io.OperationType.ERR
import lib.test.io.OperationType.IN
import lib.test.io.OperationType.OUT
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

enum class OperationType { IN, OUT, ERR }
class IOOperationChunk(val operationType: OperationType, content: ByteArray) {
    constructor(operationType: OperationType, content: String) : this(operationType, content.toByteArray())
    val content: ByteArray = content.copyOf()
        get() = field.copyOf()

    override fun hashCode() = operationType.hashCode() * 31 + content.contentHashCode()
    override fun toString() = "IOOperationChunk(operationType=$operationType, content=\"${String(content)}\")"
    override fun equals(other: Any?): Boolean = this === other ||
            other is IOOperationChunk && operationType == other.operationType && content.contentEquals(other.content)
}

class IORecorder {
    private lateinit var curOperationType: OperationType
    private var curByteChunk = byteArrayOf()
    private val _operationChunks: MutableList<IOOperationChunk> = arrayListOf()
    val operationChunks: List<IOOperationChunk>
        get() = _operationChunks +
                if (::curOperationType.isInitialized) listOf(IOOperationChunk(curOperationType, curByteChunk))
                else emptyList()

    fun recordByte(byte: Byte, operationType: OperationType) = recordBytes(byteArrayOf(byte), operationType)
    fun recordBytes(bytes: ByteArray, operationType: OperationType, off: Int = 0, len: Int = bytes.size - off) {
        if (::curOperationType.isInitialized && curOperationType != operationType) {
            _operationChunks += IOOperationChunk(curOperationType, curByteChunk)
            curByteChunk = byteArrayOf()
        }
        curOperationType = operationType
        curByteChunk += bytes.copyOfRange(off, off + len)
    }
}

class RecordedInStream(buf: ByteArray, private val ioRecorder: IORecorder) : ByteArrayInputStream(buf) {
    override fun read() = super.read().also { ioRecorder.recordByte(it.toByte(), IN) }
    override fun read(b: ByteArray, off: Int, len: Int) =
            super.read(b, off, len).also { ioRecorder.recordBytes(bytes = b, operationType = IN, off = off, len = it) }
}

sealed class RecordedOutputStream(
        private val ioRecorder: IORecorder,
        private val operationType: OperationType
) : ByteArrayOutputStream() {
    override fun write(b: Int) = super.write(b).also { ioRecorder.recordByte(b.toByte(), operationType) }
    override fun write(b: ByteArray, off: Int, len: Int) = super.write(b, off, len).also {
        ioRecorder.recordBytes(bytes = b, operationType = operationType, off = off, len = len)
    }
}

class RecordedOutStream(ioRecorder: IORecorder) : RecordedOutputStream(ioRecorder, OUT)
class RecordedErrStream(ioRecorder: IORecorder) : RecordedOutputStream(ioRecorder, ERR)

fun recordIO(input: String, block: () -> Unit): List<IOOperationChunk> = IORecorder().let { recorder ->
    ManagedValue(System::`in`, System::setIn).on(RecordedInStream(input.toByteArray(), recorder)) {
        ManagedValue(System::out, System::setOut).on(PrintStream(RecordedOutStream(recorder))) {
            ManagedValue(System::err, System::setErr).on(PrintStream(RecordedErrStream(recorder))) {
                block()
            }
        }
    }
    recorder.operationChunks
}