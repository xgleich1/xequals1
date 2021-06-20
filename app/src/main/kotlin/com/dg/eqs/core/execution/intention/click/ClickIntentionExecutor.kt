package com.dg.eqs.core.execution.intention.click

import com.dg.eqs.core.execution.intention.Intention.Click
import com.dg.eqs.core.execution.intention.Intention.Click.Invert
import com.dg.eqs.core.execution.intention.click.invert.InvertExecutor


class ClickIntentionExecutor(
        private val invertExecutor: InvertExecutor) {

    fun execute(click: Click) = when(click) {
        is Invert -> invertExecutor.execute(click)
    }
}