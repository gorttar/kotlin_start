package org.gorttar.data.heterogeneous.list.generators

fun main(): Unit = writeSrc(
    "Destructuring",
    """
    |${(minPropName..maxPropName).joinToString("\n\n") { propName ->
        val typeName = propName.typeName
        val typesBefore = (minPropName until propName).map { "*" } + typeName
        val propNumber = propName.number
        val funName = "component$propNumber"
        """
        |/** $funName of [HList$propNumber]..[HList$maxPropNumber] */
        |
        |@JvmName("$propName$propNumber")
        |inline operator fun <$typeName> HList$propNumber<${typesBefore.joinToString()}>.$funName(): $typeName =
        |    tail
        |${(propName until maxPropName).joinToString("\n") {
            val listArity = it.number + 1
            val typesStr = (typesBefore + (propName..it).map { "*" }).joinToString()
            """
            |
            |@JvmName("$propName$listArity")
            |inline operator fun <$typeName> HList$listArity<$typesStr>.$funName(): $typeName =
            |    head.$funName()
            """.trimMargin()
        }}""".trimMargin()
    }}""".trimMargin(),
    """
    |@file:Suppress("NOTHING_TO_INLINE")
    |
    """.trimMargin()
)

