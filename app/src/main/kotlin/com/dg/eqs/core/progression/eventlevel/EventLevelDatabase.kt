package com.dg.eqs.core.progression.eventlevel

import com.dg.eqs.core.progression.Level.EventLevel
import com.dg.eqs.core.progression.LevelKey.EventLevelKey
import com.dg.eqs.core.progression.eventlevel.roomdatabase.EventLevelRoomDao
import com.dg.eqs.core.progression.eventlevel.roomdatabase.EventLevelRoomEntity


class EventLevelDatabase(
        private val eventLevelRoomDao: EventLevelRoomDao,
        private val eventLevelMapper: EventLevelMapper) {

    fun saveLevel(level: EventLevel) = eventLevelRoomDao
            .saveEntity(level.mapToEntity())

    fun loadLevel(levelKey: EventLevelKey): EventLevel {
        val entity = eventLevelRoomDao
                .loadEntity(levelKey.rawKey)

        return requireNotNull(entity).mapToLevel()
    }

    private fun EventLevel.mapToEntity() = eventLevelMapper.mapToEntity(this)

    private fun EventLevelRoomEntity.mapToLevel() = eventLevelMapper.mapToLevel(this)
}