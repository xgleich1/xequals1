package com.dg.eqs.page.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.TermDrafts
import com.dg.eqs.base.composition.Size
import com.dg.eqs.base.enveloping.ColorRes
import com.dg.eqs.base.enveloping.ColorRes.*
import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataEventObserver
import com.dg.eqs.base.observation.LiveDataObserver
import com.dg.eqs.base.observation.Observable
import com.dg.eqs.classes.info
import com.dg.eqs.classes.invalidInfo
import com.dg.eqs.classes.validInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.interaction.linking.LinkingCommand
import com.dg.eqs.core.interaction.linking.LinkingCommand.*
import com.dg.eqs.core.interaction.linking.LinkingCommander
import com.dg.eqs.core.interaction.scrolling.ScrollingCommand
import com.dg.eqs.core.interaction.scrolling.ScrollingCommand.*
import com.dg.eqs.core.interaction.scrolling.ScrollingCommander
import com.dg.eqs.core.orchestration.GameCommand
import com.dg.eqs.core.orchestration.GameCommand.*
import com.dg.eqs.core.orchestration.GameCommander
import org.mockito.kotlin.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GamePageViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var gameConfig: GameConfig
    @Mock
    private lateinit var gameDrafter: GameDrafter
    @Mock
    private lateinit var gameTracking: GameTracking
    @Mock
    private lateinit var gameCommander: GameCommander
    @Mock
    private lateinit var linkingCommander: LinkingCommander
    @Mock
    private lateinit var scrollingCommander: ScrollingCommander

    private lateinit var viewModel: GamePageViewModel

    private lateinit var gameCommands: Observable<GameCommand>

    private lateinit var linkingCommands: Observable<LinkingCommand>

    private lateinit var scrollingCommands: Observable<ScrollingCommand>

    @Mock
    private lateinit var showRevertButtonAction: (Boolean) -> Unit
    @Mock
    private lateinit var showInvertButtonAction: (Boolean) -> Unit
    @Mock
    private lateinit var showSkipButtonAction: (Boolean) -> Unit
    @Mock
    private lateinit var showDraftAction: (AnyDraft) -> Unit
    @Mock
    private lateinit var showSourceMarkAction: (TermDrafts) -> Unit
    @Mock
    private lateinit var showTargetMarkAction: (TermDrafts) -> Unit
    @Mock
    private lateinit var showSourceArrowAction: (Touch) -> Unit
    @Mock
    private lateinit var showGridLinesAction: () -> Unit
    @Mock
    private lateinit var showFinishedIconAction: (Boolean) -> Unit
    @Mock
    private lateinit var showStatusAction: (StringRes) -> Unit
    @Mock
    private lateinit var showInfoContentAction: (LayoutRes) -> Unit
    @Mock
    private lateinit var showInfoOverlayAction: (Boolean) -> Unit
    @Mock
    private lateinit var hideSourceMarkAction: () -> Unit
    @Mock
    private lateinit var hideTargetMarkAction: () -> Unit
    @Mock
    private lateinit var hideSourceArrowAction: () -> Unit
    @Mock
    private lateinit var hideGridLinesAction: () -> Unit
    @Mock
    private lateinit var scrollGridXAction: (Int) -> Unit
    @Mock
    private lateinit var scrollGridYAction: (Int) -> Unit
    @Mock
    private lateinit var colorInfoButtonAction: (ColorRes) -> Unit
    @Mock
    private lateinit var colorSourceMarkAction: (ColorRes) -> Unit
    @Mock
    private lateinit var colorTargetMarkAction: (ColorRes) -> Unit
    @Mock
    private lateinit var colorSourceArrowAction: (ColorRes) -> Unit
    @Mock
    private lateinit var navigateBackAction: () -> Unit

    @Mock
    private lateinit var originA: AnyOrigin
    @Mock
    private lateinit var originB: AnyOrigin
    @Mock
    private lateinit var draftA: AnyDraft
    @Mock
    private lateinit var draftB: AnyDraft


    @Before
    fun setUp() {
        whenever(gameConfig.showRevertButton) doReturn true
        whenever(gameConfig.showInvertButton) doReturn true
        whenever(gameConfig.showSkipButton) doReturn true

        gameCommands = Observable()
        linkingCommands = Observable()
        scrollingCommands = Observable()

        whenever(gameCommander.commands) doReturn gameCommands
        whenever(linkingCommander.commands) doReturn linkingCommands
        whenever(scrollingCommander.commands) doReturn scrollingCommands

        viewModel = createAndObserveViewModel()

        whenever(gameDrafter.draft(originA)) doReturn draftA
        whenever(gameDrafter.draft(originB)) doReturn draftB
    }

    @Test
    fun `Should hide the revert button when it shouldn't be shown`() {
        // GIVEN
        whenever(gameConfig.showRevertButton) doReturn false

        createAndObserveViewModel()

        // THEN
        verify(showRevertButtonAction).invoke(false)
    }

    @Test
    fun `Should not hide the revert button when it should be shown`() {
        // GIVEN
        whenever(gameConfig.showRevertButton) doReturn true

        createAndObserveViewModel()

        // THEN
        verify(showRevertButtonAction, never()).invoke(false)
    }

    @Test
    fun `Should hide the invert button when it shouldn't be shown`() {
        // GIVEN
        whenever(gameConfig.showInvertButton) doReturn false

        createAndObserveViewModel()

        // THEN
        verify(showInvertButtonAction).invoke(false)
    }

    @Test
    fun `Should not hide the invert button when it should be shown`() {
        // GIVEN
        whenever(gameConfig.showInvertButton) doReturn true

        createAndObserveViewModel()

        // THEN
        verify(showInvertButtonAction, never()).invoke(false)
    }

    @Test
    fun `Should hide the skip button button when it shouldn't be shown`() {
        // GIVEN
        whenever(gameConfig.showSkipButton) doReturn false

        createAndObserveViewModel()

        // THEN
        verify(showSkipButtonAction).invoke(false)
    }

    @Test
    fun `Should not hide the skip button when it should be shown`() {
        // GIVEN
        whenever(gameConfig.showSkipButton) doReturn true

        createAndObserveViewModel()

        // THEN
        verify(showSkipButtonAction, never()).invoke(false)
    }

    @Test
    fun `Should inform the game commander when the page is created`() {
        verify(gameCommander).onPageCreated()
    }

    @Test
    fun `Should hide the info overlay when its shown and back is pressed`() {
        // GIVEN
        gameCommands.emit(ShowInfo(info()))

        viewModel.onInfoButtonClicked()

        // WHEN
        viewModel.onBackPressed()

        // THEN
        verify(showInfoOverlayAction).invoke(false)
    }

    @Test
    fun `Should not hide the info overlay when it isn't shown and back is pressed`() {
        // WHEN
        viewModel.onBackPressed()

        // THEN
        verify(showInfoOverlayAction, never()).invoke(any())
    }

    @Test
    fun `Should navigate back when the info overlay isn't shown & back is pressed`() {
        // WHEN
        viewModel.onBackPressed()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should not navigate back when the info overlay is shown & back is pressed`() {
        // GIVEN
        gameCommands.emit(ShowInfo(info()))

        viewModel.onInfoButtonClicked()

        // WHEN
        viewModel.onBackPressed()

        // THEN
        verify(navigateBackAction, never()).invoke()
    }

    @Test
    fun `Should navigate back when the back button is clicked`() {
        // WHEN
        viewModel.onBackButtonClicked()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should inform the game tracking when the invert button is clicked`() {
        // WHEN
        viewModel.onInvertButtonClicked()

        // THEN
        verify(gameTracking).onInvertButtonClicked()
    }

    @Test
    fun `Should inform the game commander when the invert button is clicked`() {
        // WHEN
        viewModel.onInvertButtonClicked()

        // THEN
        verify(gameCommander).onInvertButtonClicked()
    }

    @Test
    fun `Should inform the linking commander when the invert button is clicked`() {
        // WHEN
        viewModel.onInvertButtonClicked()

        // THEN
        verify(linkingCommander).onInvertButtonClicked()
    }

    @Test
    fun `Should inform the game tracking when the revert button is clicked`() {
        // WHEN
        viewModel.onRevertButtonClicked()

        // THEN
        verify(gameTracking).onRevertButtonClicked()
    }

    @Test
    fun `Should inform the game commander when the revert button is clicked`() {
        // WHEN
        viewModel.onRevertButtonClicked()

        // THEN
        verify(gameCommander).onRevertButtonClicked()
    }

    @Test
    fun `Should inform the linking commander when the revert button is clicked`() {
        // WHEN
        viewModel.onRevertButtonClicked()

        // THEN
        verify(linkingCommander).onRevertButtonClicked()
    }

    @Test
    fun `Should inform the game tracking when the skip button is clicked`() {
        // WHEN
        viewModel.onSkipButtonClicked()

        // THEN
        verify(gameTracking).onSkipButtonClicked()
    }

    @Test
    fun `Should inform the game commander when the skip button is clicked`() {
        // WHEN
        viewModel.onSkipButtonClicked()

        // THEN
        verify(gameCommander).onSkipButtonClicked()
    }

    @Test
    fun `Should inform the linking commander when the skip button is clicked`() {
        // WHEN
        viewModel.onSkipButtonClicked()

        // THEN
        verify(linkingCommander).onSkipButtonClicked()
    }

    @Test
    fun `Should inform the game tracking when the finished icon is clicked`() {
        // WHEN
        viewModel.onFinishedIconClicked()

        // THEN
        verify(gameTracking).onFinishedIconClicked()
    }

    @Test
    fun `Should inform the game commander when the finished icon is clicked`() {
        // WHEN
        viewModel.onFinishedIconClicked()

        // THEN
        verify(gameCommander).onFinishedIconClicked()
    }

    @Test
    fun `Should inform the game tracking when the info button is clicked`() {
        // WHEN
        viewModel.onInfoButtonClicked()

        // THEN
        verify(gameTracking).onInfoButtonClicked()
    }

    @Test
    fun `Should inform the linking commander when the info button is clicked`() {
        // WHEN
        viewModel.onInfoButtonClicked()

        // THEN
        verify(linkingCommander).onInfoButtonClicked()
    }

    @Test
    fun `Should show the content of the initial info when the info button is clicked`() {
        // GIVEN
        val initialInfoContent: LayoutRes = mock()

        gameCommands.emit(ShowInfo(info(initialInfoContent)))

        // WHEN
        viewModel.onInfoButtonClicked()

        // THEN
        verify(showInfoContentAction).invoke(initialInfoContent)
    }

    @Test
    fun `Should show the content of an ensuing info when the info button is clicked`() {
        // GIVEN
        val initialInfoContent: LayoutRes = mock()
        val ensuingInfoContent: LayoutRes = mock()

        gameCommands.emit(ShowInfo(info(initialInfoContent)))
        gameCommands.emit(ShowInfo(info(ensuingInfoContent)))

        // WHEN
        viewModel.onInfoButtonClicked()

        // THEN
        verify(showInfoContentAction).invoke(ensuingInfoContent)
    }

    @Test
    fun `Should not show the content of a info again when the info button is clicked`() {
        // GIVEN
        gameCommands.emit(ShowInfo(info()))

        viewModel.onInfoButtonClicked()

        clearInvocations(showInfoContentAction)

        // WHEN
        viewModel.onInfoButtonClicked()

        // THEN
        verify(showInfoContentAction, never()).invoke(any())
    }

    @Test
    fun `Should show the info overlay when the info button is clicked`() {
        // GIVEN
        gameCommands.emit(ShowInfo(info()))

        // WHEN
        viewModel.onInfoButtonClicked()

        // THEN
        verify(showInfoOverlayAction).invoke(true)
    }

    @Test
    fun `Should hide the info overlay when the info overlay is clicked`() {
        // GIVEN
        gameCommands.emit(ShowInfo(info()))

        viewModel.onInfoButtonClicked()

        // WHEN
        viewModel.onInfoOverlayClicked()

        // THEN
        verify(showInfoOverlayAction).invoke(false)
    }

    @Test
    fun `Should inform the scrolling commander when the game grid is laid out`() {
        // GIVEN
        val gameGridSize: Size = mock()

        // WHEN
        viewModel.onGameGridLaidOut(gameGridSize)

        // THEN
        verify(scrollingCommander).onGridLaidOut(gameGridSize)
    }

    @Test
    fun `Should show the draft centered in the game grid every time its laid out`() {
        // GIVEN
        whenever(draftA.centerX) doReturn 400
        whenever(draftA.centerY) doReturn 200

        gameCommands.emit(ShowOrigin(originA))

        // WHEN
        viewModel.onGameGridLaidOut(Size(1000, 800))

        // THEN
        verify(draftA).moveX(100)
        verify(draftA).moveY(200)

        verify(showDraftAction).invoke(draftA)
    }

    @Test
    fun `Should inform the linking commander when the game grid is touched`() {
        // GIVEN
        gameCommands.emit(ShowOrigin(originA))

        val gameGridTouch: Touch = mock()

        // WHEN
        viewModel.onGameGridTouched(gameGridTouch)

        // THEN
        verify(linkingCommander).onGridTouched(draftA, gameGridTouch)
    }

    @Test
    fun `Should inform the scrolling commander when the game grid is touched`() {
        // GIVEN
        gameCommands.emit(ShowOrigin(originA))

        val gameGridTouch: Touch = mock()

        // WHEN
        viewModel.onGameGridTouched(gameGridTouch)

        // THEN
        verify(scrollingCommander).onGridTouched(draftA, gameGridTouch)
    }

    //<editor-fold desc="GameCommand execution">
    @Test
    fun `Should show the info content when the game commands to show a vital info`() {
        // GIVEN
        val infoContent: LayoutRes = mock()

        // WHEN
        gameCommands.emit(ShowInfo(info(infoContent, isVital = true)))

        // THEN
        verify(showInfoContentAction).invoke(infoContent)
    }

    @Test
    fun `Should show the info overlay when the game commands to show a vital info`() {
        // WHEN
        gameCommands.emit(ShowInfo(info(isVital = true)))

        // THEN
        verify(showInfoOverlayAction).invoke(true)
    }

    @Test
    fun `Should not show the info content when the game commands to show a info which isn't vital`() {
        // WHEN
        gameCommands.emit(ShowInfo(info(isVital = false)))

        // THEN
        verify(showInfoContentAction, never()).invoke(any())
    }

    @Test
    fun `Should not show the info overlay when the game commands to show a info which isn't vital`() {
        // WHEN
        gameCommands.emit(ShowInfo(info(isVital = false)))

        // THEN
        verify(showInfoOverlayAction, never()).invoke(any())
    }

    @Test
    fun `Should reset the color of the info button when the game commands to show a valid info`() {
        // WHEN
        gameCommands.emit(ShowInfo(validInfo()))

        // THEN
        verify(colorInfoButtonAction).invoke(WHITE)
    }

    @Test
    fun `Should hide the source mark when the game commands to show a valid info`() {
        // WHEN
        gameCommands.emit(ShowInfo(validInfo()))

        // THEN
        verify(hideSourceMarkAction).invoke()
    }

    @Test
    fun `Should hide the target mark when the game commands to show a valid info`() {
        // WHEN
        gameCommands.emit(ShowInfo(validInfo()))

        // THEN
        verify(hideTargetMarkAction).invoke()
    }

    @Test
    fun `Should hide the source arrow when the game commands to show a valid info`() {
        // WHEN
        gameCommands.emit(ShowInfo(validInfo()))

        // THEN
        verify(hideSourceArrowAction).invoke()
    }

    @Test
    fun `Should accent the info button when the game commands to show an invalid info`() {
        // WHEN
        gameCommands.emit(ShowInfo(invalidInfo()))

        // THEN
        verify(colorInfoButtonAction).invoke(RED)
    }

    @Test
    fun `Should accent the source mark when the game commands to show an invalid info`() {
        // WHEN
        gameCommands.emit(ShowInfo(invalidInfo()))

        // THEN
        verify(colorSourceMarkAction).invoke(RED)
    }

    @Test
    fun `Should accent the target mark when the game commands to show an invalid info`() {
        // WHEN
        gameCommands.emit(ShowInfo(invalidInfo()))

        // THEN
        verify(colorTargetMarkAction).invoke(RED)
    }

    @Test
    fun `Should accent the source arrow when the game commands to show an invalid info`() {
        // WHEN
        gameCommands.emit(ShowInfo(invalidInfo()))

        // THEN
        verify(colorSourceArrowAction).invoke(RED)
    }

    @Test
    fun `Should show the initial origins draft when the game grid is laid out`() {
        // GIVEN
        gameCommands.emit(ShowOrigin(originA))

        // WHEN
        viewModel.onGameGridLaidOut(mock())

        // THEN
        verify(showDraftAction).invoke(draftA)
    }

    @Test
    fun `Should show an ensuing origins draft centered to their predecessor`() {
        // GIVEN
        whenever(draftA.centerX) doReturn 400
        whenever(draftA.centerY) doReturn 600

        gameCommands.emit(ShowOrigin(originA))

        whenever(draftB.centerX) doReturn 300
        whenever(draftB.centerY) doReturn 400

        // WHEN
        gameCommands.emit(ShowOrigin(originB))

        // THEN
        verify(draftB).moveX(100)
        verify(draftB).moveY(200)

        verify(showDraftAction).invoke(draftB)
    }

    @Test
    fun `Should show the status when the game commands it`() {
        // GIVEN
        val status: StringRes = mock()

        // WHEN
        gameCommands.emit(ShowStatus(status))

        // THEN
        verify(showStatusAction).invoke(status)
    }

    @Test
    fun `Should hide the revert button when showing the finished layout`() {
        // WHEN
        gameCommands.emit(ShowFinishedLayout)

        // THEN
        verify(showRevertButtonAction).invoke(false)
    }

    @Test
    fun `Should hide the invert button when showing the finished layout`() {
        // WHEN
        gameCommands.emit(ShowFinishedLayout)

        // THEN
        verify(showInvertButtonAction).invoke(false)
    }

    @Test
    fun `Should hide the skip button when showing the finished layout`() {
        // WHEN
        gameCommands.emit(ShowFinishedLayout)

        // THEN
        verify(showSkipButtonAction).invoke(false)
    }

    @Test
    fun `Should show the finished icon when showing the finished layout`() {
        // WHEN
        gameCommands.emit(ShowFinishedLayout)

        // THEN
        verify(showFinishedIconAction).invoke(true)
    }

    @Test
    fun `Should show the revert button when it should be shown when hiding the finished layout`() {
        // GIVEN
        whenever(gameConfig.showRevertButton) doReturn true

        // WHEN
        gameCommands.emit(HideFinishedLayout)

        // THEN
        verify(showRevertButtonAction).invoke(true)
    }

    @Test
    fun `Should not show the revert button when it shouldn't be shown when hiding the finished layout`() {
        // GIVEN
        whenever(gameConfig.showRevertButton) doReturn false

        // WHEN
        gameCommands.emit(HideFinishedLayout)

        // THEN
        verify(showRevertButtonAction, never()).invoke(true)
    }

    @Test
    fun `Should show the invert button when it should be shown when hiding the finished layout`() {
        // GIVEN
        whenever(gameConfig.showInvertButton) doReturn true

        // WHEN
        gameCommands.emit(HideFinishedLayout)

        // THEN
        verify(showInvertButtonAction).invoke(true)
    }

    @Test
    fun `Should not show the invert button when it shouldn't be shown when hiding the finished layout`() {
        // GIVEN
        whenever(gameConfig.showInvertButton) doReturn false

        // WHEN
        gameCommands.emit(HideFinishedLayout)

        // THEN
        verify(showInvertButtonAction, never()).invoke(true)
    }

    @Test
    fun `Should show the skip button when it should be shown when hiding the finished layout`() {
        // GIVEN
        whenever(gameConfig.showSkipButton) doReturn true

        // WHEN
        gameCommands.emit(HideFinishedLayout)

        // THEN
        verify(showSkipButtonAction).invoke(true)
    }

    @Test
    fun `Should not show the skip button when it shouldn't be shown when hiding the finished layout`() {
        // GIVEN
        whenever(gameConfig.showSkipButton) doReturn false

        // WHEN
        gameCommands.emit(HideFinishedLayout)

        // THEN
        verify(showSkipButtonAction, never()).invoke(true)
    }

    @Test
    fun `Should hide the finished icon button when hiding the finished layout`() {
        // WHEN
        gameCommands.emit(HideFinishedLayout)

        // THEN
        verify(showFinishedIconAction).invoke(false)
    }

    @Test
    fun `Should navigate back when the game commands it`() {
        // WHEN
        gameCommands.emit(NavigateBack)

        // THEN
        verify(navigateBackAction).invoke()
    }
    //</editor-fold>

    //<editor-fold desc="LinkingCommand execution">
    @Test
    fun `Should reset the color of the source mark before showing it`() {
        // WHEN
        linkingCommands.emit(ShowSourceMark(mock()))

        // THEN
        verify(colorSourceMarkAction).invoke(WHITE)
    }

    @Test
    fun `Should show the source mark when the linking commands it`() {
        // GIVEN
        val source: TermDrafts = mock()

        // WHEN
        linkingCommands.emit(ShowSourceMark(source))

        // THEN
        verify(showSourceMarkAction).invoke(source)
    }

    @Test
    fun `Should reset the color of the target mark before showing it`() {
        // WHEN
        linkingCommands.emit(ShowTargetMark(mock()))

        // THEN
        verify(colorTargetMarkAction).invoke(GREEN)
    }

    @Test
    fun `Should show the target mark when the linking commands it`() {
        // GIVEN
        val target: TermDrafts = mock()

        // WHEN
        linkingCommands.emit(ShowTargetMark(target))

        // THEN
        verify(showTargetMarkAction).invoke(target)
    }

    @Test
    fun `Should reset the color of the source arrow before showing it`() {
        // WHEN
        linkingCommands.emit(ShowSourceArrow(mock()))

        // THEN
        verify(colorSourceArrowAction).invoke(GREEN)
    }

    @Test
    fun `Should show the source arrow when the linking commands it`() {
        // GIVEN
        val touch: Touch = mock()

        // WHEN
        linkingCommands.emit(ShowSourceArrow(touch))

        // THEN
        verify(showSourceArrowAction).invoke(touch)
    }

    @Test
    fun `Should handle the link by informing the game commander`() {
        // GIVEN
        val link: Link = mock()

        // WHEN
        linkingCommands.emit(HandleLink(link))

        // THEN
        verify(gameCommander).onLinkCreated(link)
    }

    @Test
    fun `Should hide the source mark when the linking commands it`() {
        // WHEN
        linkingCommands.emit(HideSourceMark)

        // THEN
        verify(hideSourceMarkAction).invoke()
    }

    @Test
    fun `Should hide the target mark when the linking commands it`() {
        // WHEN
        linkingCommands.emit(HideTargetMark)

        // THEN
        verify(hideTargetMarkAction).invoke()
    }

    @Test
    fun `Should hide the source arrow when the linking commands it`() {
        // WHEN
        linkingCommands.emit(HideSourceArrow)

        // THEN
        verify(hideSourceArrowAction).invoke()
    }
    //</editor-fold>

    //<editor-fold desc="ScrollingCommand execution">
    @Test
    fun `Should show the grid lines when the scrolling commands it`() {
        // WHEN
        scrollingCommands.emit(ShowGridLines)

        // THEN
        verify(showGridLinesAction).invoke()
    }

    @Test
    fun `Should move the draft when the grid is scrolled in x-direction`() {
        // GIVEN
        gameCommands.emit(ShowOrigin(originA))

        // WHEN
        scrollingCommands.emit(ScrollGridX(100))

        // THEN
        verify(draftA).moveX(100)
    }

    @Test
    fun `Should scroll the grid in x-direction when the linking commands it`() {
        // WHEN
        scrollingCommands.emit(ScrollGridX(100))

        // THEN
        verify(scrollGridXAction).invoke(100)
    }

    @Test
    fun `Should move the draft when the grid is scrolled in y-direction`() {
        // GIVEN
        gameCommands.emit(ShowOrigin(originA))

        // WHEN
        scrollingCommands.emit(ScrollGridY(100))

        // THEN
        verify(draftA).moveY(100)
    }

    @Test
    fun `Should scroll the grid in y-direction when the linking commands it`() {
        // WHEN
        scrollingCommands.emit(ScrollGridY(100))

        // THEN
        verify(scrollGridYAction).invoke(100)
    }

    @Test
    fun `Should hide the grid lines when the scrolling commands it`() {
        // WHEN
        scrollingCommands.emit(HideGridLines)

        // THEN
        verify(hideGridLinesAction).invoke()
    }
    //</editor-fold>

    private fun createAndObserveViewModel(): GamePageViewModel {
        val viewModel = GamePageViewModel(
                gameConfig,
                gameDrafter,
                gameTracking,
                gameCommander,
                linkingCommander,
                scrollingCommander)

        viewModel.showRevertButton.observeForever(
                LiveDataObserver(showRevertButtonAction))
        viewModel.showInvertButton.observeForever(
                LiveDataObserver(showInvertButtonAction))
        viewModel.showSkipButton.observeForever(
                LiveDataObserver(showSkipButtonAction))
        viewModel.showDraft.observeForever(
                LiveDataEventObserver(showDraftAction))
        viewModel.showSourceMark.observeForever(
                LiveDataEventObserver(showSourceMarkAction))
        viewModel.showTargetMark.observeForever(
                LiveDataEventObserver(showTargetMarkAction))
        viewModel.showSourceArrow.observeForever(
                LiveDataEventObserver(showSourceArrowAction))
        viewModel.showGridLines.observeForever(
                LiveDataCommandObserver(showGridLinesAction))
        viewModel.showFinishedIcon.observeForever(
                LiveDataObserver(showFinishedIconAction))
        viewModel.showStatus.observeForever(
                LiveDataObserver(showStatusAction))
        viewModel.showInfoContent.observeForever(
                LiveDataObserver(showInfoContentAction))
        viewModel.showInfoOverlay.observeForever(
                LiveDataObserver(showInfoOverlayAction))
        viewModel.hideSourceMark.observeForever(
                LiveDataCommandObserver(hideSourceMarkAction))
        viewModel.hideTargetMark.observeForever(
                LiveDataCommandObserver(hideTargetMarkAction))
        viewModel.hideSourceArrow.observeForever(
                LiveDataCommandObserver(hideSourceArrowAction))
        viewModel.hideGridLines.observeForever(
                LiveDataCommandObserver(hideGridLinesAction))
        viewModel.scrollGridX.observeForever(
                LiveDataEventObserver(scrollGridXAction))
        viewModel.scrollGridY.observeForever(
                LiveDataEventObserver(scrollGridYAction))
        viewModel.colorInfoButton.observeForever(
                LiveDataObserver(colorInfoButtonAction))
        viewModel.colorSourceMark.observeForever(
                LiveDataEventObserver(colorSourceMarkAction))
        viewModel.colorTargetMark.observeForever(
                LiveDataEventObserver(colorTargetMarkAction))
        viewModel.colorSourceArrow.observeForever(
                LiveDataEventObserver(colorSourceArrowAction))
        viewModel.navigateBack.observeForever(
                LiveDataCommandObserver(navigateBackAction))

        return viewModel
    }
}