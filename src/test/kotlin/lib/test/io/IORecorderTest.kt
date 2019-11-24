package lib.test.io

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import lib.test.io.OperationType.IN
import lib.test.io.OperationType.OUT
import org.testng.annotations.Test

class IORecorderTest {
    @Test
    fun `operationChunks empty recorder`() = assertThat(IORecorder().operationChunks).isEmpty()

    @Test
    fun recordByte() = IORecorder().run {
        recordByte('a'.toByte(), IN)
        assertThat(operationChunks).isEqualTo(listOf(IOOperationChunk(IN, "a".toByteArray())))

        recordByte('b'.toByte(), IN)
        assertThat(operationChunks).isEqualTo(listOf(IOOperationChunk(IN, "ab".toByteArray())))

        recordByte('c'.toByte(), OUT)
        assertThat(operationChunks)
                .isEqualTo(
                        listOf(
                                IOOperationChunk(IN, "ab".toByteArray()),
                                IOOperationChunk(OUT, "c".toByteArray())
                        )
                )
        recordByte('d'.toByte(), OUT)
        assertThat(operationChunks)
                .isEqualTo(
                        listOf(
                                IOOperationChunk(IN, "ab".toByteArray()),
                                IOOperationChunk(OUT, "cd".toByteArray())
                        )
                )
    }

    @Test
    fun `recordBytes empty array`() = IORecorder().run {
        recordBytes(byteArrayOf(), IN)
        assertThat(operationChunks).isEqualTo(listOf(IOOperationChunk(IN, byteArrayOf())))

        recordBytes(byteArrayOf(), IN)
        assertThat(operationChunks).isEqualTo(listOf(IOOperationChunk(IN, byteArrayOf())))

        recordBytes(byteArrayOf(), OUT)
        assertThat(operationChunks)
                .isEqualTo(listOf(IOOperationChunk(IN, byteArrayOf()), IOOperationChunk(OUT, byteArrayOf())))

        recordBytes(byteArrayOf(), OUT)
        assertThat(operationChunks)
                .isEqualTo(listOf(IOOperationChunk(IN, byteArrayOf()), IOOperationChunk(OUT, byteArrayOf())))
    }

    @Test
    fun `recordBytes non empty array`() = IORecorder().run {
        recordBytes("ab".toByteArray(), IN)
        assertThat(operationChunks).isEqualTo(listOf(IOOperationChunk(IN, "ab".toByteArray())))

        recordBytes("cd".toByteArray(), IN)
        assertThat(operationChunks).isEqualTo(listOf(IOOperationChunk(IN, "abcd".toByteArray())))

        recordBytes("ef".toByteArray(), OUT)
        assertThat(operationChunks)
                .isEqualTo(
                        listOf(
                                IOOperationChunk(IN, "abcd".toByteArray()),
                                IOOperationChunk(OUT, "ef".toByteArray())
                        )
                )

        recordBytes("gh".toByteArray(), OUT)
        assertThat(operationChunks)
                .isEqualTo(
                        listOf(
                                IOOperationChunk(IN, "abcd".toByteArray()),
                                IOOperationChunk(OUT, "efgh".toByteArray())
                        )
                )
    }
}