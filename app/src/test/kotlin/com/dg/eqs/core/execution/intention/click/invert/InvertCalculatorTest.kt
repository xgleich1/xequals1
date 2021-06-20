package com.dg.eqs.core.execution.intention.click.invert

import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.execution.intention.Intention.Click.Invert
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class InvertCalculatorTest {
    @InjectMocks
    private lateinit var calculator: InvertCalculator


    @Test
    fun `Should calculate the result of a invert`() {
        // GIVEN
        val origin = EqualsRelation(
                NegativeVariable("x"), NegativeValue(1))

        // WHEN
        val result = calculator.calculate(Invert(origin))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveVariable("x"), PositiveValue(1))

        assertThat(result).isEqualTo(expectedResult)
    }
}