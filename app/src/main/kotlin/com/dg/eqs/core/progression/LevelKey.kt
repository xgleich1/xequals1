package com.dg.eqs.core.progression

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class LevelKey : Parcelable {
    @Parcelize
    data class EventLevelKey(val rawKey: Long) : LevelKey()

    @Parcelize
    data class PresetLevelKey(val rawKey: Long) : LevelKey()

    @Parcelize
    data class GeneratedLevelKey(val rawKey: Long) : LevelKey()
}