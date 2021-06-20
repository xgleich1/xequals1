package com.dg.eqs.core.progression.generatedlevel

import com.dg.eqs.core.mapping.OriginToStringMapper
import com.dg.eqs.core.mapping.StringToOriginMapper
import com.dg.eqs.core.progression.Level.GeneratedLevel
import com.dg.eqs.core.progression.LevelKey.GeneratedLevelKey
import com.dg.eqs.core.progression.generatedlevel.roomdatabase.GeneratedLevelRoomEntity


class GeneratedLevelMapper(
        private val stringToOriginMapper: StringToOriginMapper,
        private val originToStringMapper: OriginToStringMapper) {

    fun mapToLevel(entity: GeneratedLevelRoomEntity) = GeneratedLevel(
            GeneratedLevelKey(entity.key),
            stringToOriginMapper.mapToOrigin(entity.exercise),
            entity.finished,
            entity.gameSteps,
            entity.bestSteps)

    fun mapToEntity(level: GeneratedLevel) = GeneratedLevelRoomEntity(
            level.levelKey.rawKey,
            originToStringMapper.mapToString(level.exercise),
            level.finished,
            level.gameSteps,
            level.bestSteps)
}