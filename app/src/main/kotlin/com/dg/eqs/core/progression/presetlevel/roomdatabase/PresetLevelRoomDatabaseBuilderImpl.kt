package com.dg.eqs.core.progression.presetlevel.roomdatabase

import android.app.Application
import androidx.room.Room


class PresetLevelRoomDatabaseBuilderImpl(
        private val application: Application) : PresetLevelRoomDatabaseBuilder {

    override fun build() = Room
            .databaseBuilder(application, PresetLevelRoomDatabase::class.java, PRESET_LEVEL_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
}

private const val PRESET_LEVEL_DATABASE_NAME = "eqs_v2_preset_level_database"