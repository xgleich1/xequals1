package com.dg.eqs.page.events

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dg.eqs.R


class EventsPageRobot {
    fun launch() = also {
        launch<EventsPage>(EventsPage
                .createIntent(getApplicationContext()))
    }

    fun isVisible() = also {
        onEventsPage().check(matches(isDisplayed()))
    }

    fun isDownloadHintVisible() = also {
        onEventsHint().check(matches(withText(R.string.events_hint_download)))
    }

    fun isEmptyHintVisible() = also {
        onEventsHint().check(matches(withText(R.string.events_hint_empty)))
    }

    fun isFirstEventPlayerScoreVisible(playerScore: String) = also {
        onPlayerScore().check(matches(withText(playerScore)))
    }

    fun isFirstEventTopScoreVisible(topScore: String) = also {
        onTopScore().check(matches(withText(topScore)))
    }

    fun clickFirstEvent() = also {
        onEventItem().perform(click())
    }

    private fun onEventsPage() = onView(withId(R.id.eventsPage))

    private fun onEventsHint() = onView(withId(R.id.eventsHint))

    private fun onEventItem() = onView(withId(R.id.eventItem))

    private fun onPlayerScore() = onView(withId(R.id.playerScore))

    private fun onTopScore() = onView(withId(R.id.topScore))
}