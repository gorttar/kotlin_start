package org.gorttar.test.io

import org.gorttar.control.coManaged
import org.gorttar.control.managed
import org.gorttar.control.on
import org.gorttar.data.heterogeneous.list.`+`
import org.gorttar.data.heterogeneous.list.plus
import org.gorttar.test.io.OperationType.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

enum class OperationType { IN, OUT, ERR }
data class IOOperationChunk(val operationType: OperationType, val content: String) {
    override fun toString() = "IOOperationChunk(operationType=$operationType, content=\"$content\")"
}

class IORecorder {
    private var curOperationType: OperationType? = null
    private val curContentOutputStream = ByteArrayOutputStream()
    private val _operationChunks: MutableList<IOOperationChunk> = arrayListOf()
    private val OperationType.curChunk get() = IOOperationChunk(this, curContentOutputStream.toString())
    val operationChunks: List<IOOperationChunk>
        get() = _operationChunks +
            (curOperationType?.run { listOf(curChunk) } ?: emptyList())

    fun recordByte(byte: Byte, operationType: OperationType): Unit =
        operationType.record { curContentOutputStream.write(byte.toInt()) }

    fun recordBytes(bytes: ByteArray, operationType: OperationType, off: Int = 0, len: Int = bytes.size - off): Unit =
        operationType.record { curContentOutputStream.write(bytes, off, len) }

    private inline fun OperationType.record(recorderBlock: () -> Unit) = curOperationType.takeIf { it != this }?.let {
        _operationChunks += it.curChunk
        curContentOutputStream.reset()
    }.let {
        curOperationType = this
        recorderBlock()
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

inline fun recordIO(input: String, block: () -> Unit): List<IOOperationChunk> = IORecorder().let { recorder ->
    managed(System::`in`, System::setIn)
        .coManaged(System::out, System::setOut)
        .coManaged(System::err, System::setErr).on(
            RecordedInStream(input.toByteArray(), recorder)
                .`+`(PrintStream(RecordedOutStream(recorder))) +
                PrintStream(RecordedErrStream(recorder))
        ) { block() }
    recorder.operationChunks
}