package com.dg.eqs.util.dagger.setup

import com.dg.eqs.base.injection.component.ApplicationComponent
import com.dg.eqs.base.persistence.offline.OfflinePersistence
import com.dg.eqs.util.classes.TestSharedPreferencesBuilder
import com.dg.eqs.util.dagger.DaggerMockSetup
import com.dg.eqs.util.dagger.EqsDaggerMockRule


class TestOfflinePersistenceSetup : DaggerMockSetup {
    lateinit var testOfflinePersistence: OfflinePersistence private set


    override fun setupDependencies(rule: EqsDaggerMockRule, component: ApplicationComponent) {
        testOfflinePersistence = OfflinePersistence(TestSharedPreferencesBuilder())

        rule.provides(OfflinePersistence::class.java, testOfflinePersistence)
    }
}