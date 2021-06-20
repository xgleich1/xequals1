package com.dg.eqs.page.level

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.dg.eqs.R
import com.dg.eqs.base.extension.onClick
import com.dg.eqs.base.extension.toggleVisibleGone
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataEventObserver
import com.dg.eqs.base.observation.LiveDataObserver
import com.dg.eqs.page.Page
import com.dg.eqs.page.game.GameConfig
import com.dg.eqs.page.game.GamePage
import kotlinx.android.synthetic.main.page_level.*
import javax.inject.Inject


class LevelPage : Page(R.layout.page_level) {
    companion object {
        private const val GAME_PAGE_REQUEST_CODE = 1


        fun createIntent(context: Context) =
                Intent(context, LevelPage::class.java)
    }

    @Inject
    lateinit var viewModelFactory: LevelPageViewModelFactory

    private val viewModel: LevelPageViewModel by viewModels { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies()

        connectViewModel()
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            GAME_PAGE_REQUEST_CODE -> viewModel.onNavigatedBackFromGamePage()

            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun injectDependencies() = applicationComponent
            .levelPageComponentBuilder()
            .build()
            .inject(this)

    private fun connectViewModel() {
        viewModel.showHint.observe(this,
                LiveDataObserver(levelHint::toggleVisibleGone))
        viewModel.showItems.observe(this,
                LiveDataObserver(levelList::showItems))
        viewModel.navigateBack.observe(this,
                LiveDataCommandObserver(::navigateBack))
        viewModel.navigateToGamePage.observe(this,
                LiveDataEventObserver(::navigateToGamePage))

        backButton.onClick(viewModel::onBackButtonClicked)
        levelList.onItemClick(viewModel::onLevelItemClicked)
    }

    private fun navigateBack() = super.onBackPressed()

    private fun navigateToGamePage(gameConfig: GameConfig) = startActivityForResult(
            GamePage.createIntent(this, gameConfig), GAME_PAGE_REQUEST_CODE)
}