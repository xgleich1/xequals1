package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.findDistinctPair
import com.dg.eqs.base.extension.left
import com.dg.eqs.base.extension.right
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and


object TermAndEqualTermExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val firstTermAndItsEqual = findDistinctPair {
            left == right
        }

        val left = termsOf(firstTermAndItsEqual?.left)
        val right = termsOf(firstTermAndItsEqual?.right)

        left and right
    }
}