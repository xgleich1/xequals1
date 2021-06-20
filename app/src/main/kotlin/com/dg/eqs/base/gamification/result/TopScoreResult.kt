package com.dg.eqs.base.gamification.result


sealed class TopScoreResult {
    data class TopScorePresent(val score: Int) : TopScoreResult()

    data class TopScoreMissing(val exception: Exception? = null) : TopScoreResult()
}