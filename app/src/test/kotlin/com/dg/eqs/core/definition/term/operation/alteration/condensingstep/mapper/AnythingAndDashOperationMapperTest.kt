package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AnythingAndDashOperationMapperTest {
    @Test
    fun `Should leave the left side unmapped and map the right side to its dash operation`() {
        // GIVEN
        val dashOperation = dashOperation()

        val left: Terms = mock()
        val right = termsOf(dashOperation)

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(left)
        assertThat(mappedRight).isEqualTo(dashOperation)
    }

    private fun dashOperation(): DashOperation = mock()

    private fun map(left: Terms, right: Terms) =
            AnythingAndDashOperationMapper.map(left, right)
}