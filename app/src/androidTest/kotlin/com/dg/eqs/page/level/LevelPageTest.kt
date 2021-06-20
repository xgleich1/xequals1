package com.dg.eqs.page.level

import com.dg.eqs.core.orchestration.endlesslevelgame.info.EndlessLevelGameHowToInfoMadeVitalKey
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomEntity
import com.dg.eqs.page.game.GamePageRobot
import com.dg.eqs.util.dagger.TestDaggerMockRule
import org.junit.After
import org.junit.Rule
import org.junit.Test


class LevelPageTest {
    @get:Rule
    val daggerRule = TestDaggerMockRule()


    @After
    fun tearDown() = deleteAllPresetLevelEntities()

    @Test
    fun should_show_the_hint_when_there_are_no_level_finished_yet() {
        // GIVEN
        LevelPageRobot().launch()

        // THEN
        LevelPageRobot().isHintVisible()
    }

    @Test
    fun should_hide_the_hint_when_there_is_at_least_one_level_finished() {
        // GIVEN
        savePresetLevelEntity(PresetLevelRoomEntity(
                1, "=[+x,+±[+1,+2]]", true, 0, 0))

        LevelPageRobot().launch()

        // THEN
        LevelPageRobot().isHintHidden()
    }

    @Test
    fun should_navigate_to_the_game_page_when_a_level_is_clicked() {
        // GIVEN
        savePresetLevelEntity(PresetLevelRoomEntity(
                1, "=[+x,+±[+1,+2]]", true, 1, 1))

        LevelPageRobot().launch()

        // WHEN
        LevelPageRobot().clickFirstLevel()

        // THEN
        GamePageRobot().isVisible()
    }

    @Test
    fun should_update_the_steps_of_a_level_when_its_replayed_in_less() {
        // GIVEN
        savePresetLevelEntity(PresetLevelRoomEntity(
                1, "=[+x,+±[+1,+2]]", true, 2, 2))

        setupEndlessLevelGameHowToInfoHidden()

        LevelPageRobot()
                .launch()
                .clickFirstLevel()

        // WHEN
        GamePageRobot()
                .linkSymbols("1", "2")
                .clickFinishedIcon()

        // THEN
        LevelPageRobot().isFirstLevelFinishedInOneStepTextVisible()
    }

    private fun savePresetLevelEntity(entity: PresetLevelRoomEntity) =
            daggerRule.testPresetLevelRoomDao.saveEntity(entity)

    private fun deleteAllPresetLevelEntities() =
            daggerRule.testPresetLevelRoomDao.deleteAllEntities()

    private fun setupEndlessLevelGameHowToInfoHidden() =
            daggerRule.testOfflinePersistence.saveBoolean(
                    EndlessLevelGameHowToInfoMadeVitalKey, true)
}