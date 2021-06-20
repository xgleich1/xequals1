package com.dg.eqs.base.injection.module

import android.app.Application
import com.dg.eqs.base.injection.component.EndlessLevelGameComponent
import com.dg.eqs.base.injection.component.SingleLevelGameComponent
import com.dg.eqs.base.tracking.Tracking
import com.dg.eqs.core.mapping.OriginToDraftMapper
import com.dg.eqs.core.interaction.linking.LinkingCommander
import com.dg.eqs.core.interaction.scrolling.ScrollingCommander
import com.dg.eqs.core.orchestration.GameCommander
import com.dg.eqs.page.game.*
import com.dg.eqs.page.game.GameConfig.EndlessLevelGameConfig
import com.dg.eqs.page.game.GameConfig.SingleLevelGameConfig
import dagger.Module
import dagger.Provides


@Module
open class GamePageModule(private val gameConfig: GameConfig) {
    @Provides
    open fun provideGamePageViewModelFactory(
            gameConfig: GameConfig,
            gameDrafter: GameDrafter,
            gameTracking: GameTracking,
            gameCommander: GameCommander,
            linkingCommander: LinkingCommander,
            scrollingCommander: ScrollingCommander): GamePageViewModelFactory {

        return GamePageViewModelFactory(
                gameConfig,
                gameDrafter,
                gameTracking,
                gameCommander,
                linkingCommander,
                scrollingCommander)
    }

    @Provides
    open fun provideGameConfig(): GameConfig {
        return gameConfig
    }

    @Provides
    open fun provideGameDrafter(
            originToDraftMapper: OriginToDraftMapper,
            gamePencil: GamePencil): GameDrafter {

        return GameDrafter(
                originToDraftMapper,
                gamePencil)
    }

    @Provides
    open fun provideGamePencil(application: Application): GamePencil {
        return GamePencil(application)
    }

    @Provides
    open fun provideGameTracking(tracking: Tracking): GameTracking {
        return GameTracking(tracking)
    }

    @Provides
    open fun provideGameCommander(
            gameConfig: GameConfig,
            endlessLevelGameComponentBuilder: EndlessLevelGameComponent.Builder,
            singleLevelGameComponentBuilder: SingleLevelGameComponent.Builder): GameCommander {

        return when(gameConfig) {
            is EndlessLevelGameConfig -> endlessLevelGameComponentBuilder
                    .build()
                    .endlessLevelGameCommander()

            is SingleLevelGameConfig -> singleLevelGameComponentBuilder
                    .singleLevelGameModule(SingleLevelGameModule(gameConfig.levelKey))
                    .build()
                    .singleLevelGameCommander()
        }
    }
}