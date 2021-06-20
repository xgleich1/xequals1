package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DashOperationAndDashOperationMapperTest {
    @Test
    fun `Should map the left and right side to their dash operations`() {
        // GIVEN
        val leftDashOperation = dashOperation()
        val rightDashOperation = dashOperation()

        val left = termsOf(leftDashOperation)
        val right = termsOf(rightDashOperation)

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(leftDashOperation)
        assertThat(mappedRight).isEqualTo(rightDashOperation)
    }

    private fun dashOperation(): DashOperation = mock()

    private fun map(left: Terms, right: Terms) =
            DashOperationAndDashOperationMapper.map(left, right)
}