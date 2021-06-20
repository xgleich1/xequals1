package com.dg.eqs.page.level

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dg.eqs.R
import org.hamcrest.Matchers.not


class LevelPageRobot {
    fun launch() = also {
        launch<LevelPage>(LevelPage
                .createIntent(getApplicationContext()))
    }

    fun isVisible() = also {
        onLevelPage().check(matches(isDisplayed()))
    }

    fun isHintVisible() = also {
        onLevelHint().check(matches(isDisplayed()))
    }

    fun isHintHidden() = also {
        onLevelHint().check(matches(not(isDisplayed())))
    }

    fun isFirstLevelFinishedInOneStepTextVisible() = also {
        onSteps().check(matches(withText(R.string.level_item_one_step)))
    }

    fun clickBackButton() = also {
        onBackButton().perform(click())
    }

    fun clickFirstLevel() = also {
        onLevelItem().perform(click())
    }

    private fun onLevelPage() = onView(withId(R.id.levelPage))

    private fun onBackButton() = onView(withId(R.id.backButton))

    private fun onLevelHint() = onView(withId(R.id.levelHint))

    private fun onLevelItem() = onView(withId(R.id.levelItem))

    private fun onSteps() = onView(withId(R.id.steps))
}