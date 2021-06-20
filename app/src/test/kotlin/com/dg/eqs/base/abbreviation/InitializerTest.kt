package com.dg.eqs.base.abbreviation

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.interaction.linking.choosing.Choice
import com.dg.eqs.core.visualization.Drafts
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class InitializerTest {
    @Test
    fun `Should provide an aptly named initializer for a terms decorator taking a list of terms`() {
        // GIVEN
        val termA: Term = mock()
        val termB: Term = mock()
        val list = listOf(termA, termB)

        // THEN
        assertThat(termsOf(list)).isEqualTo(Terms(listOf(termA, termB)))
    }

    @Test
    fun `Should provide an aptly named initializer for a terms decorator taking individual nullable terms`() {
        // GIVEN
        val termA: Term = mock()
        val termB: Term = mock()
        val termC: Term? = null
        val termD: Term? = null

        // THEN)
        assertThat(termsOf(termA, termB, termC, termD)).isEqualTo(Terms(listOf(termA, termB)))
    }

    @Test
    fun `Should provide an aptly named initializer for a terms decorator taking a lists of terms and an individual term`() {
        // GIVEN
        val termA: Term = mock()
        val termB: Term = mock()
        val termC: Term = mock()
        val list = listOf(termA, termB)

        // THEN)
        assertThat(termsOf(list, termC)).isEqualTo(Terms(listOf(termA, termB, termC)))
    }

    @Test
    fun `Should provide an aptly named initializer for a terms decorator taking an individual term and a list of terms`() {
        // GIVEN
        val termA: Term = mock()
        val termB: Term = mock()
        val termC: Term = mock()
        val list = listOf(termB, termC)

        // THEN)
        assertThat(termsOf(termA, list)).isEqualTo(Terms(listOf(termA, termB, termC)))
    }

    @Test
    fun `Should provide an aptly named initializer for a value taking a negative number`() {
        assertThat(valueOf(-1)).isEqualTo(NegativeValue(1))
    }

    @Test
    fun `Should provide an aptly named initializer for a value taking a number of zero`() {
        assertThat(valueOf(0)).isEqualTo(PositiveValue(0))
    }

    @Test
    fun `Should provide an aptly named initializer for a value taking a positive number`() {
        assertThat(valueOf(1)).isEqualTo(PositiveValue(1))
    }

    @Test
    fun `Should provide an aptly named initializer for an empty drafts decorator`() {
        assertThat(draftsOf<AnyOrigin>()).isEqualTo(Drafts<AnyOrigin>(emptyList()))
    }

    @Test
    fun `Should provide an aptly named initializer for a drafts decorator taking a list of drafts`() {
        // GIVEN
        val draftA: AnyDraft = mock()
        val draftB: AnyDraft = mock()
        val list = listOf(draftA, draftB)

        // THEN)
        assertThat(draftsOf(list)).isEqualTo(Drafts(listOf(draftA, draftB)))
    }

    @Test
    fun `Should provide an aptly named initializer for a drafts decorator taking individual nullable drafts`() {
        // GIVEN
        val draftA: AnyDraft = mock()
        val draftB: AnyDraft = mock()
        val draftC: AnyDraft? = null
        val draftD: AnyDraft? = null

        // THEN)
        assertThat(draftsOf(draftA, draftB, draftC, draftD)).isEqualTo(Drafts(listOf(draftA, draftB)))
    }

    @Test
    fun `Should provide an aptly named initializer for a drafts decorator taking individual lists of drafts`() {
        // GIVEN
        val draftA: AnyDraft = mock()
        val draftB: AnyDraft = mock()
        val draftC: AnyDraft = mock()
        val draftD: AnyDraft = mock()
        val listA = listOf(draftA, draftB)
        val listB = listOf(draftC, draftD)

        // THEN)
        assertThat(draftsOf(listA, listB)).isEqualTo(Drafts(listOf(draftA, draftB, draftC, draftD)))
    }

    @Test
    fun `Should provide an aptly named initializer for a drafts decorator taking a lists of drafts and an individual draft`() {
        // GIVEN
        val draftA: AnyDraft = mock()
        val draftB: AnyDraft = mock()
        val draftC: AnyDraft = mock()
        val list = listOf(draftA, draftB)

        // THEN)
        assertThat(draftsOf(list, draftC)).isEqualTo(Drafts(listOf(draftA, draftB, draftC)))
    }

    @Test
    fun `Should provide an aptly named initializer for a drafts decorator taking an individual draft and a list of drafts`() {
        // GIVEN
        val draftA: AnyDraft = mock()
        val draftB: AnyDraft = mock()
        val draftC: AnyDraft = mock()
        val list = listOf(draftB, draftC)

        // THEN)
        assertThat(draftsOf(draftA, list)).isEqualTo(Drafts(listOf(draftA, draftB, draftC)))
    }

    @Test
    fun `Should provide an aptly named initializer for a list of symbols taking individual symbols`() {
        // GIVEN
        val symbolA: AnySymbolDraft = mock()
        val symbolB: AnySymbolDraft = mock()

        // THEN)
        assertThat(symbolsOf(symbolA, symbolB)).isEqualTo(listOf(symbolA, symbolB))
    }

    @Test
    fun `Should provide an aptly named initializer for a choice taking individual chosen`() {
        // GIVEN
        val chosenA: TermDraft = mock()
        val chosenB: TermDraft = mock()

        // THEN)
        assertThat(choiceOf(chosenA, chosenB)).isEqualTo(Choice(draftsOf(chosenA, chosenB)))
    }
}