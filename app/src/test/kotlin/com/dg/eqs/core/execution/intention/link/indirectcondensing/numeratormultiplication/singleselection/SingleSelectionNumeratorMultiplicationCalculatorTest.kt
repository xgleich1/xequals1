package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.execution.intention.Intention.Link
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class SingleSelectionNumeratorMultiplicationCalculatorTest {
    private lateinit var calculator: SingleSelectionNumeratorMultiplicationCalculator


    @Before
    fun setUp() {
        calculator = SingleSelectionNumeratorMultiplicationCalculator()
    }

    @Test
    fun `Should calculate a single selection numerator multiplication with the source on the left on a link spanning from an outside product over a division with the target as the numerator`() {
        // GIVEN
        val left = PositiveValue(2)
        val right = PositiveValue(3)

        val source = termsOf(left)
        val target = termsOf(right)
        val origin = PositiveProduct(
                source.single,
                PositiveDivision(
                        target.single,
                        PositiveValue(4)))
        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(6),
                PositiveValue(4))

        assertThat(calculate(origin, source, target, left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate a single selection numerator multiplication with the source on the left on a link spanning from a division with the source as the numerator to an outside product`() {
        // GIVEN
        val left = PositiveValue(3)
        val right = PositiveValue(2)

        val source = termsOf(left)
        val target = termsOf(right)
        val origin = PositiveProduct(
                PositiveDivision(
                        source.single,
                        PositiveValue(4)),
                target.single)

        // THEN
        val expectedResult = PositiveProduct(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(4)),
                PositiveValue(6))

        assertThat(calculate(origin, source, target, left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate a single selection numerator multiplication with the target on the left on a link spanning from a division with the source as the numerator to an outside product`() {
        // GIVEN
        val left = PositiveValue(2)
        val right = PositiveValue(3)

        val source = termsOf(right)
        val target = termsOf(left)
        val origin = PositiveProduct(
                target.single,
                PositiveDivision(
                        source.single,
                        PositiveValue(4)))
        // THEN
        val expectedResult = PositiveProduct(
                PositiveValue(6),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(4)))

        assertThat(calculate(origin, source, target, left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate a single selection numerator multiplication with the target on the left on a link spanning from an outside product over a division with the target as the numerator`() {
        // GIVEN
        val left = PositiveValue(3)
        val right = PositiveValue(2)

        val source = termsOf(right)
        val target = termsOf(left)
        val origin = PositiveProduct(
                PositiveDivision(
                        target.single,
                        PositiveValue(4)),
                source.single)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(6),
                PositiveValue(4))

        assertThat(calculate(origin, source, target, left, right)).isEqualTo(expectedResult)
    }

    private fun calculate(origin: AnyOrigin, source: Terms, target: Terms, left: Term, right: Term) = calculator.calculate(Link(origin, source, target), left, right)
}