package com.dg.eqs.core.definition.term.operation.dotoperation.division

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.DivisionThroughZeroException
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class DivisionTest {
    @Test
    fun `A division is a division through positive one when its denominator is a positive one`() {
        // GIVEN
        val numerator: Term = mock()
        val denominator = PositiveValue(1)
        val division = division(numerator, denominator)

        // THEN
        assertTrue(division.isDivisionThroughPositiveOne)
    }

    @Test
    fun `A division is not a division through positive one when its denominator is a negative one`() {
        // GIVEN
        val numerator: Term = mock()
        val denominator = NegativeValue(1)
        val division = division(numerator, denominator)

        // THEN
        assertFalse(division.isDivisionThroughPositiveOne)
    }

    @Test
    fun `A division is not a division through positive one when its denominator isn't a positive one`() {
        // GIVEN
        val numerator: Term = mock()
        val denominator = PositiveValue(2)
        val division = division(numerator, denominator)

        // THEN
        assertFalse(division.isDivisionThroughPositiveOne)
    }

    @Test
    fun `Should convert a division to a string`() {
        // GIVEN
        val numerator: Term = mock { on { toString() } doReturn "1" }
        val denominator: Term = mock { on { toString() } doReturn "2" }
        val division = division(numerator, denominator)

        // THEN
        assertThat(division).hasToString("/[1, 2]")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `A division cannot be recreated with more than two new operands`() {
        // GIVEN
        val division = division(mock(), mock())

        val newOperands = termsOf(mock(), mock(), mock())

        // WHEN
        division.recreate(newOperands)
    }

    @Test
    fun `Should recreate a division with two new operands`() {
        // GIVEN
        val division = division(mock(), mock())

        val newOperandA: Term = mock()
        val newOperandB: Term = mock()
        val newOperands = termsOf(newOperandA, newOperandB)

        // THEN
        val expectedRecreation = division(newOperandA, newOperandB)

        assertThat(division.recreate(newOperands)).isEqualTo(expectedRecreation)
    }

    @Test
    fun `A division can remove its numerator or from its numerator`() {
        // GIVEN
        val numerator: Term = mock()
        val denominator: Term = mock()
        val division = division(numerator, denominator)

        // THEN
        val expectedResult = division(PositiveValue(1), denominator)

        assertThat(division.remove(numerator)).isEqualTo(expectedResult)
    }

    @Test
    fun `A division can remove its denominator or from its denominator`() {
        // GIVEN
        val numerator: Term = mock()
        val denominator: Term = mock()
        val division = division(numerator, denominator)

        // THEN
        assertThat(division.remove(denominator)).isEqualTo(numerator)
    }

    @Test
    fun `A division can replace a term in itself or in its operands`() {
        // GIVEN
        val new: Term = mock()

        val numerator: Term = mock()
        val denominator: Term = mock()
        val division = division(numerator, denominator)

        // THEN
        val expectedResult = division(numerator, new)

        assertThat(division.replace(denominator, new)).isEqualTo(expectedResult)
    }

    @Test(expected = DivisionThroughZeroException::class)
    fun `A division through zero cannot be resolved`() {
        // GIVEN
        val division = division(
                PositiveDivision(PositiveValue(0), PositiveValue(2)),
                PositiveDivision(PositiveValue(0), PositiveValue(2)))

        // THEN
        division.resolve()
    }

    @Test
    fun `A division can be resolved`() {
        // GIVEN
        val division = division(
                PositiveDivision(PositiveValue(8), PositiveValue(2)),
                PositiveDivision(PositiveValue(4), PositiveValue(2)))

        // THEN
        val expectedResult = PositiveValue(2)

        assertThat(division.resolve()).isEqualTo(expectedResult)
    }

    @Test(expected = DivisionThroughZeroException::class)
    fun `A division through zero cannot be condensed`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                PositiveValue(0))

        // THEN
        division.condense()
    }

    @Test
    fun `A division can be condensed`() {
        // GIVEN
        val division = division(
                PositiveValue(4),
                PositiveValue(2))

        // THEN
        val expectedResult = PositiveValue(2)

        assertThat(division.condense()).isEqualTo(expectedResult)
    }

    @Test(expected = DivisionThroughZeroException::class)
    fun `A division through zero cannot condense source & target`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                PositiveValue(0))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        division.condense(source, target)
    }

    //<editor-fold desc="Condensing step">
    @Test
    fun `A division can condense source & target when they select a value & a value having an gcd greater one`() {
        // GIVEN
        val division = division(
                PositiveValue(4),
                PositiveValue(2))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveValue(2)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A division can condense source & target when they select a zero and anything`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                PositiveVariable("x"))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A division can condense source & target when they select anything and a one`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                PositiveValue(1))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveVariable("x")

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A division can condense source & target when they select a term and its equal`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                PositiveVariable("x"))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveValue(1)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A division can condense source & target when they select a term and its opposite`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                NegativeVariable("x"))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeValue(1)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A division can condense source & target when they select a division and a division`() {
        // GIVEN
        val division = division(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(4)),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A division can condense source & target when they select a division and anything`() {
        // GIVEN
        val division = division(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(1),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A division can condense source & target when they select anything and a division`() {
        // GIVEN
        val division = division(
                PositiveValue(1),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveValue(2))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A division can condense source & target when they select a negative term and a negative term`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                NegativeVariable("y"))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveVariable("x"),
                PositiveVariable("y"))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A division can condense source & target when they select a positive term and a negative term`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                NegativeVariable("y"))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeDivision(
                PositiveVariable("x"),
                PositiveVariable("y"))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A division can condense source & target when they select a negative term and a positive term`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                PositiveVariable("y"))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeDivision(
                PositiveVariable("x"),
                PositiveVariable("y"))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }
    //</editor-fold>

    //<editor-fold desc="Condensing step prioritisation">
    @Test
    fun `Should prioritize the value & value with an gcd greater one condensing over the negative term and negative term condensing`() {
        // GIVEN
        val division = division(
                NegativeValue(4),
                NegativeValue(2))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveValue(2)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the value & value with an gcd greater one condensing over the positive term and negative term condensing`() {
        // GIVEN
        val division = division(
                PositiveValue(4),
                NegativeValue(2))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeValue(2)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the value & value with an gcd greater one condensing over the negative term and positive term condensing`() {
        // GIVEN
        val division = division(
                NegativeValue(4),
                PositiveValue(2))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeValue(2)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the zero & anything condensing over the anything and division condensing`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the zero & anything condensing over the negative term and negative term condensing`() {
        // GIVEN
        val division = division(
                NegativeValue(0),
                NegativeVariable("x"))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the zero & anything condensing over the positive term and negative term condensing`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                NegativeVariable("x"))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the zero & anything condensing over the negative term and positive term condensing`() {
        // GIVEN
        val division = division(
                NegativeValue(0),
                PositiveVariable("x"))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & one condensing over the division and anything condensing`() {
        // GIVEN
        val division = division(
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(2)),
                PositiveValue(1))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(3),
                PositiveValue(2))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & one condensing over the negative term and negative term condensing`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                NegativeValue(1))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveVariable("x")

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & one condensing over the positive term and negative term condensing`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                NegativeValue(1))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeVariable("x")

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & one condensing over the negative term and positive term condensing`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                PositiveValue(1))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeVariable("x")

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the term & equal term condensing over the division and division condensing`() {
        // GIVEN
        val division = division(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveValue(1)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the term & equal term condensing over the negative term and negative term condensing`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                NegativeVariable("x"))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveValue(1)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the term & opposite term condensing over the division and division condensing`() {
        // GIVEN
        val division = division(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                NegativeDivision(
                        PositiveValue(1),
                        PositiveValue(2)))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeValue(1)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the term & opposite term condensing over the positive term and negative term condensing`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                NegativeVariable("x"))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeValue(1)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the term & opposite term condensing over the negative term and positive term condensing`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                PositiveVariable("x"))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeValue(1)

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the division & division condensing over the negative term and negative term condensing`() {
        // GIVEN
        val division = division(
                NegativeDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                NegativeDivision(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(4)),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the division & division condensing over the positive term and negative term condensing`() {
        // GIVEN
        val division = division(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                NegativeDivision(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeDivision(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(4)),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the division & division condensing over the negative term and positive term condensing`() {
        // GIVEN
        val division = division(
                NegativeDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeDivision(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(4)),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the division & anything condensing over the negative term and negative term condensing`() {
        // GIVEN
        val division = division(
                NegativeDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                NegativeValue(3))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(1),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the division & anything condensing over the positive term and negative term condensing`() {
        // GIVEN
        val division = division(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                NegativeValue(3))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeDivision(
                PositiveValue(1),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the division & anything condensing over the negative term and positive term condensing`() {
        // GIVEN
        val division = division(
                NegativeDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeDivision(
                PositiveValue(1),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & division condensing over the negative term and negative term condensing`() {
        // GIVEN
        val division = division(
                NegativeValue(1),
                NegativeDivision(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveValue(2))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & division condensing over the positive term and negative term condensing`() {
        // GIVEN
        val division = division(
                PositiveValue(1),
                NegativeDivision(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeDivision(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveValue(2))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & division condensing over the negative term and positive term condensing`() {
        // GIVEN
        val division = division(
                NegativeValue(1),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(division.first)
        val target = termsOf(division.second)

        // THEN
        val expectedResult = NegativeDivision(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveValue(2))

        assertThat(division.condense(source, target)).isEqualTo(expectedResult)
    }
    //</editor-fold>

    private fun division(numerator: Term, denominator: Term) =
            TestDivision(numerator, denominator)

    private class TestDivision(numerator: Term, denominator: Term)
        : Division(numerator, denominator) {

        override val isNegative = false


        override fun invert() = TODO("not implemented")

        override fun recreate(newNumerator: Term, newDenominator: Term) =
                TestDivision(newNumerator, newDenominator)

        override fun applySign() = TODO("not implemented")
    }
}