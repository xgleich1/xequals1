package com.dg.eqs.page.online

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dg.eqs.R
import com.dg.eqs.base.enveloping.HtmlRes
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.base.gamification.GoogleGamesService
import com.dg.eqs.base.gamification.result.SignInResult
import com.dg.eqs.base.gamification.result.SignInResult.SignInFinished
import com.dg.eqs.base.gamification.result.SignInResult.SignInNecessary
import com.dg.eqs.base.observation.LiveDataCommand
import com.dg.eqs.base.observation.LiveDataEvent
import com.dg.eqs.base.observation.emit
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


class OnlinePageViewModel(
        private val googleGamesService: GoogleGamesService
) : ViewModel() {

    val showStatus = MutableLiveData<HtmlRes>()
    val googleButtonText = MutableLiveData<StringRes>()
    val googleButtonEnabled = MutableLiveData<Boolean>()
    val eventsButtonEnabled = MutableLiveData<Boolean>()
    val eventsButtonLoading = MutableLiveData<Boolean>()
    val navigateBack = MutableLiveData<LiveDataCommand>()
    val navigateToSignIn = MutableLiveData<LiveDataEvent<Intent>>()
    val navigateToEventsPage = MutableLiveData<LiveDataCommand>()

    private lateinit var signInResult: SignInResult


    init {
        signIntoGoogleGames()
    }

    fun onBackPressed() = emitNavigateBack()

    fun onBackButtonClicked() = emitNavigateBack()

    fun onGoogleButtonClicked() {
        emitDisableGoogleButton()
        emitDisableEventsButton()
        showEventsButtonLoading()

        when(signInResult) {
            is SignInFinished -> signOutOfGoogleGames()
            is SignInNecessary -> emitNavigateToSignIn()
        }
    }

    fun onEventsButtonClicked() = emitNavigateToEventsPage()

    fun onNavigatedBackFromSignInPage() = signIntoGoogleGames()

    private fun signIntoGoogleGames() {
        viewModelScope.launch(Main) {
            signInResult = googleGamesService.signIn()

            when(signInResult) {
                is SignInFinished -> showSignedInUi()
                is SignInNecessary -> showSignedOutUi()
            }
        }
    }

    private fun signOutOfGoogleGames() {
        viewModelScope.launch(Main) {
            signInResult = SignInNecessary()

            googleGamesService.signOut()

            showSignedOutUi()
        }
    }

    private fun showSignedInUi() {
        emitSignedInStatus()
        emitSignOutButtonText()
        emitEnableGoogleButton()
        emitEnableEventsButton()
        hideEventsButtonLoading()
    }

    private fun showSignedOutUi() {
        emitSignedOutStatus()
        emitSignInButtonText()
        emitEnableGoogleButton()
        emitDisableEventsButton()
        hideEventsButtonLoading()
    }

    private fun emitSignedInStatus() = showStatus.emit(
            HtmlRes(R.string.online_status_signed_in))

    private fun emitSignedOutStatus() = showStatus.emit(
            HtmlRes(R.string.online_status_signed_out))

    private fun emitSignInButtonText() = googleButtonText.emit(
            StringRes(R.string.online_button_sign_in))

    private fun emitSignOutButtonText() = googleButtonText.emit(
            StringRes(R.string.online_button_sign_out))

    private fun emitEnableGoogleButton() = googleButtonEnabled.emit(true)

    private fun emitDisableGoogleButton() = googleButtonEnabled.emit(false)

    private fun emitEnableEventsButton() = eventsButtonEnabled.emit(true)

    private fun emitDisableEventsButton() = eventsButtonEnabled.emit(false)

    private fun showEventsButtonLoading() = eventsButtonLoading.emit(true)

    private fun hideEventsButtonLoading() = eventsButtonLoading.emit(false)

    private fun emitNavigateBack() = navigateBack.emit()

    private fun emitNavigateToSignIn() = navigateToSignIn.emit(
            googleGamesService.loadSignInIntent())

    private fun emitNavigateToEventsPage() = navigateToEventsPage.emit()
}