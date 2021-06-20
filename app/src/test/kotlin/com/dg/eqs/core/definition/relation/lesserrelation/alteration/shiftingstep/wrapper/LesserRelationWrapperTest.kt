package com.dg.eqs.core.definition.relation.lesserrelation.alteration.shiftingstep.wrapper

import com.dg.eqs.core.definition.relation.lesserrelation.LesserRelation
import com.dg.eqs.core.definition.term.Term
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class LesserRelationWrapperTest {
    @Test
    fun `Should wrap the new sides into a lesser relation`() {
        // GIVEN
        val newLeft: Term = mock()
        val newRight: Term = mock()

        // THEN
        assertThat(wrap(newLeft, newRight))
                .isEqualTo(LesserRelation(newLeft, newRight))
    }

    private fun wrap(newLeft: Term, newRight: Term) =
            LesserRelationWrapper.wrap(newLeft, newRight)
}