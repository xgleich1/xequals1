package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class SingleSelectionReduceCalculatorTest {
    private lateinit var calculator: SingleSelectionReduceCalculator


    @Before
    fun setUp() {
        calculator = SingleSelectionReduceCalculator()
    }

    //<editor-fold desc="Single selection reduce spanning over a division with a product as the denominator">
    @Test
    fun `Should calculate a single selection reduce spanning over a division with a product as the denominator and the division of the selected terms resulting in a positive division`() {
        // GIVEN
        val numerator = PositiveValue(2)
        val denominator = PositiveValue(4)
        val origin = PositiveDivision(
                numerator,
                PositiveProduct(
                        PositiveValue(3),
                        denominator))
        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(1),
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(2)))

        assertThat(calculate(origin, numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate a single selection reduce spanning over a division with a product as the denominator and the division of the selected terms resulting in a negative division`() {
        // GIVEN
        val numerator = PositiveValue(2)
        val denominator = NegativeValue(4)
        val origin = PositiveDivision(
                numerator,
                PositiveProduct(
                        PositiveValue(3),
                        denominator))
        // THEN
        val expectedResult = PositiveDivision(
                NegativeValue(1),
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(2)))

        assertThat(calculate(origin, numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate a single selection reduce spanning over a division with a product as the denominator and the division of the selected terms resulting in a positive one`() {
        // GIVEN
        val numerator = PositiveValue(2)
        val denominator = PositiveValue(2)
        val origin = PositiveDivision(
                numerator,
                PositiveProduct(
                        PositiveValue(3),
                        denominator))
        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(1),
                PositiveValue(3))

        assertThat(calculate(origin, numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate a single selection reduce spanning over a division with a product as the denominator and the division of the selected terms resulting in a term other than a division or a positive one`() {
        // GIVEN
        val numerator = PositiveValue(4)
        val denominator = PositiveValue(2)
        val origin = PositiveDivision(
                numerator,
                PositiveProduct(
                        PositiveValue(3),
                        denominator))
        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(2),
                PositiveValue(3))

        assertThat(calculate(origin, numerator, denominator)).isEqualTo(expectedResult)
    }
    //</editor-fold>

    //<editor-fold desc="Single selection reduce spanning over a division with a product as the numerator">
    @Test
    fun `Should calculate a single selection reduce spanning over a division with a product as the numerator and the division of the selected terms resulting in a positive division`() {
        // GIVEN
        val numerator = PositiveValue(2)
        val denominator = PositiveValue(4)
        val origin = PositiveDivision(
                PositiveProduct(
                        numerator,
                        PositiveValue(3)),
                denominator)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveValue(2))

        assertThat(calculate(origin, numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate a single selection reduce spanning over a division with a product as the numerator and the division of the selected terms resulting in a negative division`() {
        // GIVEN
        val numerator = PositiveValue(2)
        val denominator = NegativeValue(4)
        val origin = PositiveDivision(
                PositiveProduct(
                        numerator,
                        PositiveValue(3)),
                denominator)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveProduct(
                        NegativeValue(1),
                        PositiveValue(3)),
                PositiveValue(2))

        assertThat(calculate(origin, numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate a single selection reduce spanning over a division with a product as the numerator and the division of the selected terms resulting in a positive one`() {
        // GIVEN
        val numerator = PositiveValue(2)
        val denominator = PositiveValue(2)
        val origin = PositiveDivision(
                PositiveProduct(
                        numerator,
                        PositiveValue(3)),
                denominator)

        // THEN
        val expectedResult = PositiveValue(3)

        assertThat(calculate(origin, numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate a single selection reduce spanning over a division with a product as the numerator and the division of the selected terms resulting in a term other than a division or a positive one`() {
        // GIVEN
        val numerator = PositiveValue(4)
        val denominator = PositiveValue(2)
        val origin = PositiveDivision(
                PositiveProduct(
                        numerator,
                        PositiveValue(3)),
                denominator)

        // THEN
        val expectedResult = PositiveProduct(
                PositiveValue(2),
                PositiveValue(3))

        assertThat(calculate(origin, numerator, denominator)).isEqualTo(expectedResult)
    }
    //</editor-fold>

    //<editor-fold desc="Single selection reduce spanning over a division with products as the numerator & denominator">
    @Test
    fun `Should calculate a single selection reduce spanning over a division with products as the numerator & denominator and the division of the selected terms resulting in a positive division`() {
        // GIVEN
        val numerator = PositiveValue(2)
        val denominator = PositiveValue(4)
        val origin = PositiveDivision(
                PositiveProduct(
                        PositiveValue(3),
                        numerator),
                PositiveProduct(
                        PositiveValue(5),
                        denominator))
        // THEN
        val expectedResult = PositiveDivision(
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(1)),
                PositiveProduct(
                        PositiveValue(5),
                        PositiveValue(2)))

        assertThat(calculate(origin, numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate a single selection reduce spanning over a division with products as the numerator & denominator and the division of the selected terms resulting in a negative division`() {
        // GIVEN
        val numerator = PositiveValue(2)
        val denominator = NegativeValue(4)
        val origin = PositiveDivision(
                PositiveProduct(
                        PositiveValue(3),
                        numerator),
                PositiveProduct(
                        PositiveValue(5),
                        denominator))
        // THEN
        val expectedResult = PositiveDivision(
                PositiveProduct(
                        PositiveValue(3),
                        NegativeValue(1)),
                PositiveProduct(
                        PositiveValue(5),
                        PositiveValue(2)))

        assertThat(calculate(origin, numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate a single selection reduce spanning over a division with products as the numerator & denominator and the division of the selected terms resulting in a positive one`() {
        // GIVEN
        val numerator = PositiveValue(2)
        val denominator = PositiveValue(2)
        val origin = PositiveDivision(
                PositiveProduct(
                        PositiveValue(3),
                        numerator),
                PositiveProduct(
                        PositiveValue(5),
                        denominator))
        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(3),
                PositiveValue(5))

        assertThat(calculate(origin, numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should calculate a single selection reduce spanning over a division with products as the numerator & denominator and the division of the selected terms resulting in a term other than a division or a positive one`() {
        // GIVEN
        val numerator = PositiveValue(4)
        val denominator = PositiveValue(2)
        val origin = PositiveDivision(
                PositiveProduct(
                        PositiveValue(3),
                        numerator),
                PositiveProduct(
                        PositiveValue(5),
                        denominator))
        // THEN
        val expectedResult = PositiveDivision(
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(2)),
                PositiveValue(5))

        assertThat(calculate(origin, numerator, denominator)).isEqualTo(expectedResult)
    }
    //</editor-fold>

    private fun calculate(origin: AnyOrigin, numerator: Term, denominator: Term) = calculator.calculate(origin, numerator, denominator)
}