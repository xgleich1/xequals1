package com.dg.eqs.core.definition.relation.alteration

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.definition.term.operation.alteration.OperationAlteration
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class DefaultRelationReplacingTest {
    private lateinit var replacing: DefaultRelationReplacing


    @Before
    fun setUp() {
        replacing = DefaultRelationReplacing()
    }

    @Test
    fun `Should compare two equal default relation replacings`() {
        // GIVEN
        val replacingA = DefaultRelationReplacing()
        val replacingB = DefaultRelationReplacing()

        // THEN
        assertThat(replacingA).isEqualTo(replacingB)
    }

    @Test
    fun `Should replace the left side of an relation by identity`() {
        // GIVEN
        val left = termA()
        val right = termA()
        val relation = relation(left, right)

        val new = operation(termA(), termB())

        // THEN
        val expectedResult = relation(new, right)

        assertThat(replacing.replace(relation, left, new)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should replace the right side of an relation by identity`() {
        // GIVEN
        val left = termA()
        val right = termA()
        val relation = relation(left, right)

        val new = operation(termA(), termB())

        // THEN
        val expectedResult = relation(left, new)

        assertThat(replacing.replace(relation, right, new)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should replace a term in the left side of an relation`() {
        // GIVEN
        val innerTermA = termA()
        val innerTermB = termB()

        val left = operation(innerTermA, innerTermB)
        val right = termB()
        val relation = relation(left, right)

        val new = operation(termA(), termB())

        // THEN
        val expectedResult = relation(operation(innerTermA, new), right)

        assertThat(replacing.replace(relation, innerTermB, new)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should replace a term in the right side of an relation`() {
        // GIVEN
        val innerTermA = termA()
        val innerTermB = termB()

        val left = termA()
        val right = operation(innerTermA, innerTermB)
        val relation = relation(left, right)

        val new = operation(termA(), termB())

        // THEN
        val expectedResult = relation(left, operation(new, innerTermB))

        assertThat(replacing.replace(relation, innerTermA, new)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should not try to replace in the left side when the right side is replaced`() {
        // GIVEN
        val left: Term = mock()
        val right: Term = mock()
        val relation = relation(left, right)

        // WHEN
        replacing.replace(relation, right, mock())

        verify(left, never()).replace(any(), any())
    }

    @Test
    fun `Should not try to replace in the right side when the left side is replaced`() {
        // GIVEN
        val left: Term = mock()
        val right: Term = mock()
        val relation = relation(left, right)

        // WHEN
        replacing.replace(relation, left, mock())

        verify(right, never()).replace(any(), any())
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

        override fun remove(term: Term) = TODO("not implemented")

        override fun replace(old: Term, new: Term) = this

        override fun invert() = TODO("not implemented")

        override fun copy() = TODO("not implemented")

        override fun resolve() = TODO("not implemented")
    }

    private class TestOperation(operands: Terms) : Operation(operands) {
        override val isNegative = false

        override val alteration = OperationAlteration()


        override fun invert() = TODO("not implemented")

        override fun recreate(newOperands: Terms) = TestOperation(newOperands)

        override fun applySign() = TODO("not implemented")
    }

    private class TestRelation(left: Term, right: Term) : Relation(left, right) {
        override val alteration get() = TODO("not implemented")


        override fun recreate(newLeft: Term, newRight: Term) = TestRelation(newLeft, newRight)
    }
}