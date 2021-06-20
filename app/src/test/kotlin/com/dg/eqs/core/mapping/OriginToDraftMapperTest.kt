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
class OriginToDraftMapperTest {
    @InjectMocks
    private lateinit var mapper: OriginToDraftMapper


    @Test
    fun `Should map a equals relation into a equals relation draft`() {
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
    fun `Should map a positive value into a positive value draft`() {
        // GIVEN
        val value = PositiveValue(1)

        // THEN
        assertThat(mapper.mapToDraft(value)).isEqualTo(PositiveValueDraft(value))
    }

    @Test
    fun `Should map a negative value into a negative value draft`() {
        // GIVEN
        val value = NegativeValue(1)

        // THEN
        assertThat(mapper.mapToDraft(value)).isEqualTo(NegativeValueDraft(value))
    }

    @Test
    fun `Should map a positive variable into a positive variable draft`() {
        // GIVEN
        val variable = PositiveVariable("x")

        // THEN
        assertThat(mapper.mapToDraft(variable)).isEqualTo(PositiveVariableDraft(variable))
    }

    @Test
    fun `Should map a negative variable into a negative variable draft`() {
        // GIVEN
        val variable = NegativeVariable("x")

        // THEN
        assertThat(mapper.mapToDraft(variable)).isEqualTo(NegativeVariableDraft(variable))
    }

    @Test
    fun `Should map a positive dash operation into a positive dash operation draft`() {
        // GIVEN
        val operandA = PositiveValue(1)
        val operandB = PositiveValue(2)
        val dashOperation = PositiveDashOperation(operandA, operandB)

        // THEN
        val expected = PositiveDashOperationDraft(dashOperation,
                PositiveValueDraft(operandA),
                PositiveValueDraft(operandB))

        assertThat(mapper.mapToDraft(dashOperation)).isEqualTo(expected)
    }

    @Test
    fun `Should map a negative dash operation into a negative dash operation draft`() {
        // GIVEN
        val operandA = PositiveValue(1)
        val operandB = PositiveValue(2)
        val dashOperation = NegativeDashOperation(operandA, operandB)

        // THEN
        val expected = NegativeDashOperationDraft(dashOperation,
                PositiveValueDraft(operandA),
                PositiveValueDraft(operandB))

        assertThat(mapper.mapToDraft(dashOperation)).isEqualTo(expected)
    }

    @Test
    fun `Should map a positive division into a positive division draft`() {
        // GIVEN
        val operandA = PositiveValue(1)
        val operandB = PositiveValue(2)
        val division = PositiveDivision(operandA, operandB)

        // THEN
        val expected = PositiveDivisionDraft(division,
                PositiveValueDraft(operandA),
                PositiveValueDraft(operandB))

        assertThat(mapper.mapToDraft(division)).isEqualTo(expected)
    }

    @Test
    fun `Should map a negative division into a negative division draft`() {
        // GIVEN
        val operandA = PositiveValue(1)
        val operandB = PositiveValue(2)
        val division = NegativeDivision(operandA, operandB)

        // THEN
        val expected = NegativeDivisionDraft(division,
                PositiveValueDraft(operandA),
                PositiveValueDraft(operandB))

        assertThat(mapper.mapToDraft(division)).isEqualTo(expected)
    }

    @Test
    fun `Should map a positive product into a positive product draft`() {
        // GIVEN
        val operandA = PositiveValue(1)
        val operandB = PositiveValue(2)
        val product = PositiveProduct(operandA, operandB)

        // THEN
        val expected = PositiveProductDraft(product,
                PositiveValueDraft(operandA),
                PositiveValueDraft(operandB))

        assertThat(mapper.mapToDraft(product)).isEqualTo(expected)
    }

    @Test
    fun `Should map a negative product into a negative product draft`() {
        // GIVEN
        val operandA = PositiveValue(1)
        val operandB = PositiveValue(2)
        val product = NegativeProduct(operandA, operandB)

        // THEN
        val expected = NegativeProductDraft(product,
                PositiveValueDraft(operandA),
                PositiveValueDraft(operandB))

        assertThat(mapper.mapToDraft(product)).isEqualTo(expected)
    }
}