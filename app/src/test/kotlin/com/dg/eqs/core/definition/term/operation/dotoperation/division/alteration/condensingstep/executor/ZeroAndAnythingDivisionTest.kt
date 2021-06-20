package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ZeroAndAnythingDivisionTest {
    @Test
    fun `Should divide a positive zero through anything`() {
        // GIVEN
        val numerator = PositiveValue(0)
        val denominator = anything()

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide a negative zero through anything`() {
        // GIVEN
        val numerator = NegativeValue(0)
        val denominator = anything()

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun divide(numerator: Value, denominator: Term) =
            ZeroAndAnythingDivision.execute(numerator, denominator)
}