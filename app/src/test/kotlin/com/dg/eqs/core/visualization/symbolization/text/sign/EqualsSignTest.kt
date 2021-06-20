package com.dg.eqs.core.visualization.symbolization.text.sign

import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class EqualsSignTest {
    @Test
    fun `A equals signs text is a equals`() {
        assertThat(EqualsSign(mock()).text).isEqualTo("=")
    }
}