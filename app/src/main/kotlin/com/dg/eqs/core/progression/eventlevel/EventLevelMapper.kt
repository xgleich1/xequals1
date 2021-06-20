package com.dg.eqs.core.progression.eventlevel

import com.dg.eqs.core.mapping.OriginToStringMapper
import com.dg.eqs.core.mapping.StringToOriginMapper
import com.dg.eqs.core.progression.Level.EventLevel
import com.dg.eqs.core.progression.LevelKey.EventLevelKey
import com.dg.eqs.core.progression.eventlevel.roomdatabase.EventLevelRoomEntity


class EventLevelMapper(
        private val stringToOriginMapper: StringToOriginMapper,
        private val originToStringMapper: OriginToStringMapper) {

    fun mapToLevel(entity: EventLevelRoomEntity) = EventLevel(
            EventLevelKey(entity.key),
            stringToOriginMapper.mapToOrigin(entity.exercise),
            entity.finished,
            entity.gameSteps,
            entity.bestSteps)

    fun mapToEntity(level: EventLevel) = EventLevelRoomEntity(
            level.levelKey.rawKey,
            originToStringMapper.mapToString(level.exercise),
            level.finished,
            level.gameSteps,
            level.bestSteps)
}