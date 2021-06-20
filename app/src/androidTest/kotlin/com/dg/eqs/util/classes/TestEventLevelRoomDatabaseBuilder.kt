package com.dg.eqs.util.classes

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.dg.eqs.core.progression.eventlevel.roomdatabase.EventLevelRoomDatabase
import com.dg.eqs.core.progression.eventlevel.roomdatabase.EventLevelRoomDatabaseBuilder


class TestEventLevelRoomDatabaseBuilder : EventLevelRoomDatabaseBuilder {
    private val context get() = getInstrumentation().targetContext


    override fun build() = Room
            .inMemoryDatabaseBuilder(context, EventLevelRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}