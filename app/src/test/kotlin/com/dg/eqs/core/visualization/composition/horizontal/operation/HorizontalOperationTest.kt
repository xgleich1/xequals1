package com.dg.eqs.core.visualization.composition.horizontal.operation

import com.dg.eqs.base.abbreviation.*
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.visualization.Pencil
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class HorizontalOperationTest {
    @Test
    fun `A horizontal operation distributes its text signs between its operands`() {
        // GIVEN
        val operandA: TermDraft = mock()
        val operandB: TermDraft = mock()
        val operandC: TermDraft = mock()
        val signA: AnyTextSign = mock()
        val signB: AnyTextSign = mock()
        val operation = operation(
                operands = draftsOf(operandA, operandB, operandC),
                signs = listOf(signA, signB))

        // THEN
        assertThat(operation.parts).isEqualTo(draftsOf(operandA, signA, operandB, signB, operandC))
    }

    @Test
    fun `A horizontal operations baseline is the baseline of its last operand`() {
        // GIVEN
        val operandA: TermDraft = mock { on { baseline } doReturn 20 }
        val operandB: TermDraft = mock { on { baseline } doReturn 40 }
        val sign: AnyTextSign = mock { on { baseline } doReturn 30 }
        val operation = operation(
                operands = draftsOf(operandA, operandB),
                signs = listOf(sign))

        // THEN
        assertThat(operation.baseline).isEqualTo(40)
    }

    @Test
    fun `A horizontal operation connects its operands with each other to allow choosing them in succession`() {
        // GIVEN
        val operandA: TermDraft = mock()
        val operandB: TermDraft = mock()
        val operandC: TermDraft = mock()
        operation(
                operands = draftsOf(operandA, operandB, operandC),
                signs = listOf(mock(), mock()))

        // THEN
        verify(operandA).choosableSuccessor = operandB
        verify(operandB).choosableSuccessor = operandC

        verify(operandC).choosablePredecessor = operandB
        verify(operandB).choosablePredecessor = operandA
    }

    private fun operation(origin: Operation = mock(), operands: TermDrafts, signs: AnyTextSigns) =
            TestAnyHorizontalOperation(origin, operands, signs)

    private class TestAnyHorizontalOperation(origin: Operation, operands: TermDrafts, signs: AnyTextSigns)
        : HorizontalOperation<Operation>(origin, operands, signs) {

        override fun specifyPaddingLeft(pencil: Pencil) = TODO("not implemented")

        override fun specifyPaddingRight(pencil: Pencil) = TODO("not implemented")

        override fun calculatePartWidth(part: AnyDraft) = TODO("not implemented")

        override fun calculatePartHeight(part: AnyDraft) = TODO("not implemented")
    }
}