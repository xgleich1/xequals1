package com.dg.eqs.core.visualization.symbolization

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.ImageView.ScaleType.FIT_XY
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.getDimensionPixelSizeOrThrow
import androidx.core.content.withStyledAttributes
import androidx.core.view.forEach
import com.dg.eqs.R
import com.dg.eqs.R.styleable.*
import com.dg.eqs.base.extension.hide
import com.dg.eqs.base.extension.show
import com.dg.eqs.base.extension.typedLayoutParams
import java.util.*


open class SymbolGrid(context: Context, attrs: AttributeSet)
    : SymbolPanel(context, attrs) {

    private var lineGap = 0
    private var lineSize = 0
    private var gridWidth = 0
    private var gridHeight = 0
    private val gridLines = LinkedList<Line>()
    private val gridBorder = Rect()


    init {
        context.withStyledAttributes(attrs, SymbolGrid) {
            lineGap = getDimensionPixelSizeOrThrow(
                    SymbolGrid_symbolGridLineGap)

            lineSize = getDimensionPixelSizeOrThrow(
                    SymbolGrid_symbolGridLineSize)
        }
    }

    fun showLines() = gridLines.forEach(Line::show)

    fun hideLines() = gridLines.forEach(Line::hide)

    fun scrollX(amountX: Int) = scroll(amountX, 0)

    fun scrollY(amountY: Int) = scroll(0, amountY)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        gridWidth = w
        gridHeight = h

        clearLines()
        buildLines()
    }

    private fun buildLines() {
        // ---------------------------------------------------
        // Create the lines starting at the center of the
        // grid to its edges (and 1 line beyond, to shuffle
        // it to the opposite side out of sight). This makes
        // it possible to build 2 lines with each new offset
        // ---------------------------------------------------
        var offsetX = 0
        var offsetY = 0

        val centerX = gridWidth / 2
        val centerY = gridHeight / 2

        buildVerticalLine(centerX)
        buildHorizontalLine(centerY)

        while(centerX - offsetX >= 0) {
            offsetX += lineGap

            buildVerticalLine(centerX - offsetX)
            buildVerticalLine(centerX + offsetX)
        }

        while(centerY - offsetY >= 0) {
            offsetY += lineGap

            buildHorizontalLine(centerY - offsetY)
            buildHorizontalLine(centerY + offsetY)
        }

        // ---------------------------------------------------
        // Retrieve the positions of the outermost lines.
        // They are the border of the grid and a line gets
        // shuffled to the other end of the border once it
        // leaves (by scrolling) the area defined by it
        // ---------------------------------------------------
        gridBorder.top = centerY - offsetY - lineSize / 2
        gridBorder.left = centerX - offsetX - lineSize / 2
        gridBorder.right = centerX + offsetX - lineSize / 2
        gridBorder.bottom = centerY + offsetY - lineSize / 2
    }

    private fun buildVerticalLine(linePositionX: Int) {
        // ---------------------------------------------------
        // A vertical line is composed of three individual
        // parts. They span the height of the grid and the
        // same height above and below it. This makes it
        // possible to shuffle them around out of sight
        // ---------------------------------------------------
        val x = linePositionX - lineSize / 2

        for(y in -gridHeight..gridHeight step gridHeight) {
            val part = VerticalLine(context)

            gridLines += part

            addView(part)

            part.typedLayoutParams<PanelParams>().apply {
                width = lineSize
                height = gridHeight
                firstX = x
                firstY = y
            }
        }
    }

    private fun buildHorizontalLine(linePositionY: Int) {
        // ---------------------------------------------------
        // A horizontal line is composed of three individual
        // parts. They span the width of the grid and the
        // same width left & right from it. This makes it
        // possible to shuffle them around out of sight
        // ---------------------------------------------------
        val y = linePositionY - lineSize / 2

        for(x in -gridWidth..gridWidth step gridWidth) {
            val part = HorizontalLine(context)

            gridLines += part

            addView(part)

            part.typedLayoutParams<PanelParams>().apply {
                width = gridWidth
                height = lineSize
                firstX = x
                firstY = y
            }
        }
    }

    private fun clearLines() {
        while(gridLines.isNotEmpty()) {
            removeView(gridLines.removeLast())
        }
    }

    private fun scroll(amountX: Int, amountY: Int) {
        // ---------------------------------------------------
        // Move all child-views and shuffle lines which leave
        // the visible area to the opposing end of the grid
        // ---------------------------------------------------
        forEach { view ->
            view.typedLayoutParams<PanelParams>().apply {
                firstX += amountX
                firstY += amountY

                when(view) {
                    is VerticalLine -> {
                        when {
                            firstX < gridBorder.left -> firstX += gridBorder.right - gridBorder.left + lineGap
                            firstX > gridBorder.right -> firstX -= gridBorder.right - gridBorder.left + lineGap
                        }

                        when {
                            firstY < -gridHeight -> firstY += 3 * gridHeight
                            firstY > 2 * gridHeight -> firstY -= 3 * gridHeight
                        }
                    }
                    is HorizontalLine -> {
                        when {
                            firstY < gridBorder.top -> firstY += gridBorder.bottom - gridBorder.top + lineGap
                            firstY > gridBorder.bottom -> firstY -= gridBorder.bottom - gridBorder.top + lineGap
                        }

                        when {
                            firstX < -gridWidth -> firstX += 3 * gridWidth
                            firstX > 2 * gridWidth -> firstX -= 3 * gridWidth
                        }
                    }
                }
            }
        }

        // ---------------------------------------------------
        // Layout the whole grid instead of each child to
        // mitigate the performance impact of the scroll
        // ---------------------------------------------------
        requestLayout()
    }

    private abstract class Line(context: Context) : AppCompatImageView(context) {
        init {
            alpha = 0.1f
            scaleType = FIT_XY
            visibility = GONE
        }
    }

    private class VerticalLine(context: Context) : Line(context) {
        init {
            setImageResource(R.drawable.stroke_vertical)
        }
    }

    private class HorizontalLine(context: Context) : Line(context) {
        init {
            setImageResource(R.drawable.stroke_horizontal)
        }
    }
}