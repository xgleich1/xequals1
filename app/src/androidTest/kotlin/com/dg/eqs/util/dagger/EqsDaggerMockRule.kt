package com.dg.eqs.util.dagger

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.dg.eqs.Eqs
import com.dg.eqs.base.injection.component.ApplicationComponent
import com.dg.eqs.base.injection.module.ApplicationModule
import com.dg.eqs.base.injection.module.CompetitionModule
import com.dg.eqs.base.injection.module.MappingModule
import com.dg.eqs.base.injection.module.ProgressionModule
import it.cosenonjaviste.daggermock.DaggerMockRule


open class EqsDaggerMockRule(private vararg val daggerMockSetups: DaggerMockSetup)
    : DaggerMockRule<ApplicationComponent>(ApplicationComponent::class.java,
        ApplicationModule(application),
        MappingModule(),
        ProgressionModule(),
        CompetitionModule()) {

    init {
        set { applicationComponent ->
            setupDependencies(applicationComponent)

            application.applicationComponent = applicationComponent
        }
    }

    private fun setupDependencies(component: ApplicationComponent) =
            daggerMockSetups.forEach {
                it.setupDependencies(this, component)
            }

    private companion object {
        private val application get() = getApplicationContext<Eqs>()
    }
}