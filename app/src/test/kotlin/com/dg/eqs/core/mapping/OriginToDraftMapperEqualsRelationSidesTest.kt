package com.dg.eqs.core.mapping

import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
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
import com.dg.eqs.core.visualization.composition.horizontal.operation.dashoperation.NegativeDashOperationDraft
import com.dg.eqs.core.visualization.composition.horizontal.operation.dashoperation.PositiveDashOperationDraft
import com.dg.eqs.core.visualization.composition.horizontal.operation.product.NegativeProductDraft
import com.dg.eqs.core.visualization.composition.horizontal.operation.product.PositiveProductDraft
import com.dg.eqs.core.visualization.composition.horizontal.relation.equalsrelation.EqualsRelationDraft
import com.dg.eqs.core.visualization.composition.vertical.operation.division.NegativeDivisionDraft
import com.dg.eqs.core.visualization.composition.vertical.operation.division.PositiveDivisionDraft
import com.dg.eqs.core.visualization.symbolization.text.item.value.NegativeValueDraft
import com.dg.eqs.core.visualization.symbolization.text.item.value.PositiveValueDraft
import com.dg.eqs.core.visualization.symbolization.text.item.variable.NegativeVariableDraft
import com.dg.eqs.core.visualization.symbolization.text.item.variable.PositiveVariableDraft
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class OriginToDraftMapperEqualsRelationSidesTest {
    @InjectMocks
    private lateinit var mapper: OriginToDraftMapper


    @Test
    fun `Mapping a equals relation doesn't influence the mapping of its sides (check for positive values)`() {
        // GIVEN
        val left = PositiveValue(1)
        val right = PositiveValue(1)
        val equalsRelation = EqualsRelation(left, right)

        // THEN
        val expected = EqualsRelationDraft(equalsRelation,
                PositiveValueDraft(left),
                PositiveValueDraft(right))

        assertThat(mapper.mapToDraft(equalsRelation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a equals relation doesn't influence the mapping of its sides (check for negative values)`() {
        // GIVEN
        val left = NegativeValue(1)
        val right = NegativeValue(1)
        val equalsRelation = EqualsRelation(left, right)

        // THEN
        val expected = EqualsRelationDraft(equalsRelation,
                NegativeValueDraft(left),
                NegativeValueDraft(right))

        assertThat(mapper.mapToDraft(equalsRelation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a equals relation doesn't influence the mapping of its sides (check for positive variables)`() {
        // GIVEN
        val left = PositiveVariable("x")
        val right = PositiveVariable("x")
        val equalsRelation = EqualsRelation(left, right)

        // THEN
        val expected = EqualsRelationDraft(equalsRelation,
                PositiveVariableDraft(left),
                PositiveVariableDraft(right))

        assertThat(mapper.mapToDraft(equalsRelation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a equals relation doesn't influence the mapping of its sides (check for negative variables)`() {
        // GIVEN
        val left = NegativeVariable("x")
        val right = NegativeVariable("x")
        val equalsRelation = EqualsRelation(left, right)

        // THEN
        val expected = EqualsRelationDraft(equalsRelation,
                NegativeVariableDraft(left),
                NegativeVariableDraft(right))

        assertThat(mapper.mapToDraft(equalsRelation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a equals relation doesn't influence the mapping of its sides (check for positive dash operations)`() {
        // GIVEN
        val operandA = PositiveValue(1)
        val operandB = PositiveValue(2)
        val operandC = PositiveValue(1)
        val operandD = PositiveValue(2)
        val left = PositiveDashOperation(operandA, operandB)
        val right = PositiveDashOperation(operandC, operandD)
        val equalsRelation = EqualsRelation(left, right)

        // THEN
        val expected = EqualsRelationDraft(equalsRelation,
                PositiveDashOperationDraft(left,
                        PositiveValueDraft(operandA),
                        PositiveValueDraft(operandB)),
                PositiveDashOperationDraft(right,
                        PositiveValueDraft(operandC),
                        PositiveValueDraft(operandD)))

        assertThat(mapper.mapToDraft(equalsRelation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a equals relation doesn't influence the mapping of its sides (check for negative dash operations)`() {
        // GIVEN
        val operandA = PositiveValue(1)
        val operandB = PositiveValue(2)
        val operandC = PositiveValue(1)
        val operandD = PositiveValue(2)
        val left = NegativeDashOperation(operandA, operandB)
        val right = NegativeDashOperation(operandC, operandD)
        val equalsRelation = EqualsRelation(left, right)

        // THEN
        val expected = EqualsRelationDraft(equalsRelation,
                NegativeDashOperationDraft(left,
                        PositiveValueDraft(operandA),
                        PositiveValueDraft(operandB)),
                NegativeDashOperationDraft(right,
                        PositiveValueDraft(operandC),
                        PositiveValueDraft(operandD)))

        assertThat(mapper.mapToDraft(equalsRelation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a equals relation doesn't influence the mapping of its sides (check for positive divisions)`() {
        // GIVEN
        val operandA = PositiveValue(1)
        val operandB = PositiveValue(2)
        val operandC = PositiveValue(1)
        val operandD = PositiveValue(2)
        val left = PositiveDivision(operandA, operandB)
        val right = PositiveDivision(operandC, operandD)
        val equalsRelation = EqualsRelation(left, right)

        // THEN
        val expected = EqualsRelationDraft(equalsRelation,
                PositiveDivisionDraft(left,
                        PositiveValueDraft(operandA),
                        PositiveValueDraft(operandB)),
                PositiveDivisionDraft(right,
                        PositiveValueDraft(operandC),
                        PositiveValueDraft(operandD)))

        assertThat(mapper.mapToDraft(equalsRelation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a equals relation doesn't influence the mapping of its sides (check for negative divisions)`() {
        // GIVEN
        val operandA = PositiveValue(1)
        val operandB = PositiveValue(2)
        val operandC = PositiveValue(1)
        val operandD = PositiveValue(2)
        val left = NegativeDivision(operandA, operandB)
        val right = NegativeDivision(operandC, operandD)
        val equalsRelation = EqualsRelation(left, right)

        // THEN
        val expected = EqualsRelationDraft(equalsRelation,
                NegativeDivisionDraft(left,
                        PositiveValueDraft(operandA),
                        PositiveValueDraft(operandB)),
                NegativeDivisionDraft(right,
                        PositiveValueDraft(operandC),
                        PositiveValueDraft(operandD)))

        assertThat(mapper.mapToDraft(equalsRelation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a equals relation doesn't influence the mapping of its sides (check for positive products)`() {
        // GIVEN
        val operandA = PositiveValue(1)
        val operandB = PositiveValue(2)
        val operandC = PositiveValue(1)
        val operandD = PositiveValue(2)
        val left = PositiveProduct(operandA, operandB)
        val right = PositiveProduct(operandC, operandD)
        val equalsRelation = EqualsRelation(left, right)

        // THEN
        val expected = EqualsRelationDraft(equalsRelation,
                PositiveProductDraft(left,
                        PositiveValueDraft(operandA),
                        PositiveValueDraft(operandB)),
                PositiveProductDraft(right,
                        PositiveValueDraft(operandC),
                        PositiveValueDraft(operandD)))

        assertThat(mapper.mapToDraft(equalsRelation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a equals relation doesn't influence the mapping of its sides (check for negative products)`() {
        // GIVEN
        val operandA = PositiveValue(1)
        val operandB = PositiveValue(2)
        val operandC = PositiveValue(1)
        val operandD = PositiveValue(2)
        val left = NegativeProduct(operandA, operandB)
        val right = NegativeProduct(operandC, operandD)
        val equalsRelation = EqualsRelation(left, right)

        // THEN
        val expected = EqualsRelationDraft(equalsRelation,
                NegativeProductDraft(left,
                        PositiveValueDraft(operandA),
                        PositiveValueDraft(operandB)),
                NegativeProductDraft(right,
                        PositiveValueDraft(operandC),
                        PositiveValueDraft(operandD)))

        assertThat(mapper.mapToDraft(equalsRelation)).isEqualTo(expected)
    }
}