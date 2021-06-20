package com.dg.eqs.base.injection.module

import android.app.Application
import com.dg.eqs.base.injection.scope.ApplicationScope
import com.dg.eqs.base.persistence.offline.OfflinePersistence
import com.dg.eqs.core.mapping.OriginToStringMapper
import com.dg.eqs.core.mapping.StringToOriginMapper
import com.dg.eqs.core.progression.eventlevel.EventLevelDatabase
import com.dg.eqs.core.progression.eventlevel.EventLevelMapper
import com.dg.eqs.core.progression.eventlevel.roomdatabase.EventLevelRoomDao
import com.dg.eqs.core.progression.eventlevel.roomdatabase.EventLevelRoomDatabase
import com.dg.eqs.core.progression.eventlevel.roomdatabase.EventLevelRoomDatabaseBuilder
import com.dg.eqs.core.progression.eventlevel.roomdatabase.EventLevelRoomDatabaseBuilderImpl
import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelMapper
import com.dg.eqs.core.progression.generatedlevel.roomdatabase.GeneratedLevelRoomDao
import com.dg.eqs.core.progression.generatedlevel.roomdatabase.GeneratedLevelRoomDatabase
import com.dg.eqs.core.progression.generatedlevel.roomdatabase.GeneratedLevelRoomDatabaseBuilder
import com.dg.eqs.core.progression.generatedlevel.roomdatabase.GeneratedLevelRoomDatabaseBuilderImpl
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelMapper
import com.dg.eqs.core.progression.presetlevel.PresetLevelPreset
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomDao
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomDatabase
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomDatabaseBuilder
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomDatabaseBuilderImpl
import dagger.Module
import dagger.Provides


@Module
open class ProgressionModule {
    @Provides
    @ApplicationScope
    open fun provideEventLevelDatabase(
            eventLevelRoomDao: EventLevelRoomDao,
            eventLevelMapper: EventLevelMapper): EventLevelDatabase {

        return EventLevelDatabase(
                eventLevelRoomDao,
                eventLevelMapper)
    }

    @Provides
    @ApplicationScope
    open fun provideEventLevelRoomDao(
            eventLevelRoomDatabase: EventLevelRoomDatabase): EventLevelRoomDao {

        return eventLevelRoomDatabase.dao
    }

    @Provides
    @ApplicationScope
    open fun provideEventLevelRoomDatabase(
            eventLevelRoomDatabaseBuilder: EventLevelRoomDatabaseBuilder): EventLevelRoomDatabase {

        return eventLevelRoomDatabaseBuilder.build()
    }

    @Provides
    @ApplicationScope
    open fun provideEventLevelRoomDatabaseBuilder(
            application: Application): EventLevelRoomDatabaseBuilder {

        return EventLevelRoomDatabaseBuilderImpl(application)
    }

    @Provides
    @ApplicationScope
    open fun provideEventLevelMapper(
            stringToOriginMapper: StringToOriginMapper,
            originToStringMapper: OriginToStringMapper): EventLevelMapper {

        return EventLevelMapper(
                stringToOriginMapper,
                originToStringMapper)
    }

    @Provides
    @ApplicationScope
    open fun providePresetLevelDatabase(
            offlinePersistence: OfflinePersistence,
            presetLevelPreset: PresetLevelPreset,
            presetLevelRoomDao: PresetLevelRoomDao,
            presetLevelMapper: PresetLevelMapper): PresetLevelDatabase {

        return PresetLevelDatabase(
                offlinePersistence,
                presetLevelPreset,
                presetLevelRoomDao,
                presetLevelMapper)
    }

    @Provides
    @ApplicationScope
    open fun providePresetLevelPreset(): PresetLevelPreset {
        return PresetLevelPreset()
    }

    @Provides
    @ApplicationScope
    open fun providePresetLevelRoomDao(
            presetLevelRoomDatabase: PresetLevelRoomDatabase): PresetLevelRoomDao {

        return presetLevelRoomDatabase.dao
    }

    @Provides
    @ApplicationScope
    open fun providePresetLevelRoomDatabase(
            presetLevelRoomDatabaseBuilder: PresetLevelRoomDatabaseBuilder): PresetLevelRoomDatabase {

        return presetLevelRoomDatabaseBuilder.build()
    }

    @Provides
    @ApplicationScope
    open fun providePresetLevelRoomDatabaseBuilder(
            application: Application): PresetLevelRoomDatabaseBuilder {

        return PresetLevelRoomDatabaseBuilderImpl(application)
    }

    @Provides
    @ApplicationScope
    open fun providePresetLevelMapper(
            stringToOriginMapper: StringToOriginMapper,
            originToStringMapper: OriginToStringMapper): PresetLevelMapper {

        return PresetLevelMapper(
                stringToOriginMapper,
                originToStringMapper)
    }

    @Provides
    @ApplicationScope
    open fun provideGeneratedLevelDatabase(
            generatedLevelRoomDao: GeneratedLevelRoomDao,
            generatedLevelMapper: GeneratedLevelMapper): GeneratedLevelDatabase {

        return GeneratedLevelDatabase(
                generatedLevelRoomDao,
                generatedLevelMapper)
    }

    @Provides
    @ApplicationScope
    open fun provideGeneratedLevelRoomDao(
            generatedLevelRoomDatabase: GeneratedLevelRoomDatabase): GeneratedLevelRoomDao {

        return generatedLevelRoomDatabase.dao
    }

    @Provides
    @ApplicationScope
    open fun provideGeneratedLevelRoomDatabase(
            generatedLevelRoomDatabaseBuilder: GeneratedLevelRoomDatabaseBuilder): GeneratedLevelRoomDatabase {

        return generatedLevelRoomDatabaseBuilder.build()
    }

    @Provides
    @ApplicationScope
    open fun provideGeneratedLevelRoomDatabaseBuilder(
            application: Application): GeneratedLevelRoomDatabaseBuilder {

        return GeneratedLevelRoomDatabaseBuilderImpl(application)
    }

    @Provides
    @ApplicationScope
    open fun provideGeneratedLevelMapper(
            stringToOriginMapper: StringToOriginMapper,
            originToStringMapper: OriginToStringMapper): GeneratedLevelMapper {

        return GeneratedLevelMapper(
                stringToOriginMapper,
                originToStringMapper)
    }
}