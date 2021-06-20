package com.dg.eqs.core.visualization.symbolization.text.sign

import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class MinusSignTest {
    @Test
    fun `A minus signs text is a minus`() {
        assertThat(MinusSign(mock()).text).isEqualTo("-")
    }
}