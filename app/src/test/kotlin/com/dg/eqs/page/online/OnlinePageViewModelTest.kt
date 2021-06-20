package com.dg.eqs.page.online

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dg.eqs.R
import com.dg.eqs.base.enveloping.HtmlRes
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.base.gamification.GoogleGamesService
import com.dg.eqs.base.gamification.result.SignInResult
import com.dg.eqs.base.gamification.result.SignInResult.SignInFinished
import com.dg.eqs.base.gamification.result.SignInResult.SignInNecessary
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataEventObserver
import com.dg.eqs.base.observation.LiveDataObserver
import com.dg.eqs.util.rules.MainDispatcherRule
import org.mockito.kotlin.*
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class OnlinePageViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var googleGamesService: GoogleGamesService

    @Mock
    private lateinit var showStatusAction: (HtmlRes) -> Unit
    @Mock
    private lateinit var googleButtonTextAction: (StringRes) -> Unit
    @Mock
    private lateinit var googleButtonEnabledAction: (Boolean) -> Unit
    @Mock
    private lateinit var eventsButtonEnabledAction: (Boolean) -> Unit
    @Mock
    private lateinit var eventsButtonLoadingAction: (Boolean) -> Unit
    @Mock
    private lateinit var navigateBackAction: () -> Unit
    @Mock
    private lateinit var navigateToSignInAction: (Intent) -> Unit
    @Mock
    private lateinit var navigateToEventsPageAction: () -> Unit

    @Mock
    private lateinit var signInIntent: Intent
    @Mock
    private lateinit var signInFinished: SignInFinished
    @Mock
    private lateinit var signInNecessary: SignInNecessary


    //<editor-fold desc="Automatic google games sign in">
    @Test
    fun `Should automatically sign into google games when the page is opened`() = runBlocking<Unit> {
        // GIVEN
        setupSignInResults(signInFinished)

        createAndObserveViewModel()

        // THEN
        verify(googleGamesService).signIn()
    }

    @Test
    fun `Should emit the signed in status when the automatic sign in finished`() {
        // GIVEN
        setupSignInResults(signInFinished)

        createAndObserveViewModel()

        // THEN
        verify(showStatusAction).invoke(
                HtmlRes(R.string.online_status_signed_in))
    }

    @Test
    fun `Should emit the sign out button text when the automatic sign in finished`() {
        // GIVEN
        setupSignInResults(signInFinished)

        createAndObserveViewModel()

        // THEN
        verify(googleButtonTextAction).invoke(
                StringRes(R.string.online_button_sign_out))
    }

    @Test
    fun `Should enable the google button when the automatic sign in finished`() {
        // GIVEN
        setupSignInResults(signInFinished)

        createAndObserveViewModel()

        // THEN
        verify(googleButtonEnabledAction).invoke(true)
    }

    @Test
    fun `Should enable the events button when the automatic sign in finished`() {
        // GIVEN
        setupSignInResults(signInFinished)

        createAndObserveViewModel()

        // THEN
        verify(eventsButtonEnabledAction).invoke(true)
    }

    @Test
    fun `Should hide the events button loading when the automatic sign in finished`() {
        // GIVEN
        setupSignInResults(signInFinished)

        createAndObserveViewModel()

        // THEN
        verify(eventsButtonLoadingAction).invoke(false)
    }

    @Test
    fun `Should emit the signed out status when a manual sign in is necessary`() {
        // GIVEN
        setupSignInResults(signInNecessary)

        createAndObserveViewModel()

        // THEN
        verify(showStatusAction).invoke(
                HtmlRes(R.string.online_status_signed_out))
    }

    @Test
    fun `Should emit the sign in button text when a manual sign in is necessary`() {
        // GIVEN
        setupSignInResults(signInNecessary)

        createAndObserveViewModel()

        // THEN
        verify(googleButtonTextAction).invoke(
                StringRes(R.string.online_button_sign_in))
    }

    @Test
    fun `Should enable the google button when a manual sign in is necessary`() {
        // GIVEN
        setupSignInResults(signInNecessary)

        createAndObserveViewModel()

        // THEN
        verify(googleButtonEnabledAction).invoke(true)
    }

    @Test
    fun `Should disable the events button when a manual sign in is necessary`() {
        // GIVEN
        setupSignInResults(signInNecessary)

        createAndObserveViewModel()

        // THEN
        verify(eventsButtonEnabledAction).invoke(false)
    }

    @Test
    fun `Should hide the events button loading when a manual sign in is necessary`() {
        // GIVEN
        setupSignInResults(signInNecessary)

        createAndObserveViewModel()

        // THEN
        verify(eventsButtonLoadingAction).invoke(false)
    }
    //</editor-fold>

    //<editor-fold desc="Manual google games sign out">
    @Test
    fun `Should sign out of google games when manually signing out`() = runBlocking<Unit> {
        // GIVEN
        setupSignInResults(signInFinished)

        val viewModel = createAndObserveViewModel()

        clearInvocations(googleGamesService)

        // WHEN
        viewModel.onGoogleButtonClicked()

        // THEN
        verify(googleGamesService).signOut()
    }

    @Test
    fun `Should disable the google button before manually signing out`() = runBlocking {
        // GIVEN
        setupSignInResults(signInFinished)

        val viewModel = createAndObserveViewModel()

        clearInvocations(googleButtonEnabledAction)

        // WHEN
        viewModel.onGoogleButtonClicked()

        // THEN
        inOrder(googleButtonEnabledAction, googleGamesService) {
            verify(googleButtonEnabledAction).invoke(false)

            verify(googleGamesService).signOut()
        }
    }

    @Test
    fun `Should disable the events button before manually signing out`() = runBlocking {
        // GIVEN
        setupSignInResults(signInFinished)

        val viewModel = createAndObserveViewModel()

        clearInvocations(eventsButtonEnabledAction)

        // WHEN
        viewModel.onGoogleButtonClicked()

        // THEN
        inOrder(eventsButtonEnabledAction, googleGamesService) {
            verify(eventsButtonEnabledAction).invoke(false)

            verify(googleGamesService).signOut()
        }
    }

    @Test
    fun `Should show the events button loading before manually signing out`() = runBlocking {
        // GIVEN
        setupSignInResults(signInFinished)

        val viewModel = createAndObserveViewModel()

        clearInvocations(eventsButtonLoadingAction)

        // WHEN
        viewModel.onGoogleButtonClicked()

        // THEN
        inOrder(eventsButtonLoadingAction, googleGamesService) {
            verify(eventsButtonLoadingAction).invoke(true)

            verify(googleGamesService).signOut()
        }
    }

    @Test
    fun `Should emit the signed out status after manually signing out`() = runBlocking {
        // GIVEN
        setupSignInResults(signInFinished)

        val viewModel = createAndObserveViewModel()

        clearInvocations(showStatusAction)

        // WHEN
        viewModel.onGoogleButtonClicked()

        // THEN
        inOrder(googleGamesService, showStatusAction) {
            verify(googleGamesService).signOut()

            verify(showStatusAction).invoke(
                    HtmlRes(R.string.online_status_signed_out))
        }
    }

    @Test
    fun `Should emit the sign in button text after manually signing out`() = runBlocking {
        // GIVEN
        setupSignInResults(signInFinished)

        val viewModel = createAndObserveViewModel()

        clearInvocations(googleButtonTextAction)

        // WHEN
        viewModel.onGoogleButtonClicked()

        // THEN
        inOrder(googleGamesService, googleButtonTextAction) {
            verify(googleGamesService).signOut()

            verify(googleButtonTextAction).invoke(
                    StringRes(R.string.online_button_sign_in))
        }
    }

    @Test
    fun `Should enable the google button after manually signing out`() = runBlocking {
        // GIVEN
        setupSignInResults(signInFinished)

        val viewModel = createAndObserveViewModel()

        clearInvocations(googleButtonEnabledAction)

        // WHEN
        viewModel.onGoogleButtonClicked()

        // THEN
        inOrder(googleGamesService, googleButtonEnabledAction) {
            verify(googleGamesService).signOut()

            verify(googleButtonEnabledAction).invoke(true)
        }
    }

    @Test
    fun `Should disable the events button after manually signing out`() = runBlocking {
        // GIVEN
        setupSignInResults(signInFinished)

        val viewModel = createAndObserveViewModel()

        clearInvocations(eventsButtonEnabledAction)

        // WHEN
        viewModel.onGoogleButtonClicked()

        // THEN
        inOrder(googleGamesService, eventsButtonEnabledAction) {
            verify(googleGamesService).signOut()

            verify(eventsButtonEnabledAction).invoke(false)
        }
    }

    @Test
    fun `Should hide the events button loading after manually signing out`() = runBlocking {
        // GIVEN
        setupSignInResults(signInFinished)

        val viewModel = createAndObserveViewModel()

        clearInvocations(eventsButtonLoadingAction)

        // WHEN
        viewModel.onGoogleButtonClicked()

        // THEN
        inOrder(googleGamesService, eventsButtonLoadingAction) {
            verify(googleGamesService).signOut()

            verify(eventsButtonLoadingAction).invoke(false)
        }
    }
    //</editor-fold>

    //<editor-fold desc="Manual google games sign in">
    @Test
    fun `Should navigate to the sign in when manually signing in`() {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary)

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onGoogleButtonClicked()

        // THEN
        verify(navigateToSignInAction).invoke(signInIntent)
    }

    @Test
    fun `Should disable the google button before manually signing in`() {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary)

        val viewModel = createAndObserveViewModel()

        clearInvocations(googleButtonEnabledAction)

        // WHEN
        viewModel.onGoogleButtonClicked()

        // THEN
        inOrder(googleButtonEnabledAction, navigateToSignInAction) {
            verify(googleButtonEnabledAction).invoke(false)

            verify(navigateToSignInAction).invoke(any())
        }
    }

    @Test
    fun `Should disable the events button before manually signing in`() {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary)

        val viewModel = createAndObserveViewModel()

        clearInvocations(eventsButtonEnabledAction)

        // WHEN
        viewModel.onGoogleButtonClicked()

        // THEN
        inOrder(eventsButtonEnabledAction, navigateToSignInAction) {
            verify(eventsButtonEnabledAction).invoke(false)

            verify(navigateToSignInAction).invoke(any())
        }
    }

    @Test
    fun `Should show the events button loading before manually signing in`() {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary)

        val viewModel = createAndObserveViewModel()

        clearInvocations(eventsButtonLoadingAction)

        // WHEN
        viewModel.onGoogleButtonClicked()

        // THEN
        inOrder(eventsButtonLoadingAction, navigateToSignInAction) {
            verify(eventsButtonLoadingAction).invoke(true)

            verify(navigateToSignInAction).invoke(any())
        }
    }

    @Test
    fun `Should sign into google games after manually signing in`() = runBlocking<Unit> {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary, signInFinished)

        val viewModel = createAndObserveViewModel()

        viewModel.onGoogleButtonClicked()

        clearInvocations(googleGamesService)

        // WHEN
        viewModel.onNavigatedBackFromSignInPage()

        // THEN
        verify(googleGamesService).signIn()
    }

    @Test
    fun `Should emit the signed in status when the manual sign in finished`() {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary, signInFinished)

        val viewModel = createAndObserveViewModel()

        viewModel.onGoogleButtonClicked()

        clearInvocations(showStatusAction)

        // WHEN
        viewModel.onNavigatedBackFromSignInPage()

        // THEN
        verify(showStatusAction).invoke(
                HtmlRes(R.string.online_status_signed_in))
    }

    @Test
    fun `Should emit the sign out button text when the manual sign in finished`() {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary, signInFinished)

        val viewModel = createAndObserveViewModel()

        viewModel.onGoogleButtonClicked()

        clearInvocations(googleButtonTextAction)

        // WHEN
        viewModel.onNavigatedBackFromSignInPage()

        // THEN
        verify(googleButtonTextAction).invoke(
                StringRes(R.string.online_button_sign_out))
    }

    @Test
    fun `Should enable the google button when the manual sign in finished`() {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary, signInFinished)

        val viewModel = createAndObserveViewModel()

        viewModel.onGoogleButtonClicked()

        clearInvocations(googleButtonEnabledAction)

        // WHEN
        viewModel.onNavigatedBackFromSignInPage()

        // THEN
        verify(googleButtonEnabledAction).invoke(true)
    }

    @Test
    fun `Should enable the events button when the manual sign in finished`() {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary, signInFinished)

        val viewModel = createAndObserveViewModel()

        viewModel.onGoogleButtonClicked()

        clearInvocations(eventsButtonEnabledAction)

        // WHEN
        viewModel.onNavigatedBackFromSignInPage()

        // THEN
        verify(eventsButtonEnabledAction).invoke(true)
    }

    @Test
    fun `Should hide the events button loading when the manual sign in finished`() {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary, signInFinished)

        val viewModel = createAndObserveViewModel()

        viewModel.onGoogleButtonClicked()

        clearInvocations(eventsButtonLoadingAction)

        // WHEN
        viewModel.onNavigatedBackFromSignInPage()

        // THEN
        verify(eventsButtonLoadingAction).invoke(false)
    }

    @Test
    fun `Should manually sign out after a successful manual sign in`() = runBlocking<Unit> {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary, signInFinished)

        val viewModel = createAndObserveViewModel()

        viewModel.onGoogleButtonClicked()
        viewModel.onNavigatedBackFromSignInPage()

        clearInvocations(googleGamesService)

        // WHEN
        viewModel.onGoogleButtonClicked()

        // THEN
        verify(googleGamesService).signOut()
    }

    @Test
    fun `Should emit the signed out status when the manual sign in fails`() {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary, signInNecessary)

        val viewModel = createAndObserveViewModel()

        viewModel.onGoogleButtonClicked()

        clearInvocations(showStatusAction)

        // WHEN
        viewModel.onNavigatedBackFromSignInPage()

        // THEN
        verify(showStatusAction).invoke(
                HtmlRes(R.string.online_status_signed_out))
    }

    @Test
    fun `Should emit the sign in button text when the manual sign in fails`() {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary, signInNecessary)

        val viewModel = createAndObserveViewModel()

        viewModel.onGoogleButtonClicked()

        clearInvocations(googleButtonTextAction)

        // WHEN
        viewModel.onNavigatedBackFromSignInPage()

        // THEN
        verify(googleButtonTextAction).invoke(
                StringRes(R.string.online_button_sign_in))
    }

    @Test
    fun `Should enable the google button when the manual sign in fails`() {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary, signInNecessary)

        val viewModel = createAndObserveViewModel()

        viewModel.onGoogleButtonClicked()

        clearInvocations(googleButtonEnabledAction)

        // WHEN
        viewModel.onNavigatedBackFromSignInPage()

        // THEN
        verify(googleButtonEnabledAction).invoke(true)
    }

    @Test
    fun `Should disable the events button when the manual sign in fails`() {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary, signInNecessary)

        val viewModel = createAndObserveViewModel()

        viewModel.onGoogleButtonClicked()

        clearInvocations(eventsButtonEnabledAction)

        // WHEN
        viewModel.onNavigatedBackFromSignInPage()

        // THEN
        verify(eventsButtonEnabledAction).invoke(false)
    }

    @Test
    fun `Should hide the events button loading when the manual sign in fails`() {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary, signInNecessary)

        val viewModel = createAndObserveViewModel()

        viewModel.onGoogleButtonClicked()

        clearInvocations(eventsButtonLoadingAction)

        // WHEN
        viewModel.onNavigatedBackFromSignInPage()

        // THEN
        verify(eventsButtonLoadingAction).invoke(false)
    }

    @Test
    fun `Should manually sign in after a failed manual sign in`() = runBlocking<Unit> {
        // GIVEN
        setupSignInIntent(signInIntent)
        setupSignInResults(signInNecessary, signInNecessary, signInFinished)

        val viewModel = createAndObserveViewModel()

        viewModel.onGoogleButtonClicked()
        viewModel.onNavigatedBackFromSignInPage()
        viewModel.onGoogleButtonClicked()

        clearInvocations(googleGamesService)

        // WHEN
        viewModel.onNavigatedBackFromSignInPage()

        // THEN
        verify(googleGamesService).signIn()
    }
    //</editor-fold>

    @Test
    fun `Should navigate back when back is pressed`() {
        // GIVEN
        setupSignInResults(signInFinished)

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onBackPressed()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should navigate back when the back button is clicked`() {
        // GIVEN
        setupSignInResults(signInFinished)

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onBackButtonClicked()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should navigate to the events page when the events button is clicked`() {
        // GIVEN
        setupSignInResults(signInFinished)

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onEventsButtonClicked()

        // THEN
        verify(navigateToEventsPageAction).invoke()
    }

    private fun createAndObserveViewModel(): OnlinePageViewModel {
        val viewModel = OnlinePageViewModel(googleGamesService)

        viewModel.showStatus.observeForever(
                LiveDataObserver(showStatusAction))
        viewModel.googleButtonText.observeForever(
                LiveDataObserver(googleButtonTextAction))
        viewModel.googleButtonEnabled.observeForever(
                LiveDataObserver(googleButtonEnabledAction))
        viewModel.eventsButtonEnabled.observeForever(
                LiveDataObserver(eventsButtonEnabledAction))
        viewModel.eventsButtonLoading.observeForever(
                LiveDataObserver(eventsButtonLoadingAction))
        viewModel.navigateBack.observeForever(
                LiveDataCommandObserver(navigateBackAction))
        viewModel.navigateToSignIn.observeForever(
                LiveDataEventObserver(navigateToSignInAction))
        viewModel.navigateToEventsPage.observeForever(
                LiveDataCommandObserver(navigateToEventsPageAction))

        return viewModel
    }

    private fun setupSignInIntent(signInIntent: Intent) =
            whenever(googleGamesService.loadSignInIntent()) doReturn signInIntent

    private fun setupSignInResults(vararg signInResults: SignInResult) = runBlocking {
        whenever(googleGamesService.signIn()) doReturnConsecutively signInResults.toList()
    }
}