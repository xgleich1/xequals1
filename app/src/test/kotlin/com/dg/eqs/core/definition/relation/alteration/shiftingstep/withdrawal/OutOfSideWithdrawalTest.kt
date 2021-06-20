package com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class OutOfSideWithdrawalTest {
    @Test
    fun `Should withdraw the source from its side by removing it`() {
        // GIVEN
        val source = termsOf(anything(), anything())

        val sourceSide = anything()

        val sourceSideWithoutFirstSource = anything()
        val sourceSideWithoutSecondSource = anything()

        whenever(sourceSide.remove(source.first))
                .thenReturn(sourceSideWithoutFirstSource)

        whenever(sourceSideWithoutFirstSource.remove(source.second))
                .thenReturn(sourceSideWithoutSecondSource)

        // WHEN
        val newSourceSide = withdraw(source, sourceSide)

        // THEN
        assertThat(newSourceSide).isEqualTo(sourceSideWithoutSecondSource)
    }

    private fun anything(): Term = mock()

    private fun withdraw(source: Terms, sourceSide: Term) =
            OutOfSideWithdrawal.withdraw(source, sourceSide)
}