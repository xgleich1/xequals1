package com.dg.eqs.core.definition.relation.greaterrelation

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class GreaterRelationTest {
    @Test
    fun `Should convert a greater relation to a string`() {
        // GIVEN
        val left: Term = mock { on { toString() } doReturn "x" }
        val right: Term = mock { on { toString() } doReturn "1" }
        val greaterRelation = GreaterRelation(left, right)

        // THEN
        assertThat(greaterRelation).hasToString(">[x, 1]")
    }

    @Test
    fun `A greater relation can remove a term from itself or its sides`() {
        // GIVEN
        val left: Term = mock()
        val right: Term = mock()
        val greaterRelation = GreaterRelation(left, right)

        // THEN
        val expectedResult = GreaterRelation(left, PositiveValue(0))

        assertThat(greaterRelation.remove(right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A greater relation can replace a term in itself or in its sides`() {
        // GIVEN
        val new: Term = mock()

        val left: Term = mock()
        val right: Term = mock()
        val greaterRelation = GreaterRelation(left, right)

        // THEN
        val expectedResult = GreaterRelation(left, new)

        assertThat(greaterRelation.replace(right, new)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should recreate a greater relation with the new sides`() {
        // GIVEN
        val greaterRelation = GreaterRelation(mock(), mock())

        val newLeft: Term = mock()
        val newRight: Term = mock()

        // THEN
        val expectedRecreation = GreaterRelation(newLeft, newRight)

        assertThat(greaterRelation.recreate(newLeft, newRight)).isEqualTo(expectedRecreation)
    }
}