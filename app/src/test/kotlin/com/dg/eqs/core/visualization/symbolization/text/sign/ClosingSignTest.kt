package com.dg.eqs.core.visualization.symbolization.text.sign

import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ClosingSignTest {
    @Test
    fun `A closing signs text is a closing bracket`() {
        assertThat(ClosingSign(mock()).text).isEqualTo(")")
    }
}