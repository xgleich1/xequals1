package com.dg.eqs.page.feedback

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dg.eqs.R


class FeedbackPageRobot {
    fun launch() = also {
        launch<FeedbackPage>(FeedbackPage
                .createIntent(getApplicationContext()))
    }

    fun isVisible() = also {
        onFeedbackPage().check(matches(isDisplayed()))
    }

    private fun onFeedbackPage() = onView(withId(R.id.feedbackPage))
}