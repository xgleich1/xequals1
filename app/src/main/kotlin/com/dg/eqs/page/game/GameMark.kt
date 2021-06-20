package com.dg.eqs.page.game

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.dg.eqs.R
import com.dg.eqs.base.enveloping.ColorRes
import com.dg.eqs.base.extension.setColorFilter


class GameMark(context: Context) : ConstraintLayout(context) {
    private val topStroke: ImageView
    private val leftStroke: ImageView
    private val rightStroke: ImageView
    private val bottomStroke: ImageView
    private val topLeftStroke: ImageView
    private val topRightStroke: ImageView
    private val bottomLeftStroke: ImageView
    private val bottomRightStroke: ImageView


    init {
        val layout = inflate(context, R.layout.mark_game, this)

        topStroke = layout.findViewById(R.id.topStroke)
        leftStroke = layout.findViewById(R.id.leftStroke)
        rightStroke = layout.findViewById(R.id.rightStroke)
        bottomStroke = layout.findViewById(R.id.bottomStroke)
        topLeftStroke = layout.findViewById(R.id.topLeftStroke)
        topRightStroke = layout.findViewById(R.id.topRightStroke)
        bottomLeftStroke = layout.findViewById(R.id.bottomLeftStroke)
        bottomRightStroke = layout.findViewById(R.id.bottomRightStroke)
    }

    fun color(color: ColorRes) {
        topStroke.setColorFilter(color)
        leftStroke.setColorFilter(color)
        rightStroke.setColorFilter(color)
        bottomStroke.setColorFilter(color)
        topLeftStroke.setColorFilter(color)
        topRightStroke.setColorFilter(color)
        bottomLeftStroke.setColorFilter(color)
        bottomRightStroke.setColorFilter(color)
    }
}