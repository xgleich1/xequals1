package com.dg.eqs.core.visualization.symbolization.text.sign

import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class PlusSignTest {
    @Test
    fun `A plus signs text is a plus`() {
        assertThat(PlusSign(mock()).text).isEqualTo("+")
    }
}