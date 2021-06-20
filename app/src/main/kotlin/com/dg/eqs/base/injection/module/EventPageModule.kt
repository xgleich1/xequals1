package com.dg.eqs.base.injection.module

import com.dg.eqs.core.competition.Event
import com.dg.eqs.core.competition.EventRepository
import com.dg.eqs.page.event.EventPageViewModelFactory
import dagger.Module
import dagger.Provides


@Module
open class EventPageModule(private val event: Event) {
    @Provides
    open fun provideEventPageViewModelFactory(
            event: Event,
            eventRepository: EventRepository
    ): EventPageViewModelFactory {

        return EventPageViewModelFactory(
                event,
                eventRepository)
    }

    @Provides
    open fun provideEvent(): Event {
        return event
    }
}