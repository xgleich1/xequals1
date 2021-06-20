package com.dg.eqs.base.extension

import android.view.MotionEvent
import android.view.View
import android.view.View.*
import android.view.ViewGroup.LayoutParams
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.dg.eqs.Eqs
import com.dg.eqs.base.composition.Size


val View.halfWidth
    get() = width / 2

val View.halfHeight
    get() = height / 2

val View.size
    get() = Size(width, height)

val View.applicationComponent
    get() = (context.applicationContext as Eqs).applicationComponent


inline fun <reified T : LayoutParams> View.typedLayoutParams() = layoutParams as T

fun View.show() {
    isVisible = true
}

fun View.hide() {
    isGone = true
}

fun View.toggleVisibleGone(visible: Boolean) {
    visibility = if(visible) VISIBLE else GONE
}

fun View.toggleVisibleInvisible(visible: Boolean) {
    visibility = if(visible) VISIBLE else INVISIBLE
}

fun View.toggleTransparentOpaque(transparent: Boolean) {
    alpha = if(transparent) 0.5f else 1.0f
}

fun View.onClick(action: () -> Unit) =
        setOnClickListener { action() }

fun View.onTouch(action: (MotionEvent) -> Unit) =
        setOnTouchListener { _, event -> action(event); true }

fun View.getString(@StringRes resId: Int) =
        resources.getString(resId)

fun View.getFont(@FontRes fontId: Int, fontSize: Float) =
        context.getFont(fontId, fontSize)