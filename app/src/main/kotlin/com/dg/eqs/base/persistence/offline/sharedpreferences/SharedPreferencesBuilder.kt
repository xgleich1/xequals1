package com.dg.eqs.base.persistence.offline.sharedpreferences

import android.content.SharedPreferences


interface SharedPreferencesBuilder {
    fun build(): SharedPreferences
}