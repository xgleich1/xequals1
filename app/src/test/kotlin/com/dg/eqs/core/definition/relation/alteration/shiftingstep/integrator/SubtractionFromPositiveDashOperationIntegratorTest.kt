package com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class SubtractionFromPositiveDashOperationIntegratorTest {
    @Test
    fun `Should integrate a single source by subtracting it from the positive dash operation`() {
        // GIVEN
        val (singleSource, oppositeSingleSource) = anythingAndOpposite()
        val source = termsOf(singleSource)

        val targetSide = PositiveDashOperation(anything(), anything())

        // WHEN
        val newTargetSide = integrate(source, targetSide)

        // THEN
        val expectedNewTargetSide = PositiveDashOperation(
                targetSide.first,
                targetSide.second,
                oppositeSingleSource)

        assertThat(newTargetSide).isEqualTo(expectedNewTargetSide)
    }

    @Test
    fun `Should integrate multiple sources by subtracting them from the positive dash operation`() {
        // GIVEN
        val (firstSource, oppositeFirstSource) = anythingAndOpposite()
        val (secondSource, oppositeSecondSource) = anythingAndOpposite()
        val source = termsOf(firstSource, secondSource)

        val targetSide = PositiveDashOperation(anything(), anything())

        // WHEN
        val newTargetSide = integrate(source, targetSide)

        // THEN
        val expectedNewTargetSide = PositiveDashOperation(
                targetSide.first,
                targetSide.second,
                oppositeFirstSource,
                oppositeSecondSource)

        assertThat(newTargetSide).isEqualTo(expectedNewTargetSide)
    }

    private fun anything(): Term = mock()

    private fun anythingAndOpposite(): Pair<Term, Term> {
        val anything = anything()
        val opposite = anything()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }

    private fun integrate(source: Terms, targetSide: PositiveDashOperation) =
            SubtractionFromPositiveDashOperationIntegrator.integrate(source, targetSide)
}