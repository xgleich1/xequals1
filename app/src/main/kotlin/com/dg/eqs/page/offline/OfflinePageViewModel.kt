package com.dg.eqs.page.offline

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dg.eqs.R
import com.dg.eqs.base.enveloping.HtmlRes
import com.dg.eqs.base.observation.LiveDataCommand
import com.dg.eqs.base.observation.LiveDataEvent
import com.dg.eqs.base.observation.emit
import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase
import com.dg.eqs.page.game.GameConfig
import com.dg.eqs.page.game.GameConfig.EndlessLevelGameConfig


class OfflinePageViewModel(
        private val presetLevelDatabase: PresetLevelDatabase,
        private val generatedLevelDatabase: GeneratedLevelDatabase) : ViewModel() {

    val showMessage = MutableLiveData<HtmlRes>()
    val navigateBack = MutableLiveData<LiveDataCommand>()
    val navigateToLevelPage = MutableLiveData<LiveDataCommand>()
    val navigateToGamePage = MutableLiveData<LiveDataEvent<GameConfig>>()


    init {
        emitEquationsSolvedMessage()
    }

    fun onBackPressed() = emitNavigateBack()

    fun onBackButtonClicked() = emitNavigateBack()

    fun onLevelButtonClicked() = emitNavigateToLevelPage()

    fun onPlayButtonClicked() = emitNavigateToGamePage()

    fun onNavigatedBackFromLevelPage() = emitEquationsSolvedMessage()

    fun onNavigatedBackFromGamePage() = emitEquationsSolvedMessage()

    private fun emitEquationsSolvedMessage() {
        val solvedEquationsCount =
                presetLevelDatabase.countAllPlayedLevel() +
                        generatedLevelDatabase.countAllPlayedLevel()

        val equationsSolvedMessage = when {
            solvedEquationsCount == 0 -> HtmlRes(
                    R.string.offline_message_no_equations_solved)

            solvedEquationsCount == 1 -> HtmlRes(
                    R.string.offline_message_one_equation_solved)

            solvedEquationsCount > 99 -> HtmlRes(
                    R.string.offline_message_many_equations_solved, solvedEquationsCount)

            else -> HtmlRes(
                    R.string.offline_message_some_equations_solved, solvedEquationsCount)
        }

        showMessage.emit(equationsSolvedMessage)
    }

    private fun emitNavigateBack() = navigateBack.emit()

    private fun emitNavigateToLevelPage() = navigateToLevelPage.emit()

    private fun emitNavigateToGamePage() = navigateToGamePage.emit(EndlessLevelGameConfig)
}