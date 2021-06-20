package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep.wrapper

import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.Term
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class EqualsRelationWrapperTest {
    @Test
    fun `Should wrap the new sides into an equals relation`() {
        // GIVEN
        val newLeft: Term = mock()
        val newRight: Term = mock()

        // THEN
        assertThat(wrap(newLeft, newRight))
                .isEqualTo(EqualsRelation(newLeft, newRight))
    }

    private fun wrap(newLeft: Term, newRight: Term) =
            EqualsRelationWrapper.wrap(newLeft, newRight)
}