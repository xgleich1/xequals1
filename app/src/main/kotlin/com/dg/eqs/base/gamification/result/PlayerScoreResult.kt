package com.dg.eqs.base.gamification.result


sealed class PlayerScoreResult {
    data class PlayerScorePresent(val score: Int) : PlayerScoreResult()

    data class PlayerScoreMissing(val exception: Exception? = null) : PlayerScoreResult()
}