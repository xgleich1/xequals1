package com.dg.eqs.page.offline

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dg.eqs.R
import org.hamcrest.Matchers.startsWith


class OfflinePageRobot {
    fun launch() = also {
        launch<OfflinePage>(OfflinePage
                .createIntent(getApplicationContext()))
    }

    fun isVisible() = also {
        onOfflinePage().check(matches(isDisplayed()))
    }

    fun isNoEquationsSolvedMessageVisible() = also {
        onNoEquationsSolvedMessage().check(matches(isDisplayed()))
    }

    fun isOneEquationSolvedMessageVisible() = also {
        onOneEquationSolvedMessage().check(matches(isDisplayed()))
    }

    fun clickLevelButton() = also {
        onLevelButton().perform(click())
    }

    fun clickPlayButton() = also {
        onPlayButton().perform(click())
    }

    private fun onOfflinePage() = onView(withId(R.id.offlinePage))

    private fun onNoEquationsSolvedMessage() = onView(withText(startsWith("0")))

    private fun onOneEquationSolvedMessage() = onView(withText(startsWith("1")))

    private fun onLevelButton() = onView(withId(R.id.levelButton))

    private fun onPlayButton() = onView(withId(R.id.playButton))
}