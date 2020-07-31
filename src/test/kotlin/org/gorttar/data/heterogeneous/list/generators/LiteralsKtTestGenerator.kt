package org.gorttar.data.heterogeneous.list.generators

import org.junit.jupiter.api.Test

private const val testClassName = "LiteralsKtTest"

fun main(): Unit = writeTestSrc(
    testClassName,
    """
    |import assertk.assertThat
    |import assertk.assertions.isEqualTo
    |import assertk.assertions.isSameAs
    |import ${Test::class.qualifiedName}
    |
    |class $testClassName {
    |    @Test
    |    fun `0 args literal`() = assertThat(
    |        hListOf()
    |    ).isSameAs(xs0)
    |
    |${(minPropName..maxPropName).joinToString("\n\n") { lastPropName ->
        val lastPropNumber = lastPropName.number
        """
        |    @Test
        |    fun `$lastPropNumber  args literal`() = assertThat(
        |        hListOf(${(minPropName..lastPropName).joinToString()})
        |    ).isEqualTo(xs$lastPropNumber)
        """.trimMargin()
    }}
    |}
    """.trimMargin()
)