package org.gorttar.data.heterogeneous.list

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class DestructuringKtTest {
    @Test
    fun `xs1 destructuring`(): Unit = assertAll {
        val (_a) = xs1
        assertThat(_a).isEqualTo(a)
    }

    @Test
    fun `xs2 destructuring`(): Unit = assertAll {
        val (_a, _b) = xs2
        assertThat(_a).isEqualTo(a)
        assertThat(_b).isEqualTo(b)
    }

    @Test
    fun `xs3 destructuring`(): Unit = assertAll {
        val (_a, _b, _c) = xs3
        assertThat(_a).isEqualTo(a)
        assertThat(_b).isEqualTo(b)
        assertThat(_c).isEqualTo(c)
    }

    @Test
    fun `xs4 destructuring`(): Unit = assertAll {
        val (_a, _b, _c, _d) = xs4
        assertThat(_a).isEqualTo(a)
        assertThat(_b).isEqualTo(b)
        assertThat(_c).isEqualTo(c)
        assertThat(_d).isEqualTo(d)
    }

    @Test
    fun `xs5 destructuring`(): Unit = assertAll {
        val (_a, _b, _c, _d, _e) = xs5
        assertThat(_a).isEqualTo(a)
        assertThat(_b).isEqualTo(b)
        assertThat(_c).isEqualTo(c)
        assertThat(_d).isEqualTo(d)
        assertThat(_e).isEqualTo(e)
    }

    @Test
    fun `xs6 destructuring`(): Unit = assertAll {
        val (_a, _b, _c, _d, _e, _f) = xs6
        assertThat(_a).isEqualTo(a)
        assertThat(_b).isEqualTo(b)
        assertThat(_c).isEqualTo(c)
        assertThat(_d).isEqualTo(d)
        assertThat(_e).isEqualTo(e)
        assertThat(_f).isEqualTo(f)
    }

    @Test
    fun `xs7 destructuring`(): Unit = assertAll {
        val (_a, _b, _c, _d, _e, _f, _g) = xs7
        assertThat(_a).isEqualTo(a)
        assertThat(_b).isEqualTo(b)
        assertThat(_c).isEqualTo(c)
        assertThat(_d).isEqualTo(d)
        assertThat(_e).isEqualTo(e)
        assertThat(_f).isEqualTo(f)
        assertThat(_g).isEqualTo(g)
    }

    @Test
    fun `xs8 destructuring`(): Unit = assertAll {
        val (_a, _b, _c, _d, _e, _f, _g, _h) = xs8
        assertThat(_a).isEqualTo(a)
        assertThat(_b).isEqualTo(b)
        assertThat(_c).isEqualTo(c)
        assertThat(_d).isEqualTo(d)
        assertThat(_e).isEqualTo(e)
        assertThat(_f).isEqualTo(f)
        assertThat(_g).isEqualTo(g)
        assertThat(_h).isEqualTo(h)
    }

    @Test
    fun `xs9 destructuring`(): Unit = assertAll {
        val (_a, _b, _c, _d, _e, _f, _g, _h, _i) = xs9
        assertThat(_a).isEqualTo(a)
        assertThat(_b).isEqualTo(b)
        assertThat(_c).isEqualTo(c)
        assertThat(_d).isEqualTo(d)
        assertThat(_e).isEqualTo(e)
        assertThat(_f).isEqualTo(f)
        assertThat(_g).isEqualTo(g)
        assertThat(_h).isEqualTo(h)
        assertThat(_i).isEqualTo(i)
    }

    @Test
    fun `xs10 destructuring`(): Unit = assertAll {
        val (_a, _b, _c, _d, _e, _f, _g, _h, _i, _j) = xs10
        assertThat(_a).isEqualTo(a)
        assertThat(_b).isEqualTo(b)
        assertThat(_c).isEqualTo(c)
        assertThat(_d).isEqualTo(d)
        assertThat(_e).isEqualTo(e)
        assertThat(_f).isEqualTo(f)
        assertThat(_g).isEqualTo(g)
        assertThat(_h).isEqualTo(h)
        assertThat(_i).isEqualTo(i)
        assertThat(_j).isEqualTo(j)
    }
}