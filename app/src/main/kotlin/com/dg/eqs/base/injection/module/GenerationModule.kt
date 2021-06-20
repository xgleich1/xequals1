package com.dg.eqs.base.injection.module

import android.content.res.Resources
import com.dg.eqs.core.mapping.StringToOriginMapper
import com.dg.eqs.core.generation.GenerationSelector
import com.dg.eqs.core.generation.generatedlevel.GeneratedLevelGenerator
import com.dg.eqs.core.generation.generatedlevel.equalsrelation.*
import dagger.Module
import dagger.Provides
import kotlin.random.Random


@Module
open class GenerationModule {
    @Provides
    open fun provideGeneratedLevelGenerator(
            equalsRelationGenerator: EqualsRelationGenerator): GeneratedLevelGenerator {

        return GeneratedLevelGenerator(equalsRelationGenerator)
    }

    @Provides
    open fun provideEqualsRelationGenerator(
            equalsRelationLanguage: EqualsRelationLanguage,
            equalsRelationInstaller: EqualsRelationInstaller,
            stringToOriginMapper: StringToOriginMapper,
            equalsRelationValidator: EqualsRelationValidator): EqualsRelationGenerator {

        return EqualsRelationGenerator(
                equalsRelationLanguage,
                equalsRelationInstaller,
                stringToOriginMapper,
                equalsRelationValidator)
    }

    @Provides
    open fun provideEqualsRelationLanguage(
            equalsRelationGrammar: EqualsRelationGrammar,
            generationSelector: GenerationSelector): EqualsRelationLanguage {

        return EqualsRelationLanguage(
                equalsRelationGrammar,
                generationSelector)
    }

    @Provides
    open fun provideEqualsRelationGrammar(resources: Resources): EqualsRelationGrammar {
        return EqualsRelationGrammar(resources)
    }

    @Provides
    open fun provideGenerationSelector(random: Random): GenerationSelector {
        return GenerationSelector(random)
    }

    @Provides
    open fun provideRandom(): Random {
        return Random
    }

    @Provides
    open fun provideEqualsRelationInstaller(
            generationSelector: GenerationSelector): EqualsRelationInstaller {

        return EqualsRelationInstaller(generationSelector)
    }

    @Provides
    open fun provideEqualsRelationValidator(): EqualsRelationValidator {
        return EqualsRelationValidator()
    }
}