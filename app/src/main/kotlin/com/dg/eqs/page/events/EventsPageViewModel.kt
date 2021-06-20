package com.dg.eqs.page.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dg.eqs.R
import com.dg.eqs.base.enveloping.HtmlRes
import com.dg.eqs.base.observation.LiveDataCommand
import com.dg.eqs.base.observation.LiveDataEvent
import com.dg.eqs.base.observation.emit
import com.dg.eqs.core.competition.Event
import com.dg.eqs.core.competition.EventRepository
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


class EventsPageViewModel(
        private val eventRepository: EventRepository,
        private val eventItemBuilder: EventItemBuilder
) : ViewModel() {

    val showHint = MutableLiveData<HtmlRes>()
    val showItems = MutableLiveData<List<EventItem>>()
    val downloadButtonEnabled = MutableLiveData<Boolean>()
    val downloadButtonLoading = MutableLiveData<Boolean>()
    val navigateBack = MutableLiveData<LiveDataCommand>()
    val navigateToEventPage = MutableLiveData<LiveDataEvent<Event>>()

    private var events = emptyList<Event>()


    init {
        loadAndProcessEvents()
    }

    fun onBackPressed() = emitNavigateBack()

    fun onBackButtonClicked() = emitNavigateBack()

    fun onDownloadButtonClicked() = loadAndProcessEvents()

    fun onEventItemClicked(index: Int) = navigateToEventPage(events[index])

    fun onNavigatedBackFromEventPage() = loadAndProcessEvents()

    private fun loadAndProcessEvents() {
        emitDisableDownloadButton()
        showDownloadButtonLoading()

        if(events.isEmpty()) {
            emitDownloadingHint()
        }

        viewModelScope.launch(Main) {
            events = eventRepository.loadEvents()

            hideDownloadButtonLoading()
            emitEnableDownloadButton()

            if(events.isEmpty()) {
                emitDownloadHint()
            } else {
                emitEmptyHint()
            }

            emitEventItems()
        }
    }

    private fun emitEmptyHint() = showHint.emit(
            HtmlRes(R.string.events_hint_empty))

    private fun emitDownloadHint() = showHint.emit(
            HtmlRes(R.string.events_hint_download))

    private fun emitDownloadingHint() = showHint.emit(
            HtmlRes(R.string.events_hint_downloading))

    private fun emitEventItems() = showItems.emit(
            events.map {
                val level = eventRepository.loadLevel(it)

                eventItemBuilder.buildEventItem(level)
            })

    private fun emitEnableDownloadButton() = downloadButtonEnabled.emit(true)

    private fun emitDisableDownloadButton() = downloadButtonEnabled.emit(false)

    private fun showDownloadButtonLoading() = downloadButtonLoading.emit(true)

    private fun hideDownloadButtonLoading() = downloadButtonLoading.emit(false)

    private fun emitNavigateBack() = navigateBack.emit()

    private fun navigateToEventPage(event: Event) = navigateToEventPage.emit(event)
}