package com.dg.eqs.core.progression.eventlevel.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = EVENT_LEVEL_TABLE_NAME)
data class EventLevelRoomEntity(
        @PrimaryKey
        @ColumnInfo(name = EVENT_LEVEL_KEY_COLUMN_NAME)
        val key: Long,
        @ColumnInfo(name = EVENT_LEVEL_EXERCISE_COLUMN_NAME)
        val exercise: String,
        @ColumnInfo(name = EVENT_LEVEL_FINISHED_COLUMN_NAME)
        val finished: Boolean,
        @ColumnInfo(name = EVENT_LEVEL_GAME_STEPS_COLUMN_NAME)
        val gameSteps: Int,
        @ColumnInfo(name = EVENT_LEVEL_BEST_STEPS_COLUMN_NAME)
        val bestSteps: Int)

const val EVENT_LEVEL_TABLE_NAME = "event_level"
const val EVENT_LEVEL_KEY_COLUMN_NAME = "event_level_key"
const val EVENT_LEVEL_EXERCISE_COLUMN_NAME = "event_level_exercise"
const val EVENT_LEVEL_FINISHED_COLUMN_NAME = "event_level_finished"
const val EVENT_LEVEL_GAME_STEPS_COLUMN_NAME = "event_level_game_steps"
const val EVENT_LEVEL_BEST_STEPS_COLUMN_NAME = "event_level_best_steps"