package com.dg.eqs.core.visualization.symbolization.text.item.value

import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class PositiveValueDraftTest {
    @Test
    fun `A positive value draft is the positive representation of a value`() {
        // GIVEN
        val value: Value = mock { on { unsignedNumber } doReturn 1 }

        // THEN
        assertThat(PositiveValueDraft(value).text).isEqualTo("1")
    }
}