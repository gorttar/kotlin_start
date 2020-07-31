package org.gorttar.data.heterogeneous.list.generators

fun main(): Unit = writeMainSrc(
    srcName = "ExtensionProperties",
    content = """
    |${(minPropName..maxPropName).joinToString("\n\n") { propName ->
        val typeName = propName.typeName
        val typesBefore = (minPropName until propName).map { "*" } + typeName
        """
        |/**
        | * ${propName.ordinalStr} value of [HList${propName.number}]..[HList$maxPropNumber]
        | */
        |inline val <$typeName> HList${propName.number}<${typesBefore.joinToString()}>.$propName: $typeName @JvmName("$propName${propName.number}") get() = tail
        |${(propName until maxPropName).joinToString("\n") {
            val listArity = it.number + 1
            val typesStr = (typesBefore + (propName..it).map { "*" }).joinToString()
            """inline val <$typeName> HList$listArity<$typesStr>.$propName: $typeName @JvmName("$propName$listArity") get() = head.$propName"""
        }}""".trimMargin()
    }}""".trimMargin()
)

