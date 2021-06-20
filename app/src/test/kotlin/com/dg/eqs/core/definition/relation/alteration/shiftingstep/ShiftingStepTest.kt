package com.dg.eqs.core.definition.relation.alteration.shiftingstep

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.classes.negativeTerm
import com.dg.eqs.classes.positiveTerm
import com.dg.eqs.classes.relation
import org.mockito.kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ShiftingStepTest {
    @Mock
    private lateinit var matcher: ShiftingMatcher
    @Mock
    private lateinit var mapper: ShiftingMapper<Term, Term>
    @Mock
    private lateinit var withdrawal: ShiftingWithdrawal<Term>
    @Mock
    private lateinit var integrator: ShiftingIntegrator<Term>
    @Mock
    private lateinit var wrapper: ShiftingWrapper

    private lateinit var step: ShiftingStep<Term, Term>


    @Before
    fun setUp() {
        step = shiftingStep(
                matcher,
                mapper,
                withdrawal,
                integrator,
                wrapper)
    }

    @Test
    fun `Should compare two equal shifting steps`() {
        // GIVEN
        val stepA = shiftingStep(
                matcher,
                mapper,
                withdrawal,
                integrator,
                wrapper)

        val stepB = shiftingStep(
                matcher,
                mapper,
                withdrawal,
                integrator,
                wrapper)

        // THEN
        assertThat(stepA).isEqualTo(stepB)
    }

    @Test
    fun `A shifting step is applicable to a left to right shifting with a matching source`() {
        // GIVEN
        val left = termA()
        val right = termB()
        val relation = relation(left, right)
        val source: Terms = mock()

        whenever(matcher.matches(left, right, source)).thenReturn(true)

        // THEN
        assertTrue(step.isApplicable(relation, source, right))
    }

    @Test
    fun `A shifting step is applicable to a right to left shifting with a matching source`() {
        // GIVEN
        val left = termA()
        val right = termB()
        val relation = relation(left, right)
        val source: Terms = mock()

        whenever(matcher.matches(right, left, source)).thenReturn(true)

        // THEN
        assertTrue(step.isApplicable(relation, source, left))
    }

    @Test
    fun `A shifting step is'nt applicable to a left to right shifting when the source does'nt match`() {
        // GIVEN
        val left = termA()
        val right = termB()
        val relation = relation(left, right)
        val source: Terms = mock()

        whenever(matcher.matches(left, right, source)).thenReturn(false)

        // THEN
        assertFalse(step.isApplicable(relation, source, right))
    }

    @Test
    fun `A shifting step is'nt applicable to a right to left shifting when the source does'nt match`() {
        // GIVEN
        val left = termA()
        val right = termB()
        val relation = relation(left, right)
        val source: Terms = mock()

        whenever(matcher.matches(right, left, source)).thenReturn(false)

        // THEN
        assertFalse(step.isApplicable(relation, source, left))
    }

    @Test
    fun `A shifting step uses identity to provide the side of the source to its shifting matcher`() {
        // GIVEN
        val left = termA()
        val right = termA()
        val relation = relation(left, right)
        val source: Terms = mock()

        // WHEN
        step.isApplicable(relation, source, left)

        // THEN
        argumentCaptor<Term> {
            verify(matcher).matches(capture(), any(), any())

            assertThat(lastValue).isSameAs(right)
        }
    }

    @Test
    fun `Should apply a shifting step to a previously matched left to right shifting`() {
        // GIVEN
        val left = termA()
        val right = termB()
        val relation = relation(left, right)
        val source: Terms = mock()

        val mappedSourceSide: Term = mock()
        val mappedTargetSide: Term = mock()

        val newSourceSide: Term = mock()
        val newTargetSide: Term = mock()

        val shiftingResult: Relation = mock()

        whenever(mapper.map(left, right)).thenReturn(mappedSourceSide and mappedTargetSide)

        whenever(withdrawal.withdraw(source, mappedSourceSide)).thenReturn(newSourceSide)

        whenever(integrator.integrate(source, mappedTargetSide)).thenReturn(newTargetSide)

        whenever(wrapper.wrap(newSourceSide, newTargetSide)).thenReturn(shiftingResult)

        // THEN
        assertThat(step.apply(relation, source, right)).isEqualTo(shiftingResult)
    }

    @Test
    fun `Should apply a shifting step to a previously matched right to left shifting`() {
        // GIVEN
        val left = termA()
        val right = termB()
        val relation = relation(left, right)
        val source: Terms = mock()

        val mappedSourceSide: Term = mock()
        val mappedTargetSide: Term = mock()

        val newSourceSide: Term = mock()
        val newTargetSide: Term = mock()

        val shiftingResult: Relation = mock()

        whenever(mapper.map(right, left)).thenReturn(mappedSourceSide and mappedTargetSide)

        whenever(withdrawal.withdraw(source, mappedSourceSide)).thenReturn(newSourceSide)

        whenever(integrator.integrate(source, mappedTargetSide)).thenReturn(newTargetSide)

        whenever(wrapper.wrap(newTargetSide, newSourceSide)).thenReturn(shiftingResult)

        // THEN
        assertThat(step.apply(relation, source, left)).isEqualTo(shiftingResult)
    }

    @Test
    fun `A shifting step uses identity to decide the direction of its shifting`() {
        // GIVEN
        val left = termA()
        val right = termA()
        val relation = relation(left, right)
        val source: Terms = mock()

        whenever(mapper.map(any(), any())).thenReturn(mock())

        // WHEN
        step.apply(relation, source, left)

        // THEN
        val sourceSideCaptor = argumentCaptor<Term>()
        val targetSideCaptor = argumentCaptor<Term>()

        verify(mapper).map(
                sourceSideCaptor.capture(),
                targetSideCaptor.capture())

        assertThat(sourceSideCaptor.lastValue).isSameAs(right)
        assertThat(targetSideCaptor.lastValue).isSameAs(left)
    }

    private fun termA() = positiveTerm()

    private fun termB() = negativeTerm()

    private fun shiftingStep(
            matcher: ShiftingMatcher,
            mapper: ShiftingMapper<Term, Term>,
            withdrawal: ShiftingWithdrawal<Term>,
            integrator: ShiftingIntegrator<Term>,
            wrapper: ShiftingWrapper) = object
        : ShiftingStep<Term, Term>(matcher, mapper, withdrawal, integrator, wrapper) {}
}