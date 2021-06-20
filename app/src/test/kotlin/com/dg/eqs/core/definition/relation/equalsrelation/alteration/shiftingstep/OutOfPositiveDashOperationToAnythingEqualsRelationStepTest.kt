package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class OutOfPositiveDashOperationToAnythingEqualsRelationStepTest {
    @Test
    fun `A out of positive dash operation to anything equals relation step is applicable to a matching left to right shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))

        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        source.first,
                        PositiveValue(2)),
                PositiveValue(3))

        // THEN
        assertTrue(isApplicable(equalsRelation, source, equalsRelation.right))
    }

    @Test
    fun `A out of positive dash operation to anything equals relation step is applicable to a matching right to left shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))

        val equalsRelation = EqualsRelation(
                PositiveValue(3),
                PositiveDashOperation(
                        source.first,
                        PositiveValue(2)))
        // THEN
        assertTrue(isApplicable(equalsRelation, source, equalsRelation.left))
    }

    @Test
    fun `Should apply a out of positive dash operation to anything equals relation step to an applicable left to right shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))

        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        source.first,
                        PositiveValue(2)),
                PositiveValue(3))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(2),
                PositiveDashOperation(
                        PositiveValue(3),
                        NegativeValue(1)))

        assertThat(apply(equalsRelation, source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a out of positive dash operation to anything equals relation step to an applicable right to left shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))

        val equalsRelation = EqualsRelation(
                PositiveValue(3),
                PositiveDashOperation(
                        source.first,
                        PositiveValue(2)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveDashOperation(
                        PositiveValue(3),
                        NegativeValue(1)),
                PositiveValue(2))

        assertThat(apply(equalsRelation, source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    private fun isApplicable(equalsRelation: EqualsRelation, source: Terms, targetSide: Term) =
            OutOfPositiveDashOperationToAnythingEqualsRelationStep.isApplicable(equalsRelation, source, targetSide)

    private fun apply(equalsRelation: EqualsRelation, source: Terms, targetSide: Term) =
            OutOfPositiveDashOperationToAnythingEqualsRelationStep.apply(equalsRelation, source, targetSide)
}