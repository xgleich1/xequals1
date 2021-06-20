package com.dg.eqs.core.execution.intention.click.invert

import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.Intention.Click.Invert
import com.dg.eqs.core.information.valid.MultiplicationInfo


class InvertExecutor(
        private val calculator: InvertCalculator) {

    fun execute(invert: Invert) =
            ValidStep(MultiplicationInfo, calculator.calculate(invert))
}