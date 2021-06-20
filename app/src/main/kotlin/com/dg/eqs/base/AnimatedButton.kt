package com.dg.eqs.base

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.content.withStyledAttributes
import com.dg.eqs.R.styleable.*
import com.dg.eqs.base.extension.reset


class AnimatedButton(context: Context, attrs: AttributeSet)
    : AppCompatImageView(context, attrs) {

    init {
        context.withStyledAttributes(attrs, AnimatedButton) {
            setImageResource(getResourceIdOrThrow(
                    AnimatedButton_animatedButtonAnimation))

            setAnimated(getBoolean(
                    AnimatedButton_animatedButtonAutostart, true))
        }
    }

    fun setAnimated(animated: Boolean) {
        val animation = drawable
                as AnimationDrawable

        animation.stop()

        animation.reset()

        if(animated) {
            animation.start()
        }
    }
}