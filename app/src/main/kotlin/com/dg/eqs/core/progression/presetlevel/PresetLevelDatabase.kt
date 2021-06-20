package com.dg.eqs.core.progression.presetlevel

import com.dg.eqs.base.persistence.offline.OfflinePersistence
import com.dg.eqs.core.progression.Level.PresetLevel
import com.dg.eqs.core.progression.LevelKey.PresetLevelKey
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomDao
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomEntity


class PresetLevelDatabase(
        private val offlinePersistence: OfflinePersistence,
        private val presetLevelPreset: PresetLevelPreset,
        private val presetLevelRoomDao: PresetLevelRoomDao,
        private val presetLevelMapper: PresetLevelMapper) {

    init {
        savePresetWhenNecessary()
    }

    fun saveLevel(level: PresetLevel) = presetLevelRoomDao
            .saveEntity(level.mapToEntity())

    fun loadLevel(levelKey: PresetLevelKey): PresetLevel {
        val entity = presetLevelRoomDao
                .loadEntity(levelKey.rawKey)

        return requireNotNull(entity).mapToLevel()
    }

    fun loadFirstUnfinishedLevel() = presetLevelRoomDao.loadFirstUnfinishedEntity()?.mapToLevel()

    fun loadAllFinishedLevel() = presetLevelRoomDao.loadAllFinishedEntities().mapToLevel()

    fun countAllPlayedLevel() = presetLevelRoomDao.countAllPlayedEntities()

    private fun savePresetWhenNecessary() = with(presetLevelPreset) {
        val savedPresetVersion = offlinePersistence
                .loadInteger(PresetVersionKey, 0)

        if(presetVersion != savedPresetVersion) {
            presetLevelRoomDao.saveAllEntities(presetEntities)

            offlinePersistence.saveInteger(PresetVersionKey, presetVersion)
        }
    }

    private fun PresetLevel.mapToEntity() = presetLevelMapper.mapToEntity(this)

    private fun PresetLevelRoomEntity.mapToLevel() = presetLevelMapper.mapToLevel(this)

    private fun List<PresetLevelRoomEntity>.mapToLevel() = map(presetLevelMapper::mapToLevel)
}