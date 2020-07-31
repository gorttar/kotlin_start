package org.gorttar.control

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.gorttar.data.heterogeneous.list.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val initialX = 0
private const val changedX = 1 + initialX
private const val changedX2 = 2 + initialX

private const val initialY = "y"
private const val changedY = "y$initialY"
private const val changedY2 = "yy$initialY"

private const val initialZ = 2.5
private const val changedZ = 2 * initialZ
private const val changedZ2 = 3 * initialZ

class ManagedValueTest {
    private var x = initialX
    private var y = initialY
    private var z = initialZ

    @BeforeEach
    fun before() {
        x = initialX
        y = initialY
        z = initialZ
    }

    @Test
    fun `ManagedValue members tests`() {
        fun ManagedValue<Int>.check() {
            x = initialX
            assertThat(v).isEqualTo(initialX)
            v = changedX
            assertThat(v).isEqualTo(changedX)
            assertThat(x).isEqualTo(changedX)
        }

        managed({ x }) { x = it }.check()
        managed(::x).check()
    }

    @Test
    fun `test 'on' extension`() {
        managed(::x).on(changedX) { x1 ->
            assertThat(x1).isEqualTo(initialX)
            assertThat(x).isEqualTo(changedX)

            managed(::x).on(changedX2) { x2 ->
                assertThat(x2).isEqualTo(changedX)
                assertThat(x).isEqualTo(changedX2)
            }

            assertThat(x).isEqualTo(changedX)
        }
        assertThat(x).isEqualTo(initialX)
    }

    @Test
    fun `test 'coManaged' extension`() {
        managed(::x).coManaged(::y).on(changedX.`+`(changedY)) { (x1, y1) ->
            assertThat(x1).isEqualTo(initialX)
            assertThat(y1).isEqualTo(initialY)
            assertThat(x).isEqualTo(changedX)
            assertThat(y).isEqualTo(changedY)

            managed(::x).coManaged(::y).on(changedX2.`+`(changedY2)) { (x2, y2) ->
                assertThat(x2).isEqualTo(changedX)
                assertThat(y2).isEqualTo(changedY)
                assertThat(x).isEqualTo(changedX2)
                assertThat(y).isEqualTo(changedY2)
            }

            assertThat(x).isEqualTo(changedX)
            assertThat(y).isEqualTo(changedY)
        }
        assertThat(x).isEqualTo(initialX)
        assertThat(y).isEqualTo(initialY)
    }

    @Test
    fun `test 'coManagedN' extension`() {
        managed(::x).coManaged(::y).coManaged(::z).on(changedX.`+`(changedY) + changedZ) { (x1, y1, z1) ->
            assertThat(x1).isEqualTo(initialX)
            assertThat(y1).isEqualTo(initialY)
            assertThat(z1).isEqualTo(initialZ)
            assertThat(x).isEqualTo(changedX)
            assertThat(y).isEqualTo(changedY)
            assertThat(z).isEqualTo(changedZ)

            managed(::x).coManaged(::y).coManaged(::z).on(changedX2.`+`(changedY2) + changedZ2) { (x2, y2, z2) ->
                assertThat(x2).isEqualTo(changedX)
                assertThat(y2).isEqualTo(changedY)
                assertThat(z2).isEqualTo(changedZ)
                assertThat(x).isEqualTo(changedX2)
                assertThat(y).isEqualTo(changedY2)
                assertThat(z).isEqualTo(changedZ2)
            }

            assertThat(x).isEqualTo(changedX)
            assertThat(y).isEqualTo(changedY)
            assertThat(z).isEqualTo(changedZ)
        }
        assertThat(x).isEqualTo(initialX)
        assertThat(y).isEqualTo(initialY)
        assertThat(z).isEqualTo(initialZ)
    }
}