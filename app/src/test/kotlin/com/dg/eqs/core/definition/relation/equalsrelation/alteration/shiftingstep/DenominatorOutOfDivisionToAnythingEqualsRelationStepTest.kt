package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class DenominatorOutOfDivisionToAnythingEqualsRelationStepTest {
    @Test
    fun `A denominator out of division to anything equals relation step is applicable to a matching left to right shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(3))

        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveValue(6),
                        source.single),
                PositiveValue(2))

        // THEN
        assertTrue(isApplicable(equalsRelation, source, equalsRelation.right))
    }

    @Test
    fun `A denominator out of division to anything equals relation step is applicable to a matching right to left shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(3))

        val equalsRelation = EqualsRelation(
                PositiveValue(2),
                PositiveDivision(
                        PositiveValue(6),
                        source.single))
        // THEN
        assertTrue(isApplicable(equalsRelation, source, equalsRelation.left))
    }

    @Test
    fun `Should apply a denominator out of division to anything equals relation step to an applicable left to right shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(3))

        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveValue(6),
                        source.single),
                PositiveValue(2))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(6),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)))

        assertThat(apply(equalsRelation, source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a denominator out of division to anything equals relation step to an applicable right to left shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(3))

        val equalsRelation = EqualsRelation(
                PositiveValue(2),
                PositiveDivision(
                        PositiveValue(6),
                        source.single))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)),
                PositiveValue(6))

        assertThat(apply(equalsRelation, source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    private fun isApplicable(equalsRelation: EqualsRelation, source: Terms, targetSide: Term) =
            DenominatorOutOfDivisionToAnythingEqualsRelationStep.isApplicable(equalsRelation, source, targetSide)

    private fun apply(equalsRelation: EqualsRelation, source: Terms, targetSide: Term) =
            DenominatorOutOfDivisionToAnythingEqualsRelationStep.apply(equalsRelation, source, targetSide)
}