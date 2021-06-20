package com.dg.eqs.base.extension

import android.widget.ImageView
import com.dg.eqs.base.enveloping.ColorRes
import com.dg.eqs.base.enveloping.DrawableRes


fun ImageView.setColorFilter(color: ColorRes) =
        setColorFilter(color.resId)

fun ImageView.setDrawable(drawable: DrawableRes) =
        setImageResource(drawable.resId)