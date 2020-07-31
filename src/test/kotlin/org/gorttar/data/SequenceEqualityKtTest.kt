package org.gorttar.data

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

class SequenceEqualityKtTest {
    @Test
    fun `eq empty sequences`(): Unit = assertAll {
        assertThat(emptySequence<Nothing>() eq listOf<Nothing>().asSequence()).isTrue()
    }

    @Test
    fun `eq non empty finite sequences`(): Unit = assertAll {
        assertThat((1..3).asSequence() eq sequenceOf(1, 2, 3)).isTrue()
    }

    @Test
    fun `not eq non empty finite sequences`(): Unit = assertAll {
        assertThat((1..3).asSequence() eq sequenceOf(1, 3, 3)).isFalse()
        assertThat((1..3).asSequence() eq sequenceOf(1, 2)).isFalse()
    }

    @Test
    fun `not eq infinite sequences`() =
        assertThat(generateSequence(1) { it + 1 } eq generateSequence(1) { it + 2 }).isFalse()
}