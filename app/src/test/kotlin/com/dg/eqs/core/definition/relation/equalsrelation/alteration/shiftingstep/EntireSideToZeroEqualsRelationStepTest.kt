package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class EntireSideToZeroEqualsRelationStepTest {
    @Test
    fun `An entire side to zero equals relation step is applicable to a matching left to right shifting`() {
        // GIVEN
        val source = termsOf(NegativeVariable("x"))

        val equalsRelation = EqualsRelation(
                source.single,
                PositiveValue(0))

        // THEN
        assertTrue(isApplicable(equalsRelation, source, equalsRelation.right))
    }

    @Test
    fun `An entire side to zero equals relation step is applicable to a matching right to left shifting`() {
        // GIVEN
        val source = termsOf(NegativeVariable("x"))

        val equalsRelation = EqualsRelation(
                PositiveValue(0),
                source.single)

        // THEN
        assertTrue(isApplicable(equalsRelation, source, equalsRelation.left))
    }

    @Test
    fun `Should apply an entire side to zero equals relation step to an applicable left to right shifting`() {
        // GIVEN
        val source = termsOf(NegativeVariable("x"))

        val equalsRelation = EqualsRelation(
                source.single,
                PositiveValue(0))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(0),
                PositiveVariable("x"))

        assertThat(apply(equalsRelation, source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply an entire side to zero equals relation step to an applicable right to left shifting`() {
        // GIVEN
        val source = termsOf(NegativeVariable("x"))

        val equalsRelation = EqualsRelation(
                PositiveValue(0),
                source.single)

        // THEN
        val expectedResult = EqualsRelation(
                PositiveVariable("x"),
                PositiveValue(0))

        assertThat(apply(equalsRelation, source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    private fun isApplicable(equalsRelation: EqualsRelation, source: Terms, targetSide: Term) =
            EntireSideToZeroEqualsRelationStep.isApplicable(equalsRelation, source, targetSide)

    private fun apply(equalsRelation: EqualsRelation, source: Terms, targetSide: Term) =
            EntireSideToZeroEqualsRelationStep.apply(equalsRelation, source, targetSide)
}