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
import com.google.android.gms.games.leaderboard.LeaderboardVariant.COLLECTION_PUBLIC
import com.google.android.gms.games.leaderboard.LeaderboardVariant.TIME_SPAN_ALL_TIME
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class GoogleGamesService(
        private val googleGames: GoogleGames,
        private val googleSignIn: GoogleSignIn,
        private val googleTracking: GoogleTracking) {

    private val gamesSignInClient =
            googleSignIn.getGamesSignInClient()


    fun loadSignInIntent() =
            gamesSignInClient.signInIntent

    suspend fun signIn() = executeSignIn()
            .also(googleTracking::trackSignInResult)

    suspend fun signOut() = executeSignOut()
            .also(googleTracking::trackSignOutResult)

    suspend fun loadPlayerScore(key: GoogleScoreBoardKey): Int {
        val playerScoreResult = executeLoadPlayerScore(key)
                .also(googleTracking::trackPlayerScoreResult)

        return when(playerScoreResult) {
            is PlayerScorePresent -> playerScoreResult.score
            is PlayerScoreMissing -> 0
        }
    }

    suspend fun loadTopScore(key: GoogleScoreBoardKey): Int {
        val topScoreResult = executeLoadTopScore(key)
                .also(googleTracking::trackTopScoreResult)

        return when(topScoreResult) {
            is TopScorePresent -> topScoreResult.score
            is TopScoreMissing -> 0
        }
    }

    suspend fun loadScoreBoard(key: GoogleScoreBoardKey) =
            executeLoadScoreBoard(key)
                    .also(googleTracking::trackScoreBoardResult)

    suspend fun submitScore(key: GoogleScoreBoardKey, score: Int) =
            executeSubmitScore(key, score)
                    .also(googleTracking::trackSubmitScoreResult)

    private suspend fun executeSignIn() =
            suspendCoroutine<SignInResult> { continuation ->
                gamesSignInClient
                        .silentSignIn()
                        .addOnSuccessListener { account ->
                            googleGames.getPlayersClient(account)
                                    .getCurrentPlayer(true)
                                    .addOnSuccessListener {
                                        try {
                                            val player = requireNotNull(it.get())

                                            continuation.resume(SignInFinished(player))
                                        } catch(e: Exception) {
                                            continuation.resume(SignInNecessary(e))
                                        }
                                    }
                                    .addOnFailureListener {
                                        continuation.resume(SignInNecessary(it))
                                    }
                        }
                        .addOnFailureListener {
                            continuation.resume(SignInNecessary(it))
                        }
            }

    private suspend fun executeSignOut() =
            suspendCoroutine<SignOutResult> { continuation ->
                gamesSignInClient
                        .signOut()
                        .addOnSuccessListener {
                            continuation.resume(SignOutFinished)
                        }
                        .addOnFailureListener {
                            continuation.resume(SignOutFailed(it))
                        }
            }

    private suspend fun executeLoadPlayerScore(key: GoogleScoreBoardKey): PlayerScoreResult {
        val signedInAccount = googleSignIn
                .getLastSignedInAccount() ?: return PlayerScoreMissing()

        return suspendCoroutine { continuation ->
            googleGames
                    .getLeaderboardsClient(signedInAccount)
                    .loadCurrentPlayerLeaderboardScore(key.rawKey, TIME_SPAN_ALL_TIME, COLLECTION_PUBLIC)
                    .addOnSuccessListener {
                        try {
                            val score = requireNotNull(it.get()).rawScore.toInt()

                            continuation.resume(PlayerScorePresent(score))
                        } catch(e: Exception) {
                            continuation.resume(PlayerScoreMissing(e))
                        }
                    }
                    .addOnFailureListener {
                        continuation.resume(PlayerScoreMissing(it))
                    }
        }
    }

    private suspend fun executeLoadTopScore(key: GoogleScoreBoardKey): TopScoreResult {
        val signedInAccount = googleSignIn
                .getLastSignedInAccount() ?: return TopScoreMissing()

        return suspendCoroutine { continuation ->
            googleGames
                    .getLeaderboardsClient(signedInAccount)
                    .loadTopScores(key.rawKey, TIME_SPAN_ALL_TIME, COLLECTION_PUBLIC, 1, true)
                    .addOnSuccessListener {
                        try {
                            val score = requireNotNull(it.get()).scores[0].rawScore.toInt()

                            continuation.resume(TopScorePresent(score))
                        } catch(e: Exception) {
                            continuation.resume(TopScoreMissing(e))
                        }
                    }
                    .addOnFailureListener {
                        continuation.resume(TopScoreMissing(it))
                    }
        }
    }

    private suspend fun executeLoadScoreBoard(key: GoogleScoreBoardKey): ScoreBoardResult {
        val signedInAccount = googleSignIn
                .getLastSignedInAccount() ?: return ScoreBoardMissing()

        return suspendCoroutine { continuation ->
            googleGames
                    .getLeaderboardsClient(signedInAccount)
                    .getLeaderboardIntent(key.rawKey)
                    .addOnSuccessListener {
                        continuation.resume(ScoreBoardPresent(it))
                    }
                    .addOnFailureListener {
                        continuation.resume(ScoreBoardMissing(it))
                    }
        }
    }

    private suspend fun executeSubmitScore(key: GoogleScoreBoardKey, score: Int): SubmitScoreResult {
        val signedInAccount = googleSignIn
                .getLastSignedInAccount() ?: return SubmitScoreFailed()

        return suspendCoroutine { continuation ->
            googleGames
                    .getLeaderboardsClient(signedInAccount)
                    .submitScoreImmediate(key.rawKey, score.toLong())
                    .addOnSuccessListener {
                        continuation.resume(SubmitScoreFinished)
                    }
                    .addOnFailureListener {
                        continuation.resume(SubmitScoreFailed(it))
                    }
        }
    }
}