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
class OriginToDraftMapperDivisionOperandsTest {
    @InjectMocks
    private lateinit var mapper: OriginToDraftMapper


    @Test
    fun `Mapping a division doesn't influence the mapping of its operands (check for positive values)`() {
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
    fun `Mapping a division doesn't influence the mapping of its operands (check for negative values)`() {
        // GIVEN
        val operandA = NegativeValue(1)
        val operandB = NegativeValue(2)
        val division = PositiveDivision(operandA, operandB)

        // THEN
        val expected = PositiveDivisionDraft(division,
                NegativeValueDraft(operandA),
                NegativeValueDraft(operandB))

        assertThat(mapper.mapToDraft(division)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a division doesn't influence the mapping of its operands (check for positive variables)`() {
        // GIVEN
        val operandA = PositiveVariable("x")
        val operandB = PositiveVariable("y")
        val division = PositiveDivision(operandA, operandB)

        // THEN
        val expected = PositiveDivisionDraft(division,
                PositiveVariableDraft(operandA),
                PositiveVariableDraft(operandB))

        assertThat(mapper.mapToDraft(division)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a division doesn't influence the mapping of its operands (check for negative variables)`() {
        // GIVEN
        val operandA = NegativeVariable("x")
        val operandB = NegativeVariable("y")
        val division = PositiveDivision(operandA, operandB)

        // THEN
        val expected = PositiveDivisionDraft(division,
                NegativeVariableDraft(operandA),
                NegativeVariableDraft(operandB))

        assertThat(mapper.mapToDraft(division)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a division doesn't influence the mapping of its operands (check for positive dash operations)`() {
        // GIVEN
        val innerOperandA = PositiveValue(1)
        val innerOperandB = PositiveValue(2)
        val innerOperandC = PositiveValue(3)
        val innerOperandD = PositiveValue(4)
        val outerOperandA = PositiveDashOperation(innerOperandA, innerOperandB)
        val outerOperandB = PositiveDashOperation(innerOperandC, innerOperandD)
        val division = PositiveDivision(outerOperandA, outerOperandB)

        // THEN
        val expected = PositiveDivisionDraft(division,
                PositiveDashOperationDraft(outerOperandA,
                        PositiveValueDraft(innerOperandA),
                        PositiveValueDraft(innerOperandB)),
                PositiveDashOperationDraft(outerOperandB,
                        PositiveValueDraft(innerOperandC),
                        PositiveValueDraft(innerOperandD)))

        assertThat(mapper.mapToDraft(division)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a division doesn't influence the mapping of its operands (check for negative dash operations)`() {
        // GIVEN
        val innerOperandA = PositiveValue(1)
        val innerOperandB = PositiveValue(2)
        val innerOperandC = PositiveValue(3)
        val innerOperandD = PositiveValue(4)
        val outerOperandA = NegativeDashOperation(innerOperandA, innerOperandB)
        val outerOperandB = NegativeDashOperation(innerOperandC, innerOperandD)
        val division = PositiveDivision(outerOperandA, outerOperandB)

        // THEN
        val expected = PositiveDivisionDraft(division,
                NegativeDashOperationDraft(outerOperandA,
                        PositiveValueDraft(innerOperandA),
                        PositiveValueDraft(innerOperandB)),
                NegativeDashOperationDraft(outerOperandB,
                        PositiveValueDraft(innerOperandC),
                        PositiveValueDraft(innerOperandD)))

        assertThat(mapper.mapToDraft(division)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a division doesn't influence the mapping of its operands (check for positive divisions)`() {
        // GIVEN
        val innerOperandA = PositiveValue(1)
        val innerOperandB = PositiveValue(2)
        val innerOperandC = PositiveValue(3)
        val innerOperandD = PositiveValue(4)
        val outerOperandA = PositiveDivision(innerOperandA, innerOperandB)
        val outerOperandB = PositiveDivision(innerOperandC, innerOperandD)
        val division = PositiveDivision(outerOperandA, outerOperandB)

        // THEN
        val expected = PositiveDivisionDraft(division,
                PositiveDivisionDraft(outerOperandA,
                        PositiveValueDraft(innerOperandA),
                        PositiveValueDraft(innerOperandB)),
                PositiveDivisionDraft(outerOperandB,
                        PositiveValueDraft(innerOperandC),
                        PositiveValueDraft(innerOperandD)))

        assertThat(mapper.mapToDraft(division)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a division doesn't influence the mapping of its operands (check for negative divisions)`() {
        // GIVEN
        val innerOperandA = PositiveValue(1)
        val innerOperandB = PositiveValue(2)
        val innerOperandC = PositiveValue(3)
        val innerOperandD = PositiveValue(4)
        val outerOperandA = NegativeDivision(innerOperandA, innerOperandB)
        val outerOperandB = NegativeDivision(innerOperandC, innerOperandD)
        val division = PositiveDivision(outerOperandA, outerOperandB)

        // THEN
        val expected = PositiveDivisionDraft(division,
                NegativeDivisionDraft(outerOperandA,
                        PositiveValueDraft(innerOperandA),
                        PositiveValueDraft(innerOperandB)),
                NegativeDivisionDraft(outerOperandB,
                        PositiveValueDraft(innerOperandC),
                        PositiveValueDraft(innerOperandD)))

        assertThat(mapper.mapToDraft(division)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a division doesn't influence the mapping of its operands (check for positive products)`() {
        // GIVEN
        val innerOperandA = PositiveValue(1)
        val innerOperandB = PositiveValue(2)
        val innerOperandC = PositiveValue(3)
        val innerOperandD = PositiveValue(4)
        val outerOperandA = PositiveProduct(innerOperandA, innerOperandB)
        val outerOperandB = PositiveProduct(innerOperandC, innerOperandD)
        val division = PositiveDivision(outerOperandA, outerOperandB)

        // THEN
        val expected = PositiveDivisionDraft(division,
                PositiveProductDraft(outerOperandA,
                        PositiveValueDraft(innerOperandA),
                        PositiveValueDraft(innerOperandB)),
                PositiveProductDraft(outerOperandB,
                        PositiveValueDraft(innerOperandC),
                        PositiveValueDraft(innerOperandD)))

        assertThat(mapper.mapToDraft(division)).isEqualTo(expected)
    }

    @Test
    fun `Mapping a division doesn't influence the mapping of its operands (check for negative products)`() {
        // GIVEN
        val innerOperandA = PositiveValue(1)
        val innerOperandB = PositiveValue(2)
        val innerOperandC = PositiveValue(3)
        val innerOperandD = PositiveValue(4)
        val outerOperandA = NegativeProduct(innerOperandA, innerOperandB)
        val outerOperandB = NegativeProduct(innerOperandC, innerOperandD)
        val division = PositiveDivision(outerOperandA, outerOperandB)

        // THEN
        val expected = PositiveDivisionDraft(division,
                NegativeProductDraft(outerOperandA,
                        PositiveValueDraft(innerOperandA),
                        PositiveValueDraft(innerOperandB)),
                NegativeProductDraft(outerOperandB,
                        PositiveValueDraft(innerOperandC),
                        PositiveValueDraft(innerOperandD)))

        assertThat(mapper.mapToDraft(division)).isEqualTo(expected)
    }
}