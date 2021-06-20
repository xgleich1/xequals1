package com.dg.eqs.core.progression.generatedlevel

import com.dg.eqs.core.progression.Level.GeneratedLevel
import com.dg.eqs.core.progression.LevelKey.GeneratedLevelKey
import com.dg.eqs.core.progression.generatedlevel.roomdatabase.GeneratedLevelRoomDao
import com.dg.eqs.core.progression.generatedlevel.roomdatabase.GeneratedLevelRoomEntity


class GeneratedLevelDatabase(
        private val generatedLevelRoomDao: GeneratedLevelRoomDao,
        private val generatedLevelMapper: GeneratedLevelMapper) {

    fun saveLevel(level: GeneratedLevel): GeneratedLevelKey {
        val rawKey = generatedLevelRoomDao
                .saveEntity(level.mapToEntity())

        return GeneratedLevelKey(rawKey)
    }

    fun loadLevel(levelKey: GeneratedLevelKey): GeneratedLevel {
        val entity = generatedLevelRoomDao
                .loadEntity(levelKey.rawKey)

        return requireNotNull(entity).mapToLevel()
    }

    fun loadFirstUnfinishedLevel() = generatedLevelRoomDao.loadFirstUnfinishedEntity()?.mapToLevel()

    fun loadAllFinishedLevel() = generatedLevelRoomDao.loadAllFinishedEntities().mapToLevel()

    fun countAllPlayedLevel() = generatedLevelRoomDao.countAllPlayedLevel()

    private fun GeneratedLevel.mapToEntity() = generatedLevelMapper.mapToEntity(this)

    private fun GeneratedLevelRoomEntity.mapToLevel() = generatedLevelMapper.mapToLevel(this)

    private fun List<GeneratedLevelRoomEntity>.mapToLevel() = map(generatedLevelMapper::mapToLevel)
}