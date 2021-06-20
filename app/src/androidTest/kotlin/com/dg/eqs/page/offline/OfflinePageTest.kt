package com.dg.eqs.page.offline

import com.dg.eqs.core.orchestration.endlesslevelgame.info.EndlessLevelGameAppReviewInfoLoadedKey
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomEntity
import com.dg.eqs.page.game.GamePageRobot
import com.dg.eqs.page.level.LevelPageRobot
import com.dg.eqs.util.dagger.TestDaggerMockRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class OfflinePageTest {
    @get:Rule
    val testDaggerMockRule = TestDaggerMockRule()


    @Before
    fun setUp() {
        saveAppReviewInfoShown()
    }

    @After
    fun tearDown() {
        deleteAllPresetLevelEntities()
        deleteAllGeneratedLevelEntities()
    }

    @Test
    fun should_show_a_dedicated_message_detailing_the_equations_solved() {
        // GIVEN
        savePresetLevelEntity(PresetLevelRoomEntity(
                1, "=[+x,+±[+1,+2]]", false, 0, 0))

        OfflinePageRobot().launch()

        // THEN
        OfflinePageRobot().isNoEquationsSolvedMessageVisible()
    }

    @Test
    fun should_navigate_to_the_level_page_when_the_level_button_is_clicked() {
        // GIVEN
        OfflinePageRobot().launch()

        // WHEN
        OfflinePageRobot().clickLevelButton()

        // THEN
        LevelPageRobot().isVisible()
    }

    @Test
    fun should_navigate_to_the_game_page_when_the_play_button_is_clicked() {
        // GIVEN
        OfflinePageRobot().launch()

        // WHEN
        OfflinePageRobot().clickPlayButton()

        // THEN
        GamePageRobot().isVisible()
    }

    @Test
    fun should_update_the_dedicated_message_when_navigating_back_from_the_level_page() {
        // GIVEN
        savePresetLevelEntity(PresetLevelRoomEntity(
                1, "=[+x,+±[+1,+2]]", true, 0, 0))

        OfflinePageRobot().launch()

        OfflinePageRobot().clickLevelButton()

        LevelPageRobot().clickFirstLevel()

        GamePageRobot().linkSymbols("1", "2")

        GamePageRobot().clickFinishedIcon()

        // WHEN
        LevelPageRobot().clickBackButton()

        // THEN
        OfflinePageRobot().isOneEquationSolvedMessageVisible()
    }

    @Test
    fun should_update_the_dedicated_message_when_navigating_back_from_the_game_page() {
        // GIVEN
        savePresetLevelEntity(PresetLevelRoomEntity(
                1, "=[+x,+±[+1,+2]]", false, 0, 0))

        OfflinePageRobot().launch()

        OfflinePageRobot().clickPlayButton()

        GamePageRobot().linkSymbols("1", "2")

        GamePageRobot().clickFinishedIcon()

        // WHEN
        GamePageRobot().clickBackButton()

        // THEN
        OfflinePageRobot().isOneEquationSolvedMessageVisible()
    }

    private fun saveAppReviewInfoShown() =
            testDaggerMockRule.testOfflinePersistence
                    .saveBoolean(EndlessLevelGameAppReviewInfoLoadedKey, true)

    private fun savePresetLevelEntity(entity: PresetLevelRoomEntity) =
            testDaggerMockRule.testPresetLevelRoomDao.saveEntity(entity)

    private fun deleteAllPresetLevelEntities() =
            testDaggerMockRule.testPresetLevelRoomDao.deleteAllEntities()

    private fun deleteAllGeneratedLevelEntities() =
            testDaggerMockRule.testGeneratedLevelRoomDao.deleteAllEntities()
}