package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class NumeratorOutOfDivisionToAnythingEqualsRelationStepTest {
    @Test
    fun `A numerator out of division to anything equals relation step is applicable to a matching left to right shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(4))

        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        source.single,
                        PositiveValue(2)),
                PositiveValue(2))

        // THEN
        assertTrue(isApplicable(equalsRelation, source, equalsRelation.right))
    }

    @Test
    fun `A numerator out of division to anything equals relation step is applicable to a matching right to left shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(4))

        val equalsRelation = EqualsRelation(
                PositiveValue(2),
                PositiveDivision(
                        source.single,
                        PositiveValue(2)))
        // THEN
        assertTrue(isApplicable(equalsRelation, source, equalsRelation.left))
    }

    @Test
    fun `Should apply a numerator out of division to anything equals relation step to an applicable left to right shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(4))

        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        source.single,
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
    fun `Should apply a numerator out of division to anything equals relation step to an applicable right to left shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(4))

        val equalsRelation = EqualsRelation(
                PositiveValue(2),
                PositiveDivision(
                        source.single,
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
            NumeratorOutOfDivisionToAnythingEqualsRelationStep.isApplicable(equalsRelation, source, targetSide)

    private fun apply(equalsRelation: EqualsRelation, source: Terms, targetSide: Term) =
            NumeratorOutOfDivisionToAnythingEqualsRelationStep.apply(equalsRelation, source, targetSide)
}