package com.dg.eqs.core.execution.intention.link.directcondensing

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.execution.intention.Intention.Link
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class DirectCondensingCalculatorTest {
    private lateinit var calculator: DirectCondensingCalculator


    @Before
    fun setUp() {
        calculator = DirectCondensingCalculator()
    }

    @Test
    fun `Should calculate the result of source and targets addition`() {
        // GIVEN
        val origin = PositiveDashOperation(PositiveValue(1), PositiveValue(2))

        val source = termsOf(origin.first)
        val target = termsOf(origin.second)

        // THEN
        val expectedResult = PositiveValue(3)

        assertThat(calculate(origin, source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate the result of source and targets subtraction`() {
        // GIVEN
        val origin = PositiveDashOperation(PositiveValue(1), NegativeValue(2))

        val source = termsOf(origin.first)
        val target = termsOf(origin.second)

        // THEN
        val expectedResult = NegativeValue(1)

        assertThat(calculate(origin, source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate the result of source and targets division`() {
        // GIVEN
        val origin = PositiveDivision(PositiveValue(2), PositiveValue(2))

        val source = termsOf(origin.first)
        val target = termsOf(origin.second)

        // THEN
        val expectedResult = PositiveValue(1)

        assertThat(calculate(origin, source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate the result of source and targets multiplication`() {
        // GIVEN
        val origin = PositiveProduct(PositiveValue(1), PositiveValue(2))

        val source = termsOf(origin.first)
        val target = termsOf(origin.second)

        // THEN
        val expectedResult = PositiveValue(2)

        assertThat(calculate(origin, source, target)).isEqualTo(expectedResult)
    }

    private fun calculate(origin: AnyOrigin, source: Terms, target: Terms) =
            calculator.calculate(Link(origin, source, target))
}