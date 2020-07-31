package org.gorttar.data.heterogeneous.list.generators

fun main(): Unit = writeMainSrc(
    srcName = "Aliases",
    content = """
    |typealias HList1<A> =
    |    HCons<HNil, A>
    |
    |${(minPropName + 1..maxPropName).joinToString("\n\n") {
        val lastType = it.typeName
        val prevTypes = (minPropName.typeName until lastType).joinToString()
        """
        |typealias HList${it.number}<$prevTypes, $lastType> =
        |    HCons<HList${it.number - 1}<$prevTypes>, $lastType>
        """.trimMargin()
    }}
    """.trimMargin()
)


