package com.dg.eqs.core.definition.term.operation.alteration

import com.dg.eqs.base.abbreviation.AnyCondensingStep
import com.dg.eqs.base.extension.isSingle
import com.dg.eqs.core.definition.term.Terms


class DefaultOperandsCondensing(
        private vararg val steps: AnyCondensingStep) : OperandsCondensing {

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as DefaultOperandsCondensing

        if(!steps.contentEquals(other.steps)) return false

        return true
    }

    override fun hashCode() = steps.contentHashCode()

    override fun condense(operands: Terms): Terms {
        var condensingResult = operands

        var step = findStep(condensingResult)

        while(step != null) {
            condensingResult = step.apply(condensingResult)

            if(condensingResult.isSingle) break

            step = findStep(condensingResult)
        }

        return condensingResult
    }

    override fun condense(operands: Terms, source: Terms, target: Terms): Terms {
        val step = checkNotNull(findStep(operands, source, target)) {
            "Cannot condense $source and $target with " +
                    steps.joinToString { it.javaClass.simpleName }
        }

        return step.apply(operands, source, target)
    }

    private fun findStep(operands: Terms) =
            steps.find { it.isApplicable(operands) }

    private fun findStep(operands: Terms, source: Terms, target: Terms) =
            steps.find { it.isApplicable(operands, source, target) }
}