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


class ZeroAndAnythingMultiplicationTest {
    @Test
    fun `Should multiply a single zero with a single term`() {
        // GIVEN
        val left = termsOf(zero())
        val right = termsOf(anything())

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a single zero with several terms`() {
        // GIVEN
        val left = termsOf(zero())
        val right = termsOf(anything(), anything())

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several zeros with a single term`() {
        // GIVEN
        val left = termsOf(zero(), zero())
        val right = termsOf(anything())

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several zeros with several terms`() {
        // GIVEN
        val left = termsOf(zero(), zero())
        val right = termsOf(anything(), anything())

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    private fun zero(): Value = mock {
        on { isZero } doReturn true
    }

    private fun anything(): Term = mock()

    private fun multiply(left: Terms, right: Terms) =
            ZeroAndAnythingMultiplication.execute(left, right)
}