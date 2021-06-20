package com.dg.eqs.core.progression.generatedlevel.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [GeneratedLevelRoomEntity::class], version = 2)
abstract class GeneratedLevelRoomDatabase : RoomDatabase() {
    abstract val dao: GeneratedLevelRoomDao
}