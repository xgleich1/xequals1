package com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class EntireSideWithdrawalTest {
    @Test
    fun `Should withdraw the entire source side by subtracting it`() {
        // GIVEN
        val source = termsOf(anything())

        val sourceSide = source.single

        // WHEN
        val newSourceSide = withdraw(source, sourceSide)

        // THEN
        assertThat(newSourceSide).isEqualTo(PositiveValue(0))
    }

    private fun anything(): Term = mock()

    private fun withdraw(source: Terms, sourceSide: Term) =
            EntireSideWithdrawal.withdraw(source, sourceSide)
}