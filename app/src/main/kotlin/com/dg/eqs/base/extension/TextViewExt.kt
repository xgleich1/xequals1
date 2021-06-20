package com.dg.eqs.base.extension

import android.graphics.Typeface
import android.util.TypedValue.COMPLEX_UNIT_PX
import android.widget.TextView
import com.dg.eqs.base.enveloping.HtmlRes
import com.dg.eqs.base.enveloping.StringRes


var TextView.textColor: Int
    get() = getCurrentTextColor()
    set(value) = setTextColor(value)

var TextView.fontType: Typeface
    get() = getTypeface()
    set(value) = setTypeface(value)

var TextView.fontSize: Float
    get() = getTextSize()
    set(value) = setTextSize(COMPLEX_UNIT_PX, value)


fun TextView.setText(text: StringRes) = setText(
        resources.getString(text.resId, *text.args))

fun TextView.setHtml(html: HtmlRes) = setText(
        resources.getString(html.resId, *html.args)
                .toHtmlStyledSpanned())