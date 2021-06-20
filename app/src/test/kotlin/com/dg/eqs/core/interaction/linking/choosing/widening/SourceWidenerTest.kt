package com.dg.eqs.core.interaction.linking.choosing.widening

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.base.abbreviation.choiceOf
import com.dg.eqs.core.interaction.linking.choosing.Choice
import com.dg.eqs.core.visualization.composition.horizontal.operation.dashoperation.PositiveDashOperationDraft
import com.dg.eqs.core.visualization.symbolization.text.item.variable.PositiveVariableDraft
import org.mockito.kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SourceWidenerTest {
    @Mock
    private lateinit var wideningDelayer: WideningDelayer
    @InjectMocks
    private lateinit var sourceWidener: SourceWidener


    @Test
    fun `Should widen the source with its choosable predecessor`() {
        // GIVEN
        val partA = partA()
        val partB = partB()
        parent(partA, partB, partC())

        val source = choiceOf(partB)

        // WHEN
        widen(source, times = 1)

        // THEN
        assertThat(source).isEqualTo(choiceOf(partA, partB))
    }

    @Test
    fun `Should widen the source with its choosable successor when there is no predecessor available`() {
        // GIVEN
        val partA = partA()
        val partB = partB()
        parent(partA, partB, partC())

        val source = choiceOf(partA)

        // WHEN
        widen(source, times = 1)

        // THEN
        assertThat(source).isEqualTo(choiceOf(partA, partB))
    }

    @Test
    fun `Should widen the source with its choosable parent when widening it with its predecessor or successor would leave no parts in it left to choose`() {
        // GIVEN
        val partA = partA()
        val parent = parent(partA, partB())
        parent(parent, partC())

        val source = choiceOf(partA)

        // WHEN
        widen(source, times = 1)

        // THEN
        assertThat(source).isEqualTo(choiceOf(parent))
    }

    @Test
    fun `Should continuously widen the source until all choosable parts are chosen`() {
        // GIVEN
        val partA = partA()
        val partB = partB()
        val partC = partC()
        parent(partA, partB, partC, partD())

        val source = choiceOf(partB)

        // WHEN
        widen(source, times = 5)

        // THEN
        assertThat(source).isEqualTo(choiceOf(partA, partB, partC))
    }

    @Test
    fun `Should never widen the source with the remaining unchosen leftmost part of the draft`() {
        // GIVEN
        val partB = partB()
        val partC = partC()
        parent(partA(), partB, partC)

        val source = choiceOf(partB, partC)

        // WHEN
        widen(source, times = 1)

        // THEN
        assertThat(source).isEqualTo(choiceOf(partB, partC))
    }

    @Test
    fun `Should never widen the source with the remaining unchosen rightmost part of the draft`() {
        // GIVEN
        val partA = partA()
        val partB = partB()
        parent(partA, partB, partC())

        val source = choiceOf(partA, partB)

        // WHEN
        widen(source, times = 1)

        // THEN
        assertThat(source).isEqualTo(choiceOf(partA, partB))
    }

    @Test
    fun `Should never widen the source with the actual draft`() {
        // GIVEN
        val partA = partA()
        val partB = partB()
        parent(partA, partB)

        val source = choiceOf(partA, partB)

        // WHEN
        widen(source, times = 1)

        // THEN
        assertThat(source).isEqualTo(choiceOf(partA, partB))
    }

    @Test
    fun `Should stop widening the source by cancelling the next widening`() {
        // WHEN
        sourceWidener.stop()

        // THEN
        verify(wideningDelayer).cancel()
    }

    private fun partA() = PositiveVariableDraft(mock { on { unsignedName } doReturn "A" })
    private fun partB() = PositiveVariableDraft(mock { on { unsignedName } doReturn "B" })
    private fun partC() = PositiveVariableDraft(mock { on { unsignedName } doReturn "C" })
    private fun partD() = PositiveVariableDraft(mock { on { unsignedName } doReturn "D" })

    private fun parent(vararg parts: TermDraft) = PositiveDashOperationDraft(mock(), *parts)

    private fun widen(source: Choice, times: Int) = argumentCaptor<() -> Unit>().apply {
        var wideningCounter = 0

        whenever(wideningDelayer.delay(capture())).thenAnswer {
            if(wideningCounter < times) {
                ++wideningCounter

                firstValue()
            }
        }

        sourceWidener.start(source)
    }
}