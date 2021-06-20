package com.dg.eqs.util.classes

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.dg.eqs.core.progression.generatedlevel.roomdatabase.GeneratedLevelRoomDatabase
import com.dg.eqs.core.progression.generatedlevel.roomdatabase.GeneratedLevelRoomDatabaseBuilder


class TestGeneratedLevelRoomDatabaseBuilder : GeneratedLevelRoomDatabaseBuilder {
    private val context get() = getInstrumentation().targetContext


    override fun build() = Room
            .inMemoryDatabaseBuilder(context, GeneratedLevelRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}