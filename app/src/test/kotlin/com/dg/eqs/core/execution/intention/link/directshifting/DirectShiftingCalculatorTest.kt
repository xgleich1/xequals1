package com.dg.eqs.core.execution.intention.link.directshifting

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.execution.intention.Intention.Link
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class DirectShiftingCalculatorTest {
    private lateinit var calculator: DirectShiftingCalculator


    @Before
    fun setUp() {
        calculator = DirectShiftingCalculator()
    }

    @Test
    fun `Should calculate a shifting taking place inside an equation`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))
        val target = termsOf(PositiveValue(1))
        val origin = EqualsRelation(source.single, target.single)

        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(0),
                PositiveDashOperation(
                        PositiveValue(1),
                        NegativeValue(1)))

        assertThat(calculate(origin, source, target)).isEqualTo(expectedResult)
    }

    private fun calculate(origin: AnyOrigin, source: Terms, target: Terms) =
            calculator.calculate(Link(origin, source, target))
}