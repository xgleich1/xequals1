package com.dg.eqs.core.definition.term.operation.dotoperation.product

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ProductTest {
    @Test
    fun `Should convert a product to a string`() {
        // GIVEN
        val operandA: Term = mock { on { toString() } doReturn "1" }
        val operandB: Term = mock { on { toString() } doReturn "2" }
        val operandC: Term = mock { on { toString() } doReturn "3" }
        val product = product(operandA, operandB, operandC)

        // THEN
        assertThat(product).hasToString("*[1, 2, 3]")
    }

    @Test
    fun `A product can remove a term from itself or its operands`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val operandC: Term = mock()
        val product = product(operandA, operandB, operandC)

        // THEN
        val expectedResult = product(operandA, operandC)

        assertThat(product.remove(operandB)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can replace a term in itself or in its operands`() {
        // GIVEN
        val new: Term = mock()

        val operandA: Term = mock()
        val operandB: Term = mock()
        val operandC: Term = mock()
        val product = product(operandA, operandB, operandC)

        // THEN
        val expectedResult = product(operandA, new, operandC)

        assertThat(product.replace(operandB, new)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can be resolved`() {
        // GIVEN
        val product = product(
                PositiveProduct(PositiveValue(1), PositiveValue(2)),
                PositiveProduct(PositiveValue(3), PositiveValue(4)),
                PositiveProduct(PositiveValue(5), PositiveValue(6)))

        // THEN
        val expectedResult = PositiveValue(720)

        assertThat(product.resolve()).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can be condensed`() {
        // GIVEN
        val product = product(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3))

        // THEN
        val expectedResult = PositiveValue(6)

        assertThat(product.condense()).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply new operands to the front of a product`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val product = product(operandA, operandB)

        val newOperandA: Term = mock()
        val newOperandB: Term = mock()
        val newOperands = termsOf(newOperandA, newOperandB)

        // THEN
        val expectedResult = product(
                newOperandA,
                newOperandB,
                operandA,
                operandB)

        assertThat(product.multiplyToFront(newOperands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply new operands to the back of a product`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val product = product(operandA, operandB)

        val newOperandA: Term = mock()
        val newOperandB: Term = mock()
        val newOperands = termsOf(newOperandA, newOperandB)

        // THEN
        val expectedResult = product(
                operandA,
                operandB,
                newOperandA,
                newOperandB)

        assertThat(product.multiplyToBack(newOperands)).isEqualTo(expectedResult)
    }

    //<editor-fold desc="Condensing step">
    @Test
    fun `A product can condense source & target when they select a value and a value`() {
        // GIVEN
        val product = product(
                PositiveValue(2),
                PositiveValue(3))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveValue(6)

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can condense source & target when they select zero and anything`() {
        // GIVEN
        val product = product(
                PositiveValue(0),
                PositiveVariable("x"))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can condense source & target when they select anything and zero`() {
        // GIVEN
        val product = product(
                PositiveVariable("x"),
                PositiveValue(0))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can condense source & target when they select one and anything`() {
        // GIVEN
        val product = product(
                PositiveValue(1),
                PositiveVariable("x"))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveVariable("x")

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can condense source & target when they select anything and one`() {
        // GIVEN
        val product = product(
                PositiveVariable("x"),
                PositiveValue(1))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveVariable("x")

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can condense source & target when they select a product and a product`() {
        // GIVEN
        val product = product(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveProduct(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3),
                PositiveValue(4))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can condense source & target when they select a product and anything`() {
        // GIVEN
        val product = product(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveProduct(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can condense source & target when they select anything and a product`() {
        // GIVEN
        val product = product(
                PositiveValue(2),
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveProduct(
                PositiveValue(2),
                PositiveValue(3),
                PositiveValue(4))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can condense source & target when they select a division and a division`() {
        // GIVEN
        val product = product(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(4)))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can condense source & target when they select a division and anything`() {
        // GIVEN
        val product = product(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveValue(2))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can condense source & target when they select anything and a division `() {
        // GIVEN
        val product = product(
                PositiveValue(2),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)),
                PositiveValue(4))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can condense source & target when they select a dash operation and a dash operation`() {
        // GIVEN
        val product = product(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveProduct(
                        PositiveDashOperation(
                                PositiveValue(1),
                                PositiveValue(2)),
                        PositiveValue(3)),
                PositiveProduct(
                        PositiveDashOperation(
                                PositiveValue(1),
                                PositiveValue(2)),
                        PositiveValue(4)))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can condense source & target when they select a dash operation and anything`() {
        // GIVEN
        val product = product(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `A product can condense source & target when they select anything and a dash operation`() {
        // GIVEN
        val product = product(
                PositiveValue(2),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(4)))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }
    //</editor-fold>

    //<editor-fold desc="Condensing step prioritisation">
    @Test
    fun `Should prioritize the zero & anything condensing over the anything & one condensing`() {
        // GIVEN
        val product = product(
                PositiveValue(0),
                PositiveValue(0),
                PositiveValue(1),
                PositiveValue(1))

        val source = termsOf(product.first, product.second)
        val target = termsOf(product.third, product.fourth)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & zero condensing over the one & anything condensing`() {
        // GIVEN
        val product = product(
                PositiveValue(1),
                PositiveValue(1),
                PositiveValue(0),
                PositiveValue(0))

        val source = termsOf(product.first, product.second)
        val target = termsOf(product.third, product.fourth)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the zero & anything condensing over the anything & product condensing`() {
        // GIVEN
        val product = product(
                PositiveValue(0),
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & zero condensing over the product & anything condensing`() {
        // GIVEN
        val product = product(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(0))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the zero & anything condensing over the anything & division condensing`() {
        // GIVEN
        val product = product(
                PositiveValue(0),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & zero condensing over the division & anything condensing`() {
        // GIVEN
        val product = product(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(0))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the zero & anything condensing over the anything & dash operation condensing`() {
        // GIVEN
        val product = product(
                PositiveValue(0),
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & zero condensing over the dash operation & anything condensing`() {
        // GIVEN
        val product = product(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(0))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveValue(0)

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the one & anything condensing over the anything & product condensing`() {
        // GIVEN
        val product = product(
                PositiveValue(1),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveProduct(
                PositiveValue(2),
                PositiveValue(3))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & one condensing over the product & anything condensing`() {
        // GIVEN
        val product = product(
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(2)),
                PositiveValue(1))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveProduct(
                PositiveValue(3),
                PositiveValue(2))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the one & anything condensing over the anything & division condensing`() {
        // GIVEN
        val product = product(
                PositiveValue(1),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(2),
                PositiveValue(3))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & one condensing over the division & anything condensing`() {
        // GIVEN
        val product = product(
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(2)),
                PositiveValue(1))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveValue(3),
                PositiveValue(2))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the one & anything condensing over the anything & dash operation condensing`() {
        // GIVEN
        val product = product(
                PositiveValue(1),
                PositiveDashOperation(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveValue(2),
                PositiveValue(3))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & one condensing over the dash operation & anything condensing`() {
        // GIVEN
        val product = product(
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(2)),
                PositiveValue(1))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveDashOperation(
                PositiveValue(3),
                PositiveValue(2))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the product & anything condensing over the anything & division condensing`() {
        // GIVEN
        val product = product(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveProduct(
                PositiveValue(1),
                PositiveValue(2),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & product condensing over the division & anything condensing`() {
        // GIVEN
        val product = product(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveProduct(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3),
                PositiveValue(4))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the product & anything condensing over the anything & dash operation condensing`() {
        // GIVEN
        val product = product(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveProduct(
                PositiveValue(1),
                PositiveValue(2),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & product condensing over the dash operation & anything condensing`() {
        // GIVEN
        val product = product(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveProduct(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3),
                PositiveValue(4))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the division & anything condensing over the anything & dash operation condensing`() {
        // GIVEN
        val product = product(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveDashOperation(
                                PositiveValue(3),
                                PositiveValue(4))),
                PositiveValue(2))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should prioritize the anything & division condensing over the dash operation & anything condensing`() {
        // GIVEN
        val product = product(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(product.first)
        val target = termsOf(product.second)

        // THEN
        val expectedResult = PositiveDivision(
                PositiveProduct(
                        PositiveDashOperation(
                                PositiveValue(1),
                                PositiveValue(2)),
                        PositiveValue(3)),
                PositiveValue(4))

        assertThat(product.condense(source, target)).isEqualTo(expectedResult)
    }
    //</editor-fold>

    private fun product(vararg operands: Term) = TestProduct(termsOf(*operands))

    private class TestProduct(operands: Terms) : Product(operands) {
        override val isNegative = false


        override fun invert() = TODO("not implemented")

        override fun recreate(newOperands: Terms) = TestProduct(newOperands)

        override fun applySign() = TODO("not implemented")
    }
}