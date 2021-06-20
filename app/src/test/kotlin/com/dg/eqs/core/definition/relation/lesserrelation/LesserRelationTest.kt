package com.dg.eqs.core.definition.relation.lesserrelation

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class LesserRelationTest {
    @Test
    fun `Should convert a lesser relation to a string`() {
        // GIVEN
        val left: Term = mock { on { toString() } doReturn "x" }
        val right: Term = mock { on { toString() } doReturn "1" }
        val lesserRelation = LesserRelation(left, right)

        // THEN
        assertThat(lesserRelation).hasToString("<[x, 1]")
    }

    @Test
    fun `A lesser relation can remove a term from itself or its sides`() {
        // GIVEN
        val left: Term = mock()
        val right: Term = mock()
        val lesserRelation = LesserRelation(left, right)

        // THEN
        val expectedResult = LesserRelation(left, PositiveValue(0))

        assertThat(lesserRelation.remove(right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A lesser relation can replace a term in itself or in its sides`() {
        // GIVEN
        val new: Term = mock()

        val left: Term = mock()
        val right: Term = mock()
        val lesserRelation = LesserRelation(left, right)

        // THEN
        val expectedResult = LesserRelation(left, new)

        assertThat(lesserRelation.replace(right, new)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should recreate a lesser relation with the new sides`() {
        // GIVEN
        val lesserRelation = LesserRelation(mock(), mock())

        val newLeft: Term = mock()
        val newRight: Term = mock()

        // THEN
        val expectedRecreation = LesserRelation(newLeft, newRight)

        assertThat(lesserRelation.recreate(newLeft, newRight)).isEqualTo(expectedRecreation)
    }
}