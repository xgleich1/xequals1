package com.dg.eqs.core.generation.generatedlevel.equalsrelation

import com.dg.eqs.core.generation.GenerationLanguage
import com.dg.eqs.core.generation.GenerationSelector


class EqualsRelationLanguage(
        equalsRelationGrammar: EqualsRelationGrammar,
        generationSelector: GenerationSelector)
    : GenerationLanguage(
        equalsRelationGrammar,
        generationSelector)