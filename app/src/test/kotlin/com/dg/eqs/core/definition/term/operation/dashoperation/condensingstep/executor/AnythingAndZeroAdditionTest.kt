package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AnythingAndZeroAdditionTest {
    @Test
    fun `Should add a single term to a single zero`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(zero())

        // THEN
        assertThat(add(left, right)).isEqualTo(left)
    }

    @Test
    fun `Should add several terms to a single zero`() {
        // GIVEN
        val left = termsOf(anything(), anything())
        val right = termsOf(zero())

        // THEN
        assertThat(add(left, right)).isEqualTo(left)
    }

    @Test
    fun `Should add a single term to several zeros`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(zero(), zero())

        // THEN
        assertThat(add(left, right)).isEqualTo(left)
    }

    @Test
    fun `Should add several terms to several zeros`() {
        // GIVEN
        val left = termsOf(anything(), anything())
        val right = termsOf(zero(), zero())

        // THEN
        assertThat(add(left, right)).isEqualTo(left)
    }

    private fun anything(): Term = mock()

    private fun zero(): Value = mock {
        on { isZero } doReturn true
    }

    private fun add(left: Terms, right: Terms) =
            AnythingAndZeroAddition.execute(left, right)
}