package com.dg.eqs.core.generation.generatedlevel.equalsrelation

import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.mapping.StringToOriginMapper


class EqualsRelationGenerator(
        private val language: EqualsRelationLanguage,
        private val installer: EqualsRelationInstaller,
        private val mapper: StringToOriginMapper,
        private val validator: EqualsRelationValidator) {

    fun generateEqualsRelation() = buildEqualsRelation(
            language.generateSentence())

    private fun buildEqualsRelation(
            generatedSentence: String): EqualsRelation {

        val sentenceWithItems = installer
                .installItems(generatedSentence)

        val equalsRelation: EqualsRelation = mapper
                .mapToOrigin(sentenceWithItems)

        return if(validator.validate(equalsRelation)) {
            equalsRelation
        } else {
            buildEqualsRelation(generatedSentence)
        }
    }
}