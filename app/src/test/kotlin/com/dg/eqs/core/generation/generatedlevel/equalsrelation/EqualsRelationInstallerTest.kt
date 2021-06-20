package com.dg.eqs.core.generation.generatedlevel.equalsrelation

import com.dg.eqs.core.generation.GenerationSelector
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EqualsRelationInstallerTest {
    @Mock
    private lateinit var selector: GenerationSelector
    @InjectMocks
    private lateinit var installer: EqualsRelationInstaller


    @Test
    fun `Should install items into an equals relation sentence`() {
        // GIVEN
        whenever(selector.selectOption(listOf(6, 9, 13))) doReturn 6

        whenever(selector.selectValue(setOf())) doReturn 1
        whenever(selector.selectValue(setOf(1))) doReturn 2

        // WHEN
        val newSentence = installer.installItems("=[+±[+i,+i],+i]")

        // THEN
        assertThat(newSentence).isEqualTo("=[+±[+x,+1],+2]")
    }
}