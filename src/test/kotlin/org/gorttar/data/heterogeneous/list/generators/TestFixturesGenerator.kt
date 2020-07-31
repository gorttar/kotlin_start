package org.gorttar.data.heterogeneous.list.generators

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.KClass

fun main(): Unit = writeTestSrc(
    "TestFixtures",
    """
    |import ${BigDecimal::class.qualifiedName}
    |import ${BigInteger::class.qualifiedName}
    |
    |internal const val ${propNames.next()} = ${propValues.next()}.toByte()
    |internal const val ${propNames.next()} = ${propValues.next()}.toShort()
    |internal const val ${propNames.next()} = ${propValues.next()}.toLong()
    |internal const val ${propNames.next()} = ${propValues.next()}
    |internal const val ${propNames.next()} = '${propValues.next()}'
    |internal val ${propNames.next()} = ${propValues.next()}.toBigInteger()
    |internal const val ${propNames.next()} = ${propValues.next()}.toDouble()
    |internal const val ${propNames.next()} = ${propValues.next()}.toFloat()
    |internal val ${propNames.next()} = ${propValues.next()}.toBigDecimal()
    |internal const val ${propNames.next()} = ${propValues.next()}.toString()
    |${sequenceOf<(Char) -> String>(
        { "internal val ${it + baseValsAmount} = listOf($it)" },
        { "internal val ${it + 2 * baseValsAmount} = setOf($it)" }
    ).flatMap {
        (minPropName until minPropName + baseValsAmount).asSequence().map(it)
    }.take(maxPropNumber - baseValsAmount).joinToString("\n")}
    |
    |internal val xs$maxPropNumber: HList$maxPropNumber<
    |${sequenceOf<(String) -> String>(
        { it },
        { "List<$it>" },
        { "Set<$it>" }
    ).flatMap {
        sequenceOf(
            Byte::class, Short::class, Long::class, Int::class, Char::class,
            BigInteger::class, Double::class, Float::class, BigDecimal::class, String::class
        ).mapNotNull(KClass<*>::simpleName).map(it)
    }.take(maxPropNumber).chunked(9) { "    ${it.joinToString()}" }.joinToString(",\n")}
    |    > = $minPropName.`+`(${minPropName + 1}) + ${(minPropName + 2..maxPropName).joinToString(" + ")}
    |${(maxPropNumber - 1 downTo 0).joinToString("\n") { "internal val xs$it = xs${it + 1}.head" }}
    """.trimMargin()
)

private const val baseValsAmount = 10
private val propNames = generateSequence(minPropName) { it + 1 }.iterator()
private val propValues = generateSequence(0) { it + 1 }.iterator()
