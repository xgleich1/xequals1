package com.dg.eqs.page.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.dg.eqs.core.competition.EventRepository


class EventsPageViewModelFactory(
        private val eventRepository: EventRepository,
        private val eventItemBuilder: EventItemBuilder
) : Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            EventsPageViewModel(
                    eventRepository,
                    eventItemBuilder) as T
}