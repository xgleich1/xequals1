package com.dg.eqs.page.game

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.core.view.updateLayoutParams
import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.TermDrafts
import com.dg.eqs.base.enveloping.ColorRes
import com.dg.eqs.base.extension.hide
import com.dg.eqs.base.extension.show
import com.dg.eqs.base.extension.typedLayoutParams
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.visualization.symbolization.SymbolGrid


class GameGrid(context: Context, attrs: AttributeSet)
    : SymbolGrid(context, attrs) {

    private val sourceMark = GameMark(context)
    private val targetMark = GameMark(context)
    private val sourceArrow = GameArrow(context)


    init {
        hideSourceMark()
        hideTargetMark()

        addView(sourceMark)
        addView(targetMark)
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)

        sourceArrow.draw(canvas)
    }

    fun showDraft(draft: AnyDraft) = showSymbolViews(draft.unravel())

    fun showSourceMark(source: TermDrafts) = sourceMark.show(source)

    fun showTargetMark(target: TermDrafts) = targetMark.show(target)

    fun colorSourceMark(color: ColorRes) = sourceMark.color(color)

    fun colorTargetMark(color: ColorRes) = targetMark.color(color)

    fun hideSourceMark() = sourceMark.hide()

    fun hideTargetMark() = targetMark.hide()

    fun showSourceArrow(touch: Touch) {
        val sourceMarkParams = sourceMark
                .typedLayoutParams<PanelParams>()

        sourceArrow.build(
                sourceMarkParams.centerX,
                sourceMarkParams.firstY,
                touch.x,
                touch.y)

        invalidate()
    }

    fun colorSourceArrow(color: ColorRes) {
        sourceArrow.color(color)

        invalidate()
    }

    fun hideSourceArrow() {
        sourceArrow.clear()

        invalidate()
    }

    private fun GameMark.show(drafts: TermDrafts) {
        updateLayoutParams<PanelParams> {
            width = drafts.width
            height = drafts.height
            firstX = drafts.firstX
            firstY = drafts.firstY
        }

        show()
    }
}