package com.dg.eqs.core.definition.relation

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.relation.alteration.RelationAlteration
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.classes.negativeTerm
import com.dg.eqs.classes.positiveTerm
import com.dg.eqs.classes.term
import com.dg.eqs.mocking.lenientMock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class RelationTest {
    @Mock
    private lateinit var alteration: RelationAlteration


    @Test
    fun `Should compare two equal relations`() {
        // GIVEN
        val left = term()
        val right = term()
        val relationA = relation(left, right)
        val relationB = relation(left, right)

        // THEN
        assertThat(relationA).isEqualTo(relationB)
    }

    @Test
    fun `Should convert a relation to a string`() {
        // GIVEN
        val left: Term = mock { on { toString() } doReturn "x" }
        val right: Term = mock { on { toString() } doReturn "1" }
        val relation = relation(left, right)

        // THEN
        assertThat(relation).hasToString("[x, 1]")
    }

    @Test
    fun `A relation is solved when its left is the exempted variable and its right is solved`() {
        // GIVEN
        val left: PositiveVariable = mock()
        val right: Term = mock {
            on { isConstant() } doReturn true
            on { isSolved() } doReturn true
        }
        val relation = relation(left, right)

        // THEN
        assertTrue(relation.isSolved())
    }

    @Test
    fun `A relation is solved when its right is the exempted variable and its left is solved`() {
        // GIVEN
        val left: Term = mock {
            on { isConstant() } doReturn true
            on { isSolved() } doReturn true
        }
        val right: PositiveVariable = mock()
        val relation = relation(left, right)

        // THEN
        assertTrue(relation.isSolved())
    }

    @Test
    fun `A relation isn't solved when its left is the exempted variable but with a minus sign`() {
        // GIVEN
        val left: NegativeVariable = mock()
        val right: Term = lenientMock {
            on { isConstant() } doReturn true
            on { isSolved() } doReturn true
        }
        val relation = relation(left, right)

        // THEN
        assertFalse(relation.isSolved())
    }

    @Test
    fun `A relation isn't solved when its right is the exempted variable but with a minus sign`() {
        // GIVEN
        val left: Term = lenientMock {
            on { isConstant() } doReturn true
            on { isSolved() } doReturn true
        }
        val right: NegativeVariable = mock()
        val relation = relation(left, right)

        // THEN
        assertFalse(relation.isSolved())
    }

    @Test
    fun `A relation isn't solved when its left is the variable but its not exempted`() {
        // GIVEN
        val left: PositiveVariable = mock()
        val right: Term = lenientMock {
            on { isConstant() } doReturn false
            on { isSolved() } doReturn true
        }
        val relation = relation(left, right)

        // THEN
        assertFalse(relation.isSolved())
    }

    @Test
    fun `A relation isn't solved when its right is the variable but its not exempted`() {
        // GIVEN
        val left: Term = lenientMock {
            on { isConstant() } doReturn false
            on { isSolved() } doReturn true
        }
        val right: PositiveVariable = mock()
        val relation = relation(left, right)

        // THEN
        assertFalse(relation.isSolved())
    }

    @Test
    fun `A relation isn't solved when its left is the exempted variable but its right isn't solved`() {
        // GIVEN
        val left: PositiveVariable = mock()
        val right: Term = mock {
            on { isConstant() } doReturn true
            on { isSolved() } doReturn false
        }
        val relation = relation(left, right)

        // THEN
        assertFalse(relation.isSolved())
    }

    @Test
    fun `A relation isn't solved when its right is the exempted variable and its left isn't solved`() {
        // GIVEN
        val left: Term = mock {
            on { isConstant() } doReturn true
            on { isSolved() } doReturn false
        }
        val right: PositiveVariable = mock()
        val relation = relation(left, right)

        // THEN
        assertFalse(relation.isSolved())
    }

    @Test
    fun `A relation can remove a term from itself or its sides`() {
        // GIVEN
        val term = term()

        val relation = relation(term(), term())

        val removingResult = relation(term(), term())

        whenever(alteration.remove(relation, term))
                .thenReturn(removingResult)

        // THEN
        assertThat(relation.remove(term)).isSameAs(removingResult)
    }

    @Test
    fun `A relation can replace a term in itself or in its sides`() {
        // GIVEN
        val old = term()
        val new = term()

        val relation = relation(term(), term())

        val replacingResult = relation(term(), term())

        whenever(alteration.replace(relation, old, new))
                .thenReturn(replacingResult)

        // THEN
        assertThat(relation.replace(old, new)).isSameAs(replacingResult)
    }

    @Test
    fun `A relation can copy itself & its sides`() {
        // GIVEN
        val copyLeft: Term = mock()
        val copyRight: Term = mock()
        val left: Term = mock { on { copy() } doReturn copyLeft }
        val right: Term = mock { on { copy() } doReturn copyRight }
        val relation = relation(left, right)

        // THEN
        val expectedCopy = relation(copyLeft, copyRight)

        assertThat(relation.copy()).isEqualTo(expectedCopy)
    }

    @Test
    fun `Should return true when a relations left side matches the predicate`() {
        // GIVEN
        val left = positiveTerm()
        val right = negativeTerm()
        val relation = relation(left, right)

        // THEN
        assertTrue(relation.any(Term::isPositive))
    }

    @Test
    fun `Should return true when a relations right side matches the predicate`() {
        // GIVEN
        val left = negativeTerm()
        val right = positiveTerm()
        val relation = relation(left, right)

        // THEN
        assertTrue(relation.any(Term::isPositive))
    }

    @Test
    fun `Should return false when neither of a relations sides matches the predicate`() {
        // GIVEN
        val left = negativeTerm()
        val right = negativeTerm()
        val relation = relation(left, right)

        // THEN
        assertFalse(relation.any(Term::isPositive))
    }

    @Test
    fun `Should invoke an action on a relations left and right side`() {
        // GIVEN
        val left: Term = mock()
        val right: Term = mock()
        val relation = relation(left, right)

        val action: (Term) -> Unit = mock()

        // WHEN
        relation.forEach(action)

        // THEN
        verify(action).invoke(left)
        verify(action).invoke(right)
    }

    @Test
    fun `A relation can destructor into its left and right side`() {
        // GIVEN
        val left: Term = mock()
        val right: Term = mock()
        val relation = relation(left, right)

        // WHEN
        val (component1, component2) = relation

        // THEN
        assertThat(component1).isSameAs(left)
        assertThat(component2).isSameAs(right)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `A relation cannot shift the source to the target when the target isn't one of its sides`() {
        // GIVEN
        val left = term()
        val right = term()
        val relation = relation(left, right)

        val source = termsOf(left)
        val target = term()

        // WHEN
        relation.shift(source, target)
    }

    @Test
    fun `A relation can shift the source to the target when the target is one of its sides`() {
        // GIVEN
        val left = term()
        val right = term()
        val relation = relation(left, right)

        val source = termsOf(left)
        val target = right

        val shiftingResult = relation(term(), term())

        whenever(alteration.shift(relation, source, target))
                .thenReturn(shiftingResult)

        // THEN
        assertThat(relation.shift(source, target)).isSameAs(shiftingResult)
    }

    private fun relation(left: Term, right: Term) =
            TestRelation(alteration, left, right)

    private class TestRelation(
            override val alteration: RelationAlteration,
            left: Term,
            right: Term) : Relation(left, right) {

        override fun recreate(newLeft: Term, newRight: Term) =
                TestRelation(alteration, newLeft, newRight)
    }
}