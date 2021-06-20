package com.dg.eqs.base.extension

import android.graphics.drawable.AnimationDrawable
import org.mockito.kotlin.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AnimationDrawableExtTest {
    @Mock
    private lateinit var animationDrawable: AnimationDrawable


    @Test
    fun `Should reset a animation drawable to its first frame`() {
        // WHEN
        animationDrawable.reset()

        // THEN
        verify(animationDrawable).selectDrawable(0)
    }
}