package org.gorttar.data.heterogeneous.list.generators

import org.junit.jupiter.api.Test

private const val testClassName = "DestructuringKtTest"

fun main(): Unit = writeTestSrc(
    testClassName,
    """
    |import assertk.assertAll
    |import assertk.assertThat
    |import assertk.assertions.isEqualTo
    |import ${Test::class.qualifiedName}
    |
    |class $testClassName {
    |${(minPropName..maxPropName).joinToString("\n\n") { lastPropName ->
        (minPropName..lastPropName)
        val lastPropNumber = lastPropName.number
        """
        |    @Test
        |    fun `xs$lastPropNumber destructuring`() = assertAll {
        |        val (${(minPropName..lastPropName).joinToString { "_$it" }}) = xs$lastPropNumber
        |${(minPropName..lastPropName).joinToString("\n") { """|        assertThat(_$it).isEqualTo($it)""" }}
        |    }
        """.trimMargin()
    }}
    |}
    """.trimMargin()
)