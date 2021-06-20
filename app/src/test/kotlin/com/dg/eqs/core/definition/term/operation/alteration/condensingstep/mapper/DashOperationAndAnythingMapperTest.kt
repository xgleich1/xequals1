package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DashOperationAndAnythingMapperTest {
    @Test
    fun `Should map the left side to its dash operation and leave the right side unmapped`() {
        // GIVEN
        val dashOperation = dashOperation()

        val left = termsOf(dashOperation)
        val right: Terms = mock()

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(dashOperation)
        assertThat(mappedRight).isEqualTo(right)
    }

    private fun dashOperation(): DashOperation = mock()

    private fun map(left: Terms, right: Terms) =
            DashOperationAndAnythingMapper.map(left, right)
}