package lib.test.io

enum class OperationType { IN, OUT }
class IOOperationChunk(val operationType: OperationType, content: ByteArray) {
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
    fun recordBytes(bytes: ByteArray, operationType: OperationType, off: Int = 0, len: Int = bytes.size) {
        if (::curOperationType.isInitialized && curOperationType != operationType) {
            _operationChunks += IOOperationChunk(curOperationType, curByteChunk)
            curByteChunk = byteArrayOf()
        }
        curOperationType = operationType
        curByteChunk += bytes.copyOfRange(off, off + len)
    }
}
