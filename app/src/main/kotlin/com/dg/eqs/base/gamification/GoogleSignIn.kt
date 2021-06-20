package com.dg.eqs.base.gamification

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN


class GoogleSignIn(
        private val application: Application) {

    fun getGamesSignInClient(): GoogleSignInClient =
            GoogleSignIn.getClient(application, DEFAULT_GAMES_SIGN_IN)

    fun getLastSignedInAccount(): GoogleSignInAccount? =
            GoogleSignIn.getLastSignedInAccount(application)
}