package com.dg.eqs.page.offline

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dg.eqs.R
import com.dg.eqs.base.enveloping.HtmlRes
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataEventObserver
import com.dg.eqs.base.observation.LiveDataObserver
import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase
import com.dg.eqs.page.game.GameConfig
import com.dg.eqs.page.game.GameConfig.EndlessLevelGameConfig
import org.mockito.kotlin.doReturnConsecutively
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class OfflinePageViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var presetLevelDatabase: PresetLevelDatabase
    @Mock
    private lateinit var generatedLevelDatabase: GeneratedLevelDatabase

    @Mock
    private lateinit var showMessageAction: (HtmlRes) -> Unit
    @Mock
    private lateinit var navigateBackAction: () -> Unit
    @Mock
    private lateinit var navigateToLevelPageAction: () -> Unit
    @Mock
    private lateinit var navigateToGamePageAction: (GameConfig) -> Unit


    @Test
    fun `Should show a dedicated message when there are no equations solved`() {
        // GIVEN
        setupPlayedPresetLevelCount(0)
        setupPlayedGeneratedLevelCount(0)

        createAndObserveViewModel()

        // THEN
        verify(showMessageAction).invoke(
                HtmlRes(R.string.offline_message_no_equations_solved))
    }

    @Test
    fun `Should show a dedicated message when there is one equation solved`() {
        // GIVEN
        setupPlayedPresetLevelCount(1)
        setupPlayedGeneratedLevelCount(0)

        createAndObserveViewModel()

        // THEN
        verify(showMessageAction).invoke(
                HtmlRes(R.string.offline_message_one_equation_solved))
    }

    @Test
    fun `Should show a dedicated message when there are some equations solved`() {
        // GIVEN
        setupPlayedPresetLevelCount(40)
        setupPlayedGeneratedLevelCount(59)

        createAndObserveViewModel()

        // THEN
        verify(showMessageAction).invoke(
                HtmlRes(R.string.offline_message_some_equations_solved, 99))
    }

    @Test
    fun `Should show a dedicated message when there are many equations solved`() {
        // GIVEN
        setupPlayedPresetLevelCount(40)
        setupPlayedGeneratedLevelCount(60)

        createAndObserveViewModel()

        // THEN
        verify(showMessageAction).invoke(
                HtmlRes(R.string.offline_message_many_equations_solved, 100))
    }

    @Test
    fun `Should navigate back when back is pressed`() {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onBackPressed()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should navigate back when the back button is clicked`() {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onBackButtonClicked()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should navigate to the level page when the level button is clicked`() {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onLevelButtonClicked()

        // THEN
        verify(navigateToLevelPageAction).invoke()
    }

    @Test
    fun `Should navigate to the endless level game page when the play button is clicked`() {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onPlayButtonClicked()

        // THEN
        verify(navigateToGamePageAction).invoke(EndlessLevelGameConfig)
    }

    @Test
    fun `Should update the dedicated message when navigating back from the level page`() {
        // GIVEN
        setupPlayedPresetLevelCount(39, 40)
        setupPlayedGeneratedLevelCount(59, 60)

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onNavigatedBackFromLevelPage()

        // THEN
        verify(showMessageAction).invoke(
                HtmlRes(R.string.offline_message_many_equations_solved, 100))
    }

    @Test
    fun `Should update the dedicated message when navigating back from the game page`() {
        // GIVEN
        setupPlayedPresetLevelCount(0, 40)
        setupPlayedGeneratedLevelCount(0, 60)

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onNavigatedBackFromGamePage()

        // THEN
        verify(showMessageAction).invoke(
                HtmlRes(R.string.offline_message_many_equations_solved, 100))
    }

    private fun createAndObserveViewModel(): OfflinePageViewModel {
        val viewModel = OfflinePageViewModel(
                presetLevelDatabase,
                generatedLevelDatabase)

        viewModel.showMessage.observeForever(
                LiveDataObserver(showMessageAction))
        viewModel.navigateBack.observeForever(
                LiveDataCommandObserver(navigateBackAction))
        viewModel.navigateToLevelPage.observeForever(
                LiveDataCommandObserver(navigateToLevelPageAction))
        viewModel.navigateToGamePage.observeForever(
                LiveDataEventObserver(navigateToGamePageAction))

        return viewModel
    }

    private fun setupPlayedPresetLevelCount(vararg counts: Int) =
            whenever(presetLevelDatabase.countAllPlayedLevel()) doReturnConsecutively counts.toList()

    private fun setupPlayedGeneratedLevelCount(vararg counts: Int) =
            whenever(generatedLevelDatabase.countAllPlayedLevel()) doReturnConsecutively counts.toList()
}