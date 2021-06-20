package com.dg.eqs.core.interaction.linking.choosing

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.base.abbreviation.choiceOf
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.interaction.Touch.Action.INITIAL
import com.dg.eqs.core.interaction.linking.choosing.widening.SourceWidener
import com.dg.eqs.core.interaction.linking.choosing.widening.TargetWidener
import com.dg.eqs.core.visualization.composition.horizontal.operation.dashoperation.PositiveDashOperationDraft
import com.dg.eqs.core.visualization.composition.horizontal.relation.equalsrelation.EqualsRelationDraft
import com.dg.eqs.core.visualization.symbolization.text.item.variable.PositiveVariableDraft
import org.mockito.kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ChooserTest {
    @Mock
    private lateinit var sourceWidener: SourceWidener
    @Mock
    private lateinit var targetWidener: TargetWidener
    @InjectMocks
    private lateinit var chooser: Chooser


    @Test
    fun `Should choose the initially touched part as a source`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val draft = composite(partA, partB)

        // WHEN
        chooser.commenceChoosing(draft, touch(partA))

        // THEN
        assertThat(chooser.chosenSource).isEqualTo(choiceOf(partA))
    }

    @Test
    fun `Should not choose any part as a source if nothing is touched`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val draft = composite(partA, partB)

        // WHEN
        chooser.commenceChoosing(draft, touch(x = 15, y = 10))

        // THEN
        assertThat(chooser.chosenSource).isEqualTo(choiceOf())
    }

    @Test
    fun `Should not choose the initially touched part twice as a source`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val draft = composite(partA, partB)

        chooser.commenceChoosing(draft, touch(partA))

        // WHEN
        chooser.continueChoosing(draft, touch(partA))

        // THEN
        assertThat(chooser.chosenSource).isEqualTo(choiceOf(partA))
    }

    @Test
    fun `Should immediately start widening the chosen source`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val partC = partC(firstX = 30, firstY = 5)
        val draft = composite(partA, partB, partC)

        prepareWideningOfChosenSourceWith(partB)

        // WHEN
        chooser.commenceChoosing(draft, touch(partA))

        // THEN
        assertThat(chooser.chosenSource).isEqualTo(choiceOf(partA, partB))
    }

    @Test
    fun `Should stop widening the source once its initially chosen part is left`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val partC = partC(firstX = 30, firstY = 5)
        val draft = composite(partA, partB, partC)

        chooser.commenceChoosing(draft, touch(partA))

        // WHEN
        chooser.continueChoosing(draft, touch(x = 11, y = 5))

        // THEN
        verify(sourceWidener).stop()
    }

    @Test
    fun `Should not stop widening the source when its initially chosen part isn't left`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val partC = partC(firstX = 30, firstY = 5)
        val draft = composite(partA, partB, partC)

        chooser.commenceChoosing(draft, touch(partA))

        // WHEN
        chooser.continueChoosing(draft, touch(partA))

        // THEN
        verify(sourceWidener, never()).stop()
    }

    @Test
    fun `Should choose the subsequently touched part as a target`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val draft = composite(partA, partB)

        chooser.commenceChoosing(draft, touch(partA))

        // WHEN
        chooser.continueChoosing(draft, touch(partB))

        // THEN
        assertThat(chooser.chosenTarget).isEqualTo(choiceOf(partB))
    }

    @Test
    fun `Should choose the left side of a relation as a target if its containing the touched part`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val partC = partC(firstX = 30, firstY = 5)
        val left = composite(partA, partB, width = 10, firstX = 10, firstY = 5)
        val draft = relation(left, partC)

        chooser.commenceChoosing(draft, touch(partC))

        // WHEN
        chooser.continueChoosing(draft, touch(partB))

        // THEN
        assertThat(chooser.chosenTarget).isEqualTo(choiceOf(left))
    }

    @Test
    fun `Should choose the right side of a relation as a target if its containing the touched part`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val partC = partC(firstX = 30, firstY = 5)
        val right = composite(partB, partC, width = 10, firstX = 20, firstY = 5)
        val draft = relation(partA, right)

        chooser.commenceChoosing(draft, touch(partA))

        // WHEN
        chooser.continueChoosing(draft, touch(partB))

        // THEN
        assertThat(chooser.chosenTarget).isEqualTo(choiceOf(right))
    }

    @Test
    fun `Should not choose any part as a target if nothing is subsequently touched`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val draft = composite(partA, partB)

        chooser.commenceChoosing(draft, touch(partA))

        // WHEN
        chooser.continueChoosing(draft, touch(x = 15, y = 5))

        // THEN
        assertThat(chooser.chosenTarget).isEqualTo(choiceOf())
    }

    @Test
    fun `Should not choose the subsequently touched part twice as a target`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val draft = composite(partA, partB)

        chooser.commenceChoosing(draft, touch(partA))
        chooser.continueChoosing(draft, touch(partB))

        // WHEN
        chooser.continueChoosing(draft, touch(partB))

        // THEN
        assertThat(chooser.chosenTarget).isEqualTo(choiceOf(partB))
    }

    @Test
    fun `Should not choose any part already chosen for the source as a target`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val draft = composite(partA, partB)

        chooser.commenceChoosing(draft, touch(partA))

        // WHEN
        chooser.continueChoosing(draft, touch(partA))

        // THEN
        assertThat(chooser.chosenTarget).isEqualTo(choiceOf())
    }

    @Test
    fun `Should remove the target once the subsequently chosen part is left`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val draft = composite(partA, partB)

        chooser.commenceChoosing(draft, touch(partA))
        chooser.continueChoosing(draft, touch(partB))

        // WHEN
        chooser.continueChoosing(draft, touch(x = 21, y = 5))

        // THEN
        assertThat(chooser.chosenTarget).isEqualTo(choiceOf())
    }

    @Test
    fun `Should immediately start widening the chosen target`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val partC = partC(firstX = 30, firstY = 5)
        val draft = composite(partA, partB, partC)

        prepareWideningOfChosenTargetWith(partC)

        chooser.commenceChoosing(draft, touch(partA))

        // WHEN
        chooser.continueChoosing(draft, touch(partB))

        // THEN
        assertThat(chooser.chosenTarget).isEqualTo(choiceOf(partB, partC))
    }

    @Test
    fun `Should stop widening the target once its subsequently chosen part is left`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val partC = partC(firstX = 30, firstY = 5)
        val draft = composite(partA, partB, partC)

        chooser.commenceChoosing(draft, touch(partA))
        chooser.continueChoosing(draft, touch(partB))

        // WHEN
        chooser.continueChoosing(draft, touch(x = 21, y = 5))

        // THEN
        verify(targetWidener).stop()
    }

    @Test
    fun `Should not stop widening the target when its subsequently chosen part isn't left`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val partC = partC(firstX = 30, firstY = 5)
        val draft = composite(partA, partB, partC)

        chooser.commenceChoosing(draft, touch(partA))
        chooser.continueChoosing(draft, touch(partB))

        // WHEN
        chooser.continueChoosing(draft, touch(partB))

        // THEN
        verify(targetWidener, never()).stop()
    }

    @Test
    fun `Should stop widening the chosen source once the choosing is stopped`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val partC = partC(firstX = 30, firstY = 5)
        val draft = composite(partA, partB, partC)

        chooser.commenceChoosing(draft, touch(partA))

        // WHEN
        chooser.stopChoosing()

        // THEN
        verify(sourceWidener).stop()
    }

    @Test
    fun `Should stop widening the chosen target once the choosing is stopped`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val partC = partC(firstX = 30, firstY = 5)
        val draft = composite(partA, partB, partC)

        chooser.commenceChoosing(draft, touch(partA))
        chooser.continueChoosing(draft, touch(partB))

        // WHEN
        chooser.stopChoosing()

        // THEN
        verify(targetWidener).stop()
    }

    @Test
    fun `Should allow choosing different parts in a new choosing after a stopped one`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val partC = partC(firstX = 30, firstY = 5)
        val partD = partD(firstX = 40, firstY = 5)
        val draft = composite(partA, partB, partC, partD)

        chooser.commenceChoosing(draft, touch(partA))
        chooser.continueChoosing(draft, touch(partB))
        chooser.stopChoosing()

        // WHEN
        chooser.commenceChoosing(draft, touch(partC))
        chooser.continueChoosing(draft, touch(partD))

        // THEN
        assertThat(chooser.chosenSource).isEqualTo(choiceOf(partC))
        assertThat(chooser.chosenTarget).isEqualTo(choiceOf(partD))
    }

    @Test
    fun `Should allow choosing the same parts in a new choosing after a stopped one`() {
        // GIVEN
        val partA = partA(firstX = 10, firstY = 5)
        val partB = partB(firstX = 20, firstY = 5)
        val draft = composite(partA, partB)

        chooser.commenceChoosing(draft, touch(partA))
        chooser.continueChoosing(draft, touch(partB))
        chooser.stopChoosing()

        // WHEN
        chooser.commenceChoosing(draft, touch(partA))
        chooser.continueChoosing(draft, touch(partB))

        // THEN
        assertThat(chooser.chosenSource).isEqualTo(choiceOf(partA))
        assertThat(chooser.chosenTarget).isEqualTo(choiceOf(partB))
    }

    private fun partA(firstX: Int, firstY: Int) = part("A", firstX, firstY)
    private fun partB(firstX: Int, firstY: Int) = part("B", firstX, firstY)
    private fun partC(firstX: Int, firstY: Int) = part("C", firstX, firstY)
    private fun partD(firstX: Int, firstY: Int) = part("D", firstX, firstY)

    private fun part(name: String, firstX: Int, firstY: Int) =
            PositiveVariableDraft(mock { on { unsignedName } doReturn name })
                    .also { it.firstX = firstX; it.firstY = firstY }

    private fun composite(vararg parts: TermDraft, width: Int = 0, firstX: Int = 0, firstY: Int = 0) =
            PositiveDashOperationDraft(mock(), *parts)
                    .also { it.width = width; it.firstX = firstX; it.firstY = firstY }

    private fun relation(left: TermDraft, right: TermDraft) =
            EqualsRelationDraft(mock(), left, right)

    private fun touch(part: AnyDraft) = touch(part.firstX, part.firstY)

    private fun touch(x: Int, y: Int) = Touch(x, y, INITIAL)

    private fun prepareWideningOfChosenSourceWith(part: TermDraft) = argumentCaptor<Choice>().apply {
        whenever(sourceWidener.start(capture())).thenAnswer {
            firstValue.addEnsuingChosen(part)
        }
    }

    private fun prepareWideningOfChosenTargetWith(part: TermDraft) = argumentCaptor<Choice>().apply {
        whenever(targetWidener.start(capture(), any())).thenAnswer {
            firstValue.addEnsuingChosen(part)
        }
    }
}