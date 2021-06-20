package com.dg.eqs.page.level

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dg.eqs.base.observation.LiveDataCommand
import com.dg.eqs.base.observation.LiveDataEvent
import com.dg.eqs.base.observation.emit
import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase
import com.dg.eqs.page.game.GameConfig
import com.dg.eqs.page.game.GameConfig.SingleLevelGameConfig


class LevelPageViewModel(
        private val generatedLevelDatabase: GeneratedLevelDatabase,
        private val presetLevelDatabase: PresetLevelDatabase,
        private val levelItemBuilder: LevelItemBuilder
) : ViewModel() {

    val showHint = MutableLiveData<Boolean>()
    val showItems = MutableLiveData<List<LevelItem>>()
    val navigateBack = MutableLiveData<LiveDataCommand>()
    val navigateToGamePage = MutableLiveData<LiveDataEvent<GameConfig>>()

    private var finishedLevel = loadAllFinishedLevel()


    init {
        val levelItemsFromFinishedLevel =
                buildLevelItemsFromFinishedLevel()

        showHint.emit(levelItemsFromFinishedLevel.isEmpty())
        showItems.emit(levelItemsFromFinishedLevel)
    }

    fun onBackPressed() = onBackButtonClicked()

    fun onBackButtonClicked() = navigateBack.emit()

    fun onLevelItemClicked(index: Int) = navigateToGamePage.emit(
            SingleLevelGameConfig(finishedLevel[index].levelKey))

    fun onNavigatedBackFromGamePage() {
        finishedLevel = loadAllFinishedLevel()

        showItems.emit(buildLevelItemsFromFinishedLevel())
    }

    private fun loadAllFinishedLevel() =
            generatedLevelDatabase.loadAllFinishedLevel() +
                    presetLevelDatabase.loadAllFinishedLevel()

    private fun buildLevelItemsFromFinishedLevel() =
            finishedLevel.map(levelItemBuilder::buildLevelItem)
}