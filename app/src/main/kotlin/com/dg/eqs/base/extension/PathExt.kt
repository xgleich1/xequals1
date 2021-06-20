package com.dg.eqs.base.extension

import android.graphics.Path


fun Path.addLine(firstX: Int, firstY: Int, finalX: Int, finalY: Int) {
    moveTo(firstX.toFloat(), firstY.toFloat())

    lineTo(finalX.toFloat(), finalY.toFloat())
}

fun Path.addArc(firstX: Int, firstY: Int, pointX1: Int, pointY1: Int, pointX2: Int, pointY2: Int, finalX: Int, finalY: Int) {
    moveTo(firstX.toFloat(), firstY.toFloat())

    cubicTo(pointX1.toFloat(), pointY1.toFloat(), pointX2.toFloat(), pointY2.toFloat(), finalX.toFloat(), finalY.toFloat())
}