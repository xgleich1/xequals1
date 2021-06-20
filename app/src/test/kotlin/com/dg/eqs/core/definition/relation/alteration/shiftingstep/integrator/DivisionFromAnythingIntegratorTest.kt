package com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionFromAnythingIntegratorTest {
    @Test
    fun `Should integrate a single source by dividing it from the target side`() {
        // GIVEN
        val singleSource = anything()
        val source = termsOf(singleSource)

        val targetSide = anything()

        // WHEN
        val newTargetSide = integrate(source, targetSide)

        // THEN
        val expectedNewTargetSide = PositiveDivision(
                targetSide,
                singleSource)

        assertThat(newTargetSide).isEqualTo(expectedNewTargetSide)
    }

    @Test
    fun `Should integrate multiple sources by dividing them from the target side`() {
        // GIVEN
        val firstSource = anything()
        val secondSource = anything()
        val source = termsOf(firstSource, secondSource)

        val targetSide = anything()

        // WHEN
        val newTargetSide = integrate(source, targetSide)

        // THEN
        val expectedNewTargetSide = PositiveDivision(
                targetSide,
                PositiveProduct(
                        firstSource,
                        secondSource))

        assertThat(newTargetSide).isEqualTo(expectedNewTargetSide)
    }

    private fun anything(): Term = mock()

    private fun integrate(source: Terms, targetSide: Term) =
            DivisionFromAnythingIntegrator.integrate(source, targetSide)
}