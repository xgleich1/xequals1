package com.dg.eqs.core.mapping

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.extension.split
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.NegativeProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct


class StringToOriginMapper {
    fun <T : AnyOrigin> mapToOrigin(string: String): T {
        val origin = with(string) {
            if(isRepresentingRelation()) {
                mapToRelation()
            } else {
                mapToTerm()
            }
        }

        @Suppress("UNCHECKED_CAST")
        return requireNotNull(origin as? T) {
            "$string isn't representing the desired type"
        }
    }

    private fun String.mapToRelation() = when {
        isRepresentingEqualsRelation() -> mapToEqualsRelation()

        else -> throw IllegalArgumentException(
                "$this isn't representing a relation")
    }

    private fun String.mapToTerm() = when {
        isRepresentingPositiveValue() -> mapToPositiveValue()
        isRepresentingNegativeValue() -> mapToNegativeValue()
        isRepresentingPositiveVariable() -> mapToPositiveVariable()
        isRepresentingNegativeVariable() -> mapToNegativeVariable()
        isRepresentingPositiveDashOperation() -> mapToPositiveDashOperation()
        isRepresentingNegativeDashOperation() -> mapToNegativeDashOperation()
        isRepresentingPositiveDivision() -> mapToPositiveDivision()
        isRepresentingNegativeDivision() -> mapToNegativeDivision()
        isRepresentingPositiveProduct() -> mapToPositiveProduct()
        isRepresentingNegativeProduct() -> mapToNegativeProduct()

        else -> throw IllegalArgumentException(
                "$this isn't representing a term")
    }

    private fun String.isRepresentingRelation() = matches("""[^+\-].+""".toRegex())

    private fun String.isRepresentingEqualsRelation() = matches("""=.+""".toRegex())

    private fun String.isRepresentingPositiveValue() = matches("""\+\d+""".toRegex())

    private fun String.isRepresentingNegativeValue() = matches("""-\d+""".toRegex())

    private fun String.isRepresentingPositiveVariable() = matches("""\+[a-zA-Z]+""".toRegex())

    private fun String.isRepresentingNegativeVariable() = matches("""-[a-zA-Z]+""".toRegex())

    private fun String.isRepresentingPositiveDashOperation() = matches("""\+±.+""".toRegex())

    private fun String.isRepresentingNegativeDashOperation() = matches("""-±.+""".toRegex())

    private fun String.isRepresentingPositiveDivision() = matches("""\+/.+""".toRegex())

    private fun String.isRepresentingNegativeDivision() = matches("""-/.+""".toRegex())

    private fun String.isRepresentingPositiveProduct() = matches("""\+\*.+""".toRegex())

    private fun String.isRepresentingNegativeProduct() = matches("""-\*.+""".toRegex())

    private fun String.mapToEqualsRelation(): EqualsRelation {
        val (left, right) = mapToSides()

        return EqualsRelation(left, right)
    }

    private fun String.mapToPositiveValue() = PositiveValue(mapToUnsignedNumber())

    private fun String.mapToNegativeValue() = NegativeValue(mapToUnsignedNumber())

    private fun String.mapToPositiveVariable() = PositiveVariable(mapToUnsignedName())

    private fun String.mapToNegativeVariable() = NegativeVariable(mapToUnsignedName())

    private fun String.mapToPositiveDashOperation() = PositiveDashOperation(mapToOperands())

    private fun String.mapToNegativeDashOperation() = NegativeDashOperation(mapToOperands())

    private fun String.mapToPositiveDivision(): PositiveDivision {
        val (numerator, denominator) = mapToOperands()

        return PositiveDivision(numerator, denominator)
    }

    private fun String.mapToNegativeDivision(): NegativeDivision {
        val (numerator, denominator) = mapToOperands()

        return NegativeDivision(numerator, denominator)
    }

    private fun String.mapToPositiveProduct() = PositiveProduct(mapToOperands())

    private fun String.mapToNegativeProduct() = NegativeProduct(mapToOperands())

    private fun String.mapToSides() = drop(2).dropLast(1).mapToTerms()

    private fun String.mapToUnsignedNumber() = drop(1).toInt()

    private fun String.mapToUnsignedName() = drop(1)

    private fun String.mapToOperands() = drop(3).dropLast(1).mapToTerms()

    private fun String.mapToTerms(): List<Term> {
        var operationDepth = 0

        val separatorIndices = mutableListOf<Int>()

        forEachIndexed { currentIndex, currentChar ->
            when(currentChar) {
                '[' -> ++operationDepth
                ']' -> --operationDepth

                ',' -> if(operationDepth == 0) {
                    separatorIndices += currentIndex
                }
            }
        }

        return split(separatorIndices).map { it.mapToTerm() }
    }
}