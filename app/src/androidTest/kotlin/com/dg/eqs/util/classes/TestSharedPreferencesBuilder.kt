package com.dg.eqs.util.classes

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.dg.eqs.base.persistence.offline.sharedpreferences.SharedPreferencesBuilder


class TestSharedPreferencesBuilder : SharedPreferencesBuilder {
    private val context get() = getInstrumentation().targetContext


    override fun build(): SharedPreferences = context
            .getSharedPreferences(TEST_SHARED_PREFERENCES_NAME, MODE_PRIVATE)
}

private const val TEST_SHARED_PREFERENCES_NAME = "eqs_v2_test_shared_preferences"