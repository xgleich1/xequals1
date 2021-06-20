package com.dg.eqs.page.level

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataEventObserver
import com.dg.eqs.base.observation.LiveDataObserver
import com.dg.eqs.core.progression.Level.GeneratedLevel
import com.dg.eqs.core.progression.Level.PresetLevel
import com.dg.eqs.core.progression.LevelKey.GeneratedLevelKey
import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase
import com.dg.eqs.page.game.GameConfig
import com.dg.eqs.page.game.GameConfig.SingleLevelGameConfig
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LevelPageViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var generatedLevelDatabase: GeneratedLevelDatabase
    @Mock
    private lateinit var presetLevelDatabase: PresetLevelDatabase
    @Mock
    private lateinit var levelItemBuilder: LevelItemBuilder

    @Mock
    private lateinit var showHintAction: (Boolean) -> Unit
    @Mock
    private lateinit var showItemsAction: (List<LevelItem>) -> Unit
    @Mock
    private lateinit var navigateBackAction: () -> Unit
    @Mock
    private lateinit var navigateToGamePageAction: (GameConfig) -> Unit

    @Mock
    private lateinit var generatedLevelA: GeneratedLevel
    @Mock
    private lateinit var generatedLevelB: GeneratedLevel
    @Mock
    private lateinit var presetLevelA: PresetLevel
    @Mock
    private lateinit var presetLevelB: PresetLevel
    @Mock
    private lateinit var generatedLevelItemA: LevelItem
    @Mock
    private lateinit var generatedLevelItemB: LevelItem
    @Mock
    private lateinit var presetLevelItemA: LevelItem
    @Mock
    private lateinit var presetLevelItemB: LevelItem


    @Before
    fun setUp() = setupLevelItemBuilding()

    @Test
    fun `Should show the hint when there are no level finished yet`() {
        // GIVEN
        createAndObserveViewModel()

        // THEN
        verify(showHintAction).invoke(true)
    }

    @Test
    fun `Should hide the hint when there's at least one finished level`() {
        // GIVEN
        setupFinishedPresetLevel(presetLevelA)

        createAndObserveViewModel()

        // THEN
        verify(showHintAction).invoke(false)
    }

    @Test
    fun `Should show all finished level with the generated ones first`() {
        // GIVEN
        setupFinishedGeneratedLevel(generatedLevelA, generatedLevelB)
        setupFinishedPresetLevel(presetLevelA, presetLevelB)

        createAndObserveViewModel()

        // THEN
        verify(showItemsAction).invoke(listOf(
                generatedLevelItemA,
                generatedLevelItemB,
                presetLevelItemA,
                presetLevelItemB))
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
    fun `Should navigate to the single level game page when a level item is clicked`() {
        // GIVEN
        val levelKey: GeneratedLevelKey = mock()

        whenever(generatedLevelA.levelKey) doReturn levelKey

        setupFinishedGeneratedLevel(generatedLevelA)

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onLevelItemClicked(0)

        // THEN
        verify(navigateToGamePageAction).invoke(SingleLevelGameConfig(levelKey))
    }

    @Test
    fun `Should update the shown level when navigating back from the game page`() {
        // GIVEN
        val viewModel = createAndObserveViewModel()

        setupFinishedGeneratedLevel(generatedLevelA, generatedLevelB)
        setupFinishedPresetLevel(presetLevelA, presetLevelB)

        // WHEN
        viewModel.onNavigatedBackFromGamePage()

        // THEN
        verify(showItemsAction).invoke(listOf(
                generatedLevelItemA,
                generatedLevelItemB,
                presetLevelItemA,
                presetLevelItemB))
    }

    private fun setupLevelItemBuilding() {
        whenever(levelItemBuilder.buildLevelItem(
                generatedLevelA)) doReturn generatedLevelItemA

        whenever(levelItemBuilder.buildLevelItem(
                generatedLevelB)) doReturn generatedLevelItemB

        whenever(levelItemBuilder.buildLevelItem(
                presetLevelA)) doReturn presetLevelItemA

        whenever(levelItemBuilder.buildLevelItem(
                presetLevelB)) doReturn presetLevelItemB
    }

    private fun createAndObserveViewModel(): LevelPageViewModel {
        val viewModel = LevelPageViewModel(
                generatedLevelDatabase,
                presetLevelDatabase,
                levelItemBuilder)

        viewModel.showHint.observeForever(
                LiveDataObserver(showHintAction))
        viewModel.showItems.observeForever(
                LiveDataObserver(showItemsAction))
        viewModel.navigateBack.observeForever(
                LiveDataCommandObserver(navigateBackAction))
        viewModel.navigateToGamePage.observeForever(
                LiveDataEventObserver(navigateToGamePageAction))

        return viewModel
    }

    private fun setupFinishedGeneratedLevel(vararg level: GeneratedLevel) {
        whenever(generatedLevelDatabase.loadAllFinishedLevel()) doReturn listOf(*level)
    }

    private fun setupFinishedPresetLevel(vararg level: PresetLevel) {
        whenever(presetLevelDatabase.loadAllFinishedLevel()) doReturn listOf(*level)
    }
}