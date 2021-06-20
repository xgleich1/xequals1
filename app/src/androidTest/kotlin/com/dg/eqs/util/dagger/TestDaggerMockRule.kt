package com.dg.eqs.util.dagger

import com.dg.eqs.util.dagger.setup.TestLevelRoomDatabaseSetup
import com.dg.eqs.util.dagger.setup.TestOfflinePersistenceSetup


class TestDaggerMockRule(
        private val testLevelRoomDatabaseSetup: TestLevelRoomDatabaseSetup = TestLevelRoomDatabaseSetup(),
        private val testOfflinePersistenceSetup: TestOfflinePersistenceSetup = TestOfflinePersistenceSetup())
    : EqsDaggerMockRule(testLevelRoomDatabaseSetup, testOfflinePersistenceSetup) {

    val testEventLevelRoomDao get() = testLevelRoomDatabaseSetup.testEventLevelRoomDatabase.dao
    val testPresetLevelRoomDao get() = testLevelRoomDatabaseSetup.testPresetLevelRoomDatabase.dao
    val testGeneratedLevelRoomDao get() = testLevelRoomDatabaseSetup.testGeneratedLevelRoomDatabase.dao
    val testOfflinePersistence get() = testOfflinePersistenceSetup.testOfflinePersistence
}