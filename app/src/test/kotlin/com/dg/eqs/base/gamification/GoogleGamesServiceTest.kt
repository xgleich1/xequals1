package com.dg.eqs.base.gamification

import android.content.Intent
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
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.games.AnnotatedData
import com.google.android.gms.games.LeaderboardsClient
import com.google.android.gms.games.LeaderboardsClient.LeaderboardScores
import com.google.android.gms.games.Player
import com.google.android.gms.games.PlayersClient
import com.google.android.gms.games.leaderboard.LeaderboardScore
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer
import com.google.android.gms.games.leaderboard.LeaderboardVariant.COLLECTION_PUBLIC
import com.google.android.gms.games.leaderboard.LeaderboardVariant.TIME_SPAN_ALL_TIME
import com.google.android.gms.games.leaderboard.ScoreSubmissionData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import org.mockito.kotlin.*
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GoogleGamesServiceTest {
    @Mock
    private lateinit var googleGames: GoogleGames
    @Mock
    private lateinit var googleSignIn: GoogleSignIn
    @Mock
    private lateinit var googleTracking: GoogleTracking

    private lateinit var googleGamesService: GoogleGamesService

    @Mock
    private lateinit var playersClient: PlayersClient
    @Mock
    private lateinit var leaderboardsClient: LeaderboardsClient
    @Mock
    private lateinit var googleSignInClient: GoogleSignInClient

    @Mock
    private lateinit var intent: Intent
    @Mock
    private lateinit var player: Player
    @Mock
    private lateinit var exception: Exception
    @Mock
    private lateinit var runtimeException: RuntimeException
    @Mock
    private lateinit var googleSignInAccount: GoogleSignInAccount


    @Before
    fun setUp() {
        setupClientsAvailable()

        googleGamesService = createService()
    }

    //<editor-fold desc="Signing in">
    @Test
    fun `Should provide an intent to manually sign in`() = runBlocking<Unit> {
        // GIVEN
        setupSignInIntentAvailable(intent)

        // WHEN
        val signInIntent = googleGamesService.loadSignInIntent()

        // THEN
        assertThat(signInIntent).isEqualTo(intent)
    }

    @Test
    fun `Should return the result when a sign in finishes`() = runBlocking<Unit> {
        // GIVEN
        setupSilentSignInFinished()

        setupGetPlayerFinished(player)

        // WHEN
        val signInResult = googleGamesService.signIn()

        // THEN
        assertThat(signInResult).isEqualTo(SignInFinished(player))
    }

    @Test
    fun `Should return the result when a sign in is necessary due to silent sign in failing`() = runBlocking<Unit> {
        // GIVEN
        setupSilentSignInFailed(exception)

        // WHEN
        val signInResult = googleGamesService.signIn()

        // THEN
        assertThat(signInResult).isEqualTo(SignInNecessary(exception))
    }

    @Test
    fun `Should return the result when a sign in is necessary due to get player failing`() = runBlocking<Unit> {
        // GIVEN
        setupSilentSignInFinished()

        setupGetPlayerFailed(exception)

        // WHEN
        val signInResult = googleGamesService.signIn()

        // THEN
        assertThat(signInResult).isEqualTo(SignInNecessary(exception))
    }

    @Test
    fun `Should return the result when a sign in is necessary due to the player being inaccessible`() = runBlocking<Unit> {
        // GIVEN
        setupSilentSignInFinished()

        setupPlayerInaccessible(runtimeException)

        // WHEN
        val signInResult = googleGamesService.signIn()

        // THEN
        assertThat(signInResult).isEqualTo(SignInNecessary(runtimeException))
    }

    @Test
    fun `Should track the result when a sign in finishes`() = runBlocking {
        // GIVEN
        setupSilentSignInFinished()

        setupGetPlayerFinished(player)

        // WHEN
        googleGamesService.signIn()

        // THEN
        verify(googleTracking).trackSignInResult(SignInFinished(player))
    }

    @Test
    fun `Should track the result when a sign in is necessary due to silent sign in failing`() = runBlocking {
        // GIVEN
        setupSilentSignInFailed(exception)

        // WHEN
        googleGamesService.signIn()

        // THEN
        verify(googleTracking).trackSignInResult(SignInNecessary(exception))
    }

    @Test
    fun `Should track the result when a sign in is necessary due to get player failing`() = runBlocking {
        // GIVEN
        setupSilentSignInFinished()

        setupGetPlayerFailed(exception)

        // WHEN
        googleGamesService.signIn()

        // THEN
        verify(googleTracking).trackSignInResult(SignInNecessary(exception))
    }

    @Test
    fun `Should track the result when a sign in is necessary due to the player being inaccessible`() = runBlocking {
        // GIVEN
        setupSilentSignInFinished()

        setupPlayerInaccessible(runtimeException)

        // WHEN
        googleGamesService.signIn()

        // THEN
        verify(googleTracking).trackSignInResult(SignInNecessary(runtimeException))
    }
    //</editor-fold>

    //<editor-fold desc="Signing out">
    @Test
    fun `Should return the result when a sign out finishes`() = runBlocking<Unit> {
        // GIVEN
        setupSignOutFinished()

        // WHEN
        val signOutResult = googleGamesService.signOut()

        // THEN
        assertThat(signOutResult).isEqualTo(SignOutFinished)
    }

    @Test
    fun `Should return the result when a sign out fails`() = runBlocking<Unit> {
        // GIVEN
        setupSignOutFailed(exception)

        // WHEN
        val signOutResult = googleGamesService.signOut()

        // THEN
        assertThat(signOutResult).isEqualTo(SignOutFailed(exception))
    }

    @Test
    fun `Should track the result when a sign out finishes`() = runBlocking {
        // GIVEN
        setupSignOutFinished()

        // WHEN
        googleGamesService.signOut()

        // THEN
        verify(googleTracking).trackSignOutResult(SignOutFinished)
    }

    @Test
    fun `Should track the result when a sign out fails`() = runBlocking {
        // GIVEN
        setupSignOutFailed(exception)

        // WHEN
        googleGamesService.signOut()

        // THEN
        verify(googleTracking).trackSignOutResult(SignOutFailed(exception))
    }
    //</editor-fold>

    //<editor-fold desc="Loading a player score">
    @Test
    fun `Should load the player score identified by the key`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountAvailable()

        setupPlayerLeaderboardScorePresent(1)

        // WHEN
        googleGamesService.loadPlayerScore(GoogleScoreBoardKey("a"))

        // THEN
        verify(leaderboardsClient)
                .loadCurrentPlayerLeaderboardScore("a", TIME_SPAN_ALL_TIME, COLLECTION_PUBLIC)
    }

    @Test
    fun `Should return the score when a player score is present`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountAvailable()

        setupPlayerLeaderboardScorePresent(1)

        // WHEN
        val score = googleGamesService
                .loadPlayerScore(GoogleScoreBoardKey("a"))

        // THEN
        assertThat(score).isEqualTo(1)
    }

    @Test
    fun `Should return zero when a player score is missing due to load leaderboard score failing`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountAvailable()

        setupLoadPlayerLeaderboardScoreFailed(exception)

        // WHEN
        val score = googleGamesService
                .loadPlayerScore(GoogleScoreBoardKey("a"))

        // THEN
        assertThat(score).isEqualTo(0)
    }

    @Test
    fun `Should return zero when a player score is missing due to the score being inaccessible`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountAvailable()

        setupPlayerLeaderboardScoreInaccessible(runtimeException)

        // WHEN
        val score = googleGamesService
                .loadPlayerScore(GoogleScoreBoardKey("a"))

        // THEN
        assertThat(score).isEqualTo(0)
    }

    @Test
    fun `Should return zero when a player score is missing due to the signed in account missing`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountMissing()

        // WHEN
        val score = googleGamesService
                .loadPlayerScore(GoogleScoreBoardKey("a"))

        // THEN
        assertThat(score).isEqualTo(0)
    }

    @Test
    fun `Should track the result when a player score is present`() = runBlocking {
        // GIVEN
        setupSignedInAccountAvailable()

        setupPlayerLeaderboardScorePresent(1)

        // WHEN
        googleGamesService.loadPlayerScore(GoogleScoreBoardKey("a"))

        // THEN
        verify(googleTracking).trackPlayerScoreResult(PlayerScorePresent(1))
    }

    @Test
    fun `Should track the result when a player score is missing due to load leaderboard score failing`() = runBlocking {
        // GIVEN
        setupSignedInAccountAvailable()

        setupLoadPlayerLeaderboardScoreFailed(exception)

        // WHEN
        googleGamesService.loadPlayerScore(GoogleScoreBoardKey("a"))

        // THEN
        verify(googleTracking).trackPlayerScoreResult(PlayerScoreMissing(exception))
    }

    @Test
    fun `Should track the result when a player score is missing due to the score being inaccessible`() = runBlocking {
        // GIVEN
        setupSignedInAccountAvailable()

        setupPlayerLeaderboardScoreInaccessible(runtimeException)

        // WHEN
        googleGamesService.loadPlayerScore(GoogleScoreBoardKey("a"))

        // THEN
        verify(googleTracking).trackPlayerScoreResult(PlayerScoreMissing(runtimeException))
    }

    @Test
    fun `Should track the result when a player score is missing due to the signed in account missing`() = runBlocking {
        // GIVEN
        setupSignedInAccountMissing()

        // WHEN
        googleGamesService.loadPlayerScore(GoogleScoreBoardKey("a"))

        // THEN
        verify(googleTracking).trackPlayerScoreResult(PlayerScoreMissing())
    }
    //</editor-fold>

    //<editor-fold desc="Loading a top score">
    @Test
    fun `Should load the top score identified by the key`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountAvailable()

        setupTopLeaderboardScorePresent(1)

        // WHEN
        googleGamesService.loadTopScore(GoogleScoreBoardKey("a"))

        // THEN
        verify(leaderboardsClient)
                .loadTopScores("a", TIME_SPAN_ALL_TIME, COLLECTION_PUBLIC, 1, true)
    }

    @Test
    fun `Should return the score when a top score is present`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountAvailable()

        setupTopLeaderboardScorePresent(1)

        // WHEN
        val score = googleGamesService
                .loadTopScore(GoogleScoreBoardKey("a"))

        // THEN
        assertThat(score).isEqualTo(1)
    }

    @Test
    fun `Should return zero when a top score is missing due to load leaderboard score failing`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountAvailable()

        setupLoadTopLeaderboardScoreFailed(exception)

        // WHEN
        val score = googleGamesService
                .loadTopScore(GoogleScoreBoardKey("a"))

        // THEN
        assertThat(score).isEqualTo(0)
    }

    @Test
    fun `Should return zero when a top score is missing due to the score being inaccessible`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountAvailable()

        setupTopLeaderboardScoreInaccessible(runtimeException)

        // WHEN
        val score = googleGamesService
                .loadTopScore(GoogleScoreBoardKey("a"))

        // THEN
        assertThat(score).isEqualTo(0)
    }

    @Test
    fun `Should return zero when a top score is missing due to the signed in account missing`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountMissing()

        // WHEN
        val score = googleGamesService
                .loadTopScore(GoogleScoreBoardKey("a"))

        // THEN
        assertThat(score).isEqualTo(0)
    }

    @Test
    fun `Should track the result when a top score is present`() = runBlocking {
        // GIVEN
        setupSignedInAccountAvailable()

        setupTopLeaderboardScorePresent(1)

        // WHEN
        googleGamesService.loadTopScore(GoogleScoreBoardKey("a"))

        // THEN
        verify(googleTracking).trackTopScoreResult(TopScorePresent(1))
    }

    @Test
    fun `Should track the result when a top score is missing due to load leaderboard score failing`() = runBlocking {
        // GIVEN
        setupSignedInAccountAvailable()

        setupLoadTopLeaderboardScoreFailed(exception)

        // WHEN
        googleGamesService.loadTopScore(GoogleScoreBoardKey("a"))

        // THEN
        verify(googleTracking).trackTopScoreResult(TopScoreMissing(exception))
    }

    @Test
    fun `Should track the result when a top score is missing due to the score being inaccessible`() = runBlocking {
        // GIVEN
        setupSignedInAccountAvailable()

        setupTopLeaderboardScoreInaccessible(runtimeException)

        // WHEN
        googleGamesService.loadTopScore(GoogleScoreBoardKey("a"))

        // THEN
        verify(googleTracking).trackTopScoreResult(TopScoreMissing(runtimeException))
    }

    @Test
    fun `Should track the result when a top score is missing due to the signed in account missing`() = runBlocking {
        // GIVEN
        setupSignedInAccountMissing()

        // WHEN
        googleGamesService.loadTopScore(GoogleScoreBoardKey("a"))

        // THEN
        verify(googleTracking).trackTopScoreResult(TopScoreMissing())
    }
    //</editor-fold>

    //<editor-fold desc="Loading a score board">
    @Test
    fun `Should load the score board identified by the key`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountAvailable()

        setupLeaderboardIntentPresent(intent)

        // WHEN
        googleGamesService.loadScoreBoard(GoogleScoreBoardKey("a"))

        // THEN
        verify(leaderboardsClient).getLeaderboardIntent("a")
    }

    @Test
    fun `Should return the result when a score board is present`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountAvailable()

        setupLeaderboardIntentPresent(intent)

        // WHEN
        val scoreBoardResult = googleGamesService
                .loadScoreBoard(GoogleScoreBoardKey("a"))

        // THEN
        assertThat(scoreBoardResult).isEqualTo(ScoreBoardPresent(intent))
    }

    @Test
    fun `Should return the result when a score board is missing`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountAvailable()

        setupGetLeaderboardIntentFailed(exception)

        // WHEN
        val scoreBoardResult = googleGamesService
                .loadScoreBoard(GoogleScoreBoardKey("a"))

        // THEN
        assertThat(scoreBoardResult).isEqualTo(ScoreBoardMissing(exception))
    }

    @Test
    fun `Should return the result when a score board is missing due to the signed in account missing`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountMissing()

        // WHEN
        val scoreBoardResult = googleGamesService
                .loadScoreBoard(GoogleScoreBoardKey("a"))

        // THEN
        assertThat(scoreBoardResult).isEqualTo(ScoreBoardMissing())
    }

    @Test
    fun `Should track the result when a score board is present`() = runBlocking {
        // GIVEN
        setupSignedInAccountAvailable()

        setupLeaderboardIntentPresent(intent)

        // WHEN
        googleGamesService.loadScoreBoard(GoogleScoreBoardKey("a"))

        // THEN
        verify(googleTracking).trackScoreBoardResult(ScoreBoardPresent(intent))
    }

    @Test
    fun `Should track the result when a score board is missing`() = runBlocking {
        // GIVEN
        setupSignedInAccountAvailable()

        setupGetLeaderboardIntentFailed(exception)

        // WHEN
        googleGamesService.loadScoreBoard(GoogleScoreBoardKey("a"))

        // THEN
        verify(googleTracking).trackScoreBoardResult(ScoreBoardMissing(exception))
    }

    @Test
    fun `Should track the result when a score board is missing due to the signed in account missing`() = runBlocking {
        // GIVEN
        setupSignedInAccountMissing()

        // WHEN
        googleGamesService.loadScoreBoard(GoogleScoreBoardKey("a"))

        // THEN
        verify(googleTracking).trackScoreBoardResult(ScoreBoardMissing())
    }
    //</editor-fold>

    //<editor-fold desc="Submitting a score">
    @Test
    fun `Should submit the score to the key's board`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountAvailable()

        setupSubmitScoreFinished()

        // WHEN
        googleGamesService.submitScore(GoogleScoreBoardKey("a"), 1)

        // THEN
        verify(leaderboardsClient).submitScoreImmediate("a", 1)
    }

    @Test
    fun `Should return the result when a score submit finishes`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountAvailable()

        setupSubmitScoreFinished()

        // WHEN
        val submitScoreResult = googleGamesService
                .submitScore(GoogleScoreBoardKey("a"), 1)

        // THEN
        assertThat(submitScoreResult).isEqualTo(SubmitScoreFinished)
    }

    @Test
    fun `Should return the result when a score submit fails`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountAvailable()

        setupSubmitScoreFailed(exception)

        // WHEN
        val submitScoreResult = googleGamesService
                .submitScore(GoogleScoreBoardKey("a"), 1)

        // THEN
        assertThat(submitScoreResult).isEqualTo(SubmitScoreFailed(exception))
    }

    @Test
    fun `Should return the result when a score submit is impossible due to the signed in account missing`() = runBlocking<Unit> {
        // GIVEN
        setupSignedInAccountMissing()

        // WHEN
        val submitScoreResult = googleGamesService
                .submitScore(GoogleScoreBoardKey("a"), 1)

        // THEN
        assertThat(submitScoreResult).isEqualTo(SubmitScoreFailed())
    }

    @Test
    fun `Should track the result when a score submit finishes`() = runBlocking {
        // GIVEN
        setupSignedInAccountAvailable()

        setupSubmitScoreFinished()

        // WHEN
        googleGamesService.submitScore(GoogleScoreBoardKey("a"), 1)

        // THEN
        verify(googleTracking).trackSubmitScoreResult(SubmitScoreFinished)
    }

    @Test
    fun `Should track the result when a score submit fails`() = runBlocking {
        // GIVEN
        setupSignedInAccountAvailable()

        setupSubmitScoreFailed(exception)

        // WHEN
        googleGamesService.submitScore(GoogleScoreBoardKey("a"), 1)

        // THEN
        verify(googleTracking).trackSubmitScoreResult(SubmitScoreFailed(exception))
    }

    @Test
    fun `Should track the result when a score submit is impossible due to the signed in account missing`() = runBlocking {
        // GIVEN
        setupSignedInAccountMissing()

        // WHEN
        googleGamesService.submitScore(GoogleScoreBoardKey("a"), 1)

        // THEN
        verify(googleTracking).trackSubmitScoreResult(SubmitScoreFailed())
    }
    //</editor-fold>

    private fun setupClientsAvailable() {
        whenever(googleGames.getPlayersClient(any())) doReturn playersClient
        whenever(googleGames.getLeaderboardsClient(any())) doReturn leaderboardsClient
        whenever(googleSignIn.getGamesSignInClient()) doReturn googleSignInClient
    }

    private fun createService() = GoogleGamesService(googleGames, googleSignIn, googleTracking)

    private fun setupSignInIntentAvailable(signInIntent: Intent) = whenever(
            googleSignInClient.signInIntent) doReturn signInIntent

    private fun setupSilentSignInFinished() {
        val silentSignInTask: Task<GoogleSignInAccount> = mock()

        whenever(googleSignInClient.silentSignIn()) doReturn silentSignInTask

        argumentCaptor<OnSuccessListener<GoogleSignInAccount>>().apply {
            whenever(silentSignInTask.addOnSuccessListener(capture())) doAnswer {
                lastValue.onSuccess(mock())

                silentSignInTask
            }
        }
    }

    private fun setupSilentSignInFailed(silentSignInException: Exception) {
        val silentSignInTask: Task<GoogleSignInAccount> = mock()

        whenever(googleSignInClient.silentSignIn()) doReturn silentSignInTask

        whenever(silentSignInTask.addOnSuccessListener(any())) doReturn silentSignInTask

        argumentCaptor<OnFailureListener>().apply {
            whenever(silentSignInTask.addOnFailureListener(capture())) doAnswer {
                lastValue.onFailure(silentSignInException)

                silentSignInTask
            }
        }
    }

    private fun setupGetPlayerFinished(player: Player) {
        val getPlayerTask: Task<AnnotatedData<Player>> = mock()

        whenever(playersClient.getCurrentPlayer(true)) doReturn getPlayerTask

        val annotatedData: AnnotatedData<Player> = mock {
            on { get() } doReturn player
        }

        argumentCaptor<OnSuccessListener<AnnotatedData<Player>>>().apply {
            whenever(getPlayerTask.addOnSuccessListener(capture())) doAnswer {
                lastValue.onSuccess(annotatedData)

                getPlayerTask
            }
        }
    }

    private fun setupGetPlayerFailed(getPlayerException: Exception) {
        val getPlayerTask: Task<AnnotatedData<Player>> = mock()

        whenever(playersClient.getCurrentPlayer(true)) doReturn getPlayerTask

        whenever(getPlayerTask.addOnSuccessListener(any())) doReturn getPlayerTask

        argumentCaptor<OnFailureListener>().apply {
            whenever(getPlayerTask.addOnFailureListener(capture())) doAnswer {
                lastValue.onFailure(getPlayerException)

                getPlayerTask
            }
        }
    }

    private fun setupPlayerInaccessible(getPlayerException: RuntimeException) {
        val getPlayerTask: Task<AnnotatedData<Player>> = mock()

        whenever(playersClient.getCurrentPlayer(true)) doReturn getPlayerTask

        val annotatedData: AnnotatedData<Player> = mock {
            on { get() } doThrow getPlayerException
        }

        argumentCaptor<OnSuccessListener<AnnotatedData<Player>>>().apply {
            whenever(getPlayerTask.addOnSuccessListener(capture())) doAnswer {
                lastValue.onSuccess(annotatedData)

                getPlayerTask
            }
        }
    }

    private fun setupSignOutFinished() {
        val signOutTask: Task<Void> = mock()

        whenever(googleSignInClient.signOut()) doReturn signOutTask

        argumentCaptor<OnSuccessListener<Void>>().apply {
            whenever(signOutTask.addOnSuccessListener(capture())) doAnswer {
                lastValue.onSuccess(mock())

                signOutTask
            }
        }
    }

    private fun setupSignOutFailed(signOutException: Exception) {
        val signOutTask: Task<Void> = mock()

        whenever(googleSignInClient.signOut()) doReturn signOutTask

        whenever(signOutTask.addOnSuccessListener(any())) doReturn signOutTask

        argumentCaptor<OnFailureListener>().apply {
            whenever(signOutTask.addOnFailureListener(capture())) doAnswer {
                lastValue.onFailure(signOutException)

                signOutTask
            }
        }
    }

    private fun setupSignedInAccountAvailable() = whenever(
            googleSignIn.getLastSignedInAccount()) doReturn googleSignInAccount

    private fun setupSignedInAccountMissing() = whenever(
            googleSignIn.getLastSignedInAccount()) doReturn null

    private fun setupPlayerLeaderboardScorePresent(score: Long) {
        val loadPlayerLeaderboardScoreTask: Task<AnnotatedData<LeaderboardScore>> = mock()

        whenever(leaderboardsClient.loadCurrentPlayerLeaderboardScore(
                any(), any(), any())) doReturn loadPlayerLeaderboardScoreTask

        val leaderboardScore: LeaderboardScore = mock {
            on { rawScore } doReturn score
        }

        val annotatedData: AnnotatedData<LeaderboardScore> = mock {
            on { get() } doReturn leaderboardScore
        }

        argumentCaptor<OnSuccessListener<AnnotatedData<LeaderboardScore>>>().apply {
            whenever(loadPlayerLeaderboardScoreTask.addOnSuccessListener(capture())) doAnswer {
                lastValue.onSuccess(annotatedData)

                loadPlayerLeaderboardScoreTask
            }
        }
    }

    private fun setupLoadPlayerLeaderboardScoreFailed(loadPlayerLeaderboardScoreException: Exception) {
        val loadPlayerLeaderboardScoreTask: Task<AnnotatedData<LeaderboardScore>> = mock()

        whenever(leaderboardsClient.loadCurrentPlayerLeaderboardScore(
                any(), any(), any())) doReturn loadPlayerLeaderboardScoreTask

        whenever(loadPlayerLeaderboardScoreTask
                .addOnSuccessListener(any())) doReturn loadPlayerLeaderboardScoreTask

        argumentCaptor<OnFailureListener>().apply {
            whenever(loadPlayerLeaderboardScoreTask.addOnFailureListener(capture())) doAnswer {
                lastValue.onFailure(loadPlayerLeaderboardScoreException)

                loadPlayerLeaderboardScoreTask
            }
        }
    }

    private fun setupPlayerLeaderboardScoreInaccessible(getLeaderboardScoreException: RuntimeException) {
        val loadCurrentLeaderboardScoreTask: Task<AnnotatedData<LeaderboardScore>> = mock()

        whenever(leaderboardsClient.loadCurrentPlayerLeaderboardScore(
                any(), any(), any())) doReturn loadCurrentLeaderboardScoreTask

        val annotatedData: AnnotatedData<LeaderboardScore> = mock {
            on { get() } doThrow getLeaderboardScoreException
        }

        argumentCaptor<OnSuccessListener<AnnotatedData<LeaderboardScore>>>().apply {
            whenever(loadCurrentLeaderboardScoreTask.addOnSuccessListener(capture())) doAnswer {
                lastValue.onSuccess(annotatedData)

                loadCurrentLeaderboardScoreTask
            }
        }
    }

    private fun setupTopLeaderboardScorePresent(score: Long) {
        val loadTopScoresTask: Task<AnnotatedData<LeaderboardScores>> = mock()

        whenever(leaderboardsClient.loadTopScores(
                any(), any(), any(), any(), any())) doReturn loadTopScoresTask

        val leaderboardScore: LeaderboardScore = mock {
            on { rawScore } doReturn score
        }

        val leaderboardScoreBuffer: LeaderboardScoreBuffer = mock {
            on { get(0) } doReturn leaderboardScore
        }

        val leaderboardScores: LeaderboardScores = mock {
            on { scores } doReturn leaderboardScoreBuffer
        }

        val annotatedData: AnnotatedData<LeaderboardScores> = mock {
            on { get() } doReturn leaderboardScores
        }

        argumentCaptor<OnSuccessListener<AnnotatedData<LeaderboardScores>>>().apply {
            whenever(loadTopScoresTask.addOnSuccessListener(capture())) doAnswer {
                lastValue.onSuccess(annotatedData)

                loadTopScoresTask
            }
        }
    }

    private fun setupLoadTopLeaderboardScoreFailed(loadTopLeaderboardScoreException: Exception) {
        val loadTopScoresTask: Task<AnnotatedData<LeaderboardScores>> = mock()

        whenever(leaderboardsClient.loadTopScores(
                any(), any(), any(), any(), any())) doReturn loadTopScoresTask

        whenever(loadTopScoresTask.addOnSuccessListener(any())) doReturn loadTopScoresTask

        argumentCaptor<OnFailureListener>().apply {
            whenever(loadTopScoresTask.addOnFailureListener(capture())) doAnswer {
                lastValue.onFailure(loadTopLeaderboardScoreException)

                loadTopScoresTask
            }
        }
    }

    private fun setupTopLeaderboardScoreInaccessible(getLeaderboardScoresException: RuntimeException) {
        val loadTopScoresTask: Task<AnnotatedData<LeaderboardScores>> = mock()

        whenever(leaderboardsClient.loadTopScores(
                any(), any(), any(), any(), any())) doReturn loadTopScoresTask

        val annotatedData: AnnotatedData<LeaderboardScores> = mock {
            on { get() } doThrow getLeaderboardScoresException
        }

        argumentCaptor<OnSuccessListener<AnnotatedData<LeaderboardScores>>>().apply {
            whenever(loadTopScoresTask.addOnSuccessListener(capture())) doAnswer {
                lastValue.onSuccess(annotatedData)

                loadTopScoresTask
            }
        }
    }

    private fun setupLeaderboardIntentPresent(leaderboardIntent: Intent) {
        val getLeaderboardIntentTask: Task<Intent> = mock()

        whenever(leaderboardsClient.getLeaderboardIntent(any())) doReturn getLeaderboardIntentTask

        argumentCaptor<OnSuccessListener<Intent>>().apply {
            whenever(getLeaderboardIntentTask.addOnSuccessListener(capture())) doAnswer {
                lastValue.onSuccess(leaderboardIntent)

                getLeaderboardIntentTask
            }
        }
    }

    private fun setupGetLeaderboardIntentFailed(getLeaderboardIntentException: Exception) {
        val getLeaderboardIntentTask: Task<Intent> = mock()

        whenever(leaderboardsClient.getLeaderboardIntent(any())) doReturn getLeaderboardIntentTask

        whenever(getLeaderboardIntentTask.addOnSuccessListener(any())) doReturn getLeaderboardIntentTask

        argumentCaptor<OnFailureListener>().apply {
            whenever(getLeaderboardIntentTask.addOnFailureListener(capture())) doAnswer {
                lastValue.onFailure(getLeaderboardIntentException)

                getLeaderboardIntentTask
            }
        }
    }

    private fun setupSubmitScoreFinished() {
        val submitScoreTask: Task<ScoreSubmissionData> = mock()

        whenever(leaderboardsClient.submitScoreImmediate(any(), any())) doReturn submitScoreTask

        argumentCaptor<OnSuccessListener<ScoreSubmissionData>>().apply {
            whenever(submitScoreTask.addOnSuccessListener(capture())) doAnswer {
                lastValue.onSuccess(mock())

                submitScoreTask
            }
        }
    }

    private fun setupSubmitScoreFailed(submitScoreException: Exception) {
        val submitScoreTask: Task<ScoreSubmissionData> = mock()

        whenever(leaderboardsClient.submitScoreImmediate(any(), any())) doReturn submitScoreTask

        whenever(submitScoreTask.addOnSuccessListener(any())) doReturn submitScoreTask

        argumentCaptor<OnFailureListener>().apply {
            whenever(submitScoreTask.addOnFailureListener(capture())) doAnswer {
                lastValue.onFailure(submitScoreException)

                submitScoreTask
            }
        }
    }
}