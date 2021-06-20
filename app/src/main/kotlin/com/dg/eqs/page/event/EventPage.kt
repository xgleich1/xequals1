package com.dg.eqs.page.event

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.dg.eqs.R
import com.dg.eqs.base.extension.getParcelableExtraOrThrow
import com.dg.eqs.base.extension.onClick
import com.dg.eqs.base.extension.setText
import com.dg.eqs.base.extension.toggleVisibleGone
import com.dg.eqs.base.injection.module.EventPageModule
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataEventObserver
import com.dg.eqs.base.observation.LiveDataObserver
import com.dg.eqs.core.competition.Event
import com.dg.eqs.page.Page
import com.dg.eqs.page.game.GameConfig
import com.dg.eqs.page.game.GamePage
import kotlinx.android.synthetic.main.page_event.*
import javax.inject.Inject


class EventPage : Page(R.layout.page_event) {
    companion object {
        private const val SCORE_BOARD_REQUEST_CODE = 1986
        private const val GAME_PAGE_REQUEST_CODE = 1987

        private const val EVENT_EXTRA = "eventExtra"


        fun createIntent(context: Context, event: Event) =
                Intent(context, EventPage::class.java)
                        .putExtra(EVENT_EXTRA, event)
    }

    @Inject
    lateinit var viewModelFactory: EventPageViewModelFactory

    private val viewModel: EventPageViewModel by viewModels { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies()

        connectViewModel()
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            SCORE_BOARD_REQUEST_CODE -> viewModel.onNavigatedBackFromScoreBoard()
            GAME_PAGE_REQUEST_CODE -> viewModel.onNavigatedBackFromGamePage()

            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun injectDependencies() {
        val event: Event = intent
                .getParcelableExtraOrThrow(EVENT_EXTRA)

        applicationComponent
                .eventPageComponentBuilder()
                .eventPageModule(EventPageModule(event))
                .build()
                .inject(this)
    }

    private fun connectViewModel() {
        viewModel.showEventTitle.observe(this,
                LiveDataObserver(eventTitle::setText))
        viewModel.showEventDraft.observe(this,
                LiveDataObserver(eventPanel::showDraft))
        viewModel.scoreButtonEnabled.observe(this,
                LiveDataObserver(scoreButton::setEnabled))
        viewModel.scoreButtonLoading.observe(this,
                LiveDataObserver(scoreButton::setLoading))
        viewModel.playButtonEnabled.observe(this,
                LiveDataObserver(playButton::setEnabled))
        viewModel.playButtonLoading.observe(this,
                LiveDataObserver(playButton::setLoading))
        viewModel.showUploadOverlay.observe(this,
                LiveDataObserver(uploadOverlay::toggleVisibleGone))
        viewModel.navigateToScoreBoard.observe(this,
                LiveDataEventObserver(::navigateToScoreBoard))
        viewModel.navigateToGamePage.observe(this,
                LiveDataEventObserver(::navigateToGamePage))
        viewModel.navigateBack.observe(this,
                LiveDataCommandObserver(::navigateBack))

        backButton.onClick(viewModel::onBackButtonClicked)
        scoreButton.onClick(viewModel::onScoreButtonClicked)
        playButton.onClick(viewModel::onPlayButtonClicked)
    }

    private fun navigateToScoreBoard(scoreBoardIntent: Intent) =
            startActivityForResult(scoreBoardIntent, SCORE_BOARD_REQUEST_CODE)

    private fun navigateToGamePage(gameConfig: GameConfig) {
        val gamePageIntent = GamePage.createIntent(this, gameConfig)

        startActivityForResult(gamePageIntent, GAME_PAGE_REQUEST_CODE)
    }

    private fun navigateBack() = super.onBackPressed()
}