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
class TargetWidenerTest {
    @Mock
    private lateinit var wideningDelayer: WideningDelayer
    @InjectMocks
    private lateinit var targetWidener: TargetWidener


    @Test
    fun `Should widen the target with its choosable predecessor`() {
        // GIVEN
        val partA = partA()
        val partB = partB()
        val partC = partC()
        parent(partA, partB, partC)

        val source = choiceOf(partA)
        val target = choiceOf(partC)

        // WHEN
        widen(target, source, times = 1)

        // THEN
        assertThat(target).isEqualTo(choiceOf(partB, partC))
    }

    @Test
    fun `Should widen the target with its choosable successor when there is no predecessor available`() {
        // GIVEN
        val partA = partA()
        val partB = partB()
        val partC = partC()
        parent(partA, partB, partC)

        val source = choiceOf(partA)
        val target = choiceOf(partB)

        // WHEN
        widen(target, source, times = 1)

        // THEN
        assertThat(target).isEqualTo(choiceOf(partB, partC))
    }

    @Test
    fun `Should widen the target with its choosable parent when widening it with its predecessor or successor would leave no parts in it left to choose`() {
        // GIVEN
        val partA = partA()
        val partC = partC()
        val parent = parent(partA, partB())
        parent(parent, partC)

        val source = choiceOf(partC)
        val target = choiceOf(partA)

        // WHEN
        widen(target, source, times = 1)

        // THEN
        assertThat(target).isEqualTo(choiceOf(parent))
    }

    @Test
    fun `Should continuously widen the target until all choosable parts are chosen`() {
        // GIVEN
        val partA = partA()
        val partB = partB()
        val partC = partC()
        val partD = partD()
        parent(partA, partB, partC, partD)

        val source = choiceOf(partA)
        val target = choiceOf(partC)

        // WHEN
        widen(target, source, times = 5)

        // THEN
        assertThat(target).isEqualTo(choiceOf(partB, partC, partD))
    }

    @Test
    fun `Should never widen the target with a part of the chosen source`() {
        // GIVEN
        val partA = partA()
        val partB = partB()
        parent(partA, partB)

        val source = choiceOf(partA)
        val target = choiceOf(partB)

        // WHEN
        widen(target, source, times = 1)

        // THEN
        assertThat(target).isEqualTo(choiceOf(partB))
    }

    @Test
    fun `Should never widen the target with a parent of the chosen source`() {
        // GIVEN
        val partA = partA()
        val partD = partD()
        val parent = parent(parent(partA, partB()), partC())
        parent(parent, partD)

        val source = choiceOf(partA)
        val target = choiceOf(partD)

        // WHEN
        widen(target, source, times = 1)

        // THEN
        assertThat(target).isEqualTo(choiceOf(partD))
    }

    @Test
    fun `Should stop widening the target by cancelling the next widening`() {
        // WHEN
        targetWidener.stop()

        // THEN
        verify(wideningDelayer).cancel()
    }

    private fun partA() = PositiveVariableDraft(mock { on { unsignedName } doReturn "A" })
    private fun partB() = PositiveVariableDraft(mock { on { unsignedName } doReturn "B" })
    private fun partC() = PositiveVariableDraft(mock { on { unsignedName } doReturn "C" })
    private fun partD() = PositiveVariableDraft(mock { on { unsignedName } doReturn "D" })

    private fun parent(vararg parts: TermDraft) = PositiveDashOperationDraft(mock(), *parts)

    private fun widen(target: Choice, source: Choice, times: Int) = argumentCaptor<() -> Unit>().apply {
        var wideningCounter = 0

        whenever(wideningDelayer.delay(capture())).thenAnswer {
            if(wideningCounter < times) {
                ++wideningCounter

                firstValue()
            }
        }

        targetWidener.start(target, source)
    }
}