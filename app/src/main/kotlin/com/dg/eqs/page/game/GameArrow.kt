package com.dg.eqs.page.game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Cap.ROUND
import android.graphics.Paint.Style.STROKE
import android.graphics.Path
import android.graphics.PorterDuff.Mode.SRC_ATOP
import android.graphics.PorterDuffColorFilter
import android.graphics.Shader.TileMode.REPEAT
import com.dg.eqs.R
import com.dg.eqs.base.enveloping.ColorRes
import com.dg.eqs.base.extension.*


class GameArrow(context: Context) {
    private val path = Path()
    private val paint = Paint()

    private val endOffset = context.getDimensionInInt(R.dimen.game_arrow_end_offset)
    private val arcHeight = context.getDimensionInInt(R.dimen.game_arrow_arc_height)
    private val tipLength = context.getDimensionInInt(R.dimen.game_arrow_tip_length)


    init {
        paint.style = STROKE
        paint.strokeCap = ROUND
        paint.strokeWidth = context.getDimensionInFloat(R.dimen.game_arrow_stroke_width)
        paint.shader = context.getBitmapShader(R.drawable.arrow, REPEAT)
    }

    fun color(color: ColorRes) {
        paint.colorFilter = PorterDuffColorFilter(color.resId, SRC_ATOP)
    }

    fun build(
            firstX: Int,
            firstY: Int,
            finalX: Int,
            finalY: Int) {

        clearArrowPath()
        buildArrowPath(firstX, firstY, finalX, finalY)
    }

    fun clear() = clearArrowPath()

    fun draw(canvas: Canvas) = canvas.drawPath(path, paint)

    private fun clearArrowPath() = path.rewind()

    private fun buildArrowPath(
            firstX: Int,
            firstY: Int,
            finalX: Int,
            finalY: Int) {

        buildArrowArcPath(firstX, firstY, finalX, finalY)
        buildArrowTipPath(finalX, finalY)
    }

    private fun buildArrowArcPath(
            firstX: Int,
            firstY: Int,
            finalX: Int,
            finalY: Int) {

        path.addArc(
                firstX = firstX,
                firstY = firstY,
                pointX1 = firstX,
                pointY1 = firstY - arcHeight,
                pointX2 = finalX,
                pointY2 = finalY - arcHeight - endOffset,
                finalX = finalX,
                finalY = finalY - endOffset - tipLength)
    }

    private fun buildArrowTipPath(
            finalX: Int,
            finalY: Int) {

        buildArrowTipMiddleLinePath(finalX, finalY)
        buildArrowTipRightLinePath(finalX, finalY)
        buildArrowTipLeftLinePath(finalX, finalY)
    }

    private fun buildArrowTipMiddleLinePath(
            finalX: Int,
            finalY: Int) {

        path.addLine(
                firstX = finalX,
                firstY = finalY - endOffset - tipLength,
                finalX = finalX,
                finalY = finalY - endOffset)
    }

    private fun buildArrowTipRightLinePath(
            finalX: Int,
            finalY: Int) {

        path.addLine(
                firstX = finalX + tipLength,
                firstY = finalY - endOffset - tipLength,
                finalX = finalX,
                finalY = finalY - endOffset)
    }

    private fun buildArrowTipLeftLinePath(
            finalX: Int,
            finalY: Int) {

        path.addLine(
                firstX = finalX - tipLength,
                firstY = finalY - endOffset - tipLength,
                finalX = finalX,
                finalY = finalY - endOffset)
    }
}