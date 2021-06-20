package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.matcher

import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher
import com.google.common.math.IntMath.gcd


object ValueAndValueWithGcdGreaterOneInDivisionMatcher : CondensingMatcher {
    override fun matches(operands: Terms): Boolean {
        val numerator = operands.first as? Value ?: return false
        val denominator = operands.second as? Value ?: return false

        return gcd(numerator.unsignedNumber, denominator.unsignedNumber) > 1
    }

    override fun matches(left: Terms, right: Terms) = matches(left + right)
}