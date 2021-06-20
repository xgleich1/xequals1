package com.dg.eqs.base.gamification.result


sealed class SubmitScoreResult {
    object SubmitScoreFinished : SubmitScoreResult()

    data class SubmitScoreFailed(val exception: Exception? = null) : SubmitScoreResult()
}