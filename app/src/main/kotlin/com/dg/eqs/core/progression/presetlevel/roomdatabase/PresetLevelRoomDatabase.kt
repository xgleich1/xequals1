package com.dg.eqs.core.progression.presetlevel.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [PresetLevelRoomEntity::class], version = 2)
abstract class PresetLevelRoomDatabase : RoomDatabase() {
    abstract val dao: PresetLevelRoomDao
}