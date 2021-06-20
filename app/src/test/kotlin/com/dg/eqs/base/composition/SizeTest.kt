package com.dg.eqs.base.composition

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class SizeTest {
    @Test
    fun `A size provides its halved width`() {
        assertThat(Size(500, 0).halfWidth).isEqualTo(250)
    }

    @Test
    fun `A size provides its halved height`() {
        assertThat(Size(0, 500).halfHeight).isEqualTo(250)
    }
}