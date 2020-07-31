package org.gorttar.data.heterogeneous.list.generators

import org.junit.jupiter.api.TestFactory

private const val testClassName = "ExtensionPropertiesKtTest"

fun main(): Unit = writeTestSrc(
    testClassName,
    """
    |import assertk.assertThat
    |import assertk.assertions.isEqualTo
    |import org.gorttar.test.dynamicTests
    |import ${TestFactory::class.qualifiedName}
    |
    |class $testClassName {
    |${(minPropName..maxPropName).joinToString("\n\n") { propName ->
        """
        |    @TestFactory
        |    fun $propName() = dynamicTests(
        |${(propName.number..maxPropNumber).asSequence().map {
            "Case(xs$it, xs$it.$propName)"
        }.chunked(5) { it.joinToString() }.joinToString(",\n") { "        $it" }}
        |    ) { assertThat(actual, "${propName.ordinalStr} value in ${buck}xs").isEqualTo($propName) }
        """.trimMargin()
    }}
    |
    |    private data class Case<XS : HList<XS>, A>(val xs: XS, val actual: A)
    |}
    """.trimMargin()
)