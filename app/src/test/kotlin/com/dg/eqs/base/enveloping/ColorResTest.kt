package com.dg.eqs.base.enveloping

import android.graphics.Color
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ColorResTest {
    @Test
    fun `White provides the white color resource`() {
        assertThat(ColorRes.WHITE.resId).isEqualTo(Color.WHITE)
    }

    @Test
    fun `Red provides the red color resource`() {
        assertThat(ColorRes.RED.resId).isEqualTo(Color.RED)
    }

    @Test
    fun `Green provides the green color resource`() {
        assertThat(ColorRes.GREEN.resId).isEqualTo(Color.GREEN)
    }
}