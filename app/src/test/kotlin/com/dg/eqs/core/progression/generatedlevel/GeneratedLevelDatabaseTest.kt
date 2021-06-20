package com.dg.eqs.core.progression.generatedlevel

import com.dg.eqs.core.progression.Level.GeneratedLevel
import com.dg.eqs.core.progression.LevelKey.GeneratedLevelKey
import com.dg.eqs.core.progression.generatedlevel.roomdatabase.GeneratedLevelRoomDao
import com.dg.eqs.core.progression.generatedlevel.roomdatabase.GeneratedLevelRoomEntity
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
class GeneratedLevelDatabaseTest {
    @Mock
    private lateinit var generatedLevelRoomDao: GeneratedLevelRoomDao
    @Mock
    private lateinit var generatedLevelMapper: GeneratedLevelMapper
    @InjectMocks
    private lateinit var generatedLevelDatabase: GeneratedLevelDatabase

    @Mock
    private lateinit var levelA: GeneratedLevel
    @Mock
    private lateinit var levelB: GeneratedLevel
    @Mock
    private lateinit var entityA: GeneratedLevelRoomEntity
    @Mock
    private lateinit var entityB: GeneratedLevelRoomEntity


    @Before
    fun setUp() {
        whenever(generatedLevelMapper.mapToEntity(levelA)) doReturn entityA
        whenever(generatedLevelMapper.mapToLevel(entityA)) doReturn levelA
        whenever(generatedLevelMapper.mapToLevel(entityB)) doReturn levelB
    }

    @Test
    fun `Should save a generated level`() {
        // WHEN
        generatedLevelDatabase.saveLevel(levelA)

        // THEN
        verify(generatedLevelRoomDao).saveEntity(entityA)
    }

    @Test
    fun `Should return the database key of the saved generated level`() {
        // GIVEN
        whenever(generatedLevelRoomDao.saveEntity(entityA)) doReturn 1

        // WHEN
        val databaseKey = generatedLevelDatabase.saveLevel(levelA)

        // THEN
        assertThat(databaseKey).isEqualTo(GeneratedLevelKey(1))
    }

    @Test
    fun `Should load a generated level`() {
        // GIVEN
        val levelKey = GeneratedLevelKey(1)

        whenever(generatedLevelRoomDao.loadEntity(1)) doReturn entityA

        // THEN
        assertThat(generatedLevelDatabase.loadLevel(levelKey)).isEqualTo(levelA)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw an exception when the desired generated level is not saved`() {
        // GIVEN
        val levelKey = GeneratedLevelKey(1)

        whenever(generatedLevelRoomDao.loadEntity(1)) doReturn null

        // WHEN
        generatedLevelDatabase.loadLevel(levelKey)
    }

    @Test
    fun `Should load the first unfinished generated level`() {
        // GIVEN
        whenever(generatedLevelRoomDao.loadFirstUnfinishedEntity()) doReturn entityA

        // THEN
        assertThat(generatedLevelDatabase.loadFirstUnfinishedLevel()).isEqualTo(levelA)
    }

    @Test
    fun `Should load all finished generated level`() {
        // GIVEN
        whenever(generatedLevelRoomDao.loadAllFinishedEntities()) doReturn listOf(entityA, entityB)

        // THEN
        assertThat(generatedLevelDatabase.loadAllFinishedLevel()).isEqualTo(listOf(levelA, levelB))
    }

    @Test
    fun `Should count all played generated level`() {
        // GIVEN
        whenever(generatedLevelRoomDao.countAllPlayedLevel()) doReturn 2

        // THEN
        assertThat(generatedLevelDatabase.countAllPlayedLevel()).isEqualTo(2)
    }
}