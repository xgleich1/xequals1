package com.dg.eqs.base.extension

import android.R
import android.content.res.Resources
import android.graphics.Color.WHITE
import android.graphics.Typeface
import android.util.TypedValue.COMPLEX_UNIT_PX
import android.widget.TextView
import com.dg.eqs.base.enveloping.StringRes
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TextViewExtTest {
    @Mock
    private lateinit var textView: TextView

    @Mock
    private lateinit var resources: Resources
    @Mock
    private lateinit var fontType: Typeface


    @Before
    fun setUp() {
        whenever(textView.resources) doReturn resources
    }

    @Test
    fun `A text view provides a property to access its text color`() {
        // GIVEN
        whenever(textView.getCurrentTextColor()) doReturn WHITE

        // THEN
        assertThat(textView.textColor).isEqualTo(WHITE)
    }

    @Test
    fun `A text view provides a property to change its text color`() {
        // WHEN
        textView.textColor = WHITE

        // THEN
        verify(textView).setTextColor(WHITE)
    }

    @Test
    fun `A text view provides a property to access its font type`() {
        // GIVEN
        whenever(textView.getTypeface()) doReturn fontType

        // THEN
        assertThat(textView.fontType).isEqualTo(fontType)
    }

    @Test
    fun `A text view provides a property to change its font type`() {
        // WHEN
        textView.fontType = fontType

        // THEN
        verify(textView).setTypeface(fontType)
    }

    @Test
    fun `A text view provides a property to access its font size`() {
        // GIVEN
        whenever(textView.getTextSize()) doReturn 25f

        // THEN
        assertThat(textView.fontSize).isEqualTo(25f)
    }

    @Test
    fun `A text view provides a property to change its font size`() {
        // WHEN
        textView.fontSize = 25f

        // THEN
        verify(textView).setTextSize(COMPLEX_UNIT_PX, 25f)
    }

    @Test
    fun `A text view provides a setter for a typed string resource`() {
        // GIVEN
        whenever(resources.getString(
                R.string.ok, 1, 2)) doReturn "String 1 2"

        // WHEN
        textView.setText(StringRes(R.string.ok, 1, 2))

        // THEN
        verify(textView).setText("String 1 2")
    }
}