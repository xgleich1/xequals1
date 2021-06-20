package com.dg.eqs.core.progression.presetlevel

import com.dg.eqs.core.mapping.OriginToStringMapper
import com.dg.eqs.core.mapping.StringToOriginMapper
import com.dg.eqs.core.progression.Level.PresetLevel
import com.dg.eqs.core.progression.LevelKey.PresetLevelKey
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomEntity


class PresetLevelMapper(
        private val stringToOriginMapper: StringToOriginMapper,
        private val originToStringMapper: OriginToStringMapper) {

    fun mapToLevel(entity: PresetLevelRoomEntity) = PresetLevel(
            PresetLevelKey(entity.key),
            stringToOriginMapper.mapToOrigin(entity.exercise),
            entity.finished,
            entity.gameSteps,
            entity.bestSteps)

    fun mapToEntity(level: PresetLevel) = PresetLevelRoomEntity(
            level.levelKey.rawKey,
            originToStringMapper.mapToString(level.exercise),
            level.finished,
            level.gameSteps,
            level.bestSteps)
}