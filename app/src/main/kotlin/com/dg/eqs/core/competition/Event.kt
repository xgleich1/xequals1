package com.dg.eqs.core.competition

import android.os.Parcelable
import com.dg.eqs.base.gamification.GoogleScoreBoardKey
import com.dg.eqs.core.progression.LevelKey.EventLevelKey
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Event(
        val number: Int,
        val levelKey: EventLevelKey,
        val scoreBoardKey: GoogleScoreBoardKey,
) : Parcelable