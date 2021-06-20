package com.dg.eqs.core.progression.eventlevel.roomdatabase

import android.app.Application
import androidx.room.Room


class EventLevelRoomDatabaseBuilderImpl(
        private val application: Application) : EventLevelRoomDatabaseBuilder {

    override fun build() = Room
            .databaseBuilder(application, EventLevelRoomDatabase::class.java, EVENT_LEVEL_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
}

private const val EVENT_LEVEL_DATABASE_NAME = "eqs_v2_event_level_database"