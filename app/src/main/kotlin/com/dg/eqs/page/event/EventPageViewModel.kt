package com.dg.eqs.page.event

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dg.eqs.R.string.event_title
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.base.gamification.result.ScoreBoardResult.ScoreBoardMissing
import com.dg.eqs.base.gamification.result.ScoreBoardResult.ScoreBoardPresent
import com.dg.eqs.base.observation.LiveDataCommand
import com.dg.eqs.base.observation.LiveDataEvent
import com.dg.eqs.base.observation.emit
import com.dg.eqs.core.competition.Event
import com.dg.eqs.core.competition.EventRepository
import com.dg.eqs.page.game.GameConfig
import com.dg.eqs.page.game.GameConfig.SingleLevelGameConfig
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


class EventPageViewModel(
        private val event: Event,
        private val eventRepository: EventRepository
) : ViewModel() {

    val showEventTitle = MutableLiveData<StringRes>()
    val showEventDraft = MutableLiveData<AnyOrigin>()
    val scoreButtonEnabled = MutableLiveData<Boolean>()
    val scoreButtonLoading = MutableLiveData<Boolean>()
    val playButtonEnabled = MutableLiveData<Boolean>()
    val playButtonLoading = MutableLiveData<Boolean>()
    val showUploadOverlay = MutableLiveData<Boolean>()
    val navigateToScoreBoard = MutableLiveData<LiveDataEvent<Intent>>()
    val navigateToGamePage = MutableLiveData<LiveDataEvent<GameConfig>>()
    val navigateBack = MutableLiveData<LiveDataCommand>()


    init {
        emitEventTitle()
        emitEventDraft()
    }

    fun onBackPressed() =
            onBackButtonClicked()

    fun onBackButtonClicked() {
        if(showUploadOverlay.value != true) {
            emitNavigateBack()
        }
    }

    fun onScoreButtonClicked() {
        emitDisableScoreButton()
        showScoreButtonLoading()
        emitDisablePlayButton()

        viewModelScope.launch(Main) {
            val scoreBoardResult =
                    eventRepository.loadScoreBoard(event)

            when(scoreBoardResult) {
                is ScoreBoardPresent ->
                    emitNavigateToScoreBoard(
                            scoreBoardResult.intent)
                is ScoreBoardMissing -> {
                    hideScoreButtonLoading()
                    emitEnableScoreButton()
                    emitEnablePlayButton()
                }
            }
        }
    }

    fun onPlayButtonClicked() {
        emitDisableScoreButton()
        emitDisablePlayButton()
        showPlayButtonLoading()

        viewModelScope.launch(Main) {
            eventRepository.refreshLevel(event)

            emitNavigateToGamePage()
        }
    }

    fun onNavigatedBackFromScoreBoard() {
        hideScoreButtonLoading()
        emitEnableScoreButton()
        emitEnablePlayButton()
    }

    fun onNavigatedBackFromGamePage() {
        emitShowUploadOverlay()
        emitEnableScoreButton()
        hidePlayButtonLoading()
        emitEnablePlayButton()

        viewModelScope.launch(Main) {
            eventRepository.submitScore(event)

            emitHideUploadOverlay()
        }
    }

    private fun emitEventTitle() {
        val title = StringRes(event_title, event.number)

        showEventTitle.emit(title)
    }

    private fun emitEventDraft() {
        val level = eventRepository.loadLevel(event)

        showEventDraft.emit(level.exercise)
    }

    private fun emitEnableScoreButton() = scoreButtonEnabled.emit(true)

    private fun emitDisableScoreButton() = scoreButtonEnabled.emit(false)

    private fun showScoreButtonLoading() = scoreButtonLoading.emit(true)

    private fun hideScoreButtonLoading() = scoreButtonLoading.emit(false)

    private fun emitEnablePlayButton() = playButtonEnabled.emit(true)

    private fun emitDisablePlayButton() = playButtonEnabled.emit(false)

    private fun showPlayButtonLoading() = playButtonLoading.emit(true)

    private fun hidePlayButtonLoading() = playButtonLoading.emit(false)

    private fun emitShowUploadOverlay() = showUploadOverlay.emit(true)

    private fun emitHideUploadOverlay() = showUploadOverlay.emit(false)

    private fun emitNavigateToScoreBoard(scoreBoardIntent: Intent) =
            navigateToScoreBoard.emit(scoreBoardIntent)

    private fun emitNavigateToGamePage() {
        val gameConfig = SingleLevelGameConfig(event.levelKey)

        navigateToGamePage.emit(gameConfig)
    }

    private fun emitNavigateBack() = navigateBack.emit()
}