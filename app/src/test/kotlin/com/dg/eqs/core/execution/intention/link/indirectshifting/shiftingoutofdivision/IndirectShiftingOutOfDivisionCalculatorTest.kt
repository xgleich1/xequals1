package com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.execution.intention.Intention.Link
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class IndirectShiftingOutOfDivisionCalculatorTest {
    private lateinit var calculator: IndirectShiftingOutOfDivisionCalculator


    @Before
    fun setUp() {
        calculator = IndirectShiftingOutOfDivisionCalculator()
    }

    @Test
    fun `Should calculate a indirect shifting division out of a division taking place inside an equation`() {
        // GIVEN
        val source = termsOf(PositiveValue(2))
        val target = termsOf(PositiveValue(1))
        val origin = EqualsRelation(
                PositiveDivision(
                        PositiveProduct(
                                source.first,
                                PositiveValue(4)),
                        PositiveValue(8)),
                target.single)

        // THEN
        val expectedResult = EqualsRelation(
                PositiveDivision(
                        PositiveValue(4),
                        PositiveValue(8)),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)))

        assertThat(calculate(origin, source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate a indirect shifting multiplication out of a division taking place inside an equation`() {
        // GIVEN
        val source = termsOf(PositiveValue(2))
        val target = termsOf(PositiveValue(1))
        val origin = EqualsRelation(
                PositiveDivision(
                        PositiveValue(8),
                        PositiveProduct(
                                PositiveValue(4),
                                source.first)),
                target.single)

        // THEN
        val expectedResult = EqualsRelation(
                PositiveDivision(
                        PositiveValue(8),
                        PositiveValue(4)),
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)))

        assertThat(calculate(origin, source, target)).isEqualTo(expectedResult)
    }

    private fun calculate(origin: AnyOrigin, source: Terms, target: Terms) =
            calculator.calculate(Link(origin, source, target))
}