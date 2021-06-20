package com.dg.eqs.core.generation


abstract class GenerationGrammar(
        productionRules: List<String>) {

    private val productionRules = productionRules
            .associate { rule ->
                val (symbol, replacement) = rule
                        .split("~")

                symbol to replacement
            }

    fun getReplacement(symbol: String) =
            checkNotNull(productionRules[symbol])
}