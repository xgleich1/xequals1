package com.dg.eqs.page.assist

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dg.eqs.R


class AssistPageRobot {
    fun launch() = also {
        launch<AssistPage>(AssistPage
                .createIntent(getApplicationContext()))
    }

    fun isVisible() = also {
        onAssistPage().check(matches(isDisplayed()))
    }

    private fun onAssistPage() = onView(withId(R.id.assistPage))
}