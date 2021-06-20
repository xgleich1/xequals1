package com.dg.eqs.core.generation.generatedlevel.equalsrelation

import com.dg.eqs.base.extension.indicesOf
import com.dg.eqs.base.extension.replace
import com.dg.eqs.core.generation.GenerationSelector


class EqualsRelationInstaller(
        private val selector: GenerationSelector) {

    fun installItems(sentence: String) = sentence
            .installVariable()
            .installValues()

    private fun String.installVariable(): String {
        val selectedIndex = selector
                .selectOption(indicesOf('i'))

        return replace(selectedIndex, "x")
    }

    private fun String.installValues(): String {
        val rareValues = mutableSetOf<Int>()

        return replace('i') {
            val selectedValue = selector
                    .selectValue(rareValues)

            rareValues += selectedValue

            "$selectedValue"
        }
    }
}