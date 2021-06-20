package com.dg.eqs.base.extension

import android.R
import android.graphics.Color
import android.widget.ImageView
import com.dg.eqs.base.enveloping.ColorRes
import com.dg.eqs.base.enveloping.DrawableRes
import org.mockito.kotlin.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ImageViewExtTest {
    @Mock
    private lateinit var imageView: ImageView


    @Test
    fun `Should tint the image using a typed color resource`() {
        // WHEN
        imageView.setColorFilter(ColorRes.WHITE)

        // THEN
        verify(imageView).setColorFilter(Color.WHITE)
    }

    @Test
    fun `A image view provides a setter for a typed drawable resource`() {
        // WHEN
        imageView.setDrawable(DrawableRes(R.drawable.ic_secure))

        // THEN
        verify(imageView).setImageResource(R.drawable.ic_secure)
    }
}