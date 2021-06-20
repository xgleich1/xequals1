package com.dg.eqs.core.progression.presetlevel.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = PRESET_LEVEL_TABLE_NAME)
data class PresetLevelRoomEntity(
        @PrimaryKey
        @ColumnInfo(name = PRESET_LEVEL_KEY_COLUMN_NAME)
        val key: Long,
        @ColumnInfo(name = PRESET_LEVEL_EXERCISE_COLUMN_NAME)
        val exercise: String,
        @ColumnInfo(name = PRESET_LEVEL_FINISHED_COLUMN_NAME)
        val finished: Boolean,
        @ColumnInfo(name = PRESET_LEVEL_GAME_STEPS_COLUMN_NAME)
        val gameSteps: Int,
        @ColumnInfo(name = PRESET_LEVEL_BEST_STEPS_COLUMN_NAME)
        val bestSteps: Int)

const val PRESET_LEVEL_TABLE_NAME = "preset_level"
const val PRESET_LEVEL_KEY_COLUMN_NAME = "preset_level_key"
const val PRESET_LEVEL_EXERCISE_COLUMN_NAME = "preset_level_exercise"
const val PRESET_LEVEL_FINISHED_COLUMN_NAME = "preset_level_finished"
const val PRESET_LEVEL_GAME_STEPS_COLUMN_NAME = "preset_level_game_steps"
const val PRESET_LEVEL_BEST_STEPS_COLUMN_NAME = "preset_level_best_steps"