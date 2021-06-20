package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration

import com.dg.eqs.asserts.isContentEqualByIdentityTo
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.definition.term.operation.alteration.OperationAlteration
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class DivisionOperandsRemovingTest {
    private lateinit var removing: DivisionOperandsRemoving


    @Before
    fun setUp() {
        removing = DivisionOperandsRemoving()
    }

    @Test
    fun `Should remove the numerator by identity leaving a positive one in its place`() {
        // GIVEN
        val numerator = term()
        val denominator = term()
        val divisionOperands = termsOf(numerator, denominator)

        // THEN
        val expectedResult = termsOf(PositiveValue(1), denominator)

        assertThat(removing.remove(divisionOperands, numerator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should remove the denominator by identity leaving only the numerator behind`() {
        // GIVEN
        val numerator = term()
        val denominator = term()
        val divisionOperands = termsOf(numerator, denominator)

        // WHEN
        val removingResult = removing.remove(divisionOperands, denominator)

        // THEN
        val expectedResult = termsOf(numerator)

        assertThat(removingResult).isContentEqualByIdentityTo(expectedResult)
    }

    @Test
    fun `Should remove a term from the numerator`() {
        // GIVEN
        val innerTermA = term()
        val innerTermB = term()

        val numerator = operation(innerTermA, innerTermB)
        val denominator = term()
        val divisionOperands = termsOf(numerator, denominator)

        // WHEN
        val removingResult = removing.remove(divisionOperands, innerTermB)

        // THEN
        val expectedResult = termsOf(innerTermA, denominator)

        assertThat(removingResult).isContentEqualByIdentityTo(expectedResult)
    }

    @Test
    fun `Should remove a term from the denominator`() {
        // GIVEN
        val innerTermA = term()
        val innerTermB = term()

        val numerator = term()
        val denominator = operation(innerTermA, innerTermB)
        val divisionOperands = termsOf(numerator, denominator)

        // WHEN
        val removingResult = removing.remove(divisionOperands, innerTermB)

        // THEN
        val expectedResult = termsOf(numerator, innerTermA)

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