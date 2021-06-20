package com.dg.eqs.core.generation

import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doReturnConsecutively
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GenerationLanguageTest {
    @Mock
    private lateinit var grammar: GenerationGrammar
    @Mock
    private lateinit var selector: GenerationSelector

    private lateinit var language: GenerationLanguage


    @Before
    fun setUp() {
        language = createLanguage()
    }

    @Test
    fun `Should generate a sentence from a grammar containing a single symbol`() {
        // GIVEN
        whenever(grammar.getReplacement("<start>")) doReturn "+i"

        // THEN
        assertThat(language.generateSentence()).isEqualTo("+i")
    }

    @Test
    fun `Should generate a sentence from a grammar containing several symbols`() {
        // GIVEN
        whenever(grammar.getReplacement("<start>")) doReturn "<item>"
        whenever(grammar.getReplacement("<item>")) doReturn "+i"

        // THEN
        assertThat(language.generateSentence()).isEqualTo("+i")
    }

    @Test
    fun `Should generate a sentence from a grammar containing a single choice`() {
        // GIVEN
        whenever(grammar.getReplacement("<start>")) doReturn "{+i|-i}"

        whenever(selector.selectOption(listOf("+i", "-i"))) doReturn "-i"

        // THEN
        assertThat(language.generateSentence()).isEqualTo("-i")
    }

    @Test
    fun `Should generate a sentence from a grammar containing several choices`() {
        // GIVEN
        whenever(grammar.getReplacement("<start>")) doReturn "{<item>|<fraction>}"
        whenever(grammar.getReplacement("<item>")) doReturn "{+i|-i}"

        whenever(selector.selectOption(listOf("<item>", "<fraction>"))) doReturn "<item>"
        whenever(selector.selectOption(listOf("+i", "-i"))) doReturn "-i"

        // THEN
        assertThat(language.generateSentence()).isEqualTo("-i")
    }

    @Test
    fun `Should generate a sentence from a grammar with several symbols and choices`() {
        // GIVEN
        whenever(grammar.getReplacement("<start>")) doReturn "<term_size_1>"
        whenever(grammar.getReplacement("<term_size_1>")) doReturn "{<item>|<fraction>}"
        whenever(grammar.getReplacement("<item>")) doReturn "{+i|-i}"
        whenever(grammar.getReplacement("<fraction>")) doReturn "+/[<item>,<item>]"

        whenever(selector.selectOption(listOf("<item>", "<fraction>"))) doReturn "<fraction>"
        whenever(selector.selectOption(listOf("+i", "-i"))) doReturnConsecutively listOf("+i", "-i")

        // THEN
        assertThat(language.generateSentence()).isEqualTo("+/[+i,-i]")
    }

    private fun createLanguage() = object : GenerationLanguage(grammar, selector) {}
}