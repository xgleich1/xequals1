package com.dg.eqs.core.visualization.composition.horizontal.relation

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.AnyTextSign
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.visualization.Pencil
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class HorizontalRelationTest {
    @Test
    fun `A horizontal relations left side, sign and right side are ordered in this sequence`() {
        // GIVEN
        val left: TermDraft = mock()
        val right: TermDraft = mock()
        val sign: AnyTextSign = mock()
        val relation = relation(left = left, right = right, sign = sign)

        // THEN
        assertThat(relation.parts).isEqualTo(draftsOf(left, sign, right))
    }

    @Test
    fun `A horizontal relations baseline is the baseline of its text sign`() {
        // GIVEN
        val sign: AnyTextSign = mock { on { baseline } doReturn 100 }
        val relation = relation(sign = sign)

        // THEN
        assertThat(relation.baseline).isEqualTo(100)
    }

    private fun relation(origin: Relation = mock(), left: TermDraft = mock(), right: TermDraft = mock(), sign: AnyTextSign = mock()) =
            TestAnyHorizontalRelation(origin, left, right, sign)

    private class TestAnyHorizontalRelation(origin: Relation, left: TermDraft, right: TermDraft, sign: AnyTextSign)
        : HorizontalRelation<Relation>(origin, left, right, sign) {

        override fun specifyPaddingLeft(pencil: Pencil) = TODO("not implemented")

        override fun specifyPaddingRight(pencil: Pencil) = TODO("not implemented")

        override fun calculatePartWidth(part: AnyDraft) = TODO("not implemented")

        override fun calculatePartHeight(part: AnyDraft) = TODO("not implemented")
    }
}