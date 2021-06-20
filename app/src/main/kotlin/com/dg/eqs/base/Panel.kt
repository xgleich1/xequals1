package com.dg.eqs.base

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.forEach
import com.dg.eqs.base.extension.typedLayoutParams


open class Panel(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    class PanelParams(width: Int, height: Int, var firstX: Int, var firstY: Int)
        : LayoutParams(width, height) {

        val centerX get() = firstX + width / 2
    }

    override fun generateDefaultLayoutParams() = PanelParams(0, 0, 0, 0)

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) =
            forEach { child ->
                with(child.typedLayoutParams<PanelParams>()) {
                    child.layout(
                            firstX,
                            firstY,
                            firstX + width,
                            firstY + height)
                }
            }
}