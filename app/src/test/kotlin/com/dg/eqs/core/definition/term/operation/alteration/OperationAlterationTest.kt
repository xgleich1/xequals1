package com.dg.eqs.core.definition.term.operation.alteration

import com.dg.eqs.base.abbreviation.AnyCondensingStep
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.DivisionThroughZeroException
import org.mockito.kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class OperationAlterationTest {
    @Mock
    private lateinit var removing: OperandsRemoving
    @Mock
    private lateinit var replacing: OperandsReplacing
    @Mock
    private lateinit var condensing: OperandsCondensing
    @Mock
    private lateinit var packaging: OperandsPackaging
    @InjectMocks
    private lateinit var alteration: OperationAlteration


    @Test
    fun `Should construct a operation alteration for the default usage`() {
        // GIVEN
        val stepA: AnyCondensingStep = mock()
        val stepB: AnyCondensingStep = mock()

        // WHEN
        val alteration = OperationAlteration(stepA, stepB)

        // THEN
        val expectedAlteration = OperationAlteration(
                DefaultOperandsRemoving(),
                DefaultOperandsReplacing(),
                DefaultOperandsCondensing(stepA, stepB),
                DefaultOperandsPackaging())

        assertThat(alteration).isEqualTo(expectedAlteration)
    }

    @Test
    fun `Should compare two equal operation alterations`() {
        // GIVEN
        val alterationA = OperationAlteration(
                removing,
                replacing,
                condensing,
                packaging)

        val alterationB = OperationAlteration(
                removing,
                replacing,
                condensing,
                packaging)

        // THEN
        assertThat(alterationA).isEqualTo(alterationB)
    }

    @Test
    fun `Should alter a operation by removing a term from or in its operands and then packaging the result`() {
        // GIVEN
        val term: Term = mock()

        val operands: Terms = mock()
        val operation = operation(operands)

        val removingResult: Terms = mock()
        val packagingResult: Term = mock()

        whenever(removing.remove(operands, term))
                .thenReturn(removingResult)

        whenever(packaging.pack(operation, removingResult))
                .thenReturn(packagingResult)

        // THEN
        assertThat(alteration.remove(operation, term)).isEqualTo(packagingResult)
    }

    @Test
    fun `Should alter a operation by replacing a term from or in its operands and then packaging the result`() {
        // GIVEN
        val old: Term = mock()
        val new: Term = mock()

        val operands: Terms = mock()
        val operation = operation(operands)

        val replacingResult: Terms = mock()
        val packagingResult: Term = mock()

        whenever(replacing.replace(operands, old, new))
                .thenReturn(replacingResult)

        whenever(packaging.pack(operation, replacingResult))
                .thenReturn(packagingResult)

        // THEN
        assertThat(alteration.replace(operation, old, new)).isEqualTo(packagingResult)
    }

    @Test(expected = DivisionThroughZeroException::class)
    fun `Should expose the exception when condensing a operations operands throws one during resolving`() {
        // GIVEN
        val operandA = resolvedTerm()
        val operandB = resolvedTerm()
        val operands = termsOf(operandA, operandB)
        val operation = operation(operands)

        val exception: DivisionThroughZeroException = mock()

        whenever(condensing.condense(operands))
                .thenThrow(exception)

        // WHEN
        alteration.resolve(operation)
    }

    @Test
    fun `Should alter a operation by resolving its operands and then packaging the result`() {
        // GIVEN
        val resolvedOperandA = resolvedTerm()
        val resolvedOperandB = resolvedTerm()
        val operandA = resolvableTerm(resolvedOperandA)
        val operandB = resolvableTerm(resolvedOperandB)
        val operands = termsOf(operandA, operandB)
        val operation = operation(operands)

        val resolvingResult = termsOf(resolvedOperandA, resolvedOperandB)
        val condensingResult = termsOf(resolvedTerm(), resolvedTerm())
        val packagingResult: Term = mock()

        whenever(condensing.condense(resolvingResult))
                .thenReturn(condensingResult)

        whenever(condensing.condense(condensingResult))
                .thenReturn(condensingResult)

        whenever(packaging.pack(operation, condensingResult))
                .thenReturn(packagingResult)

        // THEN
        assertThat(alteration.resolve(operation)).isEqualTo(packagingResult)
    }

    @Test
    fun `Should stop condensing once a operations operands are condensed into a single term during resolving`() {
        // GIVEN
        val operandA = resolvedTerm()
        val operandB = resolvedTerm()
        val operands = termsOf(operandA, operandB)
        val operation = operation(operands)

        val condensingResult = termsOf(resolvedTerm())
        val packagingResult: Term = mock()

        whenever(condensing.condense(operands))
                .thenReturn(condensingResult)

        whenever(packaging.pack(operation, condensingResult))
                .thenReturn(packagingResult)

        // WHEN
        alteration.resolve(operation)

        // THEN
        verify(condensing, never()).condense(condensingResult)
    }

    @Test
    fun `Should resolve the single term once a operations operands are condensed into it during resolving`() {
        // GIVEN
        val operandA = resolvedTerm()
        val operandB = resolvedTerm()
        val operands = termsOf(operandA, operandB)
        val operation = operation(operands)

        val resolvedSingleTerm = resolvedTerm()
        val singleTerm = resolvableTerm(resolvedSingleTerm)

        val condensingResult = termsOf(singleTerm)
        val resolvingResult = termsOf(resolvedSingleTerm)
        val packagingResult: Term = mock()

        whenever(condensing.condense(operands))
                .thenReturn(condensingResult)

        whenever(packaging.pack(operation, resolvingResult))
                .thenReturn(packagingResult)

        // THEN
        assertThat(alteration.resolve(operation)).isEqualTo(packagingResult)
    }

    @Test(expected = DivisionThroughZeroException::class)
    fun `Should expose the exception when completely condensing a operations operands throws one`() {
        // GIVEN
        val operands: Terms = mock()
        val operation = operation(operands)

        val exception: DivisionThroughZeroException = mock()

        whenever(condensing.condense(operands))
                .thenThrow(exception)

        // WHEN
        alteration.condense(operation)
    }

    @Test
    fun `Should alter a operation by completely condensing its operands and then packaging the result`() {
        // GIVEN
        val operands: Terms = mock()
        val operation = operation(operands)

        val condensingResult: Terms = mock()
        val packagingResult: Term = mock()

        whenever(condensing.condense(operands))
                .thenReturn(condensingResult)

        whenever(packaging.pack(operation, condensingResult))
                .thenReturn(packagingResult)

        // THEN
        assertThat(alteration.condense(operation)).isEqualTo(packagingResult)
    }

    @Test(expected = DivisionThroughZeroException::class)
    fun `Should expose the exception when partially condensing a operations operands throws one`() {
        // GIVEN
        val operands: Terms = mock()
        val operation = operation(operands)

        val source: Terms = mock()
        val target: Terms = mock()

        val exception: DivisionThroughZeroException = mock()

        whenever(condensing.condense(operands, source, target))
                .thenThrow(exception)

        // WHEN
        alteration.condense(operation, source, target)
    }

    @Test
    fun `Should alter a operation by partially condensing its operands and then packaging the result`() {
        // GIVEN
        val operands: Terms = mock()
        val operation = operation(operands)

        val source: Terms = mock()
        val target: Terms = mock()

        val condensingResult: Terms = mock()
        val packagingResult: Term = mock()

        whenever(condensing.condense(operands, source, target))
                .thenReturn(condensingResult)

        whenever(packaging.pack(operation, condensingResult))
                .thenReturn(packagingResult)

        // THEN
        assertThat(alteration.condense(operation, source, target)).isEqualTo(packagingResult)
    }

    private fun resolvedTerm(): Term = mock { on { resolve() } doReturn it }

    private fun resolvableTerm(result: Term): Term = mock { on { resolve() } doReturn result }

    private fun operation(operands: Terms): Operation = mock { on { it.operands } doReturn operands }
}