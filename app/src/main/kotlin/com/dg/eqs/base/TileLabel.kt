package com.dg.eqs.base

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.dg.eqs.R
import com.dg.eqs.R.styleable.TileLabel
import com.dg.eqs.R.styleable.TileLabel_tileLabelHtml
import com.dg.eqs.base.enveloping.HtmlRes
import com.dg.eqs.base.extension.setHtml
import com.dg.eqs.base.extension.toHtmlStyledSpanned
import kotlinx.android.synthetic.main.label_tile.view.*


open class TileLabel(context: Context, attrs: AttributeSet)
    : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.label_tile, this)

        context.withStyledAttributes(attrs, TileLabel) {
            val html = getString(
                    TileLabel_tileLabelHtml)

            if(html != null) {
                label.setText(html.toHtmlStyledSpanned())
            }
        }
    }

    fun setHtml(html: HtmlRes) {
        label.setHtml(html)
    }
}