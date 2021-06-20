package com.dg.eqs.core.mapping

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.item.variable.Variable
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.NegativeProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct


class OriginToStringMapper {
    fun mapToString(origin: AnyOrigin): String = origin
            .mapToOriginString()

    private fun AnyOrigin.mapToOriginString() = when(this) {
        is EqualsRelation -> "=" + mapToRelationString()
        is PositiveValue -> "+" + mapToValueString()
        is NegativeValue -> "-" + mapToValueString()
        is PositiveVariable -> "+" + mapToVariableString()
        is NegativeVariable -> "-" + mapToVariableString()
        is PositiveDashOperation -> "+" + "±" + mapToOperationString()
        is NegativeDashOperation -> "-" + "±" + mapToOperationString()
        is PositiveDivision -> "+" + "/" + mapToOperationString()
        is NegativeDivision -> "-" + "/" + mapToOperationString()
        is PositiveProduct -> "+" + "*" + mapToOperationString()
        is NegativeProduct -> "-" + "*" + mapToOperationString()

        else -> throw IllegalArgumentException()
    }

    private fun Relation.mapToRelationString() =
            "[${mapToString(left)},${mapToString(right)}]"

    private fun Value.mapToValueString() = "$unsignedNumber"

    private fun Variable.mapToVariableString() = unsignedName

    private fun Operation.mapToOperationString() = operands
            .joinToString(",", "[", "]", transform = ::mapToString)
}