package com.dg.eqs.base.injection.component

import com.dg.eqs.base.injection.module.EventsPageModule
import com.dg.eqs.page.events.EventsPage
import dagger.Subcomponent


@Subcomponent(modules = [EventsPageModule::class])
interface EventsPageComponent {
    fun inject(eventsPage: EventsPage)

    @Subcomponent.Builder
    interface Builder {
        fun build(): EventsPageComponent
    }
}