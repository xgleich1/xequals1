package com.dg.eqs.core.visualization.symbolization.line.sign

import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionSignTest {
    @Test
    fun `Should convert a division sign to a string`() {
        assertThat(DivisionSign(mock())).hasToString("/")
    }
}