package com.dg.eqs.core.definition.term.operation

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.classes.negativeTerm
import com.dg.eqs.classes.positiveTerm
import com.dg.eqs.classes.term
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.OperationAlteration
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.DivisionThroughZeroException
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class OperationTest {
    @Mock
    private lateinit var alteration: OperationAlteration


    @Test(expected = IllegalStateException::class)
    fun `A operation with zero operands instead of the minimal required two throws an exception`() {
        operation()
    }

    @Test(expected = IllegalStateException::class)
    fun `A operation with one operand instead of the minimal required two throws an exception`() {
        operation(term())
    }

    @Test
    fun `Should provide a property to access the first operand`() {
        // GIVEN
        val operandA = term()
        val operandB = term()
        val operation = operation(
                operandA,
                operandB)

        // THEN
        assertThat(operation.first).isSameAs(operandA)
    }

    @Test
    fun `Should provide a property to access the second operand`() {
        // GIVEN
        val operandA = term()
        val operandB = term()
        val operandC = term()
        val operation = operation(
                operandA,
                operandB,
                operandC)

        // THEN
        assertThat(operation.second).isSameAs(operandB)
    }

    @Test
    fun `Should provide a property to access the third operand`() {
        // GIVEN
        val operandA = term()
        val operandB = term()
        val operandC = term()
        val operandD = term()
        val operation = operation(
                operandA,
                operandB,
                operandC,
                operandD)

        // THEN
        assertThat(operation.third).isSameAs(operandC)
    }

    @Test
    fun `Should provide a property to access the fourth operand`() {
        // GIVEN
        val operandA = term()
        val operandB = term()
        val operandC = term()
        val operandD = term()
        val operandE = term()
        val operation = operation(
                operandA,
                operandB,
                operandC,
                operandD,
                operandE)

        // THEN
        assertThat(operation.fourth).isSameAs(operandD)
    }

    @Test
    fun `Should compare two equal operations`() {
        // GIVEN
        val operandA = term()
        val operandB = term()
        val operationA = operation(operandA, operandB)
        val operationB = operation(operandA, operandB)

        // THEN
        assertThat(operationA).isEqualTo(operationB)
    }

    @Test
    fun `Should convert a operation to a string`() {
        // GIVEN
        val operandA: Term = mock { on { toString() } doReturn "1" }
        val operandB: Term = mock { on { toString() } doReturn "2" }
        val operation = operation(operandA, operandB)

        // THEN
        assertThat(operation).hasToString("[1, 2]")
    }

    @Test
    fun `A operation which cannot be further resolved is solved`() {
        // GIVEN
        val operation = operation(term(), term())

        val resolvingResult = operation(term(), term())

        whenever(alteration.resolve(operation))
                .thenReturn(resolvingResult)

        // THEN
        assertTrue(operation.isSolved())
    }

    @Test
    fun `A operation which can be further resolved is not solved`() {
        // GIVEN
        val operation = operation(term(), term())

        val resolvingResult = term()

        whenever(alteration.resolve(operation))
                .thenReturn(resolvingResult)

        // THEN
        assertFalse(operation.isSolved())
    }

    @Test
    fun `A operation is constant when all of its operands are`() {
        // GIVEN
        val operandA: Term = mock { on { isConstant() } doReturn true }
        val operandB: Term = mock { on { isConstant() } doReturn true }
        val operation = operation(operandA, operandB)

        // THEN
        assertTrue(operation.isConstant())
    }

    @Test
    fun `A operation isn't constant when at least one of its operands isn't`() {
        // GIVEN
        val operandA: Term = mock { on { isConstant() } doReturn true }
        val operandB: Term = mock { on { isConstant() } doReturn false }
        val operation = operation(operandA, operandB)

        // THEN
        assertFalse(operation.isConstant())
    }

    @Test(expected = IllegalArgumentException::class)
    fun `A operation cannot be removed`() {
        // GIVEN
        val operation = operation(term(), term())

        // WHEN
        operation.remove(operation)
    }

    @Test // No exception expected
    fun `A operation uses identity to check if it is removed`() {
        // GIVEN
        val operation = operation(term(), term())
        val equalOperation = operation(term(), term())

        whenever(alteration.remove(operation, equalOperation))
                .thenReturn(term())

        // WHEN
        operation.remove(equalOperation)
    }

    @Test
    fun `A operation can remove a term from itself or its operands`() {
        // GIVEN
        val term = term()

        val operation = operation(term(), term())

        val removingResult = term()

        whenever(alteration.remove(operation, term))
                .thenReturn(removingResult)

        // THEN
        assertThat(operation.remove(term)).isSameAs(removingResult)
    }

    @Test
    fun `A operation can be replaced`() {
        // GIVEN
        val new = term()

        val operation = operation(term(), term())

        // THEN
        assertThat(operation.replace(operation, new)).isSameAs(new)
    }

    @Test
    fun `A operation uses identity to check if it is replaced`() {
        // GIVEN
        val new = term()

        val operation = operation(term(), term())
        val equalOperation = operation(term(), term())

        whenever(alteration.replace(operation, equalOperation, new))
                .thenReturn(term())

        // THEN
        assertThat(operation.replace(equalOperation, new)).isNotSameAs(new)
    }

    @Test
    fun `A operation can replace a term in itself or in its operands`() {
        // GIVEN
        val old = term()
        val new = term()

        val operation = operation(term(), term())

        val replacingResult = term()

        whenever(alteration.replace(operation, old, new))
                .thenReturn(replacingResult)

        // THEN
        assertThat(operation.replace(old, new)).isSameAs(replacingResult)
    }

    @Test
    fun `A operation can copy itself & its operands`() {
        // GIVEN
        val copyOperandA: Term = mock()
        val copyOperandB: Term = mock()
        val operandA: Term = mock { on { copy() } doReturn copyOperandA }
        val operandB: Term = mock { on { copy() } doReturn copyOperandB }
        val operation = operation(operandA, operandB)

        // THEN
        val expectedCopy = operation(copyOperandA, copyOperandB)

        assertThat(operation.copy()).isEqualTo(expectedCopy)
    }

    @Test(expected = DivisionThroughZeroException::class)
    fun `A operation can't be resolved when altering it throws an exception`() {
        // GIVEN
        val operation = operation(term(), term())

        val exception: DivisionThroughZeroException = mock()

        whenever(alteration.resolve(operation))
                .thenThrow(exception)

        // WHEN
        operation.resolve()
    }

    @Test
    fun `A operation can be resolved`() {
        // GIVEN
        val operation = operation(term(), term())

        val resolvingResult = term()

        whenever(alteration.resolve(operation))
                .thenReturn(resolvingResult)

        // THEN
        assertThat(operation.resolve()).isSameAs(resolvingResult)
    }

    @Test
    fun `Should return true when one of a operations operands matches the predicate`() {
        // GIVEN
        val operandA = negativeTerm()
        val operandB = positiveTerm()
        val operation = operation(operandA, operandB)

        // THEN
        assertTrue(operation.any(Term::isPositive))
    }

    @Test
    fun `Should return false when none of a operations operands matches the predicate`() {
        // GIVEN
        val operandA = negativeTerm()
        val operandB = negativeTerm()
        val operation = operation(operandA, operandB)

        // THEN
        assertFalse(operation.any(Term::isPositive))
    }

    @Test
    fun `Should invoke an action on all of a operations operands`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val operation = operation(operandA, operandB)

        val action: (Term) -> Unit = mock()

        // WHEN
        operation.forEach(action)

        // THEN
        verify(action).invoke(operandA)
        verify(action).invoke(operandB)
    }

    @Test
    fun `A operation can destructor into its first four operands`() {
        // GIVEN
        val operandA = term()
        val operandB = term()
        val operandC = term()
        val operandD = term()
        val operation = operation(
                operandA,
                operandB,
                operandC,
                operandD)

        // WHEN
        val (component1, component2, component3, component4) = operation

        // THEN
        assertThat(component1).isSameAs(operandA)
        assertThat(component2).isSameAs(operandB)
        assertThat(component3).isSameAs(operandC)
        assertThat(component4).isSameAs(operandD)
    }

    @Test
    fun `A operation provides an contains operator using identity`() {
        // GIVEN
        val operandA = term()
        val operandB = term()
        val operation = operation(operandA, operandB)

        // THEN
        assertTrue(termsOf(operandA, operandB) in operation)
        assertFalse(termsOf(operandB, term()) in operation)
    }

    @Test
    fun `A operation provides the means to find operands`() {
        // GIVEN
        val operandA = negativeTerm()
        val operandB = positiveTerm()
        val operation = operation(operandA, operandB)

        // THEN
        assertThat(operation.find(Term::isPositive)).isSameAs(operandB)
    }

    @Test
    fun `A operation returns the first of the found operands`() {
        // GIVEN
        val operandA = positiveTerm()
        val operandB = positiveTerm()
        val operation = operation(operandA, operandB)

        // THEN
        assertThat(operation.find(Term::isPositive)).isSameAs(operandA)
    }

    @Test
    fun `A operation returns null when no operand can be found`() {
        // GIVEN
        val operandA = negativeTerm()
        val operandB = negativeTerm()
        val operation = operation(operandA, operandB)

        // THEN
        assertThat(operation.find(Term::isPositive)).isNull()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `A operation cannot provide the index of an operand not part of it`() {
        // GIVEN
        val operation = operation(term(), term())

        // WHEN
        operation.indexOf(term())
    }

    @Test
    fun `A operation provides the index of its operands using identity`() {
        // GIVEN
        val operandA = term()
        val operandB = term()
        val operation = operation(operandA, operandB)

        // THEN
        assertThat(operation.indexOf(operandA)).isEqualTo(0)
        assertThat(operation.indexOf(operandB)).isEqualTo(1)
    }

    @Test(expected = DivisionThroughZeroException::class)
    fun `A operation can't be condensed when altering it throws an exception`() {
        // GIVEN
        val operation = operation(term(), term())

        val exception: DivisionThroughZeroException = mock()

        whenever(alteration.condense(operation))
                .thenThrow(exception)

        // WHEN
        operation.condense()
    }

    @Test
    fun `A operation can be condensed`() {
        // GIVEN
        val operation = operation(term(), term())

        val condensingResult = term()

        whenever(alteration.condense(operation))
                .thenReturn(condensingResult)

        // THEN
        assertThat(operation.condense()).isSameAs(condensingResult)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `A operation can't be partially condensed when the source isn't part of its operands`() {
        // GIVEN
        val operandA = term()
        val operandB = term()
        val operation = operation(operandA, operandB)

        val source = termsOf(term())
        val target = termsOf(operandB)

        // WHEN
        operation.condense(source, target)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `A operation can't be partially condensed when the target isn't part of its operands`() {
        // GIVEN
        val operandA = term()
        val operandB = term()
        val operation = operation(operandA, operandB)

        val source = termsOf(operandA)
        val target = termsOf(term())

        // WHEN
        operation.condense(source, target)
    }

    @Test(expected = DivisionThroughZeroException::class)
    fun `A operation can't be partially condensed when altering it throws an exception`() {
        // GIVEN
        val operandA = term()
        val operandB = term()
        val operation = operation(operandA, operandB)

        val source = termsOf(operandA)
        val target = termsOf(operandB)

        val exception: DivisionThroughZeroException = mock()

        whenever(alteration.condense(operation, source, target))
                .thenThrow(exception)

        // WHEN
        operation.condense(source, target)
    }

    @Test
    fun `A operation can be partially condensed`() {
        // GIVEN
        val operandA = term()
        val operandB = term()
        val operation = operation(operandA, operandB)

        val source = termsOf(operandA)
        val target = termsOf(operandB)

        val condensingResult = term()

        whenever(alteration.condense(operation, source, target))
                .thenReturn(condensingResult)

        // THEN
        assertThat(operation.condense(source, target)).isSameAs(condensingResult)
    }

    private fun operation(vararg operands: Term) =
            TestOperation(alteration, termsOf(*operands))

    private class TestOperation(override val alteration: OperationAlteration, operands: Terms)
        : Operation(operands) {

        override val isNegative = false


        override fun invert() = TODO("not implemented")

        override fun recreate(newOperands: Terms) = TestOperation(alteration, newOperands)

        override fun applySign() = TODO("not implemented")
    }
}