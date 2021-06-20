package com.dg.eqs.util.dagger

import com.dg.eqs.base.injection.component.ApplicationComponent


interface DaggerMockSetup {
    fun setupDependencies(rule: EqsDaggerMockRule, component: ApplicationComponent)
}