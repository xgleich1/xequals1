package com.dg.eqs.core.definition.term.operation.dashoperation

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class DashOperationTest {
    @Test
    fun `Should convert a dash operation to a string`() {
        // GIVEN
        val operandA: Term = mock { on { toString() } doReturn "1" }
        val operandB: Term = mock { on { toString() } doReturn "2" }
        val operandC: Term = mock { on { toString() } doReturn "3" }
        val dashOperation = dashOperation(operandA, operandB, operandC)

        // THEN
        assertThat(dashOperation).hasToString("±[1, 2, 3]")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw an exception when the source is not part of an addition or subtraction`() {
        // GIVEN
        val source = termsOf(PositiveValue(0))
        val target = termsOf(PositiveValue(0))
        val dashOperation = dashOperation(PositiveValue(0), target.first)

        // WHEN
        dashOperation.isAddition(source, target)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw an exception when the target is not part of an addition or subtraction`() {
        // GIVEN
        val source = termsOf(PositiveValue(0))
        val target = termsOf(PositiveValue(0))
        val dashOperation = dashOperation(source.first, PositiveValue(0))

        // WHEN
        dashOperation.isAddition(source, target)
    }

    @Test
    fun `A dash operation is an addition when the target comes after the source and the first target is positive`() {
        // GIVEN
        val source = termsOf(PositiveValue(0), NegativeValue(0))
        val target = termsOf(PositiveValue(0), NegativeValue(0))
        val dashOperation = dashOperation(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertTrue(dashOperation.isAddition(source, target))
    }

    @Test
    fun `A dash operation is an addition when the source comes after the target and the first source is positive`() {
        // GIVEN
        val source = termsOf(PositiveValue(0), NegativeValue(0))
        val target = termsOf(PositiveValue(0), NegativeValue(0))
        val dashOperation = dashOperation(
                target.first,
                target.second,
                source.first,
                source.second)

        // THEN
        assertTrue(dashOperation.isAddition(source, target))
    }

    @Test
    fun `A dash operation is a subtraction when the target comes after the source and the first target is negative`() {
        // GIVEN
        val source = termsOf(NegativeValue(0), PositiveValue(0))
        val target = termsOf(NegativeValue(0), PositiveValue(0))
        val dashOperation = dashOperation(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertFalse(dashOperation.isAddition(source, target))
    }

    @Test
    fun `A dash operation is an subtraction when the source comes after the target and the first source is negative`() {
        // GIVEN
        val source = termsOf(NegativeValue(0), PositiveValue(0))
        val target = termsOf(NegativeValue(0), PositiveValue(0))
        val dashOperation = dashOperation(
                target.first,
                target.second,
                source.first,
                source.second)

        // THEN
        assertFalse(dashOperation.isAddition(source, target))
    }

    @Test
    fun `A dash operation can remove a term from itself or its operands`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val operandC: Term = mock()
        val dashOperation = dashOperation(operandA, operandB, operandC)

        // THEN
        val expectedResult = dashOperation(operandA, operandC)

        assertThat(dashOperation.remove(operandB)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can replace a term in itself or in its operands`() {
        // GIVEN
        val new: Term = mock()

        val operandA: Term = mock()
        val operandB: Term = mock()
        val operandC: Term = mock()
        val dashOperation = dashOperation(operandA, operandB, operandC)

        // THEN
        val expectedResult = dashOperation(operandA, new, operandC)

        assertThat(dashOperation.replace(operandB, new)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can be resolved`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)),
                PositiveDashOperation(PositiveValue(3), PositiveValue(4)),
                PositiveDashOperation(PositiveValue(5), PositiveValue(6)))

        // THEN
        val expectedResult = PositiveValue(21)

        assertThat(dashOperation.resolve()).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can be condensed`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3))

        // THEN
        val expectedResult = PositiveValue(6)

        assertThat(dashOperation.condense()).isEqualTo(expectedResult)
    }

    //<editor-fold desc="Condensing step">
    @Test
    fun `A dash operation can condense source & target when they select a value and a value`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(1),
                PositiveValue(2))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveValue(3)

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can condense source & target when they select zero and anything`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(0),
                PositiveVariable("x"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveVariable("x")

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can condense source & target when they select anything and zero`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveVariable("x"),
                PositiveValue(0))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveVariable("x")

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can condense source & target when they select a term and its opposite`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveVariable("x"),
                NegativeVariable("x"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can condense source & target when they select a dash operation and a dash operation`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3),
                PositiveValue(4))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can condense source & target when they select a dash operation and anything`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can condense source & target when they select anything and a dash operation`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(1),
                PositiveDashOperation(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can condense source & target when they select a division & a equal denominator division`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can condense source & target when they select a division and a division`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveDashOperation(
                        PositiveProduct(
                                PositiveValue(1),
                                PositiveValue(4)),
                        PositiveProduct(
                                PositiveValue(2),
                                PositiveValue(3))),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(4)))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can condense source & target when they select a division through positive one and anything`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)),
                PositiveValue(3))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveDashOperation(
                        PositiveValue(2),
                        PositiveValue(3)),
                PositiveValue(1))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can condense source & target when they select anything and a division through positive one`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(3),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(2)),
                PositiveValue(1))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can condense source & target when they select a division and anything`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveProduct(
                                PositiveValue(2),
                                PositiveValue(3))),
                PositiveValue(2))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can condense source & target when they select anything and a division`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(1),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveDashOperation(
                        PositiveProduct(
                                PositiveValue(1),
                                PositiveValue(3)),
                        PositiveValue(2)),
                PositiveValue(3))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A dash operation can condense source & target when they select a term and its equal`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveVariable("x"),
                PositiveVariable("x"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveProduct(
                PositiveValue(2),
                PositiveVariable("x"))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }
    //</editor-fold>

    //<editor-fold desc="Condensing step prioritisation">
    @Test
    fun `Should prioritize the zero & anything condensing over the anything & dash operation condensing`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(0),
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveValue(1),
                PositiveValue(2))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & zero condensing over the dash operation & anything condensing`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDashOperation(
                        PositiveValue(2),
                        PositiveValue(1)),
                PositiveValue(0))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveValue(2),
                PositiveValue(1))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the zero & anything condensing over the anything & division through positive one condensing`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(0),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(2),
                PositiveValue(1))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & zero condensing over the division through positive one & anything condensing`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)),
                PositiveValue(0))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(2),
                PositiveValue(1))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the zero & anything condensing over the anything & division condensing`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(0),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(1),
                PositiveValue(2))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & zero condensing over the division & anything condensing`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(0))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(1),
                PositiveValue(2))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the term & its opposite condensing over the dash operation & dash operation condensing`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                NegativeDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the term & its opposite condensing over the division & equal denominator division condensing`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                NegativeDivision(
                        PositiveValue(1),
                        PositiveValue(2)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the dash operation & dash operation condensing over the term & equal term condensing`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(1),
                PositiveValue(2))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the dash operation & anything condensing over the anything & division through positive one condensing`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDashOperation(
                        PositiveValue(4),
                        PositiveValue(3)),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveValue(4),
                PositiveValue(3),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & dash operation condensing over the division through positive one & anything condensing`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)),
                PositiveValue(3),
                PositiveValue(4))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the dash operation & anything condensing over the anything & division condensing`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveValue(1),
                PositiveValue(2),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & dash operation condensing over the division & anything condensing`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3),
                PositiveValue(4))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the division & equal denominator division condensing over the term & equal term condensing`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(3)))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(1)),
                PositiveValue(3))

        assertThat(dashOperation.condense(source, target)).isEqualTo(expectedResult)
    }
    //</editor-fold>

    private fun dashOperation(vararg operands: Term) = TestDashOperation(termsOf(*operands))

    private class TestDashOperation(operands: Terms) : DashOperation(operands) {
        override val isNegative = false


        override fun invert() = TODO("not implemented")

        override fun recreate(newOperands: Terms) = TestDashOperation(newOperands)

        override fun applySign() = TODO("not implemented")

        override fun addToFront(newOperands: Terms) = TODO("not implemented")

        override fun addToBack(newOperands: Terms) = TODO("not implemented")

        override fun subtractFromFront(newOperands: Terms) = TODO("not implemented")

        override fun subtractFromBack(newOperands: Terms) = TODO("not implemented")
    }
}