package com.dg.eqs.core.definition.term.operation.alteration

import com.dg.eqs.asserts.isContentEqualByIdentityTo
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.Operation
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class DefaultOperandsReplacingTest {
    private lateinit var replacing: DefaultOperandsReplacing


    @Before
    fun setUp() {
        replacing = DefaultOperandsReplacing()
    }

    @Test
    fun `Should compare two equal default operands replacings`() {
        // GIVEN
        val replacingA = DefaultOperandsReplacing()
        val replacingB = DefaultOperandsReplacing()

        // THEN
        assertThat(replacingA).isEqualTo(replacingB)
    }

    @Test
    fun `Should replace an operand by identity`() {
        // GIVEN
        val operandA = term()
        val operandB = term()
        val operandC = term()
        val operands = termsOf(operandA, operandB, operandC)

        val new = operation(term(), term())

        // WHEN
        val replacingResult = replacing.replace(operands, operandB, new)

        // THEN
        val expectedResult = termsOf(operandA, new, operandC)

        assertThat(replacingResult).isContentEqualByIdentityTo(expectedResult)
    }

    @Test
    fun `Should replace a term in an operand`() {
        // GIVEN
        val innerTermA = term()
        val innerTermB = term()

        val operandA = term()
        val operandB = operation(innerTermA, innerTermB)
        val operandC = term()
        val operands = termsOf(operandA, operandB, operandC)

        val new = operation(term(), term())

        // THEN
        val expectedResult = termsOf(operandA, operation(innerTermA, new), operandC)

        assertThat(replacing.replace(operands, innerTermB, new)).isEqualTo(expectedResult)
    }

    private fun term() = TestTerm()

    private fun operation(vararg operands: Term) =
            TestOperation(termsOf(*operands))

    private class TestTerm : Term() {
        override val isNegative get() = TODO("not implemented")


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
}