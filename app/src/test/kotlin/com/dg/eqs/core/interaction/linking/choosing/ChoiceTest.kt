package com.dg.eqs.core.interaction.linking.choosing

import com.dg.eqs.base.abbreviation.*
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class ChoiceTest {
    @Test
    fun `A choices choosable parent is the choosable parent of its chosen`() {
        // GIVEN
        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock()
        val choice = choiceOf(chosenA, chosenB)

        val choosableParent: TermDraft = mock()

        whenever(chosenA.choosableParent).thenReturn(choosableParent)
        whenever(chosenB.choosableParent).thenReturn(choosableParent)

        // THEN
        assertThat(choice.choosableParent).isEqualTo(choosableParent)
    }

    @Test
    fun `A choices choosable successor is the choosable successor of its chosen`() {
        // GIVEN
        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock()
        val choice = choiceOf(chosenA, chosenB)

        val choosableSuccessor: TermDraft = mock()

        whenever(chosenA.choosableSuccessor).thenReturn(chosenB)
        whenever(chosenB.choosableSuccessor).thenReturn(choosableSuccessor)

        // THEN
        assertThat(choice.choosableSuccessor).isEqualTo(choosableSuccessor)
    }

    @Test
    fun `A choices choosable predecessor is the choosable predecessor of its chosen`() {
        // GIVEN
        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock()
        val choice = choiceOf(chosenA, chosenB)

        val choosablePredecessor: TermDraft = mock()

        whenever(chosenA.choosablePredecessor).thenReturn(choosablePredecessor)
        whenever(chosenB.choosablePredecessor).thenReturn(chosenA)

        // THEN
        assertThat(choice.choosablePredecessor).isEqualTo(choosablePredecessor)
    }

    @Test
    fun `Should compare two equal choices`() {
        // GIVEN
        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock()
        val choiceA = choiceOf(chosenA, chosenB)
        val choiceB = choiceOf(chosenA, chosenB)

        // THEN
        assertThat(choiceA).isEqualTo(choiceB)
    }

    @Test
    fun `Should convert a choice to a string`() {
        // GIVEN
        val chosenA: TermDraft = mock { on { toString() } doReturn "A" }
        val chosenB: TermDraft = mock { on { toString() } doReturn "B" }
        val choice = choiceOf(chosenA, chosenB)

        // THEN
        assertThat(choice).hasToString("[A, B]")
    }

    @Test
    fun `Should clear a choice by removing its chosen`() {
        // GIVEN
        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock()
        val choice = choiceOf(chosenA, chosenB)

        // WHEN
        choice.clear()

        // THEN
        assertThat(choice).isEqualTo(choiceOf())
    }

    @Test
    fun `Should add a initial chosen to a empty choice`() {
        // GIVEN
        val choice = choiceOf()

        val initialChosen: TermDraft = mock()

        // WHEN
        choice.addInitialChosen(initialChosen)

        // THEN
        assertThat(choice).isEqualTo(choiceOf(initialChosen))
    }

    @Test
    fun `Should add a initial chosen to a filled choice by replacing all previously chosen`() {
        // GIVEN
        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock()
        val choice = choiceOf(chosenA, chosenB)

        val initialChosen: TermDraft = mock()

        // WHEN
        choice.addInitialChosen(initialChosen)

        // THEN
        assertThat(choice).isEqualTo(choiceOf(initialChosen))
    }

    @Test
    fun `Should add an ensuing parent by replacing its chosen children`() {
        // GIVEN
        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock()
        val choice = choiceOf(chosenA, chosenB)

        val ensuingParent: TermDraft = mock()

        whenever(chosenA.choosableParent).thenReturn(ensuingParent)
        whenever(chosenB.choosableParent).thenReturn(ensuingParent)

        // WHEN
        choice.addEnsuingChosen(ensuingParent)

        // THEN
        assertThat(choice).isEqualTo(choiceOf(ensuingParent))
    }

    @Test
    fun `Should add an ensuing successor to the end of the choice`() {
        // GIVEN
        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock()
        val choice = choiceOf(chosenA, chosenB)

        val ensuingSuccessor: TermDraft = mock()

        whenever(chosenA.choosableSuccessor).thenReturn(chosenB)
        whenever(chosenB.choosableSuccessor).thenReturn(ensuingSuccessor)

        // WHEN
        choice.addEnsuingChosen(ensuingSuccessor)

        // THEN
        assertThat(choice).isEqualTo(choiceOf(chosenA, chosenB, ensuingSuccessor))
    }

    @Test
    fun `Should add an ensuing predecessor to the start of the choice`() {
        // GIVEN
        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock()
        val choice = choiceOf(chosenA, chosenB)

        val ensuingPredecessor: TermDraft = mock()

        whenever(chosenA.choosablePredecessor).thenReturn(ensuingPredecessor)
        whenever(chosenB.choosablePredecessor).thenReturn(chosenA)

        // WHEN
        choice.addEnsuingChosen(ensuingPredecessor)

        // THEN
        assertThat(choice).isEqualTo(choiceOf(ensuingPredecessor, chosenA, chosenB))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw an exception when there aren't any chosen yet to add an ensuing one to`() {
        // GIVEN
        val choice = choiceOf()

        val ensuingChosen: TermDraft = mock()

        // WHEN
        choice.addEnsuingChosen(ensuingChosen)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw an exception when an ensuing chosen is not related to the previously chosen`() {
        // GIVEN
        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock()
        val choice = choiceOf(chosenA, chosenB)

        val ensuingChosen: TermDraft = mock()

        // WHEN
        choice.addEnsuingChosen(ensuingChosen)
    }

    @Test
    fun `Should inform all observers once the choice is cleared`() {
        // GIVEN
        val chosen: TermDraft = mock()
        val choice = choiceOf(chosen)

        val observerA: (TermDrafts) -> Unit = mock()
        val observerB: (TermDrafts) -> Unit = mock()

        choice.observe(observerA)
        choice.observe(observerB)

        // WHEN
        choice.clear()

        // THEN
        verify(observerA).invoke(draftsOf())
        verify(observerB).invoke(draftsOf())
    }

    @Test
    fun `Should inform all observers once an initial chosen is added`() {
        // GIVEN
        val choice = choiceOf()

        val observerA: (TermDrafts) -> Unit = mock()
        val observerB: (TermDrafts) -> Unit = mock()

        choice.observe(observerA)
        choice.observe(observerB)

        val initialChosen: TermDraft = mock()

        // WHEN
        choice.addInitialChosen(initialChosen)

        // THEN
        verify(observerA).invoke(draftsOf(initialChosen))
        verify(observerB).invoke(draftsOf(initialChosen))
    }

    @Test
    fun `Should inform all observers once an ensuing chosen is added`() {
        // GIVEN
        val chosen: TermDraft = mock()
        val choice = choiceOf(chosen)

        val observerA: (TermDrafts) -> Unit = mock()
        val observerB: (TermDrafts) -> Unit = mock()

        choice.observe(observerA)
        choice.observe(observerB)

        val ensuingSuccessor: TermDraft = mock()

        whenever(chosen.choosableSuccessor).thenReturn(ensuingSuccessor)

        // WHEN
        choice.addEnsuingChosen(ensuingSuccessor)

        // THEN
        verify(observerA).invoke(draftsOf(chosen, ensuingSuccessor))
        verify(observerB).invoke(draftsOf(chosen, ensuingSuccessor))
    }

    @Test
    fun `A choice without any chosen is empty`() {
        assertFalse(choiceOf().isNotEmpty())
    }

    @Test
    fun `A choice with at least one chosen is not empty`() {
        // GIVEN
        val chosen: TermDraft = mock()
        val choice = choiceOf(chosen)

        // THEN
        assertTrue(choice.isNotEmpty())
    }

    @Test
    fun `A choice is absent in a draft when none of its chosen is involved with it`() {
        // GIVEN
        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock()
        val choice = choiceOf(chosenA, chosenB)

        val draft: AnyDraft = mock()

        // THEN
        assertTrue(choice absentIn draft)
    }

    @Test
    fun `A choice is present in a draft when at least one of its chosen is involved with it`() {
        // GIVEN
        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock()
        val choice = choiceOf(chosenA, chosenB)

        val draft: AnyDraft = mock { on { contains(chosenB) } doReturn true }

        // THEN
        assertFalse(choice absentIn draft)
    }

    @Test
    fun `A choice indicates when a term draft is involved with its chosen`() {
        // GIVEN
        val termDraft: TermDraft = mock()

        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock { on { contains(termDraft) } doReturn true }
        val choice = choiceOf(chosenA, chosenB)

        // THEN
        assertTrue(termDraft in choice)
    }

    @Test
    fun `A choice indicates when a term draft isn't involved with its chosen`() {
        // GIVEN
        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock()
        val choice = choiceOf(chosenA, chosenB)

        val termDraft: TermDraft = mock()

        // THEN
        assertTrue(termDraft !in choice)
    }
}