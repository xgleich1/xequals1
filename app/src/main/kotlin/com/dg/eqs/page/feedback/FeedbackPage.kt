package com.dg.eqs.page.feedback

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.dg.eqs.R
import com.dg.eqs.base.extension.onClick
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.page.Page
import kotlinx.android.synthetic.main.page_feedback.*


class FeedbackPage : Page(R.layout.page_feedback) {
    companion object {
        fun createIntent(context: Context) =
                Intent(context, FeedbackPage::class.java)
    }

    private val viewModel: FeedbackPageViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectViewModel()
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    private fun connectViewModel() {
        viewModel.navigateBack.observe(this,
                LiveDataCommandObserver(::navigateBack))

        backButton.onClick(viewModel::onBackButtonClicked)
    }

    private fun navigateBack() = super.onBackPressed()
}