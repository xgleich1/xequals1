package com.dg.eqs.page.online

import com.dg.eqs.base.gamification.GoogleGamesService
import com.dg.eqs.base.gamification.result.SignInResult.SignInFinished
import com.dg.eqs.base.gamification.result.SignInResult.SignInNecessary
import com.dg.eqs.page.events.EventsPageRobot
import com.dg.eqs.util.dagger.TestDaggerMockRule
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock


class OnlinePageTest {
    @get:Rule
    val testDaggerMockRule = TestDaggerMockRule()

    @Mock
    private lateinit var googleGamesService: GoogleGamesService


    @Test
    fun should_show_the_sign_out_button_when_the_user_is_signed_in() {
        // GIVEN
        setupSignInFinished()

        OnlinePageRobot().launch()

        // THEN
        OnlinePageRobot().isSignOutButtonVisible()
    }

    @Test
    fun should_show_the_sign_in_button_when_the_user_is_signed_out() {
        // GIVEN
        setupSignInNecessary()

        OnlinePageRobot().launch()

        // THEN
        OnlinePageRobot().isSignInButtonVisible()
    }

    @Test
    fun should_navigate_to_the_events_page_using_the_events_button() {
        // GIVEN
        setupSignInFinished()

        OnlinePageRobot().launch()

        // WHEN
        OnlinePageRobot().clickEventsButton()

        // THEN
        EventsPageRobot().isVisible()
    }

    private fun setupSignInFinished() = runBlocking {
        whenever(googleGamesService.signIn()) doReturn SignInFinished(mock())
    }

    private fun setupSignInNecessary() = runBlocking {
        whenever(googleGamesService.signIn()) doReturn SignInNecessary()
    }
}