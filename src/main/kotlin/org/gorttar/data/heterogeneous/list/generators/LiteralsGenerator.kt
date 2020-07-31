package org.gorttar.data.heterogeneous.list.generators

import kotlin.math.ceil

fun main(): Unit = writeMainSrc(
    srcName = "Literals",
    content = """
    |fun hListOf(): HNil = HNil
    |${(minPropName..maxPropName).joinToString("\n") { lastPropName ->
        val propNames = minPropName..lastPropName
        val types = propNames.joinToString { "${it.typeName}" }
        """
        |
        |fun <$types> hListOf(
        |    ${propNames.chunked(ceil(lastPropName.number / 2.0).toInt()).joinToString(",\n|    ") { chunk ->
            chunk.joinToString { "$it: ${it.typeName}" }
        }}
        |): HList${lastPropName.number}<$types> =
        |    hListOf(${(minPropName until lastPropName).joinToString()}) + $lastPropName
        """.trimMargin()
    }}
    """.trimMargin()
)