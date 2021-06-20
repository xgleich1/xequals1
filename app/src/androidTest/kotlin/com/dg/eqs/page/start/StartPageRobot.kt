package com.dg.eqs.page.start

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dg.eqs.R


class StartPageRobot {
    fun launch() = also {
        launch(StartPage::class.java)
    }

    fun isVisible() = also {
        onStartPage().check(matches(isDisplayed()))
    }

    fun clickOfflineButton() = also {
        onOfflineButton().perform(click())
    }

    fun clickOnlineButton() = also {
        onOnlineButton().perform(click())
    }

    fun clickInfoButton() = also {
        onInfoButton().perform(click())
    }

    fun clickAssistButton() = also {
        onAssistButton().perform(click())
    }

    fun clickFeedbackButton() = also {
        onFeedbackButton().perform(click())
    }

    private fun onStartPage() = onView(withId(R.id.startPage))

    private fun onOfflineButton() = onView(withId(R.id.offlineButton))

    private fun onOnlineButton() = onView(withId(R.id.onlineButton))

    private fun onInfoButton() = onView(withId(R.id.infoButton))

    private fun onAssistButton() = onView(withId(R.id.assistButton))

    private fun onFeedbackButton() = onView(withId(R.id.feedbackButton))
}