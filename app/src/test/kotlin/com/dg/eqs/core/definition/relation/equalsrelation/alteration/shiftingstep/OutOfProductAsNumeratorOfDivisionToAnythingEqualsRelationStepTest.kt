package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class OutOfProductAsNumeratorOfDivisionToAnythingEqualsRelationStepTest {
    @Test
    fun `A out of product as numerator of division to anything equals relation step is applicable to a matching left to right shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(4))

        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveProduct(
                                source.first,
                                PositiveValue(1)),
                        PositiveValue(2)),
                PositiveValue(2))

        // THEN
        assertTrue(isApplicable(equalsRelation, source, equalsRelation.right))
    }

    @Test
    fun `A out of product as numerator of division to anything equals relation step is applicable to a matching right to left shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(4))

        val equalsRelation = EqualsRelation(
                PositiveValue(2),
                PositiveDivision(
                        PositiveProduct(
                                source.first,
                                PositiveValue(1)),
                        PositiveValue(2)))
        // THEN
        assertTrue(isApplicable(equalsRelation, source, equalsRelation.left))
    }

    @Test
    fun `Should apply a out of product as numerator of division to anything equals relation step to an applicable left to right shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(4))

        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveProduct(
                                source.first,
                                PositiveValue(1)),
                        PositiveValue(2)),
                PositiveValue(2))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(4)))

        assertThat(apply(equalsRelation, source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a out of product as numerator of division to anything equals relation step to an applicable right to left shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(4))

        val equalsRelation = EqualsRelation(
                PositiveValue(2),
                PositiveDivision(
                        PositiveProduct(
                                source.first,
                                PositiveValue(1)),
                        PositiveValue(2)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(4)),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)))

        assertThat(apply(equalsRelation, source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    private fun isApplicable(equalsRelation: EqualsRelation, source: Terms, targetSide: Term) =
            OutOfProductAsNumeratorOfDivisionToAnythingEqualsRelationStep.isApplicable(equalsRelation, source, targetSide)

    private fun apply(equalsRelation: EqualsRelation, source: Terms, targetSide: Term) =
            OutOfProductAsNumeratorOfDivisionToAnythingEqualsRelationStep.apply(equalsRelation, source, targetSide)
}