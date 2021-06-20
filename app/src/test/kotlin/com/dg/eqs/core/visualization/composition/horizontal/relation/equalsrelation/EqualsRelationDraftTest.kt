package com.dg.eqs.core.visualization.composition.horizontal.relation.equalsrelation

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.visualization.symbolization.text.sign.EqualsSign
import com.dg.eqs.classes.pencil
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class EqualsRelationDraftTest {
    @Test
    fun `A equals relation draft has no padding on its sides`() {
        // GIVEN
        val left: TermDraft = mock { on { firstX } doReturn 10; on { finalX } doReturn 30 }
        val right: TermDraft = mock { on { firstX } doReturn 45; on { finalX } doReturn 65 }
        val sign: EqualsSign = mock { on { firstX } doReturn 30; on { finalX } doReturn 45 }
        val equalsRelationDraft = equalsRelationDraft(left = left, right = right, sign = sign)

        // WHEN
        equalsRelationDraft.draft(pencil(1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        assertThat(equalsRelationDraft.firstX).isEqualTo(10)
        assertThat(equalsRelationDraft.finalX).isEqualTo(65)
        assertThat(equalsRelationDraft.width).isEqualTo(65 - 10)
    }

    @Test
    fun `A equals relation draft does not adjust the width of its parts`() {
        // GIVEN
        val left: TermDraft = mock { on { width } doReturn 40 }
        val right: TermDraft = mock { on { width } doReturn 40 }
        val sign: EqualsSign = mock { on { width } doReturn 15 }
        val equalsRelationDraft = equalsRelationDraft(left = left, right = right, sign = sign)

        // WHEN
        equalsRelationDraft.draft(pencil(1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(left).width = 40
        verify(right).width = 40
        verify(sign).width = 15
    }

    @Test
    fun `A equals relation draft does not adjust the height of its parts`() {
        // GIVEN
        val left: TermDraft = mock { on { height } doReturn 40 }
        val right: TermDraft = mock { on { height } doReturn 40 }
        val sign: EqualsSign = mock { on { height } doReturn 40 }
        val equalsRelationDraft = equalsRelationDraft(left = left, right = right, sign = sign)

        // WHEN
        equalsRelationDraft.draft(pencil(1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(left).height = 40
        verify(right).height = 40
        verify(sign).height = 40
    }

    private fun equalsRelationDraft(
            origin: EqualsRelation = mock(),
            left: TermDraft = mock(),
            right: TermDraft = mock(),
            sign: EqualsSign = mock()): EqualsRelationDraft {

        return EqualsRelationDraft(origin, left, right, sign)
    }
}