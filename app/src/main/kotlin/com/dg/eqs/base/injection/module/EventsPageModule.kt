package com.dg.eqs.base.injection.module

import com.dg.eqs.core.competition.EventRepository
import com.dg.eqs.page.events.EventItemBuilder
import com.dg.eqs.page.events.EventsPageViewModelFactory
import dagger.Module
import dagger.Provides


@Module
open class EventsPageModule {
    @Provides
    open fun provideEventsPageViewModelFactory(
            eventRepository: EventRepository,
            eventItemBuilder: EventItemBuilder
    ): EventsPageViewModelFactory {

        return EventsPageViewModelFactory(
                eventRepository,
                eventItemBuilder)
    }

    @Provides
    open fun provideEventItemBuilder(): EventItemBuilder {
        return EventItemBuilder()
    }
}