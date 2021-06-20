package com.dg.eqs.page.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.doOnLayout
import com.dg.eqs.R
import com.dg.eqs.base.enveloping.ColorRes
import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.base.extension.*
import com.dg.eqs.base.injection.module.GamePageModule
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataEventObserver
import com.dg.eqs.base.observation.LiveDataObserver
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.page.Page
import kotlinx.android.synthetic.main.page_game.*
import javax.inject.Inject


class GamePage : Page(R.layout.page_game) {
    companion object {
        private const val CONFIG_EXTRA = "configExtra"


        fun createIntent(context: Context, config: GameConfig) =
                Intent(context, GamePage::class.java)
                        .putExtra(CONFIG_EXTRA, config)
    }

    @Inject
    lateinit var viewModelFactory: GamePageViewModelFactory

    private val viewModel: GamePageViewModel by viewModels { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies()

        connectViewModel()
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    private fun injectDependencies() {
        val gameConfig: GameConfig = intent
                .getParcelableExtraOrThrow(CONFIG_EXTRA)

        applicationComponent
                .gamePageComponentBuilder()
                .gamePageModule(GamePageModule(gameConfig))
                .build()
                .inject(this)
    }

    private fun connectViewModel() {
        viewModel.showRevertButton.observe(this,
                LiveDataObserver(revertButton::toggleVisibleGone))
        viewModel.showInvertButton.observe(this,
                LiveDataObserver(invertButton::toggleVisibleGone))
        viewModel.showSkipButton.observe(this,
                LiveDataObserver(skipButton::toggleVisibleGone))
        viewModel.showDraft.observe(this,
                LiveDataEventObserver(gameGrid::showDraft))
        viewModel.showSourceMark.observe(this,
                LiveDataEventObserver(gameGrid::showSourceMark))
        viewModel.showTargetMark.observe(this,
                LiveDataEventObserver(gameGrid::showTargetMark))
        viewModel.showSourceArrow.observe(this,
                LiveDataEventObserver(gameGrid::showSourceArrow))
        viewModel.showGridLines.observe(this,
                LiveDataCommandObserver(gameGrid::showLines))
        viewModel.showFinishedIcon.observe(this,
                LiveDataObserver(finishedIcon::toggleVisibleGone))
        viewModel.showStatus.observe(this,
                LiveDataObserver(status::setText))
        viewModel.showInfoContent.observe(this,
                LiveDataObserver(::showInfoContent))
        viewModel.showInfoOverlay.observe(this,
                LiveDataObserver(::showInfoOverlay))
        viewModel.hideSourceMark.observe(this,
                LiveDataCommandObserver(gameGrid::hideSourceMark))
        viewModel.hideTargetMark.observe(this,
                LiveDataCommandObserver(gameGrid::hideTargetMark))
        viewModel.hideSourceArrow.observe(this,
                LiveDataCommandObserver(gameGrid::hideSourceArrow))
        viewModel.hideGridLines.observe(this,
                LiveDataCommandObserver(gameGrid::hideLines))
        viewModel.scrollGridX.observe(this,
                LiveDataEventObserver(gameGrid::scrollX))
        viewModel.scrollGridY.observe(this,
                LiveDataEventObserver(gameGrid::scrollY))
        viewModel.colorInfoButton.observe(this,
                LiveDataObserver(::colorInfoButton))
        viewModel.colorSourceMark.observe(this,
                LiveDataEventObserver(gameGrid::colorSourceMark))
        viewModel.colorTargetMark.observe(this,
                LiveDataEventObserver(gameGrid::colorTargetMark))
        viewModel.colorSourceArrow.observe(this,
                LiveDataEventObserver(gameGrid::colorSourceArrow))
        viewModel.navigateBack.observe(this,
                LiveDataCommandObserver(::navigateBack))

        backButton.onClick(viewModel::onBackButtonClicked)
        revertButton.onClick(viewModel::onRevertButtonClicked)
        invertButton.onClick(viewModel::onInvertButtonClicked)
        skipButton.onClick(viewModel::onSkipButtonClicked)
        finishedIcon.onClick(viewModel::onFinishedIconClicked)
        infoButton.onClick(viewModel::onInfoButtonClicked)
        infoLayout.onClick(viewModel::onInfoOverlayClicked)
        gameGrid.doOnLayout { viewModel.onGameGridLaidOut(it.size) }
        gameGrid.onTouch { viewModel.onGameGridTouched(Touch(it)) }
    }

    private fun showInfoContent(content: LayoutRes) {
        infoContainer.scrollTo(0, 0)
        infoContainer.removeAllViews()

        layoutInflater.inflate(content, infoContainer)
    }

    private fun showInfoOverlay(isVisible: Boolean) {
        gameLayout.toggleVisibleGone(!isVisible)
        infoLayout.toggleVisibleGone(isVisible)
    }

    private fun colorInfoButton(color: ColorRes) {
        infoButton.setColorFilter(color)
        infoInfoButton.setColorFilter(color)
    }

    private fun navigateBack() = super.onBackPressed()
}