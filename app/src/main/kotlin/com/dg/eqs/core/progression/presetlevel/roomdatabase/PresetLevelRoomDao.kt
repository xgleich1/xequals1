package com.dg.eqs.core.progression.presetlevel.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface PresetLevelRoomDao {
    @Insert(onConflict = REPLACE)
    fun saveEntity(entity: PresetLevelRoomEntity)

    @Insert(onConflict = REPLACE)
    fun saveAllEntities(entities: List<PresetLevelRoomEntity>)

    @Query("SELECT * FROM $PRESET_LEVEL_TABLE_NAME WHERE $PRESET_LEVEL_KEY_COLUMN_NAME = :key")
    fun loadEntity(key: Long): PresetLevelRoomEntity?

    @Query("SELECT * FROM $PRESET_LEVEL_TABLE_NAME WHERE $PRESET_LEVEL_FINISHED_COLUMN_NAME = 0")
    fun loadFirstUnfinishedEntity(): PresetLevelRoomEntity?

    @Query("SELECT * FROM $PRESET_LEVEL_TABLE_NAME WHERE $PRESET_LEVEL_FINISHED_COLUMN_NAME = 1 ORDER BY $PRESET_LEVEL_KEY_COLUMN_NAME DESC")
    fun loadAllFinishedEntities(): List<PresetLevelRoomEntity>

    @Query("SELECT COUNT(*) FROM $PRESET_LEVEL_TABLE_NAME WHERE $PRESET_LEVEL_GAME_STEPS_COLUMN_NAME != 0")
    fun countAllPlayedEntities(): Int

    @Query("DELETE FROM $PRESET_LEVEL_TABLE_NAME")
    fun deleteAllEntities()
}