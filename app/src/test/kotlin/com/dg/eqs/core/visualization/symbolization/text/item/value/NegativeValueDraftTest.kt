package com.dg.eqs.core.visualization.symbolization.text.item.value

import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class NegativeValueDraftTest {
    @Test
    fun `A negative value draft is the negative representation of a value`() {
        // GIVEN
        val value: Value = mock { on { unsignedNumber } doReturn 1 }

        // THEN
        assertThat(NegativeValueDraft(value).text).isEqualTo("-1")
    }

    @Test
    fun `Should not represent a negative zero with an unnecessary minus sign`() {
        // GIVEN
        val value: Value = mock { on { unsignedNumber } doReturn 0 }

        // THEN
        assertThat(NegativeValueDraft(value).text).isEqualTo("0")
    }
}