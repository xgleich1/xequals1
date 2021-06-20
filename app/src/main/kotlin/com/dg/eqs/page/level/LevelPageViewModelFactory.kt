package com.dg.eqs.page.level

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase


class LevelPageViewModelFactory(
        private val generatedLevelDatabase: GeneratedLevelDatabase,
        private val presetLevelDatabase: PresetLevelDatabase,
        private val levelItemBuilder: LevelItemBuilder
) : Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            LevelPageViewModel(
                    generatedLevelDatabase,
                    presetLevelDatabase,
                    levelItemBuilder) as T
}