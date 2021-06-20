package com.dg.eqs.core.progression.generatedlevel.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface GeneratedLevelRoomDao {
    @Insert(onConflict = REPLACE)
    fun saveEntity(entity: GeneratedLevelRoomEntity): Long

    @Query("SELECT * FROM $GENERATED_LEVEL_TABLE_NAME WHERE $GENERATED_LEVEL_KEY_COLUMN_NAME = :key")
    fun loadEntity(key: Long): GeneratedLevelRoomEntity?

    @Query("SELECT * FROM $GENERATED_LEVEL_TABLE_NAME WHERE $GENERATED_LEVEL_FINISHED_COLUMN_NAME = 0")
    fun loadFirstUnfinishedEntity(): GeneratedLevelRoomEntity?

    @Query("SELECT * FROM $GENERATED_LEVEL_TABLE_NAME WHERE $GENERATED_LEVEL_FINISHED_COLUMN_NAME = 1 ORDER BY $GENERATED_LEVEL_KEY_COLUMN_NAME DESC")
    fun loadAllFinishedEntities(): List<GeneratedLevelRoomEntity>

    @Query("SELECT COUNT(*) FROM $GENERATED_LEVEL_TABLE_NAME WHERE $GENERATED_LEVEL_GAME_STEPS_COLUMN_NAME != 0")
    fun countAllPlayedLevel(): Int

    @Query("DELETE FROM $GENERATED_LEVEL_TABLE_NAME")
    fun deleteAllEntities()
}