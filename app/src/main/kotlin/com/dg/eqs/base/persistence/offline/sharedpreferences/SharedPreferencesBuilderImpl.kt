package com.dg.eqs.base.persistence.offline.sharedpreferences

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class SharedPreferencesBuilderImpl(
        private val application: Application) : SharedPreferencesBuilder {

    override fun build(): SharedPreferences = application
            .getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)
}

private const val SHARED_PREFERENCES_NAME = "eqs_v2_shared_preferences"