package com.dg.eqs.core.definition.relation.alteration.shiftingstep

import com.dg.eqs.core.definition.term.Term
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ShiftingSidesExtTest {
    @Test
    fun `Should provide a shortcut to encapsulate the source & target shifting sides`() {
        // GIVEN
        val sourceSide: Term = mock()
        val targetSide: Term = mock()

        // THEN
        val expectedShiftingSides = ShiftingSides(sourceSide, targetSide)

        assertThat(sourceSide and targetSide).isEqualTo(expectedShiftingSides)
    }
}