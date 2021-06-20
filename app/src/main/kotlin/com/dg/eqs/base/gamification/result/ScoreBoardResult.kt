package com.dg.eqs.base.gamification.result

import android.content.Intent


sealed class ScoreBoardResult {
    data class ScoreBoardPresent(val intent: Intent) : ScoreBoardResult()

    data class ScoreBoardMissing(val exception: Exception? = null) : ScoreBoardResult()
}