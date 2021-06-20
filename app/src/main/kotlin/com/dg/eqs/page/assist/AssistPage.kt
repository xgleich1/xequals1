package com.dg.eqs.page.assist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.dg.eqs.R
import com.dg.eqs.base.extension.onClick
import com.dg.eqs.base.extension.setDrawable
import com.dg.eqs.base.extension.setText
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataObserver
import com.dg.eqs.page.Page
import kotlinx.android.synthetic.main.page_assist.*
import javax.inject.Inject


class AssistPage : Page(R.layout.page_assist) {
    companion object {
        fun createIntent(context: Context) =
                Intent(context, AssistPage::class.java)
    }

    @Inject
    lateinit var viewModelFactory: AssistPageViewModelFactory

    private val viewModel: AssistPageViewModel by viewModels { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies()

        connectViewModel()
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    private fun injectDependencies() = applicationComponent
            .assistPageComponentBuilder()
            .build()
            .inject(this)

    private fun connectViewModel() {
        viewModel.showThankYouImage.observe(this,
                LiveDataObserver(assistImage::setDrawable))
        viewModel.donateButtonText.observe(this,
                LiveDataObserver(donateButton::setText))
        viewModel.donateButtonEnabled.observe(this,
                LiveDataObserver(donateButton::setEnabled))
        viewModel.donateButtonLoading.observe(this,
                LiveDataObserver(donateButton::setLoading))
        viewModel.donationStatus.observe(this,
                LiveDataObserver(donationStatus::setText))
        viewModel.navigateBack.observe(this,
                LiveDataCommandObserver(::navigateBack))

        backButton.onClick { viewModel.onBackButtonClicked() }
        donateButton.onClick { viewModel.onDonateButtonClicked(this) }
    }

    private fun navigateBack() = super.onBackPressed()
}