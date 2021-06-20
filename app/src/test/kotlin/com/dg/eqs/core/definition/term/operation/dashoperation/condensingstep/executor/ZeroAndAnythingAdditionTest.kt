package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ZeroAndAnythingAdditionTest {
    @Test
    fun `Should add a single zero to a single term`() {
        // GIVEN
        val left = termsOf(zero())
        val right = termsOf(anything())

        // THEN
        assertThat(add(left, right)).isEqualTo(right)
    }

    @Test
    fun `Should add a single zero to several terms`() {
        // GIVEN
        val left = termsOf(zero())
        val right = termsOf(anything(), anything())

        // THEN
        assertThat(add(left, right)).isEqualTo(right)
    }

    @Test
    fun `Should add several zeros to a single term`() {
        // GIVEN
        val left = termsOf(zero(), zero())
        val right = termsOf(anything())

        // THEN
        assertThat(add(left, right)).isEqualTo(right)
    }

    @Test
    fun `Should add several zeros to several terms`() {
        // GIVEN
        val left = termsOf(zero(), zero())
        val right = termsOf(anything(), anything())

        // THEN
        assertThat(add(left, right)).isEqualTo(right)
    }

    private fun zero(): Value = mock {
        on { isZero } doReturn true
    }

    private fun anything(): Term = mock()

    private fun add(left: Terms, right: Terms) =
            ZeroAndAnythingAddition.execute(left, right)
}