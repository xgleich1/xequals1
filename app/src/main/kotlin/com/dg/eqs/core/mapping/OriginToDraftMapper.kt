package com.dg.eqs.core.mapping

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.base.abbreviation.TermDrafts
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.item.variable.Variable
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.NegativeProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.visualization.composition.horizontal.operation.dashoperation.BracketedDashOperationDraft
import com.dg.eqs.core.visualization.composition.horizontal.operation.dashoperation.NegativeDashOperationDraft
import com.dg.eqs.core.visualization.composition.horizontal.operation.dashoperation.PositiveDashOperationDraft
import com.dg.eqs.core.visualization.composition.horizontal.operation.product.BracketedProductDraft
import com.dg.eqs.core.visualization.composition.horizontal.operation.product.NegativeProductDraft
import com.dg.eqs.core.visualization.composition.horizontal.operation.product.PositiveProductDraft
import com.dg.eqs.core.visualization.composition.horizontal.relation.equalsrelation.EqualsRelationDraft
import com.dg.eqs.core.visualization.composition.vertical.operation.division.NegativeDivisionDraft
import com.dg.eqs.core.visualization.composition.vertical.operation.division.PositiveDivisionDraft
import com.dg.eqs.core.visualization.symbolization.text.item.value.NegativeValueDraft
import com.dg.eqs.core.visualization.symbolization.text.item.value.PositiveValueDraft
import com.dg.eqs.core.visualization.symbolization.text.item.variable.NegativeVariableDraft
import com.dg.eqs.core.visualization.symbolization.text.item.variable.PositiveVariableDraft


class OriginToDraftMapper {
    fun mapToDraft(origin: AnyOrigin) = when(origin) {
        is Relation -> mapToRelationDraft(origin)
        is Term -> mapToSignedTermDraft(origin)

        else -> throw IllegalArgumentException()
    }

    private fun mapToRelationDraft(relation: Relation) = when(relation) {
        is EqualsRelation -> mapToEqualsRelationDraft(relation)

        else -> throw IllegalArgumentException()
    }

    private fun mapToSignedTermDraft(term: Term): TermDraft = when(term) {
        is PositiveValue -> mapToPositiveValueDraft(term)
        is NegativeValue -> mapToNegativeValueDraft(term)
        is PositiveVariable -> mapToPositiveVariableDraft(term)
        is NegativeVariable -> mapToNegativeVariableDraft(term)
        is PositiveDashOperation -> mapToPositiveDashOperationDraft(term)
        is NegativeDashOperation -> mapToNegativeDashOperationDraft(term)
        is PositiveDivision -> mapToPositiveDivisionDraft(term)
        is NegativeDivision -> mapToNegativeDivisionDraft(term)
        is PositiveProduct -> mapToPositiveProductDraft(term)
        is NegativeProduct -> mapToNegativeProductDraft(term)

        else -> throw IllegalArgumentException()
    }

    private fun mapToUnsignedTermDraft(term: Term) = when(term) {
        is Value -> mapToPositiveValueDraft(term)
        is Variable -> mapToPositiveVariableDraft(term)
        is Division -> mapToPositiveDivisionDraft(term)
        is Product -> mapToPositiveProductDraft(term)

        else -> throw IllegalArgumentException()
    }

    private fun mapToEqualsRelationDraft(equalsRelation: EqualsRelation) = with(equalsRelation) {
        EqualsRelationDraft(equalsRelation, mapToSignedTermDraft(left), mapToSignedTermDraft(right))
    }

    private fun mapToPositiveValueDraft(value: Value) = PositiveValueDraft(value)

    private fun mapToNegativeValueDraft(value: Value) = NegativeValueDraft(value)

    private fun mapToPositiveVariableDraft(variable: Variable) = PositiveVariableDraft(variable)

    private fun mapToNegativeVariableDraft(variable: Variable) = NegativeVariableDraft(variable)

    private fun mapToPositiveDashOperationDraft(dashOperation: DashOperation) = with(dashOperation) {
        PositiveDashOperationDraft(dashOperation, mapDashOperationOperands(operands))
    }

    private fun mapToNegativeDashOperationDraft(dashOperation: DashOperation) = with(dashOperation) {
        NegativeDashOperationDraft(dashOperation, mapDashOperationOperands(operands))
    }

    private fun mapToBracketedDashOperationDraft(dashOperation: DashOperation) = with(dashOperation) {
        BracketedDashOperationDraft(dashOperation, mapDashOperationOperands(operands))
    }

    private fun mapToPositiveDivisionDraft(division: Division) = with(division) {
        PositiveDivisionDraft(division, mapToSignedTermDraft(numerator), mapToSignedTermDraft(denominator))
    }

    private fun mapToNegativeDivisionDraft(division: Division) = with(division) {
        NegativeDivisionDraft(division, mapToSignedTermDraft(numerator), mapToSignedTermDraft(denominator))
    }

    private fun mapToPositiveProductDraft(product: Product) = with(product) {
        PositiveProductDraft(product, mapProductOperands(operands))
    }

    private fun mapToNegativeProductDraft(product: Product) = with(product) {
        NegativeProductDraft(product, mapProductOperands(operands))
    }

    private fun mapToBracketedProductDraft(product: Product) = with(product) {
        BracketedProductDraft(product, mapProductOperands(operands))
    }

    private fun mapDashOperationOperands(operands: Terms): TermDrafts = draftsOf(
            operands.take(1).map(::mapDashOperationFirstOperand),
            operands.drop(1).map(::mapDashOperationLaterOperand))

    private fun mapProductOperands(operands: Terms): TermDrafts = draftsOf(
            operands.map(::mapProductOperand))

    private fun mapDashOperationFirstOperand(operand: Term) = when(operand) {
        is PositiveDashOperation -> mapToBracketedDashOperationDraft(operand)

        else -> mapToSignedTermDraft(operand)
    }

    private fun mapDashOperationLaterOperand(operand: Term) = when(operand) {
        is DashOperation -> mapToBracketedDashOperationDraft(operand)

        else -> mapToUnsignedTermDraft(operand)
    }

    private fun mapProductOperand(operand: Term) = when(operand) {
        is PositiveDashOperation -> mapToBracketedDashOperationDraft(operand)
        is PositiveProduct -> mapToBracketedProductDraft(operand)

        else -> mapToSignedTermDraft(operand)
    }
}