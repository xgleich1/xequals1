package com.dg.eqs.base.injection.component

import com.dg.eqs.base.injection.module.*
import com.dg.eqs.page.game.GamePage
import dagger.Subcomponent


@Subcomponent(modules = [
    GamePageModule::class,
    ExecutionModule::class,
    DirectShiftingModule::class,
    IndirectShiftingModule::class,
    DirectCondensingModule::class,
    IndirectCondensingModule::class,
    InvertModule::class,
    GenerationModule::class,
    InteractionModule::class])
interface GamePageComponent {
    fun inject(gamePage: GamePage)

    fun endlessLevelGameComponentBuilder(): EndlessLevelGameComponent.Builder

    fun singleLevelGameComponentBuilder(): SingleLevelGameComponent.Builder

    @Subcomponent.Builder
    interface Builder {
        fun gamePageModule(gamePageModule: GamePageModule): Builder

        fun build(): GamePageComponent
    }
}