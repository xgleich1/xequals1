package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AnythingAndZeroMultiplicationTest {
    @Test
    fun `Should multiply a single term with a single zero`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(zero())

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with a single zero`() {
        // GIVEN
        val left = termsOf(anything(), anything())
        val right = termsOf(zero())

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a single term with several zeros`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(zero(), zero())

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with several zeros`() {
        // GIVEN
        val left = termsOf(anything(), anything())
        val right = termsOf(zero(), zero())

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun zero(): Value = mock {
        on { isZero } doReturn true
    }

    private fun multiply(left: Terms, right: Terms) =
            AnythingAndZeroMultiplication.execute(left, right)
}