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


class OutOfPositiveDashOperationToPositiveDashOperationEqualsRelationStepTest {
    @Test
    fun `A out of positive dash operation to positive dash operation equals relation step is applicable to a matching left to right shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(2))

        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        source.first,
                        NegativeValue(1)),
                PositiveDashOperation(
                        PositiveValue(0),
                        PositiveValue(1)))
        // THEN
        assertTrue(isApplicable(equalsRelation, source, equalsRelation.right))
    }

    @Test
    fun `A out of positive dash operation to positive dash operation equals relation step is applicable to a matching right to left shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(2))

        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        PositiveValue(0),
                        PositiveValue(1)),
                PositiveDashOperation(
                        source.first,
                        NegativeValue(1)))
        // THEN
        assertTrue(isApplicable(equalsRelation, source, equalsRelation.left))
    }

    @Test
    fun `Should apply a out of positive dash operation to positive dash operation equals relation step to an applicable left to right shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(2))

        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        source.first,
                        NegativeValue(1)),
                PositiveDashOperation(
                        PositiveValue(0),
                        PositiveValue(1)))
        // THEN
        val expectedResult = EqualsRelation(
                NegativeValue(1),
                PositiveDashOperation(
                        PositiveValue(0),
                        PositiveValue(1),
                        NegativeValue(2)))

        assertThat(apply(equalsRelation, source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a out of positive dash operation to positive dash operation equals relation step to an applicable right to left shifting`() {
        // GIVEN
        val source = termsOf(PositiveValue(2))

        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        PositiveValue(0),
                        PositiveValue(1)),
                PositiveDashOperation(
                        source.first,
                        NegativeValue(1)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveDashOperation(
                        PositiveValue(0),
                        PositiveValue(1),
                        NegativeValue(2)),
                NegativeValue(1))

        assertThat(apply(equalsRelation, source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    private fun isApplicable(equalsRelation: EqualsRelation, source: Terms, targetSide: Term) =
            OutOfPositiveDashOperationToPositiveDashOperationEqualsRelationStep.isApplicable(equalsRelation, source, targetSide)

    private fun apply(equalsRelation: EqualsRelation, source: Terms, targetSide: Term) =
            OutOfPositiveDashOperationToPositiveDashOperationEqualsRelationStep.apply(equalsRelation, source, targetSide)
}