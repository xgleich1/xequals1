package com.dg.eqs.core.generation.generatedlevel.equalsrelation

import com.dg.eqs.core.mapping.StringToOriginMapper
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doReturnConsecutively
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EqualsRelationGeneratorTest {
    private companion object {
        private const val GENERATED_SENTENCE = "=[+/[+i,+i],+i]"
        private const val VALID_SENTENCE_WITH_ITEMS = "=[+/[+x,+1],+2]"
        private const val INVALID_SENTENCE_WITH_ITEMS = "=[+/[+x,+0],+2]"
    }

    @Mock
    private lateinit var language: EqualsRelationLanguage
    @Mock
    private lateinit var installer: EqualsRelationInstaller
    @Mock
    private lateinit var mapper: StringToOriginMapper
    @Mock
    private lateinit var validator: EqualsRelationValidator
    @InjectMocks
    private lateinit var generator: EqualsRelationGenerator

    @Mock
    private lateinit var validEqualsRelation: EqualsRelation
    @Mock
    private lateinit var invalidEqualsRelation: EqualsRelation


    @Before
    fun setUp() {
        whenever(language.generateSentence()) doReturn GENERATED_SENTENCE

        whenever(mapper.mapToOrigin<EqualsRelation>(
                VALID_SENTENCE_WITH_ITEMS)) doReturn validEqualsRelation
        whenever(mapper.mapToOrigin<EqualsRelation>(
                INVALID_SENTENCE_WITH_ITEMS)) doReturn invalidEqualsRelation

        whenever(validator.validate(validEqualsRelation)) doReturn true
        whenever(validator.validate(invalidEqualsRelation)) doReturn false
    }

    @Test
    fun `Should generate a valid equals relation in the first try`() {
        // GIVEN
        whenever(installer.installItems(GENERATED_SENTENCE)) doReturn
                VALID_SENTENCE_WITH_ITEMS

        // WHEN
        val generatedEqualsRelation = generator.generateEqualsRelation()

        // THEN
        assertThat(generatedEqualsRelation).isEqualTo(validEqualsRelation)
    }

    @Test
    fun `Should generate a valid equals relation in a subsequent try`() {
        // GIVEN
        whenever(installer.installItems(GENERATED_SENTENCE)) doReturnConsecutively
                listOf(INVALID_SENTENCE_WITH_ITEMS, VALID_SENTENCE_WITH_ITEMS)

        // WHEN
        val generatedEqualsRelation = generator.generateEqualsRelation()

        // THEN
        assertThat(generatedEqualsRelation).isEqualTo(validEqualsRelation)
    }
}