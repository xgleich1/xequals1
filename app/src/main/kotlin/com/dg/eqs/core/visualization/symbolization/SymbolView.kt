package com.dg.eqs.core.visualization.symbolization

import android.content.Context
import android.graphics.Color.WHITE
import android.view.Gravity.CENTER
import androidx.appcompat.widget.AppCompatTextView
import com.dg.eqs.R
import com.dg.eqs.base.abbreviation.AnySymbolDraft
import com.dg.eqs.base.abbreviation.AnyTextSymbol
import com.dg.eqs.base.extension.fontSize
import com.dg.eqs.base.extension.fontType
import com.dg.eqs.base.extension.textColor
import com.dg.eqs.core.visualization.symbolization.line.LineSymbol
import com.dg.eqs.core.visualization.symbolization.text.TextSymbol


class SymbolView(context: Context) : AppCompatTextView(context) {
    private var backgroundResId = 0


    init {
        gravity = CENTER
        textColor = WHITE
    }

    fun matchSymbolType(symbol: AnySymbolDraft) {
        when(symbol) {
            is TextSymbol -> matchTextSymbol(symbol)
            is LineSymbol -> matchLineSymbol()
        }
    }

    private fun matchTextSymbol(symbol: AnyTextSymbol) {
        if(text != symbol.text) {
            text = symbol.text
        }

        if(fontType != symbol.fontType) {
            fontType = symbol.fontType
        }

        if(fontSize != symbol.fontSize) {
            fontSize = symbol.fontSize
        }

        setBackgroundWhenNecessary(0)
    }

    private fun matchLineSymbol() {
        if(text != "") {
            text = ""
        }

        setBackgroundWhenNecessary(
                R.drawable.stroke_horizontal)
    }

    private fun setBackgroundWhenNecessary(resId: Int) {
        if(backgroundResId != resId) {
            backgroundResId = resId

            setBackgroundResource(resId)
        }
    }
}