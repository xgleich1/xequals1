package com.dg.eqs.page.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.dg.eqs.core.competition.Event
import com.dg.eqs.core.competition.EventRepository


class EventPageViewModelFactory(
        private val event: Event,
        private val eventRepository: EventRepository
) : Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            EventPageViewModel(
                    event,
                    eventRepository) as T
}