package com.dg.eqs.base.gamification


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
import com.google.android.gms.common.api.ApiException
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GoogleTrackingTest {
    @Mock
    private lateinit var tracking: Tracking
    @InjectMocks
    private lateinit var googleTracking: GoogleTracking


    @Test
    fun `Should track an event when a sign in finished`() {
        // WHEN
        googleTracking.trackSignInResult(
                SignInFinished(mock()))

        // THEN
        verify(tracking).trackEvent("sign_in_finished")
    }

    @Test
    fun `Should track an event when a sign in is necessary`() {
        // WHEN
        googleTracking.trackSignInResult(
                SignInNecessary())

        // THEN
        verify(tracking).trackEvent("sign_in_necessary_null")
    }

    @Test
    fun `Should track an event when a sign in is necessary due to an exception`() {
        // WHEN
        googleTracking.trackSignInResult(
                SignInNecessary(ApiException(mock())))

        // THEN
        verify(tracking).trackEvent("sign_in_necessary_ApiException")
    }

    @Test
    fun `Should track an event when a sign out finished`() {
        // WHEN
        googleTracking.trackSignOutResult(
                SignOutFinished)

        // THEN
        verify(tracking).trackEvent("sign_out_finished")
    }

    @Test
    fun `Should track an event when a sign out failed`() {
        // WHEN
        googleTracking.trackSignOutResult(
                SignOutFailed())

        // THEN
        verify(tracking).trackEvent("sign_out_failed_null")
    }

    @Test
    fun `Should track an event when a sign out failed due to an exception`() {
        // WHEN
        googleTracking.trackSignOutResult(
                SignOutFailed(ApiException(mock())))

        // THEN
        verify(tracking).trackEvent("sign_out_failed_ApiException")
    }

    @Test
    fun `Should track an event when a player score is present`() {
        // WHEN
        googleTracking.trackPlayerScoreResult(
                PlayerScorePresent(1))

        // THEN
        verify(tracking).trackEvent("player_score_present")
    }

    @Test
    fun `Should track an event when a player score is missing`() {
        // WHEN
        googleTracking.trackPlayerScoreResult(
                PlayerScoreMissing())

        // THEN
        verify(tracking).trackEvent("player_score_missing_null")
    }

    @Test
    fun `Should track an event when a player score is missing due to an exception`() {
        // WHEN
        googleTracking.trackPlayerScoreResult(
                PlayerScoreMissing(ApiException(mock())))

        // THEN
        verify(tracking).trackEvent("player_score_missing_ApiException")
    }

    @Test
    fun `Should track an event when a top score is present`() {
        // WHEN
        googleTracking.trackTopScoreResult(
                TopScorePresent(1))

        // THEN
        verify(tracking).trackEvent("top_score_present")
    }

    @Test
    fun `Should track an event when a top score is missing`() {
        // WHEN
        googleTracking.trackTopScoreResult(
                TopScoreMissing())

        // THEN
        verify(tracking).trackEvent("top_score_missing_null")
    }

    @Test
    fun `Should track an event when a top score is missing due to an exception`() {
        // WHEN
        googleTracking.trackTopScoreResult(
                TopScoreMissing(ApiException(mock())))

        // THEN
        verify(tracking).trackEvent("top_score_missing_ApiException")
    }

    @Test
    fun `Should track an event when a score board is present`() {
        // WHEN
        googleTracking.trackScoreBoardResult(
                ScoreBoardPresent(mock()))

        // THEN
        verify(tracking).trackEvent("score_board_present")
    }

    @Test
    fun `Should track an event when a score board is missing`() {
        // WHEN
        googleTracking.trackScoreBoardResult(
                ScoreBoardMissing())

        // THEN
        verify(tracking).trackEvent("score_board_missing_null")
    }

    @Test
    fun `Should track an event when a score board is missing due to an exception`() {
        // WHEN
        googleTracking.trackScoreBoardResult(
                ScoreBoardMissing(ApiException(mock())))

        // THEN
        verify(tracking).trackEvent("score_board_missing_ApiException")
    }

    @Test
    fun `Should track an event when a submit score finished`() {
        // WHEN
        googleTracking.trackSubmitScoreResult(
                SubmitScoreFinished)

        // THEN
        verify(tracking).trackEvent("submit_score_finished")
    }

    @Test
    fun `Should track an event when a submit score failed`() {
        // WHEN
        googleTracking.trackSubmitScoreResult(
                SubmitScoreFailed())

        // THEN
        verify(tracking).trackEvent("submit_score_failed_null")
    }

    @Test
    fun `Should track an event when a score submit score due to an exception`() {
        // WHEN
        googleTracking.trackSubmitScoreResult(
                SubmitScoreFailed(ApiException(mock())))

        // THEN
        verify(tracking).trackEvent("submit_score_failed_ApiException")
    }
}