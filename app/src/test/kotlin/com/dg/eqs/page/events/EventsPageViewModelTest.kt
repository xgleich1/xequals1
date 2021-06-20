package com.dg.eqs.page.events

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dg.eqs.R
import com.dg.eqs.base.enveloping.HtmlRes
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataEventObserver
import com.dg.eqs.base.observation.LiveDataObserver
import com.dg.eqs.core.competition.Event
import com.dg.eqs.core.competition.EventRepository
import com.dg.eqs.core.progression.Level.EventLevel
import com.dg.eqs.util.rules.MainDispatcherRule
import org.mockito.kotlin.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EventsPageViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var eventRepository: EventRepository
    @Mock
    private lateinit var eventItemBuilder: EventItemBuilder

    @Mock
    private lateinit var showHintAction: (HtmlRes) -> Unit
    @Mock
    private lateinit var showItemsAction: (List<EventItem>) -> Unit
    @Mock
    private lateinit var downloadButtonEnabledAction: (Boolean) -> Unit
    @Mock
    private lateinit var downloadButtonLoadingAction: (Boolean) -> Unit
    @Mock
    private lateinit var navigateBackAction: () -> Unit
    @Mock
    private lateinit var navigateToEventPageAction: (Event) -> Unit

    @Mock
    private lateinit var eventA: Event
    @Mock
    private lateinit var eventB: Event
    @Mock
    private lateinit var eventC: Event
    @Mock
    private lateinit var eventD: Event
    @Mock
    private lateinit var eventLevelA: EventLevel
    @Mock
    private lateinit var eventLevelB: EventLevel
    @Mock
    private lateinit var eventLevelC: EventLevel
    @Mock
    private lateinit var eventLevelD: EventLevel
    @Mock
    private lateinit var eventItemA: EventItem
    @Mock
    private lateinit var eventItemB: EventItem
    @Mock
    private lateinit var eventItemC: EventItem
    @Mock
    private lateinit var eventItemD: EventItem


    @Before
    fun setUp() {
        setupEventLevelLoading()
        setupEventItemBuilding()
    }

    @Test
    fun `Should automatically load the events when the page is opened`() = runBlocking<Unit> {
        // GIVEN
        setupEventsLoading(listOf(eventA))

        createAndObserveViewModel()

        // THEN
        verify(eventRepository).loadEvents()
    }

    @Test
    fun `Should hide the download button loading after automatically loading the events`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA))

        createAndObserveViewModel()

        // THEN
        inOrder(eventRepository, downloadButtonLoadingAction) {
            verify(eventRepository).loadEvents()

            verify(downloadButtonLoadingAction).invoke(false)
        }
    }

    @Test
    fun `Should enable the download button after automatically loading the events`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA))

        createAndObserveViewModel()

        // THEN
        inOrder(eventRepository, downloadButtonEnabledAction) {
            verify(eventRepository).loadEvents()

            verify(downloadButtonEnabledAction).invoke(true)
        }
    }

    @Test
    fun `Should emit the download hint when zero events are automatically loaded`() = runBlocking {
        // GIVEN
        setupEventsLoading(emptyList())

        createAndObserveViewModel()

        // THEN
        verify(showHintAction).invoke(HtmlRes(R.string.events_hint_download))
    }

    @Test
    fun `Should emit the empty hint when at least one event is automatically loaded`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA))

        createAndObserveViewModel()

        // THEN
        verify(showHintAction).invoke(HtmlRes(R.string.events_hint_empty))
    }

    @Test
    fun `Should emit the event items build from the automatically loaded events`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA, eventB))

        createAndObserveViewModel()

        // THEN
        verify(showItemsAction).invoke(listOf(eventItemA, eventItemB))
    }

    @Test
    fun `Should navigate back when back is pressed`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA))

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onBackPressed()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should navigate back when the back button is clicked`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA))

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onBackButtonClicked()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should manually load the events when the download button is clicked`() = runBlocking<Unit> {
        // GIVEN
        setupEventsLoading(emptyList(), listOf(eventA))

        val viewModel = createAndObserveViewModel()

        clearInvocations(eventRepository)

        // WHEN
        viewModel.onDownloadButtonClicked()

        // THEN
        verify(eventRepository).loadEvents()
    }

    @Test
    fun `Should disable the download button before manually loading the events`() = runBlocking {
        // GIVEN
        setupEventsLoading(emptyList(), listOf(eventA))

        val viewModel = createAndObserveViewModel()

        clearInvocations(downloadButtonEnabledAction)

        // WHEN
        viewModel.onDownloadButtonClicked()

        // THEN
        inOrder(downloadButtonEnabledAction, eventRepository) {
            verify(downloadButtonEnabledAction).invoke(false)

            verify(eventRepository).loadEvents()
        }
    }

    @Test
    fun `Should show the download button loading before manually loading the events`() = runBlocking {
        // GIVEN
        setupEventsLoading(emptyList(), listOf(eventA))

        val viewModel = createAndObserveViewModel()

        clearInvocations(downloadButtonLoadingAction)

        // WHEN
        viewModel.onDownloadButtonClicked()

        // THEN
        inOrder(downloadButtonLoadingAction, eventRepository) {
            verify(downloadButtonLoadingAction).invoke(true)

            verify(eventRepository).loadEvents()
        }
    }

    @Test
    fun `Should emit the downloading hint when there are no events before manually loading them`() = runBlocking {
        // GIVEN
        setupEventsLoading(emptyList(), listOf(eventA))

        val viewModel = createAndObserveViewModel()

        clearInvocations(showHintAction)

        // WHEN
        viewModel.onDownloadButtonClicked()

        // THEN
        inOrder(showHintAction, eventRepository) {
            verify(showHintAction).invoke(HtmlRes(R.string.events_hint_downloading))

            verify(eventRepository).loadEvents()
        }
    }

    @Test
    fun `Should not emit the downloading hint when there are events before manually loading them`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA), emptyList())

        val viewModel = createAndObserveViewModel()

        clearInvocations(showHintAction)

        // WHEN
        viewModel.onDownloadButtonClicked()

        // THEN
        verify(showHintAction, never()).invoke(HtmlRes(R.string.events_hint_downloading))
    }

    @Test
    fun `Should hide the download button loading after manually loading the events`() = runBlocking {
        // GIVEN
        setupEventsLoading(emptyList(), listOf(eventA))

        val viewModel = createAndObserveViewModel()

        clearInvocations(downloadButtonLoadingAction)

        // WHEN
        viewModel.onDownloadButtonClicked()

        // THEN
        inOrder(eventRepository, downloadButtonLoadingAction) {
            verify(eventRepository).loadEvents()

            verify(downloadButtonLoadingAction).invoke(false)
        }
    }

    @Test
    fun `Should enable the download button after manually loading the events`() = runBlocking {
        // GIVEN
        setupEventsLoading(emptyList(), listOf(eventA))

        val viewModel = createAndObserveViewModel()

        clearInvocations(downloadButtonEnabledAction)

        // WHEN
        viewModel.onDownloadButtonClicked()

        // THEN
        inOrder(eventRepository, downloadButtonEnabledAction) {
            verify(eventRepository).loadEvents()

            verify(downloadButtonEnabledAction).invoke(true)
        }
    }

    @Test
    fun `Should emit the download hint when zero events are manually loaded`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA), emptyList())

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDownloadButtonClicked()

        // THEN
        verify(showHintAction).invoke(HtmlRes(R.string.events_hint_download))
    }

    @Test
    fun `Should emit the empty hint when at least one event is manually loaded`() = runBlocking {
        // GIVEN
        setupEventsLoading(emptyList(), listOf(eventA))

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDownloadButtonClicked()

        // THEN
        verify(showHintAction).invoke(HtmlRes(R.string.events_hint_empty))
    }

    @Test
    fun `Should emit the event items build from the manually loaded events`() = runBlocking {
        // GIVEN
        setupEventsLoading(emptyList(), listOf(eventA, eventB))

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDownloadButtonClicked()

        // THEN
        verify(showItemsAction).invoke(listOf(eventItemA, eventItemB))
    }

    @Test
    fun `Should navigate to the event page when an item from an automatically loaded event is clicked`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA))

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onEventItemClicked(0)

        // THEN
        verify(navigateToEventPageAction).invoke(eventA)
    }

    @Test
    fun `Should navigate to the event page when an item from an manually loaded event is clicked`() = runBlocking {
        // GIVEN
        setupEventsLoading(emptyList(), listOf(eventA))

        val viewModel = createAndObserveViewModel()

        viewModel.onDownloadButtonClicked()

        // WHEN
        viewModel.onEventItemClicked(0)

        // THEN
        verify(navigateToEventPageAction).invoke(eventA)
    }

    @Test
    fun `Should navigate to the event page when an item from an reloaded event is clicked`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA), listOf(eventB))

        val viewModel = createAndObserveViewModel()

        viewModel.onNavigatedBackFromEventPage()

        // WHEN
        viewModel.onEventItemClicked(0)

        // THEN
        verify(navigateToEventPageAction).invoke(eventB)
    }

    @Test
    fun `Should reload the events when navigating back from the event page`() = runBlocking<Unit> {
        // GIVEN
        setupEventsLoading(listOf(eventA), listOf(eventB))

        val viewModel = createAndObserveViewModel()

        clearInvocations(eventRepository)

        // WHEN
        viewModel.onNavigatedBackFromEventPage()

        // THEN
        verify(eventRepository).loadEvents()
    }

    @Test
    fun `Should disable the download button before reloading the events`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA), listOf(eventB))

        val viewModel = createAndObserveViewModel()

        clearInvocations(downloadButtonEnabledAction)

        // WHEN
        viewModel.onNavigatedBackFromEventPage()

        // THEN
        inOrder(downloadButtonEnabledAction, eventRepository) {
            verify(downloadButtonEnabledAction).invoke(false)

            verify(eventRepository).loadEvents()
        }
    }

    @Test
    fun `Should show the download button loading before reloading the events`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA), listOf(eventB))

        val viewModel = createAndObserveViewModel()

        clearInvocations(downloadButtonLoadingAction)

        // WHEN
        viewModel.onNavigatedBackFromEventPage()

        // THEN
        inOrder(downloadButtonLoadingAction, eventRepository) {
            verify(downloadButtonLoadingAction).invoke(true)

            verify(eventRepository).loadEvents()
        }
    }

    @Test
    fun `Should not emit the downloading hint before reloading the events`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA), listOf(eventB))

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onNavigatedBackFromEventPage()

        // THEN
        verify(showHintAction, never()).invoke(HtmlRes(R.string.events_hint_downloading))
    }

    @Test
    fun `Should hide the download button loading after reloading the events`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA), listOf(eventB))

        val viewModel = createAndObserveViewModel()

        clearInvocations(downloadButtonLoadingAction)

        // WHEN
        viewModel.onNavigatedBackFromEventPage()

        // THEN
        inOrder(eventRepository, downloadButtonLoadingAction) {
            verify(eventRepository).loadEvents()

            verify(downloadButtonLoadingAction).invoke(false)
        }
    }

    @Test
    fun `Should enable the download button after reloading the events`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA), listOf(eventB))

        val viewModel = createAndObserveViewModel()

        clearInvocations(downloadButtonEnabledAction)

        // WHEN
        viewModel.onNavigatedBackFromEventPage()

        // THEN
        inOrder(eventRepository, downloadButtonEnabledAction) {
            verify(eventRepository).loadEvents()

            verify(downloadButtonEnabledAction).invoke(true)
        }
    }

    @Test
    fun `Should emit the download hint when zero events are reloaded`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA), emptyList())

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onNavigatedBackFromEventPage()

        // THEN
        verify(showHintAction).invoke(HtmlRes(R.string.events_hint_download))
    }

    @Test
    fun `Should emit the empty hint when at least one event is reloaded`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA), listOf(eventB))

        val viewModel = createAndObserveViewModel()

        clearInvocations(showHintAction)

        // WHEN
        viewModel.onNavigatedBackFromEventPage()

        // THEN
        verify(showHintAction).invoke(HtmlRes(R.string.events_hint_empty))
    }

    @Test
    fun `Should emit the event items build from the reloaded events`() = runBlocking {
        // GIVEN
        setupEventsLoading(listOf(eventA, eventB), listOf(eventC, eventD))

        val viewModel = createAndObserveViewModel()

        clearInvocations(showItemsAction)

        // WHEN
        viewModel.onNavigatedBackFromEventPage()

        // THEN
        verify(showItemsAction).invoke(listOf(eventItemC, eventItemD))
    }

    private fun setupEventLevelLoading() {
        whenever(eventRepository.loadLevel(eventA)) doReturn eventLevelA
        whenever(eventRepository.loadLevel(eventB)) doReturn eventLevelB
        whenever(eventRepository.loadLevel(eventC)) doReturn eventLevelC
        whenever(eventRepository.loadLevel(eventD)) doReturn eventLevelD
    }

    private fun setupEventItemBuilding() {
        whenever(eventItemBuilder.buildEventItem(eventLevelA)) doReturn eventItemA
        whenever(eventItemBuilder.buildEventItem(eventLevelB)) doReturn eventItemB
        whenever(eventItemBuilder.buildEventItem(eventLevelC)) doReturn eventItemC
        whenever(eventItemBuilder.buildEventItem(eventLevelD)) doReturn eventItemD
    }

    private suspend fun setupEventsLoading(vararg events: List<Event>) {
        whenever(eventRepository.loadEvents()) doReturnConsecutively events.toList()
    }

    private fun createAndObserveViewModel(): EventsPageViewModel {
        val viewModel = EventsPageViewModel(
                eventRepository,
                eventItemBuilder)

        viewModel.showHint.observeForever(
                LiveDataObserver(showHintAction))
        viewModel.showItems.observeForever(
                LiveDataObserver(showItemsAction))
        viewModel.downloadButtonEnabled.observeForever(
                LiveDataObserver(downloadButtonEnabledAction))
        viewModel.downloadButtonLoading.observeForever(
                LiveDataObserver(downloadButtonLoadingAction))
        viewModel.navigateBack.observeForever(
                LiveDataCommandObserver(navigateBackAction))
        viewModel.navigateToEventPage.observeForever(
                LiveDataEventObserver(navigateToEventPageAction))

        return viewModel
    }
}