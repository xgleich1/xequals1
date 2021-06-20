package com.dg.eqs.core.definition.relation.alteration.shiftingstep

import com.dg.eqs.base.abbreviation.AnyShiftingStep
import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


abstract class ShiftingStep<S : Term, T : Term>(
        private val matcher: ShiftingMatcher,
        private val mapper: ShiftingMapper<S, T>,
        private val withdrawal: ShiftingWithdrawal<S>,
        private val integrator: ShiftingIntegrator<T>,
        private val wrapper: ShiftingWrapper) {

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as AnyShiftingStep

        if(matcher != other.matcher) return false
        if(mapper != other.mapper) return false
        if(withdrawal != other.withdrawal) return false
        if(integrator != other.integrator) return false
        if(wrapper != other.wrapper) return false

        return true
    }

    override fun hashCode(): Int {
        var result = matcher.hashCode()

        result = 31 * result + mapper.hashCode()
        result = 31 * result + withdrawal.hashCode()
        result = 31 * result + integrator.hashCode()
        result = 31 * result + wrapper.hashCode()

        return result
    }

    fun isApplicable(
            relation: Relation,
            source: Terms,
            targetSide: Term): Boolean {

        val sourceSide = with(relation) {
            if(targetSide === right) left else right
        }

        return matcher.matches(sourceSide, targetSide, source)
    }

    fun apply(relation: Relation, source: Terms, targetSide: Term) =
            with(relation) {
                if(targetSide === right) {
                    shiftSourceToRight(source)
                } else {
                    shiftSourceToLeft(source)
                }
            }

    private fun Relation.shiftSourceToRight(source: Terms): Relation {
        val (sourceSide, targetSide) = mapper
                .map(left, right)

        val newSourceSide = withdrawal
                .withdraw(source, sourceSide)

        val newTargetSide = integrator
                .integrate(source, targetSide)

        return wrapper.wrap(newSourceSide, newTargetSide)
    }

    private fun Relation.shiftSourceToLeft(source: Terms): Relation {
        val (sourceSide, targetSide) = mapper
                .map(right, left)

        val newSourceSide = withdrawal
                .withdraw(source, sourceSide)

        val newTargetSide = integrator
                .integrate(source, targetSide)

        return wrapper.wrap(newTargetSide, newSourceSide)
    }
}