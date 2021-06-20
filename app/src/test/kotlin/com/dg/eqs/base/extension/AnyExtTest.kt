package com.dg.eqs.base.extension

import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AnyExtTest {
    @Test
    fun `Should provide a typed shortcut to create a pair of any kind of objects`() {
        // GIVEN
        val anythingA: Any = mock()
        val anythingB: Any = mock()

        // WHEN
        val pair = anythingA and anythingB

        // THEN
        assertThat(pair).isEqualTo(Pair(anythingA, anythingB))
    }
}