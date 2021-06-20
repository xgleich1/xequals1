package com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.classes.zero
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class SubtractionFromZeroIntegratorTest {
    @Test
    fun `Should integrate a single source by subtracting it from the zero`() {
        // GIVEN
        val (singleSource, oppositeSingleSource) = anythingAndOpposite()
        val source = termsOf(singleSource)

        val targetSide = zero()

        // WHEN
        val newTargetSide = integrate(source, targetSide)

        // THEN
        assertThat(newTargetSide).isEqualTo(oppositeSingleSource)
    }

    @Test
    fun `Should integrate multiple sources by subtracting them from the zero`() {
        // GIVEN
        val (firstSource, oppositeFirstSource) = anythingAndOpposite()
        val (secondSource, oppositeSecondSource) = anythingAndOpposite()
        val source = termsOf(firstSource, secondSource)

        val targetSide = zero()

        // WHEN
        val newTargetSide = integrate(source, targetSide)

        // THEN
        val expectedNewTargetSide = PositiveDashOperation(
                oppositeFirstSource,
                oppositeSecondSource)

        assertThat(newTargetSide).isEqualTo(expectedNewTargetSide)
    }

    private fun anythingAndOpposite(): Pair<Term, Term> {
        val anything: Term = mock()
        val opposite: Term = mock()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }

    private fun integrate(source: Terms, targetSide: Term) =
            SubtractionFromZeroIntegrator.integrate(source, targetSide)
}