package com.dg.eqs.page.event

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dg.eqs.R
import com.dg.eqs.core.competition.Event


class EventPageRobot {
    fun launch(event: Event) = also {
        launch<EventPage>(EventPage
                .createIntent(getApplicationContext(), event))
    }

    fun isVisible() = also {
        onEventPage().check(matches(isDisplayed()))
    }

    fun clickBackButton() = also {
        onBackButton().perform(click())
    }

    fun clickPlayButton() = also {
        onPlayButton().perform(click())
    }

    private fun onEventPage() = onView(withId(R.id.eventPage))

    private fun onBackButton() = onView(withId(R.id.backButton))

    private fun onPlayButton() = onView(withId(R.id.playButton))
}