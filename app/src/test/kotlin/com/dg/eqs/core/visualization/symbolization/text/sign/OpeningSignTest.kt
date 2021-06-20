package com.dg.eqs.core.visualization.symbolization.text.sign

import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class OpeningSignTest {
    @Test
    fun `A opening signs text is a opening bracket`() {
        assertThat(OpeningSign(mock()).text).isEqualTo("(")
    }
}