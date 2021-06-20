package com.dg.eqs

import android.app.Application
import com.dg.eqs.base.injection.component.ApplicationComponent
import com.dg.eqs.base.injection.component.DaggerApplicationComponent
import com.dg.eqs.base.injection.module.ApplicationModule
import com.dg.eqs.base.lifecycle.ApplicationStartHook
import javax.inject.Inject


class Eqs : Application() {
    lateinit var applicationComponent: ApplicationComponent
    @Inject
    lateinit var applicationStartHook: ApplicationStartHook


    override fun onCreate() {
        super.onCreate()

        injectDependencies()

        applicationStartHook()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()

        applicationComponent.inject(this)
    }
}