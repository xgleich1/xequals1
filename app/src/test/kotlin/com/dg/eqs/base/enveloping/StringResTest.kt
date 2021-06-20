package com.dg.eqs.base.enveloping

import android.R
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class StringResTest {
    @Test
    fun `Should compare two equal string res`() {
        // GIVEN
        val stringResA = StringRes(R.string.ok, 1, 2)
        val stringResB = StringRes(R.string.ok, 1, 2)

        // THEN
        assertThat(stringResA).isEqualTo(stringResB)
    }

    @Test
    fun `Should convert a string res to a string`() {
        // GIVEN
        val stringRes = StringRes(R.string.ok, 1, 2)

        // THEN
        assertThat(stringRes).hasToString(
                "StringRes(17039370, [1, 2])")
    }
}