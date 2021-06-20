package com.dg.eqs.core.progression.eventlevel.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface EventLevelRoomDao {
    @Insert(onConflict = REPLACE)
    fun saveEntity(entity: EventLevelRoomEntity)

    @Query("SELECT * FROM $EVENT_LEVEL_TABLE_NAME WHERE $EVENT_LEVEL_KEY_COLUMN_NAME = :key")
    fun loadEntity(key: Long): EventLevelRoomEntity?

    @Query("DELETE FROM $EVENT_LEVEL_TABLE_NAME")
    fun deleteAllEntities()
}