package com.dg.eqs.core.generation

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class GenerationGrammarTest {
    @Test
    fun `Should provide the replacement for each symbol in the grammar`() {
        // GIVEN
        val grammar = createGrammar("<start>~<item>", "<item>~+i")

        // THEN
        assertThat(grammar.getReplacement("<start>")).isEqualTo("<item>")
        assertThat(grammar.getReplacement("<item>")).isEqualTo("+i")
    }

    @Test(expected = IllegalStateException::class)
    fun `Should throw an exception when a symbol isn't in the grammar`() {
        // GIVEN
        val grammar = createGrammar("<start>~+i")

        // WHEN
        grammar.getReplacement("<item>")
    }

    private fun createGrammar(vararg productionRules: String) = object
        : GenerationGrammar(productionRules.toList()) {}
}