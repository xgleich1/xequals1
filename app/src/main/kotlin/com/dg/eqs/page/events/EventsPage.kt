package com.dg.eqs.page.events

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.dg.eqs.R
import com.dg.eqs.base.extension.onClick
import com.dg.eqs.base.extension.setHtml
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataEventObserver
import com.dg.eqs.base.observation.LiveDataObserver
import com.dg.eqs.core.competition.Event
import com.dg.eqs.page.Page
import com.dg.eqs.page.event.EventPage
import kotlinx.android.synthetic.main.page_events.*
import kotlinx.android.synthetic.main.page_online.backButton
import javax.inject.Inject


class EventsPage : Page(R.layout.page_events) {
    companion object {
        private const val EVENT_PAGE_REQUEST_CODE = 1


        fun createIntent(context: Context) =
                Intent(context, EventsPage::class.java)
    }

    @Inject
    lateinit var viewModelFactory: EventsPageViewModelFactory

    private val viewModel: EventsPageViewModel by viewModels { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies()

        connectViewModel()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            EVENT_PAGE_REQUEST_CODE -> viewModel.onNavigatedBackFromEventPage()

            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    private fun injectDependencies() = applicationComponent
            .eventsPageComponentBuilder()
            .build()
            .inject(this)

    private fun connectViewModel() {
        viewModel.showHint.observe(this,
                LiveDataObserver(eventsHint::setHtml))
        viewModel.showItems.observe(this,
                LiveDataObserver(eventsList::showItems))
        viewModel.downloadButtonEnabled.observe(this,
                LiveDataObserver(downloadButton::setEnabled))
        viewModel.downloadButtonLoading.observe(this,
                LiveDataObserver(downloadButton::setAnimated))
        viewModel.navigateBack.observe(this,
                LiveDataCommandObserver(::navigateBack))
        viewModel.navigateToEventPage.observe(this,
                LiveDataEventObserver(::navigateToEventPage))

        backButton.onClick(viewModel::onBackButtonClicked)
        downloadButton.onClick(viewModel::onDownloadButtonClicked)
        eventsList.onItemClick(viewModel::onEventItemClicked)
    }

    private fun navigateBack() = super.onBackPressed()

    private fun navigateToEventPage(event: Event) = startActivityForResult(
            EventPage.createIntent(this, event), EVENT_PAGE_REQUEST_CODE)
}