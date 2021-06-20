package com.dg.eqs.core.visualization.composition.horizontal.operation.product

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.base.abbreviation.TermDrafts
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.base.composition.Size
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.classes.font
import com.dg.eqs.classes.pencil
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.visualization.symbolization.text.sign.TimesSign
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class RawProductDraftTest {
    @Test
    fun `A raw product draft creates its signs depending on the count of its operands`() {
        // GIVEN
        val product: Product = mock()
        val operandA: TermDraft = mock()
        val operandB: TermDraft = mock()
        val operandC: TermDraft = mock()
        val productDraft = productDraft(
                product,
                draftsOf(operandA, operandB, operandC))

        // THEN
        assertThat(productDraft.signs).isEqualTo(listOf(
                TimesSign(product),
                TimesSign(product)))
    }

    @Test
    fun `A raw product draft has no padding on its sides`() {
        // GIVEN
        val operandA: TermDraft = mock {
            on { firstX } doReturn 10
            on { finalX } doReturn 20
        }
        val operandB: TermDraft = mock {
            on { firstX } doReturn 30
            on { finalX } doReturn 40
        }
        val operandC: TermDraft = mock {
            on { firstX } doReturn 50
            on { finalX } doReturn 60
        }
        val productDraft = productDraft(
                operands = draftsOf(operandA, operandB, operandC))

        // WHEN
        productDraft.draft(pencil(
                font(textSize = Size(1, 1)),
                1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        assertThat(productDraft.firstX).isEqualTo(10)
        assertThat(productDraft.finalX).isEqualTo(60)
        assertThat(productDraft.width).isEqualTo(50)
    }

    @Test
    fun `A raw product draft does not adjust the width of its parts`() {
        // GIVEN
        val operandA: TermDraft = mock {
            on { width } doReturn 10
        }
        val operandB: TermDraft = mock {
            on { width } doReturn 20
        }
        val operandC: TermDraft = mock {
            on { width } doReturn 30
        }
        val productDraft = productDraft(
                operands = draftsOf(operandA, operandB, operandC))

        // WHEN
        productDraft.draft(pencil(
                font(textSize = Size(40, 1)),
                1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(operandA).width = 10
        verify(operandB).width = 20
        verify(operandC).width = 30

        assertThat(productDraft.signs.first.width).isEqualTo(40)
        assertThat(productDraft.signs.second.width).isEqualTo(40)
    }

    @Test
    fun `A raw product draft does not adjust the height of its parts`() {
        // GIVEN
        val operandA: TermDraft = mock {
            on { height } doReturn 10
        }
        val operandB: TermDraft = mock {
            on { height } doReturn 20
        }
        val operandC: TermDraft = mock {
            on { height } doReturn 30
        }
        val productDraft = productDraft(
                operands = draftsOf(operandA, operandB, operandC))

        // WHEN
        productDraft.draft(pencil(
                font(textSize = Size(1, 40)),
                1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(operandA).height = 10
        verify(operandB).height = 20
        verify(operandC).height = 30

        assertThat(productDraft.signs.first.height).isEqualTo(40)
        assertThat(productDraft.signs.second.height).isEqualTo(40)
    }

    private fun productDraft(origin: Product = mock(), operands: TermDrafts) =
            RawProductDraft(origin, operands)
}