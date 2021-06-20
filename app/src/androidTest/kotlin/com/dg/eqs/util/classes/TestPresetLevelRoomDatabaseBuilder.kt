package com.dg.eqs.util.classes

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomDatabase
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomDatabaseBuilder


class TestPresetLevelRoomDatabaseBuilder : PresetLevelRoomDatabaseBuilder {
    private val context get() = getInstrumentation().targetContext


    override fun build() = Room
            .inMemoryDatabaseBuilder(context, PresetLevelRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}