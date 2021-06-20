package com.dg.eqs.core.definition.relation.greaterrelation.alteration.shiftingstep.wrapper

import com.dg.eqs.core.definition.relation.greaterrelation.GreaterRelation
import com.dg.eqs.core.definition.term.Term
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class GreaterRelationWrapperTest {
    @Test
    fun `Should wrap the new sides into a greater relation`() {
        // GIVEN
        val newLeft: Term = mock()
        val newRight: Term = mock()

        // THEN
        assertThat(wrap(newLeft, newRight))
                .isEqualTo(GreaterRelation(newLeft, newRight))
    }

    private fun wrap(newLeft: Term, newRight: Term) =
            GreaterRelationWrapper.wrap(newLeft, newRight)
}