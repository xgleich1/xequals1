package com.dg.eqs.core.progression.eventlevel

import com.dg.eqs.core.progression.Level.EventLevel
import com.dg.eqs.core.progression.LevelKey.EventLevelKey
import com.dg.eqs.core.progression.eventlevel.roomdatabase.EventLevelRoomDao
import com.dg.eqs.core.progression.eventlevel.roomdatabase.EventLevelRoomEntity
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EventLevelDatabaseTest {
    @Mock
    private lateinit var eventLevelRoomDao: EventLevelRoomDao
    @Mock
    private lateinit var eventLevelMapper: EventLevelMapper
    @InjectMocks
    private lateinit var eventLevelDatabase: EventLevelDatabase

    @Mock
    private lateinit var level: EventLevel
    @Mock
    private lateinit var entity: EventLevelRoomEntity


    @Before
    fun setUp() {
        whenever(eventLevelMapper.mapToEntity(level)) doReturn entity
        whenever(eventLevelMapper.mapToLevel(entity)) doReturn level
    }

    @Test
    fun `Should save a event level`() {
        // WHEN
        eventLevelDatabase.saveLevel(level)

        // THEN
        verify(eventLevelRoomDao).saveEntity(entity)
    }

    @Test
    fun `Should load a event level`() {
        // GIVEN
        val levelKey = EventLevelKey(1)

        whenever(eventLevelRoomDao.loadEntity(1)) doReturn entity

        // THEN
        assertThat(eventLevelDatabase.loadLevel(levelKey)).isEqualTo(level)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw an exception when the desired event level is not saved`() {
        // GIVEN
        val levelKey = EventLevelKey(1)

        whenever(eventLevelRoomDao.loadEntity(1)) doReturn null

        // WHEN
        eventLevelDatabase.loadLevel(levelKey)
    }
}