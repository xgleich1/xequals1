package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.classes.term
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class TermAndEqualTermAdditionTest {
    @Test
    fun `Should add a term to its equal`() {
        // GIVEN
        val left = term()
        val right = term()

        // THEN
        val expectedResult = termsOf(PositiveProduct(
                PositiveValue(2),
                right))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    private fun add(left: Term, right: Term) =
            TermAndEqualTermAddition.execute(left, right)
}