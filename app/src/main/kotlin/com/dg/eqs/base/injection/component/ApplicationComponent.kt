package com.dg.eqs.base.injection.component

import com.dg.eqs.Eqs
import com.dg.eqs.base.injection.module.ApplicationModule
import com.dg.eqs.base.injection.module.CompetitionModule
import com.dg.eqs.base.injection.module.MappingModule
import com.dg.eqs.base.injection.module.ProgressionModule
import com.dg.eqs.base.injection.scope.ApplicationScope
import com.dg.eqs.base.reviewing.AppReviewButton
import dagger.Component


@ApplicationScope
@Component(modules = [
    ApplicationModule::class,
    MappingModule::class,
    ProgressionModule::class,
    CompetitionModule::class])
interface ApplicationComponent {
    fun inject(eqs: Eqs)

    fun inject(appReviewButton: AppReviewButton)

    fun draftPanelComponentBuilder(): DraftPanelComponent.Builder

    fun offlinePageComponentBuilder(): OfflinePageComponent.Builder

    fun gamePageComponentBuilder(): GamePageComponent.Builder

    fun levelPageComponentBuilder(): LevelPageComponent.Builder

    fun onlinePageComponentBuilder(): OnlinePageComponent.Builder

    fun eventsPageComponentBuilder(): EventsPageComponent.Builder

    fun eventPageComponentBuilder(): EventPageComponent.Builder

    fun assistPageComponentBuilder(): AssistPageComponent.Builder
}