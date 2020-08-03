package org.gorttar.test

import org.gorttar.helpers.alphabet
import kotlin.random.Random

inline val FAIL: Nothing inline get() = TODO("Реши меня")
fun randomAlphabetPartition(): Pair<String, String> = generateSequence { alphabet.partition { Random.nextBoolean() } }
    .first { (xs, ys) -> xs.isNotEmpty() && ys.isNotEmpty() }