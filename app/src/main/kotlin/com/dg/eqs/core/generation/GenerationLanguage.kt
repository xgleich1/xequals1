package com.dg.eqs.core.generation


abstract class GenerationLanguage(
        private val generationGrammar: GenerationGrammar,
        private val generationSelector: GenerationSelector) {

    fun generateSentence() = buildString {
        startSentence()
        buildSentence()
    }

    private fun StringBuilder.startSentence() {
        append(generationGrammar
                .getReplacement("<start>"))
    }

    private fun StringBuilder.buildSentence() {
        val containsChoice = containsChoice()
        val containsSymbol = containsSymbol()

        if(containsChoice) {
            replaceChoice()
        }

        if(containsSymbol) {
            replaceSymbol()
        }

        if(containsChoice || containsSymbol) {
            buildSentence()
        }
    }

    private fun StringBuilder.replaceChoice() {
        val firstIndex = firstIndexOfChoice()
        val finalIndex = finalIndexOfChoice()

        val choices = substring(firstIndex + 1, finalIndex)
                .split('|')

        replace(firstIndex, finalIndex + 1,
                generationSelector.selectOption(choices))
    }

    private fun StringBuilder.replaceSymbol() {
        val firstIndex = firstIndexOfSymbol()
        val finalIndex = finalIndexOfSymbol()

        val symbol = substring(firstIndex, finalIndex + 1)

        replace(firstIndex, finalIndex + 1,
                generationGrammar.getReplacement(symbol))
    }

    private fun StringBuilder.containsChoice() = contains('{')

    private fun StringBuilder.containsSymbol() = contains('<')

    private fun StringBuilder.firstIndexOfChoice() = indexOf('{')

    private fun StringBuilder.finalIndexOfChoice() = indexOf('}')

    private fun StringBuilder.firstIndexOfSymbol() = indexOf('<')

    private fun StringBuilder.finalIndexOfSymbol() = indexOf('>')
}