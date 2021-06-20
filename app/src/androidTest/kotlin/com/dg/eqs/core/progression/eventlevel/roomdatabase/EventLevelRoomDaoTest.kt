package com.dg.eqs.core.progression.eventlevel.roomdatabase

import com.dg.eqs.util.classes.TestEventLevelRoomDatabaseBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test


class EventLevelRoomDaoTest {
    private lateinit var dao: EventLevelRoomDao


    @Before
    fun setUp() {
        dao = TestEventLevelRoomDatabaseBuilder().build().dao
    }

    @After
    fun tearDown() = dao.deleteAllEntities()

    @Test
    fun should_save_a_event_level_entity() {
        // GIVEN
        val entity = EventLevelRoomEntity(1, "+±[+1,+2]", false, 0, 1)

        // WHEN
        dao.saveEntity(entity)

        // THEN
        assertThat(dao.loadEntity(1)).isEqualTo(entity)
    }

    @Test
    fun should_replace_a_event_level_entity() {
        // GIVEN
        val entityA = EventLevelRoomEntity(1, "+±[+1,+2]", false, 0, 1)
        val entityB = EventLevelRoomEntity(1, "+±[+1,+2]", true, 1, 1)

        dao.saveEntity(entityA)

        // WHEN
        dao.saveEntity(entityB)

        // THEN
        assertThat(dao.loadEntity(1)).isEqualTo(entityB)
    }

    @Test
    fun should_return_null_when_the_desired_event_level_entity_is_not_saved() {
        // GIVEN
        val entity = EventLevelRoomEntity(1, "+±[+1,+2]", true, 1, 1)

        dao.saveEntity(entity)

        // THEN
        assertThat(dao.loadEntity(2)).isNull()
    }

    @Test
    fun should_delete_all_event_level_entities() {
        // GIVEN
        val entityA = EventLevelRoomEntity(1, "+±[+1,+2]", true, 1, 1)
        val entityB = EventLevelRoomEntity(2, "+±[+1,+2,+3]", true, 2, 2)
        val entityC = EventLevelRoomEntity(3, "+±[+1,+2,+3,+4]", false, 0, 3)
        val entityD = EventLevelRoomEntity(4, "+±[+1,+2,+3,+4,+5]", false, 0, 4)

        dao.saveEntity(entityA)
        dao.saveEntity(entityB)
        dao.saveEntity(entityC)
        dao.saveEntity(entityD)

        // WHEN
        dao.deleteAllEntities()

        // THEN
        assertThat(dao.loadEntity(1)).isNull()
        assertThat(dao.loadEntity(2)).isNull()
        assertThat(dao.loadEntity(3)).isNull()
        assertThat(dao.loadEntity(4)).isNull()
    }
}