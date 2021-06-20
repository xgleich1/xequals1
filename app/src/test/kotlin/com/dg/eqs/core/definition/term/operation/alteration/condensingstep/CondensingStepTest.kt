package com.dg.eqs.core.definition.term.operation.alteration.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CondensingStepTest {
    @Mock
    private lateinit var matcher: CondensingMatcher
    @Mock
    private lateinit var extractor: CondensingExtractor
    @Mock
    private lateinit var mapper: CondensingMapper<CondensingSide, CondensingSide>
    @Mock
    private lateinit var executor: CondensingExecutor<CondensingSide, CondensingSide>

    private lateinit var step: CondensingStep<CondensingSide, CondensingSide>


    @Before
    fun setUp() {
        step = condensingStep(matcher, extractor, mapper, executor)
    }

    @Test
    fun `Should compare two equal condensing steps`() {
        // GIVEN
        val stepA = condensingStep(
                matcher,
                extractor,
                mapper,
                executor)

        val stepB = condensingStep(
                matcher,
                extractor,
                mapper,
                executor)

        // THEN
        assertThat(stepA).isEqualTo(stepB)
    }

    @Test
    fun `A condensing step is applicable with operands containing the matched condensing sides`() {
        // GIVEN
        val operands: Terms = mock()

        whenever(matcher.matches(operands)).thenReturn(true)

        // THEN
        assertTrue(step.isApplicable(operands))
    }

    @Test
    fun `A condensing step isn't applicable with operands not containing the matched condensing sides`() {
        // GIVEN
        val operands: Terms = mock()

        whenever(matcher.matches(operands)).thenReturn(false)

        // THEN
        assertFalse(step.isApplicable(operands))
    }

    @Test
    fun `A condensing step is applicable with source and target selecting the matched condensing sides`() {
        // GIVEN
        val left: Term = mock()
        val right: Term = mock()
        val operands = termsOf(left, right)

        val source = termsOf(left)
        val target = termsOf(right)

        whenever(matcher.matches(source, target)).thenReturn(true)

        // THEN
        assertTrue(step.isApplicable(operands, source, target))
    }

    @Test
    fun `A condensing step isn't applicable with source and target not selecting the matched condensing sides`() {
        val left: Term = mock()
        val right: Term = mock()
        val operands = termsOf(left, right)

        val source = termsOf(left)
        val target = termsOf(right)

        whenever(matcher.matches(source, target)).thenReturn(false)

        // THEN
        assertFalse(step.isApplicable(operands, source, target))
    }

    @Test
    fun `A condensing step orders the condensing sides when they are inversely selected by source & target to decide if they match`() {
        // GIVEN
        val left: Term = mock()
        val right: Term = mock()
        val operands = termsOf(left, right)

        val source = termsOf(right)
        val target = termsOf(left)

        // WHEN
        step.isApplicable(operands, source, target)

        // THEN
        verify(matcher).matches(target, source)
    }

    @Test
    fun `Should apply a condensing step to the operands by extracting & mapping & condensing the previously matched sides`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val operandC: Term = mock()
        val operands = termsOf(operandA, operandB, operandC)

        val extractedLeft = termsOf(operandA)
        val extractedRight = termsOf(operandC)

        val mappedLeft: CondensingSide = mock()
        val mappedRight: CondensingSide = mock()

        val condensingResult: Term = mock()

        whenever(extractor.extract(operands))
                .thenReturn(extractedLeft and extractedRight)

        whenever(mapper.map(extractedLeft, extractedRight))
                .thenReturn(mappedLeft and mappedRight)

        whenever(executor.execute(mappedLeft, mappedRight))
                .thenReturn(termsOf(condensingResult))

        // THEN
        assertThat(step.apply(operands)).isEqualTo(termsOf(operandB, condensingResult))
    }

    @Test
    fun `Should apply a condensing step by mapping & condensing source & target which select the previously matched sides`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val operandC: Term = mock()
        val operands = termsOf(operandA, operandB, operandC)

        val source = termsOf(operandA)
        val target = termsOf(operandC)

        val mappedLeft: CondensingSide = mock()
        val mappedRight: CondensingSide = mock()

        val condensingResult: Term = mock()

        whenever(mapper.map(source, target))
                .thenReturn(mappedLeft and mappedRight)

        whenever(executor.execute(mappedLeft, mappedRight))
                .thenReturn(termsOf(condensingResult))

        // THEN
        assertThat(step.apply(operands, source, target)).isEqualTo(termsOf(operandB, condensingResult))
    }

    @Test
    fun `Should apply a condensing step by ordering & mapping & condensing source & target which inversely select the previously matched sides`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val operandC: Term = mock()
        val operands = termsOf(operandA, operandB, operandC)

        val source = termsOf(operandC)
        val target = termsOf(operandA)

        val mappedLeft: CondensingSide = mock()
        val mappedRight: CondensingSide = mock()

        val condensingResult: Term = mock()

        whenever(mapper.map(target, source))
                .thenReturn(mappedLeft and mappedRight)

        whenever(executor.execute(mappedLeft, mappedRight))
                .thenReturn(termsOf(condensingResult))

        // THEN
        assertThat(step.apply(operands, source, target)).isEqualTo(termsOf(condensingResult, operandB))
    }

    private fun condensingStep(
            matcher: CondensingMatcher,
            extractor: CondensingExtractor,
            mapper: CondensingMapper<CondensingSide, CondensingSide>,
            executor: CondensingExecutor<CondensingSide, CondensingSide>) = object
        : CondensingStep<CondensingSide, CondensingSide>(matcher, extractor, mapper, executor) {}
}