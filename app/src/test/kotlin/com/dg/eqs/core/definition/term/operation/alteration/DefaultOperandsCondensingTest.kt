package com.dg.eqs.core.definition.term.operation.alteration

import com.dg.eqs.base.abbreviation.AnyCondensingStep
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DefaultOperandsCondensingTest {
    @Mock
    private lateinit var stepA: AnyCondensingStep
    @Mock
    private lateinit var stepB: AnyCondensingStep

    private lateinit var condensing: DefaultOperandsCondensing


    @Before
    fun setUp() {
        condensing = DefaultOperandsCondensing(stepA, stepB)
    }

    @Test
    fun `Should compare two equal default operands condensings`() {
        // GIVEN
        val condensingA = DefaultOperandsCondensing(stepA, stepB)
        val condensingB = DefaultOperandsCondensing(stepA, stepB)

        // THEN
        assertThat(condensingA).isEqualTo(condensingB)
    }

    @Test
    fun `Should return the operands when they are already condensed`() {
        // GIVEN
        val operands = termsOf(term(), term())

        whenever(stepA.isApplicable(operands)).thenReturn(false)
        whenever(stepB.isApplicable(operands)).thenReturn(false)

        // THEN
        assertThat(condensing.condense(operands)).isEqualTo(operands)
    }

    @Test
    fun `Should condense the operands by applying the subsequently applicable steps`() {
        // GIVEN
        val operands = termsOf(term(), term(), term(), term())

        val interimResult = termsOf(term(), term(), term())
        val ultimateResult = termsOf(term(), term())

        whenever(stepA.isApplicable(operands)).thenReturn(true)
        whenever(stepA.apply(operands)).thenReturn(interimResult)

        whenever(stepB.isApplicable(interimResult)).thenReturn(true)
        whenever(stepB.apply(interimResult)).thenReturn(ultimateResult)

        // THEN
        assertThat(condensing.condense(operands)).isEqualTo(ultimateResult)
    }

    @Test
    fun `Should not find the next applicable step once the operands are condensed into a single term`() {
        // GIVEN
        val operands = termsOf(term(), term())

        val singleTermResult = termsOf(term())

        whenever(stepA.isApplicable(operands)).thenReturn(true)
        whenever(stepA.apply(operands)).thenReturn(singleTermResult)

        // WHEN
        condensing.condense(operands)

        // THEN
        verify(stepA, never()).isApplicable(singleTermResult)
        verify(stepB, never()).isApplicable(singleTermResult)
    }

    @Test(expected = IllegalStateException::class)
    fun `Should throw an exception when there's no step to condense source & target inside the operands`() {
        // GIVEN
        val operands: Terms = mock()

        val source: Terms = mock()
        val target: Terms = mock()

        whenever(stepA.isApplicable(operands, source, target)).thenReturn(false)
        whenever(stepB.isApplicable(operands, source, target)).thenReturn(false)

        // WHEN
        condensing.condense(operands, source, target)
    }

    @Test
    fun `Should condense source and target inside the operands by applying the applicable step`() {
        // GIVEN
        val operands: Terms = mock()

        val source: Terms = mock()
        val target: Terms = mock()

        val condensingResult: Terms = mock()

        whenever(stepB.isApplicable(operands, source, target)).thenReturn(true)
        whenever(stepB.apply(operands, source, target)).thenReturn(condensingResult)

        // THEN
        assertThat(condensing.condense(operands, source, target)).isEqualTo(condensingResult)
    }

    private fun term(): Term = mock()
}