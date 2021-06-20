package com.dg.eqs.base.gamification.result


sealed class SignOutResult {
    object SignOutFinished : SignOutResult()

    data class SignOutFailed(val exception: Exception? = null) : SignOutResult()
}