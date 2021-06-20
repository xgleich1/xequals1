package com.dg.eqs.base.extension

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dg.eqs.base.enveloping.LayoutRes
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LayoutInflaterExtTest {
    @Mock
    private lateinit var layoutInflater: LayoutInflater

    @Mock
    private lateinit var root: ViewGroup
    @Mock
    private lateinit var layout: View


    @Test
    fun `Should inflate a typed layout resource`() {
        // GIVEN
        val resId = R.layout.simple_list_item_1

        whenever(layoutInflater.inflate(resId, root)) doReturn layout

        // THEN
        assertThat(layoutInflater.inflate(LayoutRes(resId), root)).isEqualTo(layout)
    }
}