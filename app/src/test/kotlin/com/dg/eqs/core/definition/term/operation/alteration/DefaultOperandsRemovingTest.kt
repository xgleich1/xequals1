package com.dg.eqs.core.definition.term.operation.alteration

import com.dg.eqs.asserts.isContentEqualByIdentityTo
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.Operation
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class DefaultOperandsRemovingTest {
    private lateinit var removing: DefaultOperandsRemoving


    @Before
    fun setUp() {
        removing = DefaultOperandsRemoving()
    }

    @Test
    fun `Should compare two equal default operands removings`() {
        // GIVEN
        val removingA = DefaultOperandsRemoving()
        val removingB = DefaultOperandsRemoving()

        // THEN
        assertThat(removingA).isEqualTo(removingB)
    }

    @Test
    fun `Should remove an operand by identity`() {
        // GIVEN
        val operandA = term()
        val operandB = term()
        val operandC = term()
        val operands = termsOf(operandA, operandB, operandC)

        // WHEN
        val removingResult = removing.remove(operands, operandB)

        // THEN
        val expectedResult = termsOf(operandA, operandC)

        assertThat(removingResult).isContentEqualByIdentityTo(expectedResult)
    }

    @Test
    fun `Should remove a term from an operand`() {
        // GIVEN
        val innerTermA = term()
        val innerTermB = term()

        val operandA = term()
        val operandB = operation(innerTermA, innerTermB)
        val operandC = term()
        val operands = termsOf(operandA, operandB, operandC)

        // WHEN
        val removingResult = removing.remove(operands, innerTermB)

        // THEN
        val expectedResult = termsOf(operandA, innerTermA, operandC)

        assertThat(removingResult).isContentEqualByIdentityTo(expectedResult)
    }

    private fun term() = object : Term() {
        override val isNegative get() = TODO("not implemented")


        override fun isSolved() = TODO("not implemented")

        override fun isConstant() = TODO("not implemented")

        override fun remove(term: Term) = this

        override fun replace(old: Term, new: Term) = TODO("not implemented")

        override fun invert() = TODO("not implemented")

        override fun copy() = TODO("not implemented")

        override fun resolve() = TODO("not implemented")
    }

    private fun operation(vararg operands: Term) = object
        : Operation(termsOf(*operands)) {

        override val isNegative = false

        override val alteration = OperationAlteration()


        override fun invert() = TODO("not implemented")

        override fun recreate(newOperands: Terms) = TODO("not implemented")

        override fun applySign() = TODO("not implemented")
    }
}