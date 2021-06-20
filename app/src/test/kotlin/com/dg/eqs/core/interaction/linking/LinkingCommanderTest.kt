package com.dg.eqs.core.interaction.linking

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.choiceOf
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.classes.*
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.interaction.Touch.Action.*
import com.dg.eqs.core.interaction.linking.LinkingCommand.*
import com.dg.eqs.core.interaction.linking.choosing.Choice
import com.dg.eqs.core.interaction.linking.choosing.Chooser
import org.mockito.kotlin.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LinkingCommanderTest {
    private lateinit var chosenSource: Choice
    private lateinit var chosenTarget: Choice

    @Mock
    private lateinit var chooser: Chooser
    @Mock
    private lateinit var observer: (LinkingCommand) -> Unit

    private lateinit var linkingCommander: LinkingCommander


    @Before
    fun setUp() {
        chosenSource = choiceOf()
        chosenTarget = choiceOf()

        whenever(chooser.chosenSource) doReturn chosenSource
        whenever(chooser.chosenTarget) doReturn chosenTarget

        linkingCommander = LinkingCommander(chooser)
        linkingCommander.commands.addObserver(observer)
    }

    @Test
    fun `Should stop the choosing when the revert button is clicked`() {
        // GIVEN
        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        chosenTarget.addInitialChosen(termDraft())

        // WHEN
        linkingCommander.onRevertButtonClicked()

        // THEN
        verify(chooser).stopChoosing()
    }

    @Test
    fun `Should command the observer to hide the linking when the revert button is clicked`() {
        // GIVEN
        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        chosenTarget.addInitialChosen(termDraft())

        // WHEN
        linkingCommander.onRevertButtonClicked()

        // THEN
        verify(observer).invoke(HideSourceMark)
        verify(observer).invoke(HideTargetMark)
        verify(observer).invoke(HideSourceArrow)
    }

    @Test
    fun `A revert button click has no effect when there is no source marked`() {
        // WHEN
        linkingCommander.onRevertButtonClicked()

        // THEN
        verify(chooser, never()).stopChoosing()
        verify(observer, never()).invoke(any())
    }

    @Test
    fun `Should stop the choosing when the invert button is clicked`() {
        // GIVEN
        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        chosenTarget.addInitialChosen(termDraft())

        // WHEN
        linkingCommander.onInvertButtonClicked()

        // THEN
        verify(chooser).stopChoosing()
    }

    @Test
    fun `Should command the observer to hide the linking when the invert button is clicked`() {
        // GIVEN
        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        chosenTarget.addInitialChosen(termDraft())

        // WHEN
        linkingCommander.onInvertButtonClicked()

        // THEN
        verify(observer).invoke(HideSourceMark)
        verify(observer).invoke(HideTargetMark)
        verify(observer).invoke(HideSourceArrow)
    }

    @Test
    fun `A invert button click has no effect when there is no source marked`() {
        // WHEN
        linkingCommander.onInvertButtonClicked()

        // THEN
        verify(chooser, never()).stopChoosing()
        verify(observer, never()).invoke(any())
    }

    @Test
    fun `Should stop the choosing when the skip button is clicked`() {
        // GIVEN
        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        chosenTarget.addInitialChosen(termDraft())

        // WHEN
        linkingCommander.onSkipButtonClicked()

        // THEN
        verify(chooser).stopChoosing()
    }

    @Test
    fun `Should command the observer to hide the linking when the skip button is clicked`() {
        // GIVEN
        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        chosenTarget.addInitialChosen(termDraft())

        // WHEN
        linkingCommander.onSkipButtonClicked()

        // THEN
        verify(observer).invoke(HideSourceMark)
        verify(observer).invoke(HideTargetMark)
        verify(observer).invoke(HideSourceArrow)
    }

    @Test
    fun `A skip button click has no effect when there is no source marked`() {
        // WHEN
        linkingCommander.onSkipButtonClicked()

        // THEN
        verify(chooser, never()).stopChoosing()
        verify(observer, never()).invoke(any())
    }

    @Test
    fun `Should stop the choosing when the info button is clicked`() {
        // GIVEN
        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        chosenTarget.addInitialChosen(termDraft())

        // WHEN
        linkingCommander.onInfoButtonClicked()

        // THEN
        verify(chooser).stopChoosing()
    }

    @Test
    fun `Should command the observer to hide the linking when the info button is clicked`() {
        // GIVEN
        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        chosenTarget.addInitialChosen(termDraft())

        // WHEN
        linkingCommander.onInfoButtonClicked()

        // THEN
        verify(observer).invoke(HideSourceMark)
        verify(observer).invoke(HideTargetMark)
        verify(observer).invoke(HideSourceArrow)
    }

    @Test
    fun `A info button click has no effect when there is no source marked`() {
        // WHEN
        linkingCommander.onInfoButtonClicked()

        // THEN
        verify(chooser, never()).stopChoosing()
        verify(observer, never()).invoke(any())
    }

    @Test
    fun `Should forward the draft and the initial touch to the chooser to allow it to choose a source`() {
        // GIVEN
        val draft = draft()
        val initialTouch = initialTouch()

        // WHEN
        linkingCommander.onGridTouched(draft, initialTouch)

        // THEN
        verify(chooser).commenceChoosing(draft, initialTouch)
    }

    @Test
    fun `Should command the observer to show the source mark when a source is chosen following an initial touch`() {
        // GIVEN
        val source = termDraft()

        linkingCommander.onGridTouched(draft(), initialTouch())

        // WHEN
        chosenSource.addInitialChosen(source)

        // THEN
        verify(observer).invoke(ShowSourceMark(draftsOf(source)))
    }

    @Test
    fun `Should command the observer to show the source arrow spanning to the initial touch`() {
        // GIVEN
        val initialTouch = initialTouch()

        linkingCommander.onGridTouched(draft(), initialTouch)

        // WHEN
        chosenSource.addInitialChosen(termDraft())

        // THEN
        verify(observer).invoke(ShowSourceArrow(initialTouch))
    }

    @Test
    fun `Should forward the draft and the ensuing touch to the chooser to allow it to choose a target`() {
        // GIVEN
        val draft: AnyDraft = mock()
        val ensuingTouch = ensuingTouch()

        linkingCommander.onGridTouched(draft, initialTouch())

        chosenSource.addInitialChosen(termDraft())

        // WHEN
        linkingCommander.onGridTouched(draft, ensuingTouch)

        // THEN
        verify(chooser).continueChoosing(draft, ensuingTouch)
    }

    @Test
    fun `Should command the observer to show the source arrow spanning to the ensuing touch`() {
        // GIVEN
        val ensuingTouch = ensuingTouch()

        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        // WHEN
        linkingCommander.onGridTouched(draft(), ensuingTouch)

        // THEN
        verify(observer).invoke(ShowSourceArrow(ensuingTouch))
    }

    @Test
    fun `Should command the observer to show the target mark when a target is chosen following an ensuing touch`() {
        // GIVEN
        val target = termDraft()

        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        // WHEN
        chosenTarget.addInitialChosen(target)

        // THEN
        verify(observer).invoke(ShowTargetMark(draftsOf(target)))
    }

    @Test
    fun `Should command the observer to hide the target mark following an outside ensuing touch`() {
        // GIVEN
        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        chosenTarget.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        // WHEN
        chosenTarget.clear()

        // THEN
        verify(observer).invoke(HideTargetMark)
    }

    @Test
    fun `A ensuing touch has no effect when there is no source marked`() {
        // WHEN
        linkingCommander.onGridTouched(draft(), ensuingTouch())

        // THEN
        verify(chooser, never()).continueChoosing(any(), any())
        verify(observer, never()).invoke(any())
    }

    @Test
    fun `Should stop the choosing once a ceasing touch is registered`() {
        // GIVEN
        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        // WHEN
        linkingCommander.onGridTouched(draft(), ceasingTouch())

        // THEN
        verify(chooser).stopChoosing()
    }

    @Test
    fun `Should command the observer to handle the link when one is created following an ceasing touch`() {
        // GIVEN
        val linkOrigin = origin()
        val linkSource = linkOrigin.first
        val linkTarget = linkOrigin.second

        linkingCommander.onGridTouched(draft(linkOrigin), initialTouch())

        chosenSource.addInitialChosen(termDraft(linkSource))

        linkingCommander.onGridTouched(draft(linkOrigin), ensuingTouch())

        chosenTarget.addInitialChosen(termDraft(linkTarget))

        // WHEN
        linkingCommander.onGridTouched(draft(linkOrigin), ceasingTouch())

        // THEN
        verify(observer).invoke(HandleLink(Link(linkOrigin, linkSource, linkTarget)))
    }

    @Test
    fun `Should command the observer to hide the source mark & arrow following an outside ceasing touch`() {
        // GIVEN
        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        // WHEN
        linkingCommander.onGridTouched(draft(), ceasingTouch())

        // THEN
        verify(observer).invoke(HideSourceMark)
        verify(observer).invoke(HideSourceArrow)
    }

    @Test
    fun `A ceasing touch has no effect when there is no source marked`() {
        // WHEN
        linkingCommander.onGridTouched(draft(), ceasingTouch())

        // THEN
        verify(chooser, never()).stopChoosing()
        verify(observer, never()).invoke(any())
    }

    @Test
    fun `Should stop the choosing once an aborted touch is registered`() {
        // GIVEN
        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        chosenTarget.addInitialChosen(termDraft())

        // WHEN
        linkingCommander.onGridTouched(draft(), abortedTouch())

        // THEN
        verify(chooser).stopChoosing()
    }

    @Test
    fun `Should command the observer to hide the linking following an aborted touch`() {
        // GIVEN
        linkingCommander.onGridTouched(draft(), initialTouch())

        chosenSource.addInitialChosen(termDraft())

        linkingCommander.onGridTouched(draft(), ensuingTouch())

        chosenTarget.addInitialChosen(termDraft())

        // WHEN
        linkingCommander.onGridTouched(draft(), abortedTouch())

        // THEN
        verify(observer).invoke(HideSourceMark)
        verify(observer).invoke(HideTargetMark)
        verify(observer).invoke(HideSourceArrow)
    }

    @Test
    fun `An aborted touch has no effect when there is no source marked`() {
        // WHEN
        linkingCommander.onGridTouched(draft(), abortedTouch())

        // THEN
        verify(chooser, never()).stopChoosing()
        verify(observer, never()).invoke(any())
    }

    @Test
    fun `Should command the observer to hide the linking following an new initial touch`() {
        // GIVEN
        val linkOrigin = origin()
        val linkSource = linkOrigin.first
        val linkTarget = linkOrigin.second

        linkingCommander.onGridTouched(draft(linkOrigin), initialTouch())

        chosenSource.addInitialChosen(termDraft(linkSource))

        linkingCommander.onGridTouched(draft(linkOrigin), ensuingTouch())

        chosenTarget.addInitialChosen(termDraft(linkTarget))

        linkingCommander.onGridTouched(draft(linkOrigin), ceasingTouch())

        // WHEN
        linkingCommander.onGridTouched(draft(), initialTouch())

        // THEN
        verify(observer).invoke(HideSourceMark)
        verify(observer).invoke(HideTargetMark)
        verify(observer).invoke(HideSourceArrow)
    }

    private fun initialTouch(): Touch = mock { on { action } doReturn INITIAL }

    private fun ensuingTouch(): Touch = mock { on { action } doReturn ENSUING }

    private fun ceasingTouch(): Touch = mock { on { action } doReturn CEASING }

    private fun abortedTouch(): Touch = mock { on { action } doReturn ABORTED }

    private fun origin() = operation(positiveTerm(), negativeTerm())
}