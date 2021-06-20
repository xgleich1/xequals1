package com.dg.eqs.base.enveloping

import android.R
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class HtmlResTest {
    @Test
    fun `Should compare two equal html res`() {
        // GIVEN
        val htmlResA = HtmlRes(R.string.ok, 1, 2)
        val htmlResB = HtmlRes(R.string.ok, 1, 2)

        // THEN
        assertThat(htmlResA).isEqualTo(htmlResB)
    }

    @Test
    fun `Should convert a html res to a string`() {
        // GIVEN
        val htmlRes = HtmlRes(R.string.ok, 1, 2)

        // THEN
        assertThat(htmlRes).hasToString(
                "HtmlRes(17039370, [1, 2])")
    }
}