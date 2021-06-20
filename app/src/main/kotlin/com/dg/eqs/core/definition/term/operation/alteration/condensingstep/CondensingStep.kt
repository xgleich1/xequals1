package com.dg.eqs.core.definition.term.operation.alteration.condensingstep

import com.dg.eqs.base.abbreviation.AnyCondensingStep
import com.dg.eqs.base.extension.first
import com.dg.eqs.core.definition.term.Terms


abstract class CondensingStep<L : CondensingSide, R : CondensingSide>(
        private val matcher: CondensingMatcher,
        private val extractor: CondensingExtractor,
        private val mapper: CondensingMapper<L, R>,
        private val executor: CondensingExecutor<L, R>) {

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as AnyCondensingStep

        if(matcher != other.matcher) return false
        if(extractor != other.extractor) return false
        if(mapper != other.mapper) return false
        if(executor != other.executor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = matcher.hashCode()

        result = 31 * result + extractor.hashCode()
        result = 31 * result + mapper.hashCode()
        result = 31 * result + executor.hashCode()

        return result
    }

    fun isApplicable(operands: Terms) = matcher.matches(operands)

    fun isApplicable(operands: Terms, source: Terms, target: Terms): Boolean {
        val (left, right) = operands.order(source, target)

        return matcher.matches(left, right)
    }

    fun apply(operands: Terms): Terms {
        val (left, right) = extractor.extract(operands)

        return apply(operands, left, right)
    }

    fun apply(operands: Terms, source: Terms, target: Terms): Terms {
        val (left, right) = operands.order(source, target)

        val (mappedLeft, mappedRight) = mapper.map(left, right)

        val result = executor.execute(mappedLeft, mappedRight)

        return operands.remove(source).replace(target, result)
    }

    private fun Terms.order(source: Terms, target: Terms)
            : CondensingSides<Terms, Terms> {

        val indexOfSource = indexOf(source.first)
        val indexOfTarget = indexOf(target.first)

        return if(indexOfSource < indexOfTarget) {
            source and target
        } else {
            target and source
        }
    }

    private fun Terms.replace(target: Terms, result: Terms): Terms {
        val replacementIndex = indexOf(target.first)

        return remove(target).insert(replacementIndex, result)
    }
}