package com.dg.eqs.base

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.getIntegerOrThrow
import androidx.core.content.withStyledAttributes
import com.dg.eqs.R
import com.dg.eqs.R.styleable.*
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.base.extension.setText
import com.dg.eqs.base.extension.toggleTransparentOpaque
import com.dg.eqs.base.extension.toggleVisibleGone
import kotlinx.android.synthetic.main.button_tile.view.*


open class TileButton(context: Context, attrs: AttributeSet)
    : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.button_tile, this)

        context.withStyledAttributes(attrs, TileButton) {
            setEnabled(getBoolean(
                    TileButton_tileButtonEnabled, true))

            setLoading(getBoolean(
                    TileButton_tileButtonLoading, false))

            label.setMaxLines(getIntegerOrThrow(
                    TileButton_tileButtonLines))

            label.setText(getString(
                    TileButton_tileButtonText))
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)

        toggleTransparentOpaque(!enabled)
    }

    fun setLoading(loading: Boolean) {
        label.toggleVisibleGone(!loading)
        dots.toggleVisibleGone(loading)

        val animation = dots.drawable
                as AnimationDrawable

        if(loading) {
            animation.start()
        } else {
            animation.stop()
        }
    }

    fun setText(text: StringRes) {
        label.setText(text)
    }
}