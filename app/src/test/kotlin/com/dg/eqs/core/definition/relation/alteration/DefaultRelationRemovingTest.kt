package com.dg.eqs.core.definition.relation.alteration

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.definition.term.operation.alteration.OperationAlteration
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class DefaultRelationRemovingTest {
    private lateinit var removing: DefaultRelationRemoving


    @Before
    fun setUp() {
        removing = DefaultRelationRemoving()
    }

    @Test
    fun `Should compare two equal default relation removings`() {
        // GIVEN
        val removingA = DefaultRelationRemoving()
        val removingB = DefaultRelationRemoving()

        // THEN
        assertThat(removingA).isEqualTo(removingB)
    }

    @Test
    fun `Should remove the left side of an relation by identity`() {
        // GIVEN
        val left = termA()
        val right = termA()
        val relation = relation(left, right)

        // THEN
        val expectedResult = relation(PositiveValue(0), right)

        assertThat(removing.remove(relation, left)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should remove the right side of an relation by identity`() {
        // GIVEN
        val left = termA()
        val right = termA()
        val relation = relation(left, right)

        // THEN
        val expectedResult = relation(left, PositiveValue(0))

        assertThat(removing.remove(relation, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should remove a term in the left side of an relation`() {
        // GIVEN
        val innerTermA = termA()
        val innerTermB = termB()

        val left = operation(innerTermA, innerTermB)
        val right = termB()
        val relation = relation(left, right)

        // THEN
        val expectedResult = relation(innerTermA, right)

        assertThat(removing.remove(relation, innerTermB)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should remove a term in the right side of an relation`() {
        // GIVEN
        val innerTermA = termA()
        val innerTermB = termB()

        val left = termA()
        val right = operation(innerTermA, innerTermB)
        val relation = relation(left, right)

        // THEN
        val expectedResult = relation(left, innerTermB)

        assertThat(removing.remove(relation, innerTermA)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should not try to remove in the left side when the right side is removed`() {
        // GIVEN
        val left: Term = mock()
        val right: Term = mock()
        val relation = relation(left, right)

        // WHEN
        removing.remove(relation, right)

        verify(left, never()).remove(any())
    }

    @Test
    fun `Should not try to remove in the right side when the left side is removed`() {
        // GIVEN
        val left: Term = mock()
        val right: Term = mock()
        val relation = relation(left, right)

        // WHEN
        removing.remove(relation, left)

        verify(right, never()).remove(any())
    }

    private fun termA() = TestTerm(true)

    private fun termB() = TestTerm(false)

    private fun operation(vararg operands: Term) =
            TestOperation(termsOf(*operands))

    private fun relation(left: Term, right: Term) =
            TestRelation(left, right)

    private class TestTerm(override val isNegative: Boolean) : Term() {
        override fun isSolved() = TODO("not implemented")

        override fun isConstant() = TODO("not implemented")

        override fun remove(term: Term) = this

        override fun replace(old: Term, new: Term) = TODO("not implemented")

        override fun invert() = TODO("not implemented")

        override fun copy() = TODO("not implemented")

        override fun resolve() = TODO("not implemented")
    }

    private class TestOperation(operands: Terms) : Operation(operands) {
        override val isNegative = false

        override val alteration = OperationAlteration()


        override fun invert() = TODO("not implemented")

        override fun recreate(newOperands: Terms) = TODO("not implemented")

        override fun applySign() = TODO("not implemented")
    }

    private class TestRelation(left: Term, right: Term) : Relation(left, right) {
        override val alteration get() = TODO("not implemented")


        override fun recreate(newLeft: Term, newRight: Term) = TestRelation(newLeft, newRight)
    }
}