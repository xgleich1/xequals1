package com.dg.eqs.core.progression.generatedlevel.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = GENERATED_LEVEL_TABLE_NAME)
data class GeneratedLevelRoomEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = GENERATED_LEVEL_KEY_COLUMN_NAME)
        val key: Long,
        @ColumnInfo(name = GENERATED_LEVEL_EXERCISE_COLUMN_NAME)
        val exercise: String,
        @ColumnInfo(name = GENERATED_LEVEL_FINISHED_COLUMN_NAME)
        val finished: Boolean,
        @ColumnInfo(name = GENERATED_LEVEL_GAME_STEPS_COLUMN_NAME)
        val gameSteps: Int,
        @ColumnInfo(name = GENERATED_LEVEL_BEST_STEPS_COLUMN_NAME)
        val bestSteps: Int)

const val GENERATED_LEVEL_TABLE_NAME = "generated_level"
const val GENERATED_LEVEL_KEY_COLUMN_NAME = "generated_level_key"
const val GENERATED_LEVEL_EXERCISE_COLUMN_NAME = "generated_level_exercise"
const val GENERATED_LEVEL_FINISHED_COLUMN_NAME = "generated_level_finished"
const val GENERATED_LEVEL_GAME_STEPS_COLUMN_NAME = "generated_level_game_steps"
const val GENERATED_LEVEL_BEST_STEPS_COLUMN_NAME = "generated_level_best_steps"