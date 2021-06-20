package com.dg.eqs.core.definition.relation.alteration

import com.dg.eqs.base.abbreviation.AnyShiftingStep
import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DefaultRelationShiftingTest {
    @Mock
    private lateinit var stepA: AnyShiftingStep
    @Mock
    private lateinit var stepB: AnyShiftingStep

    private lateinit var shifting: DefaultRelationShifting


    @Before
    fun setUp() {
        shifting = DefaultRelationShifting(stepA, stepB)
    }

    @Test
    fun `Should compare two equal default relation shiftings`() {
        // GIVEN
        val shiftingA = DefaultRelationShifting(stepA, stepB)
        val shiftingB = DefaultRelationShifting(stepA, stepB)

        // THEN
        assertThat(shiftingA).isEqualTo(shiftingB)
    }

    @Test(expected = IllegalStateException::class)
    fun `Should throw an exception when there's no step to shift the source to the target side`() {
        // GIVEN
        val source: Terms = mock()
        val targetSide: Term = mock()

        val relation: Relation = mock()

        whenever(stepA.isApplicable(relation, source, targetSide)).thenReturn(false)
        whenever(stepB.isApplicable(relation, source, targetSide)).thenReturn(false)

        // WHEN
        shifting.shift(relation, source, targetSide)
    }

    @Test
    fun `Should shift the source to the target side by applying the applicable step`() {
        // GIVEN
        val source: Terms = mock()
        val targetSide: Term = mock()

        val relation: Relation = mock()

        val shiftingResult: Relation = mock()

        whenever(stepB.isApplicable(relation, source, targetSide)).thenReturn(true)
        whenever(stepB.apply(relation, source, targetSide)).thenReturn(shiftingResult)

        // THEN
        assertThat(shifting.shift(relation, source, targetSide)).isEqualTo(shiftingResult)
    }
}