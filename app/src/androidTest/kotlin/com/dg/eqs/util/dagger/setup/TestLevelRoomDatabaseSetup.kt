package com.dg.eqs.util.dagger.setup

import com.dg.eqs.base.injection.component.ApplicationComponent
import com.dg.eqs.core.progression.generatedlevel.roomdatabase.GeneratedLevelRoomDatabase
import com.dg.eqs.core.progression.eventlevel.roomdatabase.EventLevelRoomDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelPreset
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomDatabase
import com.dg.eqs.util.classes.TestGeneratedLevelRoomDatabaseBuilder
import com.dg.eqs.util.classes.TestEventLevelRoomDatabaseBuilder
import com.dg.eqs.util.classes.TestPresetLevelRoomDatabaseBuilder
import com.dg.eqs.util.dagger.DaggerMockSetup
import com.dg.eqs.util.dagger.EqsDaggerMockRule
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


class TestLevelRoomDatabaseSetup : DaggerMockSetup {
    lateinit var testEventLevelRoomDatabase: EventLevelRoomDatabase private set
    lateinit var testPresetLevelRoomDatabase: PresetLevelRoomDatabase private set
    lateinit var testGeneratedLevelRoomDatabase: GeneratedLevelRoomDatabase private set


    override fun setupDependencies(rule: EqsDaggerMockRule, component: ApplicationComponent) {
        val testPresetLevelPreset: PresetLevelPreset = mock {
            on { presetEntities } doReturn emptyList()
        }

        testEventLevelRoomDatabase = TestEventLevelRoomDatabaseBuilder().build()
        testPresetLevelRoomDatabase = TestPresetLevelRoomDatabaseBuilder().build()
        testGeneratedLevelRoomDatabase = TestGeneratedLevelRoomDatabaseBuilder().build()

        rule.provides(PresetLevelPreset::class.java, testPresetLevelPreset)
        rule.provides(EventLevelRoomDatabase::class.java, testEventLevelRoomDatabase)
        rule.provides(PresetLevelRoomDatabase::class.java, testPresetLevelRoomDatabase)
        rule.provides(GeneratedLevelRoomDatabase::class.java, testGeneratedLevelRoomDatabase)
    }
}