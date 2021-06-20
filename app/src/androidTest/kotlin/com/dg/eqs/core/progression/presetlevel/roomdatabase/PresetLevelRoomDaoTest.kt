package com.dg.eqs.core.progression.presetlevel.roomdatabase

import com.dg.eqs.util.classes.TestPresetLevelRoomDatabaseBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test


class PresetLevelRoomDaoTest {
    private lateinit var dao: PresetLevelRoomDao


    @Before
    fun setUp() {
        dao = TestPresetLevelRoomDatabaseBuilder().build().dao
    }

    @After
    fun tearDown() = dao.deleteAllEntities()

    @Test
    fun should_save_a_preset_level_entity() {
        // GIVEN
        val entity = PresetLevelRoomEntity(1, "+±[+1,+2]", false, 0, 0)

        // WHEN
        dao.saveEntity(entity)

        // THEN
        assertThat(dao.loadEntity(1)).isEqualTo(entity)
    }

    @Test
    fun should_replace_a_preset_level_entity() {
        // GIVEN
        val entityA = PresetLevelRoomEntity(1, "+±[+1,+2]", false, 0, 0)
        val entityB = PresetLevelRoomEntity(1, "+±[+1,+2]", true, 1, 1)

        dao.saveEntity(entityA)

        // WHEN
        dao.saveEntity(entityB)

        // THEN
        assertThat(dao.loadEntity(1)).isEqualTo(entityB)
    }

    @Test
    fun should_return_null_when_the_desired_preset_level_entity_is_not_saved() {
        // GIVEN
        val entity = PresetLevelRoomEntity(1, "+±[+1,+2]", true, 1, 1)

        dao.saveEntity(entity)

        // THEN
        assertNull(dao.loadEntity(2))
    }

    @Test
    fun should_save_preset_level_entities() {
        // GIVEN
        val entityA = PresetLevelRoomEntity(1, "+±[+1,+2]", false, 0, 0)
        val entityB = PresetLevelRoomEntity(2, "+±[+1,+2,+3]", false, 0, 0)

        // WHEN
        dao.saveAllEntities(listOf(entityA, entityB))

        // THEN
        assertThat(dao.loadEntity(1)).isEqualTo(entityA)
        assertThat(dao.loadEntity(2)).isEqualTo(entityB)
    }

    @Test
    fun should_replace_preset_level_entities() {
        // GIVEN
        val entityA = PresetLevelRoomEntity(1, "+±[+1,+2]", true, 1, 1)
        val entityB = PresetLevelRoomEntity(2, "+±[+1,+2,+3]", true, 2, 2)
        val entityC = PresetLevelRoomEntity(1, "+±[+1,+2,+3,+4]", false, 0, 0)
        val entityD = PresetLevelRoomEntity(2, "+±[+1,+2,+3,+4,+5]", false, 0, 0)

        dao.saveAllEntities(listOf(entityA, entityB))

        // WHEN
        dao.saveAllEntities(listOf(entityC, entityD))

        // THEN
        assertThat(dao.loadEntity(1)).isEqualTo(entityC)
        assertThat(dao.loadEntity(2)).isEqualTo(entityD)
    }

    @Test
    fun should_load_the_first_unfinished_preset_level_entity() {
        // GIVEN
        val entityA = PresetLevelRoomEntity(1, "+±[+1,+2]", true, 0, 0)
        val entityB = PresetLevelRoomEntity(2, "+±[+1,+2,+3]", true, 0, 0)
        val entityC = PresetLevelRoomEntity(3, "+±[+1,+2,+3,+4]", false, 0, 0)
        val entityD = PresetLevelRoomEntity(4, "+±[+1,+2,+3,+4,+5]", false, 0, 0)

        dao.saveEntity(entityA)
        dao.saveEntity(entityB)
        dao.saveEntity(entityC)
        dao.saveEntity(entityD)

        // THEN
        assertThat(dao.loadFirstUnfinishedEntity()).isEqualTo(entityC)
    }

    @Test
    fun should_return_null_when_there_is_no_unfinished_preset_level_entity_saved() {
        // GIVEN
        val entityA = PresetLevelRoomEntity(1, "+±[+1,+2]", true, 0, 0)
        val entityB = PresetLevelRoomEntity(2, "+±[+1,+2,+3]", true, 0, 0)

        dao.saveEntity(entityA)
        dao.saveEntity(entityB)

        // THEN
        assertNull(dao.loadFirstUnfinishedEntity())
    }

    @Test
    fun should_load_all_finished_preset_level_entities_ordered_by_most_recently_finished() {
        // GIVEN
        val entityA = PresetLevelRoomEntity(1, "+±[+1,+2]", true, 0, 0)
        val entityB = PresetLevelRoomEntity(2, "+±[+1,+2,+3]", true, 0, 0)
        val entityC = PresetLevelRoomEntity(3, "+±[+1,+2,+3,+4]", false, 0, 0)
        val entityD = PresetLevelRoomEntity(4, "+±[+1,+2,+3,+4,+5]", false, 0, 0)

        dao.saveEntity(entityA)
        dao.saveEntity(entityB)
        dao.saveEntity(entityC)
        dao.saveEntity(entityD)

        // THEN
        val expectedEntities = listOf(entityB, entityA)

        assertThat(dao.loadAllFinishedEntities()).isEqualTo(expectedEntities)
    }

    @Test
    fun should_return_an_empty_list_when_there_are_no_finished_preset_level_entities_saved() {
        // GIVEN
        val entityA = PresetLevelRoomEntity(1, "+±[+1,+2]", false, 0, 0)
        val entityB = PresetLevelRoomEntity(2, "+±[+1,+2,+3]", false, 0, 0)

        dao.saveEntity(entityA)
        dao.saveEntity(entityB)

        // THEN
        assertThat(dao.loadAllFinishedEntities()).isEmpty()
    }

    @Test
    fun should_count_all_played_preset_level_entities() {
        // GIVEN
        val entityA = PresetLevelRoomEntity(1, "+±[+1,+2]", true, 1, 1)
        val entityB = PresetLevelRoomEntity(2, "+±[+1,+2,+3]", true, 2, 2)
        val entityC = PresetLevelRoomEntity(3, "+±[+1,+2,+3,+4]", true, 0, 0)
        val entityD = PresetLevelRoomEntity(4, "+±[+1,+2,+3,+4,+5]", true, 0, 0)

        dao.saveEntity(entityA)
        dao.saveEntity(entityB)
        dao.saveEntity(entityC)
        dao.saveEntity(entityD)

        // THEN
        assertThat(dao.countAllPlayedEntities()).isEqualTo(2)
    }

    @Test
    fun should_delete_all_preset_level_entities() {
        // GIVEN
        val entityA = PresetLevelRoomEntity(1, "+±[+1,+2]", true, 1, 1)
        val entityB = PresetLevelRoomEntity(2, "+±[+1,+2,+3]", true, 2, 2)
        val entityC = PresetLevelRoomEntity(3, "+±[+1,+2,+3,+4]", false, 0, 0)
        val entityD = PresetLevelRoomEntity(4, "+±[+1,+2,+3,+4,+5]", false, 0, 0)

        dao.saveEntity(entityA)
        dao.saveEntity(entityB)
        dao.saveEntity(entityC)
        dao.saveEntity(entityD)

        // WHEN
        dao.deleteAllEntities()

        // THEN
        assertNull(dao.loadEntity(1))
        assertNull(dao.loadEntity(2))
        assertNull(dao.loadEntity(3))
        assertNull(dao.loadEntity(4))
    }
}