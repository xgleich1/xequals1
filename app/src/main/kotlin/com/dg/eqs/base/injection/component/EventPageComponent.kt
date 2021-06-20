package com.dg.eqs.base.injection.component

import com.dg.eqs.base.injection.module.EventPageModule
import com.dg.eqs.page.event.EventPage
import dagger.Subcomponent


@Subcomponent(modules = [EventPageModule::class])
interface EventPageComponent {
    fun inject(eventPage: EventPage)

    @Subcomponent.Builder
    interface Builder {
        fun eventPageModule(eventPageModule: EventPageModule): Builder

        fun build(): EventPageComponent
    }
}