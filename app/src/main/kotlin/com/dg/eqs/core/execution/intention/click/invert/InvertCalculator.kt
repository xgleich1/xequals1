package com.dg.eqs.core.execution.intention.click.invert

import com.dg.eqs.core.execution.intention.Intention.Click.Invert


class InvertCalculator {
    fun calculate(invert: Invert) = invert.origin.invert()
}