package com.dg.eqs.base.gamification.result

import com.google.android.gms.games.Player


sealed class SignInResult {
    data class SignInFinished(val player: Player) : SignInResult()

    data class SignInNecessary(val exception: Exception? = null) : SignInResult()
}