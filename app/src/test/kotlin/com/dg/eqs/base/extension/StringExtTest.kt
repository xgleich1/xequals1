package com.dg.eqs.base.extension

import org.mockito.kotlin.doReturnConsecutively
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class StringExtTest {
    @Test
    fun `Should provide the indices of a char in a string`() {
        assertThat("bab".indicesOf('b')).isEqualTo(listOf(0, 2))
    }

    @Test
    fun `Should split a string at the provided split indices`() {
        assertThat("aa,bb,cc".split(listOf(2, 5)))
                .isEqualTo(listOf("aa", "bb", "cc"))
    }

    @Test
    fun `Should replace a char in a string at the provided index`() {
        assertThat("bab".replace(1, "cc")).isEqualTo("bccb")
    }

    @Test
    fun `Should replace a char in a string using a transformation`() {
        // GIVEN
        val transform: () -> String = mock {
            on { invoke() } doReturnConsecutively listOf("cc", "dd")
        }

        // THEN
        assertThat("bab".replace('b', transform)).isEqualTo("ccadd")
    }
}