package com.dg.eqs.core.mapping

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
import com.dg.eqs.core.visualization.composition.horizontal.operation.dashoperation.BracketedDashOperationDraft
import com.dg.eqs.core.visualization.composition.horizontal.operation.dashoperation.NegativeDashOperationDraft
import com.dg.eqs.core.visualization.composition.horizontal.operation.dashoperation.PositiveDashOperationDraft
import com.dg.eqs.core.visualization.composition.horizontal.operation.product.NegativeProductDraft
import com.dg.eqs.core.visualization.composition.horizontal.operation.product.PositiveProductDraft
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
class OriginToDraftMapperDashOperationOperandsTest {
    @InjectMocks
    private lateinit var mapper: OriginToDraftMapper


    @Test
    fun `Mapping a dash operation doesn't influence the mapping of its operands when they are positive values`() {
        // GIVEN
        val operandA = PositiveValue(1)
        val operandB = PositiveValue(2)
        val operandC = PositiveValue(3)
        val dashOperation = PositiveDashOperation(operandA, operandB, operandC)

        // THEN
        val expected = PositiveDashOperationDraft(dashOperation,
                PositiveValueDraft(operandA),
                PositiveValueDraft(operandB),
                PositiveValueDraft(operandC))

        assertThat(mapper.mapToDraft(dashOperation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a dash operation influences the mapping of its operands when they are negative values by removing the signs from the later ones`() {
        // GIVEN
        val operandA = NegativeValue(1)
        val operandB = NegativeValue(2)
        val operandC = NegativeValue(3)
        val dashOperation = PositiveDashOperation(operandA, operandB, operandC)

        // THEN
        val expected = PositiveDashOperationDraft(dashOperation,
                NegativeValueDraft(operandA),
                PositiveValueDraft(operandB),
                PositiveValueDraft(operandC))

        assertThat(mapper.mapToDraft(dashOperation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a dash operation doesn't influence the mapping of its operands when they are positive variables`() {
        // GIVEN
        val operandA = PositiveVariable("x")
        val operandB = PositiveVariable("y")
        val operandC = PositiveVariable("z")
        val dashOperation = PositiveDashOperation(operandA, operandB, operandC)

        // THEN
        val expected = PositiveDashOperationDraft(dashOperation,
                PositiveVariableDraft(operandA),
                PositiveVariableDraft(operandB),
                PositiveVariableDraft(operandC))

        assertThat(mapper.mapToDraft(dashOperation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a dash operation influences the mapping of its operands when they are negative variables by removing the signs from the later ones`() {
        // GIVEN
        val operandA = NegativeVariable("x")
        val operandB = NegativeVariable("y")
        val operandC = NegativeVariable("z")
        val dashOperation = PositiveDashOperation(operandA, operandB, operandC)

        // THEN
        val expected = PositiveDashOperationDraft(dashOperation,
                NegativeVariableDraft(operandA),
                PositiveVariableDraft(operandB),
                PositiveVariableDraft(operandC))

        assertThat(mapper.mapToDraft(dashOperation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a dash operation influences the mapping of its operands when they are positive dash operations by putting them in brackets`() {
        // GIVEN
        val innerOperandA = PositiveValue(1)
        val innerOperandB = PositiveValue(2)
        val innerOperandC = PositiveValue(3)
        val innerOperandD = PositiveValue(4)
        val innerOperandE = PositiveValue(5)
        val innerOperandF = PositiveValue(6)
        val outerOperandA = PositiveDashOperation(innerOperandA, innerOperandB)
        val outerOperandB = PositiveDashOperation(innerOperandC, innerOperandD)
        val outerOperandC = PositiveDashOperation(innerOperandE, innerOperandF)
        val dashOperation = PositiveDashOperation(outerOperandA, outerOperandB, outerOperandC)

        // THEN
        val expected = PositiveDashOperationDraft(dashOperation,
                BracketedDashOperationDraft(outerOperandA,
                        PositiveValueDraft(innerOperandA),
                        PositiveValueDraft(innerOperandB)),
                BracketedDashOperationDraft(outerOperandB,
                        PositiveValueDraft(innerOperandC),
                        PositiveValueDraft(innerOperandD)),
                BracketedDashOperationDraft(outerOperandC,
                        PositiveValueDraft(innerOperandE),
                        PositiveValueDraft(innerOperandF)))

        assertThat(mapper.mapToDraft(dashOperation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a dash operation influences the mapping of its operands when they are negative dash operations by putting the later ones in brackets`() {
        // GIVEN
        val innerOperandA = PositiveValue(1)
        val innerOperandB = PositiveValue(2)
        val innerOperandC = PositiveValue(3)
        val innerOperandD = PositiveValue(4)
        val innerOperandE = PositiveValue(5)
        val innerOperandF = PositiveValue(6)
        val outerOperandA = NegativeDashOperation(innerOperandA, innerOperandB)
        val outerOperandB = NegativeDashOperation(innerOperandC, innerOperandD)
        val outerOperandC = NegativeDashOperation(innerOperandE, innerOperandF)
        val dashOperation = PositiveDashOperation(outerOperandA, outerOperandB, outerOperandC)

        // THEN
        val expected = PositiveDashOperationDraft(dashOperation,
                NegativeDashOperationDraft(outerOperandA,
                        PositiveValueDraft(innerOperandA),
                        PositiveValueDraft(innerOperandB)),
                BracketedDashOperationDraft(outerOperandB,
                        PositiveValueDraft(innerOperandC),
                        PositiveValueDraft(innerOperandD)),
                BracketedDashOperationDraft(outerOperandC,
                        PositiveValueDraft(innerOperandE),
                        PositiveValueDraft(innerOperandF)))

        assertThat(mapper.mapToDraft(dashOperation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a dash operation doesn't influence the mapping of its operands when they are positive divisions`() {
        // GIVEN
        val innerOperandA = PositiveValue(1)
        val innerOperandB = PositiveValue(2)
        val innerOperandC = PositiveValue(3)
        val innerOperandD = PositiveValue(4)
        val innerOperandE = PositiveValue(5)
        val innerOperandF = PositiveValue(6)
        val outerOperandA = PositiveDivision(innerOperandA, innerOperandB)
        val outerOperandB = PositiveDivision(innerOperandC, innerOperandD)
        val outerOperandC = PositiveDivision(innerOperandE, innerOperandF)
        val dashOperation = PositiveDashOperation(outerOperandA, outerOperandB, outerOperandC)

        // THEN
        val expected = PositiveDashOperationDraft(dashOperation,
                PositiveDivisionDraft(outerOperandA,
                        PositiveValueDraft(innerOperandA),
                        PositiveValueDraft(innerOperandB)),
                PositiveDivisionDraft(outerOperandB,
                        PositiveValueDraft(innerOperandC),
                        PositiveValueDraft(innerOperandD)),
                PositiveDivisionDraft(outerOperandC,
                        PositiveValueDraft(innerOperandE),
                        PositiveValueDraft(innerOperandF)))

        assertThat(mapper.mapToDraft(dashOperation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a dash operation influences the mapping of its operands when they are negative divisions by removing the signs from the later ones`() {
        // GIVEN
        val innerOperandA = PositiveValue(1)
        val innerOperandB = PositiveValue(2)
        val innerOperandC = PositiveValue(3)
        val innerOperandD = PositiveValue(4)
        val innerOperandE = PositiveValue(5)
        val innerOperandF = PositiveValue(6)
        val outerOperandA = NegativeDivision(innerOperandA, innerOperandB)
        val outerOperandB = NegativeDivision(innerOperandC, innerOperandD)
        val outerOperandC = NegativeDivision(innerOperandE, innerOperandF)
        val dashOperation = PositiveDashOperation(outerOperandA, outerOperandB, outerOperandC)

        // THEN
        val expected = PositiveDashOperationDraft(dashOperation,
                NegativeDivisionDraft(outerOperandA,
                        PositiveValueDraft(innerOperandA),
                        PositiveValueDraft(innerOperandB)),
                PositiveDivisionDraft(outerOperandB,
                        PositiveValueDraft(innerOperandC),
                        PositiveValueDraft(innerOperandD)),
                PositiveDivisionDraft(outerOperandC,
                        PositiveValueDraft(innerOperandE),
                        PositiveValueDraft(innerOperandF)))

        assertThat(mapper.mapToDraft(dashOperation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a dash operation doesn't influence the mapping of its operands when they are positive products`() {
        // GIVEN
        val innerOperandA = PositiveValue(1)
        val innerOperandB = PositiveValue(2)
        val innerOperandC = PositiveValue(3)
        val innerOperandD = PositiveValue(4)
        val innerOperandE = PositiveValue(5)
        val innerOperandF = PositiveValue(6)
        val outerOperandA = PositiveProduct(innerOperandA, innerOperandB)
        val outerOperandB = PositiveProduct(innerOperandC, innerOperandD)
        val outerOperandC = PositiveProduct(innerOperandE, innerOperandF)
        val dashOperation = PositiveDashOperation(outerOperandA, outerOperandB, outerOperandC)

        // THEN
        val expected = PositiveDashOperationDraft(dashOperation,
                PositiveProductDraft(outerOperandA,
                        PositiveValueDraft(innerOperandA),
                        PositiveValueDraft(innerOperandB)),
                PositiveProductDraft(outerOperandB,
                        PositiveValueDraft(innerOperandC),
                        PositiveValueDraft(innerOperandD)),
                PositiveProductDraft(outerOperandC,
                        PositiveValueDraft(innerOperandE),
                        PositiveValueDraft(innerOperandF)))

        assertThat(mapper.mapToDraft(dashOperation)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a dash operation influences the mapping of its operands when they are negative products by removing the signs from the later ones`() {
        // GIVEN
        val innerOperandA = PositiveValue(1)
        val innerOperandB = PositiveValue(2)
        val innerOperandC = PositiveValue(3)
        val innerOperandD = PositiveValue(4)
        val innerOperandE = PositiveValue(5)
        val innerOperandF = PositiveValue(6)
        val outerOperandA = NegativeProduct(innerOperandA, innerOperandB)
        val outerOperandB = NegativeProduct(innerOperandC, innerOperandD)
        val outerOperandC = NegativeProduct(innerOperandE, innerOperandF)
        val dashOperation = PositiveDashOperation(outerOperandA, outerOperandB, outerOperandC)

        // THEN
        val expected = PositiveDashOperationDraft(dashOperation,
                NegativeProductDraft(outerOperandA,
                        PositiveValueDraft(innerOperandA),
                        PositiveValueDraft(innerOperandB)),
                PositiveProductDraft(outerOperandB,
                        PositiveValueDraft(innerOperandC),
                        PositiveValueDraft(innerOperandD)),
                PositiveProductDraft(outerOperandC,
                        PositiveValueDraft(innerOperandE),
                        PositiveValueDraft(innerOperandF)))

        assertThat(mapper.mapToDraft(dashOperation)).isEqualTo(expected)
    }
}