package com.dg.eqs.core.visualization.composition.horizontal.operation.product

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.visualization.composition.horizontal.envelope.BracketDraft
import com.dg.eqs.core.visualization.composition.horizontal.envelope.NegationDraft
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class NegativeProductDraftTest {
    @Test
    fun `A negative product draft is the negative representation of a product`() {
        // GIVEN
        val product: Product = mock()
        val operandA: TermDraft = mock { on { origin } doReturn product }
        val operandB: TermDraft = mock { on { origin } doReturn product }
        val productDraft = NegativeProductDraft(product, operandA, operandB)

        // THEN
        val expectedInner = NegationDraft(BracketDraft(RawProductDraft(product, operandA, operandB)))

        assertThat(productDraft.inner).isEqualTo(expectedInner)
    }

    @Test
    fun `A negative product draft is the choosable parent of its operands`() {
        // GIVEN
        val product: Product = mock()
        val operandA: TermDraft = mock { on { origin } doReturn product }
        val operandB: TermDraft = mock { on { origin } doReturn product }
        val productDraft = NegativeProductDraft(product, operandA, operandB)

        // THEN
        verify(operandA).choosableParent = productDraft
        verify(operandB).choosableParent = productDraft
    }
}