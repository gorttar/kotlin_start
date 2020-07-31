package org.gorttar.test.io

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import org.gorttar.test.io.OperationType.*
import org.junit.jupiter.api.Test

class IORecorderTest {
    @Test
    fun `operationChunks empty recorder`() = assertThat(IORecorder().chunks).isEmpty()

    @Test
    fun recordByte() = IORecorder().run {
        recordByte('a'.toByte(), IN)
        assertThat(chunks).containsExactly(IOOperationChunk(IN, "a"))

        recordByte('b'.toByte(), IN)
        assertThat(chunks).containsExactly(IOOperationChunk(IN, "ab"))

        recordByte('c'.toByte(), OUT)
        assertThat(chunks).containsExactly(
            IOOperationChunk(IN, "ab"),
            IOOperationChunk(OUT, "c")
        )

        recordByte('d'.toByte(), ERR)
        assertThat(chunks).containsExactly(
            IOOperationChunk(IN, "ab"),
            IOOperationChunk(OUT, "c"),
            IOOperationChunk(ERR, "d")
        )
    }

    @Test
    fun `recordBytes empty array`() = IORecorder().run {
        recordBytes(byteArrayOf(), IN)
        assertThat(chunks).containsExactly(IOOperationChunk(IN, ""))

        recordBytes(byteArrayOf(), IN)
        assertThat(chunks).containsExactly(IOOperationChunk(IN, ""))

        recordBytes(byteArrayOf(), OUT)
        assertThat(chunks).containsExactly(
            IOOperationChunk(IN, ""),
            IOOperationChunk(OUT, "")
        )

        recordBytes(byteArrayOf(), ERR)
        assertThat(chunks).containsExactly(
            IOOperationChunk(IN, ""),
            IOOperationChunk(OUT, ""),
            IOOperationChunk(ERR, "")
        )
    }

    @Test
    fun `recordBytes non empty array`() = IORecorder().run {
        recordBytes("ab".toByteArray(), IN)
        assertThat(chunks).containsExactly(IOOperationChunk(IN, "ab"))

        recordBytes("cd".toByteArray(), IN)
        assertThat(chunks).containsExactly(IOOperationChunk(IN, "abcd"))

        recordBytes("ef".toByteArray(), OUT)
        assertThat(chunks).containsExactly(
            IOOperationChunk(IN, "abcd"),
            IOOperationChunk(OUT, "ef")
        )

        recordBytes("gh".toByteArray(), ERR)
        assertThat(chunks).containsExactly(
            IOOperationChunk(IN, "abcd"),
            IOOperationChunk(OUT, "ef"),
            IOOperationChunk(ERR, "gh")
        )
    }

    @Test
    fun `recordBytes off len`() = IORecorder().run {
        recordBytes(bytes = "abcd".toByteArray(), operationType = IN, off = 1, len = 2)
        assertThat(chunks).containsExactly(IOOperationChunk(IN, "bc"))
    }
}

class RecordIOTest {
    @Test
    fun recordIOTest() {
        val actual = recordIO("hello\n") {
            println("Start")
            val readLine = readLine()
            println("Read: $readLine")
            System.err.println("ErrRead: $readLine")
            println("End")
            System.err.print("ErrEnd")
            System.err.println()
        }
        val cr = if (actual.first().content.takeLast(2).first() == '\r') "\r\n"
        else "\n"
        assertThat(actual).containsExactly(
                IOOperationChunk(OUT, "Start$cr"),
                IOOperationChunk(IN, "hello\n"),
                IOOperationChunk(OUT, "Read: hello$cr"),
                IOOperationChunk(ERR, "ErrRead: hello$cr"),
                IOOperationChunk(OUT, "End$cr"),
                IOOperationChunk(ERR, "ErrEnd$cr")
        )
    }
}