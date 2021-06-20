package com.dg.eqs.core.progression.eventlevel.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [EventLevelRoomEntity::class], version = 2)
abstract class EventLevelRoomDatabase : RoomDatabase() {
    abstract val dao: EventLevelRoomDao
}