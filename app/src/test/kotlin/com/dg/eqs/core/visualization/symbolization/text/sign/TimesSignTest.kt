package com.dg.eqs.core.visualization.symbolization.text.sign

import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class TimesSignTest {
    @Test
    fun `A times signs text is a times`() {
        assertThat(TimesSign(mock()).text).isEqualTo("*")
    }
}