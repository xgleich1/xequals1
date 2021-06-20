package com.dg.eqs.base.gamification

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.dg.eqs.R
import com.dg.eqs.R.styleable.*
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.base.extension.setText
import com.dg.eqs.base.extension.toggleTransparentOpaque
import kotlinx.android.synthetic.main.button_google.view.*


class GoogleButton(context: Context, attrs: AttributeSet)
    : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.button_google, this)

        context.withStyledAttributes(attrs, GoogleButton) {
            setEnabled(getBoolean(
                    GoogleButton_googleButtonEnabled, true))

            label.setText(getString(
                    GoogleButton_googleButtonText))
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)

        toggleTransparentOpaque(!enabled)
    }

    fun setText(text: StringRes) {
        label.setText(text)
    }
}