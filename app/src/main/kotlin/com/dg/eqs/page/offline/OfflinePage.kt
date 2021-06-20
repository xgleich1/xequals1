package com.dg.eqs.page.offline

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
import com.dg.eqs.page.game.GameConfig
import com.dg.eqs.page.game.GamePage
import com.dg.eqs.page.level.LevelPage
import kotlinx.android.synthetic.main.page_assist.backButton
import kotlinx.android.synthetic.main.page_offline.*
import javax.inject.Inject


class OfflinePage : Page(R.layout.page_offline) {
    companion object {
        private const val LEVEL_PAGE_REQUEST_CODE = 1986
        private const val GAME_PAGE_REQUEST_CODE = 1987


        fun createIntent(context: Context) =
                Intent(context, OfflinePage::class.java)
    }

    @Inject
    lateinit var viewModelFactory: OfflinePageViewModelFactory

    private val viewModel: OfflinePageViewModel by viewModels { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies()

        connectViewModel()
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            LEVEL_PAGE_REQUEST_CODE -> viewModel.onNavigatedBackFromLevelPage()
            GAME_PAGE_REQUEST_CODE -> viewModel.onNavigatedBackFromGamePage()

            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun injectDependencies() = applicationComponent
            .offlinePageComponentBuilder()
            .build()
            .inject(this)

    private fun connectViewModel() {
        viewModel.showMessage.observe(this,
                LiveDataObserver(message::setHtml))
        viewModel.navigateBack.observe(this,
                LiveDataCommandObserver(::navigateBack))
        viewModel.navigateToLevelPage.observe(this,
                LiveDataCommandObserver(::navigateToLevelPage))
        viewModel.navigateToGamePage.observe(this,
                LiveDataEventObserver(::navigateToGamePage))

        backButton.onClick(viewModel::onBackButtonClicked)
        levelButton.onClick(viewModel::onLevelButtonClicked)
        playButton.onClick(viewModel::onPlayButtonClicked)
    }

    private fun navigateBack() = super.onBackPressed()

    private fun navigateToLevelPage() =
            startActivityForResult(LevelPage.createIntent(this), LEVEL_PAGE_REQUEST_CODE)

    private fun navigateToGamePage(gameConfig: GameConfig) =
            startActivityForResult(GamePage.createIntent(this, gameConfig), GAME_PAGE_REQUEST_CODE)
}