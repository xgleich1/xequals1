package com.dg.eqs.core.definition.relation.alteration

import com.dg.eqs.base.abbreviation.AnyShiftingStep
import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class RelationAlterationTest {
    @Mock
    private lateinit var removing: RelationRemoving
    @Mock
    private lateinit var replacing: RelationReplacing
    @Mock
    private lateinit var shifting: RelationShifting
    @InjectMocks
    private lateinit var alteration: RelationAlteration


    @Test
    fun `Should construct a relation alteration for the default usage`() {
        // GIVEN
        val stepA: AnyShiftingStep = mock()
        val stepB: AnyShiftingStep = mock()

        // WHEN
        val alteration = RelationAlteration(stepA, stepB)

        // THEN
        val expectedAlteration = RelationAlteration(
                DefaultRelationRemoving(),
                DefaultRelationReplacing(),
                DefaultRelationShifting(stepA, stepB))

        assertThat(alteration).isEqualTo(expectedAlteration)
    }

    @Test
    fun `Should compare two equal relation alterations`() {
        // GIVEN
        val alterationA = RelationAlteration(
                removing,
                replacing,
                shifting)

        val alterationB = RelationAlteration(
                removing,
                replacing,
                shifting)

        // THEN
        assertThat(alterationA).isEqualTo(alterationB)
    }

    @Test
    fun `Should alter a relation by removing a term from it or from its sides`() {
        // GIVEN
        val term: Term = mock()

        val relation: Relation = mock()

        val removingResult: Relation = mock()

        whenever(removing.remove(relation, term)).thenReturn(removingResult)

        // THEN
        assertThat(alteration.remove(relation, term)).isEqualTo(removingResult)
    }

    @Test
    fun `Should alter a relation by replacing a term in it or in its sides`() {
        // GIVEN
        val old: Term = mock()
        val new: Term = mock()

        val relation: Relation = mock()

        val replacingResult: Relation = mock()

        whenever(replacing.replace(relation, old, new)).thenReturn(replacingResult)

        // THEN
        assertThat(alteration.replace(relation, old, new)).isEqualTo(replacingResult)
    }

    @Test
    fun `Should alter a relation by shifting the source to the target side`() {
        // GIVEN
        val source: Terms = mock()
        val targetSide: Term = mock()

        val relation: Relation = mock()

        val shiftingResult: Relation = mock()

        whenever(shifting.shift(relation, source, targetSide)).thenReturn(shiftingResult)

        // THEN
        assertThat(alteration.shift(relation, source, targetSide)).isEqualTo(shiftingResult)
    }
}