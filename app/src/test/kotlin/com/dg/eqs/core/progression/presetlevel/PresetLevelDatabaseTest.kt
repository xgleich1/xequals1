package com.dg.eqs.core.progression.presetlevel

import com.dg.eqs.base.persistence.offline.OfflinePersistence
import com.dg.eqs.core.progression.Level.PresetLevel
import com.dg.eqs.core.progression.LevelKey.PresetLevelKey
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomDao
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomEntity
import org.mockito.kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PresetLevelDatabaseTest {
    @Mock
    private lateinit var offlinePersistence: OfflinePersistence
    @Mock
    private lateinit var presetLevelPreset: PresetLevelPreset
    @Mock
    private lateinit var presetLevelRoomDao: PresetLevelRoomDao
    @Mock
    private lateinit var presetLevelMapper: PresetLevelMapper
    @InjectMocks
    private lateinit var presetLevelDatabase: PresetLevelDatabase

    @Mock
    private lateinit var levelA: PresetLevel
    @Mock
    private lateinit var levelB: PresetLevel
    @Mock
    private lateinit var entityA: PresetLevelRoomEntity
    @Mock
    private lateinit var entityB: PresetLevelRoomEntity


    @Before
    fun setUp() {
        whenever(presetLevelMapper.mapToEntity(levelA)) doReturn entityA
        whenever(presetLevelMapper.mapToLevel(entityA)) doReturn levelA
        whenever(presetLevelMapper.mapToLevel(entityB)) doReturn levelB
    }

    @Test
    fun `Should save the preset when there's a new version of it`() {
        // GIVEN
        val preset: List<PresetLevelRoomEntity> = mock()

        whenever(offlinePersistence.loadInteger(PresetVersionKey, 0)) doReturn 0

        whenever(presetLevelPreset.presetVersion) doReturn 1

        whenever(presetLevelPreset.presetEntities) doReturn preset

        createPresetLevelDatabase()

        // THEN
        verify(presetLevelRoomDao).saveAllEntities(preset)
    }

    @Test
    fun `Should not save the preset when there's no new version of it`() {
        // GIVEN
        whenever(offlinePersistence.loadInteger(PresetVersionKey, 0)) doReturn 1

        whenever(presetLevelPreset.presetVersion) doReturn 1

        createPresetLevelDatabase()

        // THEN
        verify(presetLevelRoomDao, never()).saveAllEntities(any())
    }

    @Test
    fun `Should persist the version of the preset when there's a new one`() {
        // GIVEN
        whenever(offlinePersistence.loadInteger(PresetVersionKey, 0)) doReturn 0

        whenever(presetLevelPreset.presetVersion) doReturn 1

        whenever(presetLevelPreset.presetEntities) doReturn mock()

        createPresetLevelDatabase()

        // THEN
        verify(offlinePersistence).saveInteger(PresetVersionKey, 1)
    }

    @Test
    fun `Should not persist the version of the preset when there's no new one`() {
        // GIVEN
        whenever(offlinePersistence.loadInteger(PresetVersionKey, 0)) doReturn 1

        whenever(presetLevelPreset.presetVersion) doReturn 1

        createPresetLevelDatabase()

        // THEN
        verify(offlinePersistence, never()).saveInteger(eq(PresetVersionKey), any())
    }

    @Test
    fun `Should save a preset level`() {
        // WHEN
        presetLevelDatabase.saveLevel(levelA)

        // THEN
        verify(presetLevelRoomDao).saveEntity(entityA)
    }

    @Test
    fun `Should load a preset level`() {
        // GIVEN
        val levelKey = PresetLevelKey(1)

        whenever(presetLevelRoomDao.loadEntity(1)) doReturn entityA

        // THEN
        assertThat(presetLevelDatabase.loadLevel(levelKey)).isEqualTo(levelA)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw an exception when the desired preset level is not saved`() {
        // GIVEN
        val levelKey = PresetLevelKey(1)

        whenever(presetLevelRoomDao.loadEntity(1)) doReturn null

        // WHEN
        presetLevelDatabase.loadLevel(levelKey)
    }

    @Test
    fun `Should load the first unfinished preset level`() {
        // GIVEN
        whenever(presetLevelRoomDao.loadFirstUnfinishedEntity()) doReturn entityA

        // THEN
        assertThat(presetLevelDatabase.loadFirstUnfinishedLevel()).isEqualTo(levelA)
    }

    @Test
    fun `Should load all finished preset level`() {
        // GIVEN
        whenever(presetLevelRoomDao.loadAllFinishedEntities()) doReturn listOf(entityA, entityB)

        // THEN
        assertThat(presetLevelDatabase.loadAllFinishedLevel()).isEqualTo(listOf(levelA, levelB))
    }

    @Test
    fun `Should count all played preset level`() {
        // GIVEN
        whenever(presetLevelRoomDao.countAllPlayedEntities()) doReturn 2

        // THEN
        assertThat(presetLevelDatabase.countAllPlayedLevel()).isEqualTo(2)
    }

    private fun createPresetLevelDatabase() = PresetLevelDatabase(
            offlinePersistence,
            presetLevelPreset,
            presetLevelRoomDao,
            presetLevelMapper)
}