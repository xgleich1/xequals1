package com.dg.eqs.page.start

import android.os.Bundle
import androidx.activity.viewModels
import com.dg.eqs.R
import com.dg.eqs.base.extension.onClick
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.page.Page
import com.dg.eqs.page.assist.AssistPage
import com.dg.eqs.page.feedback.FeedbackPage
import com.dg.eqs.page.info.InfoPage
import com.dg.eqs.page.offline.OfflinePage
import com.dg.eqs.page.online.OnlinePage
import kotlinx.android.synthetic.main.page_start.*


class StartPage : Page(R.layout.page_start) {
    private val viewModel: StartPageViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectViewModel()
    }

    private fun connectViewModel() {
        viewModel.navigateToOfflinePage.observe(this,
                LiveDataCommandObserver(::navigateToOfflinePage))
        viewModel.navigateToOnlinePage.observe(this,
                LiveDataCommandObserver(::navigateToOnlinePage))
        viewModel.navigateToInfoPage.observe(this,
                LiveDataCommandObserver(::navigateToInfoPage))
        viewModel.navigateToAssistPage.observe(this,
                LiveDataCommandObserver(::navigateToAssistPage))
        viewModel.navigateToFeedbackPage.observe(this,
                LiveDataCommandObserver(::navigateToFeedbackPage))

        offlineButton.onClick(viewModel::onOfflineButtonClicked)
        onlineButton.onClick(viewModel::onOnlineButtonClicked)
        infoButton.onClick(viewModel::onInfoButtonClicked)
        assistButton.onClick(viewModel::onAssistButtonClicked)
        feedbackButton.onClick(viewModel::onFeedbackButtonClicked)
    }

    private fun navigateToOfflinePage() = startActivity(OfflinePage.createIntent(this))

    private fun navigateToOnlinePage() = startActivity(OnlinePage.createIntent(this))

    private fun navigateToInfoPage() = startActivity(InfoPage.createIntent(this))

    private fun navigateToAssistPage() = startActivity(AssistPage.createIntent(this))

    private fun navigateToFeedbackPage() = startActivity(FeedbackPage.createIntent(this))
}