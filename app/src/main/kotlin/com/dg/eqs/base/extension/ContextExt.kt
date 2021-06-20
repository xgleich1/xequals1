package com.dg.eqs.base.extension

import android.content.Context
import android.graphics.BitmapFactory.decodeResource
import android.graphics.BitmapShader
import android.graphics.Shader.TileMode
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat.getFont
import com.dg.eqs.base.composition.Font


fun Context.getDimensionInInt(@DimenRes dimensionId: Int) =
        resources.getDimensionPixelSize(dimensionId)

fun Context.getDimensionInFloat(@DimenRes dimensionId: Int) =
        resources.getDimension(dimensionId)

fun Context.getBitmapShader(@DrawableRes drawableId: Int, tileMode: TileMode) =
        BitmapShader(decodeResource(resources, drawableId), tileMode, tileMode)

fun Context.getFont(@FontRes fontId: Int, fontSize: Float) =
        Font(requireNotNull(getFont(this, fontId)), fontSize)

fun Context.getFont(@FontRes fontId: Int, @DimenRes fontSizeId: Int) =
        getFont(fontId, getDimensionInFloat(fontSizeId))