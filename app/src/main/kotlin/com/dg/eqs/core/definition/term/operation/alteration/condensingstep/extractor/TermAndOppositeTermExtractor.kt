package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.findDistinctPair
import com.dg.eqs.base.extension.left
import com.dg.eqs.base.extension.right
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and


object TermAndOppositeTermExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val firstTermAndItsOpposite = findDistinctPair {
            left == right.invert()
        }

        val left = termsOf(firstTermAndItsOpposite?.left)
        val right = termsOf(firstTermAndItsOpposite?.right)

        left and right
    }
}