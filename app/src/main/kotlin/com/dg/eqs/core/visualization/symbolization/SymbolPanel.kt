package com.dg.eqs.core.visualization.symbolization

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.updateLayoutParams
import com.dg.eqs.base.Panel
import com.dg.eqs.base.abbreviation.AnySymbolDraft
import com.dg.eqs.base.abbreviation.AnySymbolDrafts
import com.dg.eqs.base.extension.toggleVisibleGone
import com.dg.eqs.base.extension.typedLayoutParams


open class SymbolPanel(context: Context, attrs: AttributeSet)
    : Panel(context, attrs) {

    private val symbolViews = ArrayList<SymbolView>()


    fun showSymbolViews(symbols: AnySymbolDrafts) {
        updateSymbolViewVisibility(symbols)
        updateSymbolViewAmount(symbols)
        updateSymbolViews(symbols)
    }

    private fun updateSymbolViewVisibility(symbols: AnySymbolDrafts) {
        // ---------------------------------------------------
        // Symbol views are never removed to reduce the
        // amount of performance impacting addView calls.
        // Already added views are dynamically shown/hidden
        // ---------------------------------------------------
        symbolViews.forEachIndexed { i, symbolView ->
            symbolView.toggleVisibleGone(i <= symbols.lastIndex)
        }
    }

    private fun updateSymbolViewAmount(symbols: AnySymbolDrafts) {
        repeat(symbols.size - symbolViews.size) {
            val symbolView = SymbolView(context)

            symbolViews += symbolView

            addView(symbolView)
        }
    }

    private fun updateSymbolViews(symbols: AnySymbolDrafts) {
        symbols.forEachIndexed { i, symbol ->
            val symbolView = symbolViews[i]

            symbolView.matchSymbolType(symbol)
            symbolView.matchSymbolLayout(symbol)
        }
    }

    private fun SymbolView.matchSymbolLayout(symbol: AnySymbolDraft) {
        // ---------------------------------------------------
        // Changing the layout params triggers a layout pass.
        // This performance hit is only taken when necessary
        // ---------------------------------------------------
        if(isMatchingSymbolLayout(symbol)) return

        updateLayoutParams<PanelParams> {
            width = symbol.width
            height = symbol.height
            firstX = symbol.firstX
            firstY = symbol.firstY
        }
    }

    private fun SymbolView.isMatchingSymbolLayout(symbol: AnySymbolDraft) =
            with(typedLayoutParams<PanelParams>()) {
                width == symbol.width &&
                height == symbol.height &&
                firstX == symbol.firstX &&
                firstY == symbol.firstY
            }
}