package com.dg.eqs.page.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.dg.eqs.R
import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.base.extension.*
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataObserver
import com.dg.eqs.page.Page
import kotlinx.android.synthetic.main.page_info.*


class InfoPage : Page(R.layout.page_info) {
    companion object {
        fun createIntent(context: Context) =
                Intent(context, InfoPage::class.java)
    }

    private val viewModel: InfoPageViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectViewModel()
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    private fun connectViewModel() {
        viewModel.showInfoTitle.observe(this,
                LiveDataObserver(infoTitle::setText))
        viewModel.showInfoContent.observe(this,
                LiveDataObserver(::showInfoContent))
        viewModel.showMenuOverlay.observe(this,
                LiveDataObserver(menuOverlay::toggleVisibleGone))
        viewModel.greyOutHowToMenuButton.observe(this,
                LiveDataObserver(howToMenuButton::toggleTransparentOpaque))
        viewModel.greyOutEquationMenuButton.observe(this,
                LiveDataObserver(equationMenuButton::toggleTransparentOpaque))
        viewModel.greyOutOrderOfOperationsMenuButton.observe(this,
                LiveDataObserver(orderOfOperationsMenuButton::toggleTransparentOpaque))
        viewModel.greyOutAdditionMenuButton.observe(this,
                LiveDataObserver(additionMenuButton::toggleTransparentOpaque))
        viewModel.greyOutSubtractionMenuButton.observe(this,
                LiveDataObserver(subtractionMenuButton::toggleTransparentOpaque))
        viewModel.greyOutMultiplicationMenuButton.observe(this,
                LiveDataObserver(multiplicationMenuButton::toggleTransparentOpaque))
        viewModel.greyOutDivisionMenuButton.observe(this,
                LiveDataObserver(divisionMenuButton::toggleTransparentOpaque))
        viewModel.greyOutReduceMenuButton.observe(this,
                LiveDataObserver(reduceMenuButton::toggleTransparentOpaque))
        viewModel.navigateBack.observe(this,
                LiveDataCommandObserver(::navigateBack))

        backButton.onClick(viewModel::onBackButtonClicked)
        menuButton.onClick(viewModel::onMenuButtonClicked)
        menuOverlay.onClick(viewModel::onMenuOverlayClicked)
        howToMenuButton.onClick(viewModel::onHowToMenuButtonClicked)
        equationMenuButton.onClick(viewModel::onEquationMenuButtonClicked)
        orderOfOperationsMenuButton.onClick(viewModel::onOrderOfOperationsMenuButtonClicked)
        additionMenuButton.onClick(viewModel::onAdditionMenuButtonClicked)
        subtractionMenuButton.onClick(viewModel::onSubtractionMenuButtonClicked)
        multiplicationMenuButton.onClick(viewModel::onMultiplicationMenuButtonClicked)
        divisionMenuButton.onClick(viewModel::onDivisionMenuButtonClicked)
        reduceMenuButton.onClick(viewModel::onReduceMenuButtonClicked)
    }

    private fun showInfoContent(content: LayoutRes) {
        infoContent.scrollTo(0, 0)
        infoContent.removeAllViews()

        layoutInflater.inflate(content, infoContent)
    }

    private fun navigateBack() = super.onBackPressed()
}