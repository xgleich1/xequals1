package com.dg.eqs.page.online

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.dg.eqs.R
import com.dg.eqs.base.extension.onClick
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataEventObserver
import com.dg.eqs.base.observation.LiveDataObserver
import com.dg.eqs.page.Page
import com.dg.eqs.page.events.EventsPage
import kotlinx.android.synthetic.main.page_online.*
import javax.inject.Inject


class OnlinePage : Page(R.layout.page_online) {
    companion object {
        private const val SIGN_IN_REQUEST_CODE = 1986


        fun createIntent(context: Context) =
                Intent(context, OnlinePage::class.java)
    }

    @Inject
    lateinit var viewModelFactory: OnlinePageViewModelFactory

    private val viewModel: OnlinePageViewModel by viewModels { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies()

        connectViewModel()
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        when(requestCode) {
            SIGN_IN_REQUEST_CODE -> viewModel.onNavigatedBackFromSignInPage()

            else -> super.onActivityResult(requestCode, resultCode, intent)
        }
    }

    private fun injectDependencies() = applicationComponent
            .onlinePageComponentBuilder()
            .build()
            .inject(this)

    private fun connectViewModel() {
        viewModel.showStatus.observe(this,
                LiveDataObserver(status::setHtml))
        viewModel.googleButtonText.observe(this,
                LiveDataObserver(googleButton::setText))
        viewModel.googleButtonEnabled.observe(this,
                LiveDataObserver(googleButton::setEnabled))
        viewModel.eventsButtonEnabled.observe(this,
                LiveDataObserver(eventsButton::setEnabled))
        viewModel.eventsButtonLoading.observe(this,
                LiveDataObserver(eventsButton::setLoading))
        viewModel.navigateBack.observe(this,
                LiveDataCommandObserver(::navigateBack))
        viewModel.navigateToSignIn.observe(this,
                LiveDataEventObserver(::navigateToSignIn))
        viewModel.navigateToEventsPage.observe(this,
                LiveDataCommandObserver(::navigateToEventsPage))

        backButton.onClick(viewModel::onBackButtonClicked)
        googleButton.onClick(viewModel::onGoogleButtonClicked)
        eventsButton.onClick(viewModel::onEventsButtonClicked)
    }

    private fun navigateBack() = super.onBackPressed()

    private fun navigateToSignIn(intent: Intent) =
            startActivityForResult(intent, SIGN_IN_REQUEST_CODE)

    private fun navigateToEventsPage() =
            startActivity(EventsPage.createIntent(this))
}