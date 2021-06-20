package com.dg.eqs.core.definition.relation.equalsrelation

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.relation.alteration.RelationAlteration
import com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep.*
import com.dg.eqs.core.definition.term.Term


class EqualsRelation(left: Term, right: Term) : Relation(left, right) {
    override val alteration = RelationAlteration(
            EntireSideToZeroEqualsRelationStep,
            EntireSideToPositiveDashOperationEqualsRelationStep,
            EntireSideToAnythingEqualsRelationStep,
            OutOfNegativeDashOperationToZeroEqualsRelationStep,
            OutOfNegativeDashOperationToPositiveDashOperationEqualsRelationStep,
            OutOfNegativeDashOperationToAnythingEqualsRelationStep,
            OutOfPositiveDashOperationToZeroEqualsRelationStep,
            OutOfPositiveDashOperationToPositiveDashOperationEqualsRelationStep,
            OutOfPositiveDashOperationToAnythingEqualsRelationStep,
            NumeratorOutOfDivisionToAnythingEqualsRelationStep,
            OutOfProductAsNumeratorOfDivisionToAnythingEqualsRelationStep,
            DenominatorOutOfDivisionToPositiveProductEqualsRelationStep,
            DenominatorOutOfDivisionToAnythingEqualsRelationStep,
            OutOfProductAsDenominatorOfDivisionToPositiveProductEqualsRelationStep,
            OutOfProductAsDenominatorOfDivisionToAnythingEqualsRelationStep,
            OutOfProductToAnythingEqualsRelationStep)


    override fun toString() = "=${super.toString()}"

    override fun recreate(newLeft: Term, newRight: Term) = EqualsRelation(newLeft, newRight)
}