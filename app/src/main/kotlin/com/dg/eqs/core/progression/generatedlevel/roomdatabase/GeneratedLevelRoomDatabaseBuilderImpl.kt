package com.dg.eqs.core.progression.generatedlevel.roomdatabase

import android.app.Application
import androidx.room.Room


class GeneratedLevelRoomDatabaseBuilderImpl(
        private val application: Application) : GeneratedLevelRoomDatabaseBuilder {

    override fun build() = Room
            .databaseBuilder(application, GeneratedLevelRoomDatabase::class.java, GENERATED_LEVEL_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
}

private const val GENERATED_LEVEL_DATABASE_NAME = "eqs_v2_generated_level_database"