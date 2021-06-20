package com.dg.eqs.base.persistence.offline

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.core.content.edit
import com.dg.eqs.base.persistence.offline.sharedpreferences.SharedPreferencesBuilder


class OfflinePersistence(
        sharedPreferencesBuilder: SharedPreferencesBuilder) {

    private val sharedPreferences = sharedPreferencesBuilder.build()


    fun saveBoolean(key: OfflinePersistenceKey, value: Boolean) = sharedPreferences
            .commit { putBoolean(key.rawKey, value) }

    fun loadBoolean(key: OfflinePersistenceKey, default: Boolean) = sharedPreferences
            .getBoolean(key.rawKey, default)

    fun saveInteger(key: OfflinePersistenceKey, value: Int) = sharedPreferences
            .commit { putInt(key.rawKey, value) }

    fun loadInteger(key: OfflinePersistenceKey, default: Int) = sharedPreferences
            .getInt(key.rawKey, default)

    fun deleteAllValues() = sharedPreferences.commit { clear() }

    private fun SharedPreferences.commit(action: Editor.() -> Unit) =
            edit(commit = true, action = action)
}