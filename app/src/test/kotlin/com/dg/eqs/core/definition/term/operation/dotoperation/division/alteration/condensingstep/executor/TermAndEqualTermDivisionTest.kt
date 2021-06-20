package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.classes.term
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class TermAndEqualTermDivisionTest {
    @Test
    fun `Should divide a term through its equal`() {
        // GIVEN
        val numerator = term()
        val denominator = term()

        // THEN
        val expectedResult = termsOf(PositiveValue(1))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    private fun divide(numerator: Term, denominator: Term) =
            TermAndEqualTermDivision.execute(numerator, denominator)
}