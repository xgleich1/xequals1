package com.dg.eqs.core.visualization.composition.vertical.operation

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.AnyLineSign
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.visualization.Pencil
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class VerticalOperationTest {
    @Test
    fun `A vertical operations top sign and bottom parts are ordered in this sequence`() {
        // GIVEN
        val top: TermDraft = mock()
        val bottom: TermDraft = mock()
        val sign: AnyLineSign = mock()
        val operation = operation(top = top, bottom = bottom, sign = sign)

        // THEN
        assertThat(operation.parts).isEqualTo(draftsOf(top, sign, bottom))
    }

    @Test
    fun `A vertical operations baseline is the baseline of its line sign`() {
        // GIVEN
        val sign: AnyLineSign = mock { on { baseline } doReturn 100 }
        val operation = operation(sign = sign)

        // THEN
        assertThat(operation.baseline).isEqualTo(100)
    }

    @Test
    fun `A vertical operation connects its operands with each other to allow choosing them in succession`() {
        // GIVEN
        val top: TermDraft = mock()
        val bottom: TermDraft = mock()
        operation(top = top, bottom = bottom)

        // THEN
        verify(top).choosableSuccessor = bottom
        verify(bottom).choosablePredecessor = top
    }

    private fun operation(origin: Operation = mock(), top: TermDraft = mock(), bottom: TermDraft = mock(), sign: AnyLineSign = mock()) =
            TestAnyVerticalOperation(origin, top, bottom, sign)

    private class TestAnyVerticalOperation(origin: Operation, top: TermDraft, bottom: TermDraft, sign: AnyLineSign)
        : VerticalOperation<Operation>(origin, top, bottom, sign) {

        override fun specifyPaddingLeft(pencil: Pencil) = TODO("not implemented")

        override fun specifyPaddingRight(pencil: Pencil) = TODO("not implemented")

        override fun calculatePartWidth(part: AnyDraft) = TODO("not implemented")

        override fun calculatePartHeight(part: AnyDraft) = TODO("not implemented")
    }
}