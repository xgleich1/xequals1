package com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AdditionToPositiveDashOperationIntegratorTest {
    @Test
    fun `Should integrate a single source by adding it to the positive dash operation`() {
        // GIVEN
        val singleSource = anything()
        val source = termsOf(singleSource)

        val targetSide = PositiveDashOperation(anything(), anything())

        // WHEN
        val newTargetSide = integrate(source, targetSide)

        // THEN
        val expectedNewTargetSide = PositiveDashOperation(
                targetSide.first,
                targetSide.second,
                singleSource)

        assertThat(newTargetSide).isEqualTo(expectedNewTargetSide)
    }

    @Test
    fun `Should integrate multiple sources by adding them to the positive dash operation`() {
        // GIVEN
        val firstSource = anything()
        val secondSource = anything()
        val source = termsOf(firstSource, secondSource)

        val targetSide = PositiveDashOperation(anything(), anything())

        // WHEN
        val newTargetSide = integrate(source, targetSide)

        // THEN
        val expectedNewTargetSide = PositiveDashOperation(
                targetSide.first,
                targetSide.second,
                firstSource,
                secondSource)

        assertThat(newTargetSide).isEqualTo(expectedNewTargetSide)
    }

    private fun anything(): Term = mock()

    private fun integrate(source: Terms, targetSide: PositiveDashOperation) =
            AdditionToPositiveDashOperationIntegrator.integrate(source, targetSide)
}