package com.dg.eqs.page.offline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase


class OfflinePageViewModelFactory(
        private val presetLevelDatabase: PresetLevelDatabase,
        private val generatedLevelDatabase: GeneratedLevelDatabase) : Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            OfflinePageViewModel(
                    presetLevelDatabase,
                    generatedLevelDatabase) as T
}