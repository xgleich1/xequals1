package com.dg.eqs.base.extension

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class PairExtTest {
    @Test
    fun `Should provide an aptly named shortcut to access the left part of a pair`() {
        assertThat(Pair(1, "2").left).isEqualTo(1)
    }

    @Test
    fun `Should provide an aptly named shortcut to access the right part of a pair`() {
        assertThat(Pair(1, "2").right).isEqualTo("2")
    }
}