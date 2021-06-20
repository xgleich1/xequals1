package com.dg.eqs.base.gamification

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.games.Games
import com.google.android.gms.games.LeaderboardsClient
import com.google.android.gms.games.PlayersClient


class GoogleGames(
        private val application: Application) {

    fun getPlayersClient(account: GoogleSignInAccount): PlayersClient =
            Games.getPlayersClient(application, account)

    fun getLeaderboardsClient(account: GoogleSignInAccount): LeaderboardsClient =
            Games.getLeaderboardsClient(application, account)
}