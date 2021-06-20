package com.dg.eqs.base.injection.module

import com.dg.eqs.base.gamification.GoogleGamesService
import com.dg.eqs.page.online.OnlinePageViewModelFactory
import dagger.Module
import dagger.Provides


@Module
open class OnlinePageModule {
    @Provides
    open fun provideOnlinePageViewModelFactory(
            googleGamesService: GoogleGamesService): OnlinePageViewModelFactory {

        return OnlinePageViewModelFactory(googleGamesService)
    }
}