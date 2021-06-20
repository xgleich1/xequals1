package com.dg.eqs.core.definition.term.operation.alteration.condensingstep

import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class CondensingSidesExtTest {
    @Test
    fun `Should provide a shortcut to encapsulate the left & right condensing sides`() {
        // GIVEN
        val left: CondensingSide = mock()
        val right: CondensingSide = mock()

        // THEN
        assertThat(left and right).isEqualTo(CondensingSides(left, right))
    }
}