package com.dg.eqs.page.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.dg.eqs.core.interaction.linking.LinkingCommander
import com.dg.eqs.core.interaction.scrolling.ScrollingCommander
import com.dg.eqs.core.orchestration.GameCommander


class GamePageViewModelFactory(
        private val gameConfig: GameConfig,
        private val gameDrafter: GameDrafter,
        private val gameTracking: GameTracking,
        private val gameCommander: GameCommander,
        private val linkingCommander: LinkingCommander,
        private val scrollingCommander: ScrollingCommander
) : Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            GamePageViewModel(
                    gameConfig,
                    gameDrafter,
                    gameTracking,
                    gameCommander,
                    linkingCommander,
                    scrollingCommander) as T
}