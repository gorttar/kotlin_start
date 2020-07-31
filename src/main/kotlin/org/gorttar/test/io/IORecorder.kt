package org.gorttar.test.io

import org.gorttar.control.coManaged
import org.gorttar.control.managed
import org.gorttar.control.on
import org.gorttar.data.heterogeneous.list.hListOf
import org.gorttar.test.io.OperationType.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

enum class OperationType { IN, OUT, ERR }
data class IOOperationChunk(val operationType: OperationType, val content: String) {
    override fun toString() = "IOOperationChunk(operationType=$operationType, content=\"$content\")"
}

fun recordIO(input: String, block: () -> Unit): List<IOOperationChunk> = IORecorder().also {
    managed(System::`in`, System::setIn).coManaged(System::out, System::setOut).coManaged(System::err, System::setErr)
        .on(
            hListOf(
                RecordedInStream(input.toByteArray(), it),
                RecordedOutputStream(it, OUT).let(::PrintStream),
                RecordedOutputStream(it, ERR).let(::PrintStream)
            )
        ) { block() }
}.chunks

internal class IORecorder {
    private var curOperationType: OperationType? = null
    private val curContentOutputStream = ByteArrayOutputStream()
    private val _chunks = arrayListOf<IOOperationChunk>()
    private val OperationType.curChunk get() = IOOperationChunk(this, curContentOutputStream.toString())
    internal val chunks get() = _chunks + (curOperationType?.run { listOf(curChunk) } ?: emptyList())

    internal fun recordByte(byte: Byte, operationType: OperationType) =
        operationType.record { curContentOutputStream.write(byte.toInt()) }

    internal fun recordBytes(
        bytes: ByteArray, operationType: OperationType, off: Int = 0, len: Int = bytes.size - off
    ) = operationType.record { curContentOutputStream.write(bytes, off, len) }

    private inline fun OperationType.record(recorderBlock: () -> Unit) = curOperationType.takeIf { it != this }?.also {
        _chunks += it.curChunk
        curContentOutputStream.reset()
    }.let {
        curOperationType = this
        recorderBlock()
    }
}

private class RecordedInStream(buf: ByteArray, private val ioRecorder: IORecorder) : ByteArrayInputStream(buf) {
    override fun read() = super.read().also { ioRecorder.recordByte(it.toByte(), IN) }
    override fun read(b: ByteArray, off: Int, len: Int) =
        super.read(b, off, len).also { ioRecorder.recordBytes(bytes = b, operationType = IN, off = off, len = it) }
}

private class RecordedOutputStream(
    private val ioRecorder: IORecorder,
    private val operationType: OperationType
) : ByteArrayOutputStream() {
    override fun write(b: Int) = super.write(b).also { ioRecorder.recordByte(b.toByte(), operationType) }
    override fun write(b: ByteArray, off: Int, len: Int) = super.write(b, off, len).also {
        ioRecorder.recordBytes(bytes = b, operationType = operationType, off = off, len = len)
    }
}