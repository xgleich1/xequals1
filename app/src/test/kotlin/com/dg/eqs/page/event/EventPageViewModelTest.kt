package com.dg.eqs.page.event

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dg.eqs.R
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.base.gamification.result.ScoreBoardResult.ScoreBoardMissing
import com.dg.eqs.base.gamification.result.ScoreBoardResult.ScoreBoardPresent
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataEventObserver
import com.dg.eqs.base.observation.LiveDataObserver
import com.dg.eqs.core.competition.Event
import com.dg.eqs.core.competition.EventRepository
import com.dg.eqs.core.progression.Level.EventLevel
import com.dg.eqs.core.progression.LevelKey.EventLevelKey
import com.dg.eqs.page.game.GameConfig
import com.dg.eqs.page.game.GameConfig.SingleLevelGameConfig
import com.dg.eqs.util.rules.MainDispatcherRule
import org.mockito.kotlin.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EventPageViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var event: Event
    @Mock
    private lateinit var eventRepository: EventRepository

    @Mock
    private lateinit var showEventTitleAction: (StringRes) -> Unit
    @Mock
    private lateinit var showEventDraftAction: (AnyOrigin) -> Unit
    @Mock
    private lateinit var scoreButtonEnabledAction: (Boolean) -> Unit
    @Mock
    private lateinit var scoreButtonLoadingAction: (Boolean) -> Unit
    @Mock
    private lateinit var playButtonEnabledAction: (Boolean) -> Unit
    @Mock
    private lateinit var playButtonLoadingAction: (Boolean) -> Unit
    @Mock
    private lateinit var showUploadOverlayAction: (Boolean) -> Unit
    @Mock
    private lateinit var navigateToScoreBoardAction: (Intent) -> Unit
    @Mock
    private lateinit var navigateToGamePageAction: (GameConfig) -> Unit
    @Mock
    private lateinit var navigateBackAction: () -> Unit

    @Mock
    private lateinit var eventLevel: EventLevel
    @Mock
    private lateinit var eventLevelKey: EventLevelKey
    @Mock
    private lateinit var eventLevelExercise: AnyOrigin
    @Mock
    private lateinit var eventScoreBoardIntent: Intent


    @Before
    fun setUp() {
        setupEventLevelAvailable()
        setupEventLevelKeyAvailable()
    }

    @Test
    fun `Should show the event's number by incorporating it into the title`() {
        // GIVEN
        whenever(event.number) doReturn 1

        createAndObserveViewModel()

        // THEN
        verify(showEventTitleAction).invoke(
                StringRes(R.string.event_title, 1))
    }

    @Test
    fun `Should show the event's draft from the exercise of the event level`() {
        // GIVEN
        createAndObserveViewModel()

        // THEN
        verify(showEventDraftAction).invoke(eventLevelExercise)
    }

    @Test
    fun `Should navigate back when the upload overlay is hidden and back is pressed`() {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onBackPressed()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should navigate back when the upload overlay is hidden and the back button is clicked`() {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onBackButtonClicked()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should load the event's score board when the score button is clicked`() = runBlocking<Unit> {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onScoreButtonClicked()

        // THEN
        verify(eventRepository).loadScoreBoard(event)
    }

    @Test
    fun `Should disable the score button before loading the event's score board`() = runBlocking {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onScoreButtonClicked()

        // THEN
        inOrder(scoreButtonEnabledAction, eventRepository) {
            verify(scoreButtonEnabledAction).invoke(false)

            verify(eventRepository).loadScoreBoard(any())
        }
    }

    @Test
    fun `Should show the score button loading before loading the event's score board`() = runBlocking {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onScoreButtonClicked()

        // THEN
        inOrder(scoreButtonLoadingAction, eventRepository) {
            verify(scoreButtonLoadingAction).invoke(true)

            verify(eventRepository).loadScoreBoard(any())
        }
    }

    @Test
    fun `Should disable the play button before loading the event's score board`() = runBlocking {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onScoreButtonClicked()

        // THEN
        inOrder(playButtonEnabledAction, eventRepository) {
            verify(playButtonEnabledAction).invoke(false)

            verify(eventRepository).loadScoreBoard(any())
        }
    }

    @Test
    fun `Should navigate to the event's score board when the score board is present`() = runBlocking {
        // GIVEN
        setupEventScoreBoardPresent()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onScoreButtonClicked()

        // THEN
        verify(navigateToScoreBoardAction).invoke(eventScoreBoardIntent)
    }

    @Test
    fun `Should hide the score button loading when the event's score board is missing`() = runBlocking {
        // GIVEN
        setupEventScoreBoardMissing()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onScoreButtonClicked()

        // THEN
        inOrder(eventRepository, scoreButtonLoadingAction) {
            verify(eventRepository).loadScoreBoard(any())

            verify(scoreButtonLoadingAction).invoke(false)
        }
    }

    @Test
    fun `Should enable the score button when the event's score board is missing`() = runBlocking {
        // GIVEN
        setupEventScoreBoardMissing()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onScoreButtonClicked()

        // THEN
        inOrder(eventRepository, scoreButtonEnabledAction) {
            verify(eventRepository).loadScoreBoard(any())

            verify(scoreButtonEnabledAction).invoke(true)
        }
    }

    @Test
    fun `Should enable the play button when the event's score board is missing`() = runBlocking {
        // GIVEN
        setupEventScoreBoardMissing()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onScoreButtonClicked()

        // THEN
        inOrder(eventRepository, playButtonEnabledAction) {
            verify(eventRepository).loadScoreBoard(any())

            verify(playButtonEnabledAction).invoke(true)
        }
    }

    @Test
    fun `Should refresh the event's level when the play button is clicked`() = runBlocking {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onPlayButtonClicked()

        // THEN
        verify(eventRepository).refreshLevel(event)
    }

    @Test
    fun `Should disable the score button before refreshing the event's level`() = runBlocking {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onPlayButtonClicked()

        // THEN
        inOrder(scoreButtonEnabledAction, eventRepository) {
            verify(scoreButtonEnabledAction).invoke(false)

            verify(eventRepository).refreshLevel(any())
        }
    }

    @Test
    fun `Should disable the play button before refreshing the event's level`() = runBlocking {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onPlayButtonClicked()

        // THEN
        inOrder(playButtonEnabledAction, eventRepository) {
            verify(playButtonEnabledAction).invoke(false)

            verify(eventRepository).refreshLevel(any())
        }
    }

    @Test
    fun `Should show the play button loading before refreshing the event's level`() = runBlocking {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onPlayButtonClicked()

        // THEN
        inOrder(playButtonLoadingAction, eventRepository) {
            verify(playButtonLoadingAction).invoke(true)

            verify(eventRepository).refreshLevel(any())
        }
    }

    @Test
    fun `Should navigate to the single level game page when the event's level is refreshed`() = runBlocking {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onPlayButtonClicked()

        // THEN
        inOrder(eventRepository, navigateToGamePageAction) {
            verify(eventRepository).refreshLevel(any())

            verify(navigateToGamePageAction).invoke(
                    SingleLevelGameConfig(eventLevelKey))
        }
    }

    @Test
    fun `Should hide the score button loading when navigating back from the score board`() {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onNavigatedBackFromScoreBoard()

        // THEN
        verify(scoreButtonLoadingAction).invoke(false)
    }

    @Test
    fun `Should enable the score button when navigating back from the score board`() {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onNavigatedBackFromScoreBoard()

        // THEN
        verify(scoreButtonEnabledAction).invoke(true)
    }

    @Test
    fun `Should enable the play button when navigating back from the score board`() {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onNavigatedBackFromScoreBoard()

        // THEN
        verify(playButtonEnabledAction).invoke(true)
    }

    @Test
    fun `Should submit the event's score when navigating back from the game page`() = runBlocking {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onNavigatedBackFromGamePage()

        // THEN
        verify(eventRepository).submitScore(event)
    }

    @Test
    fun `Should show the upload overlay before submitting the event's score`() = runBlocking {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onNavigatedBackFromGamePage()

        // THEN
        inOrder(showUploadOverlayAction, eventRepository) {
            verify(showUploadOverlayAction).invoke(true)

            verify(eventRepository).submitScore(any())
        }
    }

    @Test
    fun `Should enable the score button before submitting the event's score`() = runBlocking {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onNavigatedBackFromGamePage()

        // THEN
        inOrder(scoreButtonEnabledAction, eventRepository) {
            verify(scoreButtonEnabledAction).invoke(true)

            verify(eventRepository).submitScore(any())
        }
    }

    @Test
    fun `Should hide the play button loading before submitting the event's score`() = runBlocking {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onNavigatedBackFromGamePage()

        // THEN
        inOrder(playButtonLoadingAction, eventRepository) {
            verify(playButtonLoadingAction).invoke(false)

            verify(eventRepository).submitScore(any())
        }
    }

    @Test
    fun `Should enable the play button before submitting the event's score`() = runBlocking {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onNavigatedBackFromGamePage()

        // THEN
        inOrder(playButtonEnabledAction, eventRepository) {
            verify(playButtonEnabledAction).invoke(true)

            verify(eventRepository).submitScore(any())
        }
    }

    @Test
    fun `Should hide the upload overlay after submitting the event's score`() = runBlocking {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onNavigatedBackFromGamePage()

        // THEN
        inOrder(eventRepository, showUploadOverlayAction) {
            verify(eventRepository).submitScore(any())

            verify(showUploadOverlayAction).invoke(false)
        }
    }

    private fun setupEventLevelAvailable() {
        whenever(eventLevel.exercise) doReturn eventLevelExercise

        whenever(eventRepository.loadLevel(event)) doReturn eventLevel
    }

    private fun setupEventLevelKeyAvailable() =
            whenever(event.levelKey) doReturn eventLevelKey

    private suspend fun setupEventScoreBoardPresent() =
            whenever(eventRepository.loadScoreBoard(event)) doReturn
                    ScoreBoardPresent(eventScoreBoardIntent)

    private suspend fun setupEventScoreBoardMissing() =
            whenever(eventRepository.loadScoreBoard(event)) doReturn
                    ScoreBoardMissing()

    private fun createAndObserveViewModel(): EventPageViewModel {
        val viewModel = EventPageViewModel(
                event,
                eventRepository)

        viewModel.showEventTitle.observeForever(
                LiveDataObserver(showEventTitleAction))
        viewModel.showEventDraft.observeForever(
                LiveDataObserver(showEventDraftAction))
        viewModel.scoreButtonEnabled.observeForever(
                LiveDataObserver(scoreButtonEnabledAction))
        viewModel.scoreButtonLoading.observeForever(
                LiveDataObserver(scoreButtonLoadingAction))
        viewModel.playButtonEnabled.observeForever(
                LiveDataObserver(playButtonEnabledAction))
        viewModel.playButtonLoading.observeForever(
                LiveDataObserver(playButtonLoadingAction))
        viewModel.showUploadOverlay.observeForever(
                LiveDataObserver(showUploadOverlayAction))
        viewModel.navigateToScoreBoard.observeForever(
                LiveDataEventObserver(navigateToScoreBoardAction))
        viewModel.navigateToGamePage.observeForever(
                LiveDataEventObserver(navigateToGamePageAction))
        viewModel.navigateBack.observeForever(
                LiveDataCommandObserver(navigateBackAction))

        return viewModel
    }
}