package com.dg.eqs.core.visualization.composition.horizontal.operation.product

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class PositiveProductDraftTest {
    @Test
    fun `A positive product draft is the positive representation of a product`() {
        // GIVEN
        val product: Product = mock()
        val operandA: TermDraft = mock { on { origin } doReturn product }
        val operandB: TermDraft = mock { on { origin } doReturn product }
        val productDraft = PositiveProductDraft(product, operandA, operandB)

        // THEN
        val expectedInner = RawProductDraft(product, operandA, operandB)

        assertThat(productDraft.inner).isEqualTo(expectedInner)
    }

    @Test
    fun `A positive product draft is the choosable parent of its operands`() {
        // GIVEN
        val product: Product = mock()
        val operandA: TermDraft = mock { on { origin } doReturn product }
        val operandB: TermDraft = mock { on { origin } doReturn product }
        val productDraft = PositiveProductDraft(product, operandA, operandB)

        // THEN
        verify(operandA).choosableParent = productDraft
        verify(operandB).choosableParent = productDraft
    }
}