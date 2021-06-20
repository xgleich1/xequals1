package com.dg.eqs.page.online

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dg.eqs.R


class OnlinePageRobot {
    fun launch() = also {
        launch<OnlinePage>(OnlinePage
                .createIntent(getApplicationContext()))
    }

    fun isVisible() = also {
        onOnlinePage().check(matches(isDisplayed()))
    }

    fun isSignInButtonVisible() = also {
        onSignInButton().check(matches(isDisplayed()))
    }

    fun isSignOutButtonVisible() = also {
        onSignOutButton().check(matches(isDisplayed()))
    }

    fun clickEventsButton() = also {
        onEventsButton().perform(click())
    }

    private fun onOnlinePage() = onView(withId(R.id.onlinePage))

    private fun onSignInButton() = onView(withText(R.string.online_button_sign_in))

    private fun onSignOutButton() = onView(withText(R.string.online_button_sign_out))

    private fun onEventsButton() = onView(withId(R.id.eventsButton))
}