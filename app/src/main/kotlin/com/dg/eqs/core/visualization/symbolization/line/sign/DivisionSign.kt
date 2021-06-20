package com.dg.eqs.core.visualization.symbolization.line.sign

import com.dg.eqs.core.definition.term.Term


class DivisionSign(origin: Term) : LineSign<Term>(origin) {
    override fun toString() = "/"
}