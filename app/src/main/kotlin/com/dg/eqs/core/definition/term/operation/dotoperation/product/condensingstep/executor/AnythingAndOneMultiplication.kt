package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.isEven
import com.dg.eqs.base.extension.last
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor


object AnythingAndOneMultiplication : CondensingExecutor<Terms, Terms> {
    override fun execute(left: Terms, right: Terms): Terms {
        val negativeOneCount = right.count(Term::isNegative)

        return if(negativeOneCount.isEven) left else left.timesNegativeOne()
    }

    private fun Terms.timesNegativeOne() = termsOf(dropLast(1), last.invert())
}