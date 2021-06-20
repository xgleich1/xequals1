package com.dg.eqs.base.gamification

import com.dg.eqs.base.gamification.result.*
import com.dg.eqs.base.gamification.result.PlayerScoreResult.PlayerScoreMissing
import com.dg.eqs.base.gamification.result.PlayerScoreResult.PlayerScorePresent
import com.dg.eqs.base.gamification.result.ScoreBoardResult.ScoreBoardMissing
import com.dg.eqs.base.gamification.result.ScoreBoardResult.ScoreBoardPresent
import com.dg.eqs.base.gamification.result.SignInResult.SignInFinished
import com.dg.eqs.base.gamification.result.SignInResult.SignInNecessary
import com.dg.eqs.base.gamification.result.SignOutResult.SignOutFailed
import com.dg.eqs.base.gamification.result.SignOutResult.SignOutFinished
import com.dg.eqs.base.gamification.result.SubmitScoreResult.SubmitScoreFailed
import com.dg.eqs.base.gamification.result.SubmitScoreResult.SubmitScoreFinished
import com.dg.eqs.base.gamification.result.TopScoreResult.TopScoreMissing
import com.dg.eqs.base.gamification.result.TopScoreResult.TopScorePresent
import com.dg.eqs.base.tracking.Tracking


class GoogleTracking(private val tracking: Tracking) {
    fun trackSignInResult(result: SignInResult) = when(result) {
        is SignInFinished -> trackSignInFinished()
        is SignInNecessary -> trackSignInNecessary(result.exception)
    }

    fun trackSignOutResult(result: SignOutResult) = when(result) {
        is SignOutFinished -> trackSignOutFinished()
        is SignOutFailed -> trackSignOutFailed(result.exception)
    }

    fun trackPlayerScoreResult(result: PlayerScoreResult) = when(result) {
        is PlayerScorePresent -> trackPlayerScorePresent()
        is PlayerScoreMissing -> trackPlayerScoreMissing(result.exception)
    }

    fun trackTopScoreResult(result: TopScoreResult) = when(result) {
        is TopScorePresent -> trackTopScorePresent()
        is TopScoreMissing -> trackTopScoreMissing(result.exception)
    }

    fun trackScoreBoardResult(result: ScoreBoardResult) = when(result) {
        is ScoreBoardPresent -> trackScoreBoardPresent()
        is ScoreBoardMissing -> trackScoreBoardMissing(result.exception)
    }

    fun trackSubmitScoreResult(result: SubmitScoreResult) = when(result) {
        is SubmitScoreFinished -> trackSubmitScoreFinished()
        is SubmitScoreFailed -> trackSubmitScoreFailed(result.exception)
    }

    private fun trackSignInFinished() =
            tracking.trackEvent("sign_in_finished")

    private fun trackSignInNecessary(exception: Exception?) =
            tracking.trackEvent("sign_in_necessary_${exception?.toTrackingString()}")

    private fun trackSignOutFinished() =
            tracking.trackEvent("sign_out_finished")

    private fun trackSignOutFailed(exception: Exception?) =
            tracking.trackEvent("sign_out_failed_${exception?.toTrackingString()}")

    private fun trackPlayerScorePresent() =
            tracking.trackEvent("player_score_present")

    private fun trackPlayerScoreMissing(exception: Exception?) =
            tracking.trackEvent("player_score_missing_${exception?.toTrackingString()}")

    private fun trackTopScorePresent() =
            tracking.trackEvent("top_score_present")

    private fun trackTopScoreMissing(exception: Exception?) =
            tracking.trackEvent("top_score_missing_${exception?.toTrackingString()}")

    private fun trackScoreBoardPresent() =
            tracking.trackEvent("score_board_present")

    private fun trackScoreBoardMissing(exception: Exception?) =
            tracking.trackEvent("score_board_missing_${exception?.toTrackingString()}")

    private fun trackSubmitScoreFinished() =
            tracking.trackEvent("submit_score_finished")

    private fun trackSubmitScoreFailed(exception: Exception?) =
            tracking.trackEvent("submit_score_failed_${exception?.toTrackingString()}")

    private fun Exception?.toTrackingString() = this?.javaClass?.simpleName
}