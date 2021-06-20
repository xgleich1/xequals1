package com.dg.eqs.page.info

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dg.eqs.R
import org.hamcrest.Matchers.not


class InfoPageRobot {
    fun launch() = also {
        launch<InfoPage>(InfoPage
                .createIntent(getApplicationContext()))
    }

    fun isVisible() = also {
        onInfoPage().check(matches(isDisplayed()))
    }

    fun isHowToInfoTitleVisible() = also {
        onInfoTitle().check(matches(withText(R.string.info_how_to_title)))
    }

    fun isEquationInfoTitleVisible() = also {
        onInfoTitle().check(matches(withText(R.string.info_equation_title)))
    }

    fun isOrderOfOperationsInfoTitleVisible() = also {
        onInfoTitle().check(matches(withText(R.string.info_order_of_operations_title)))
    }

    fun isAdditionInfoTitleVisible() = also {
        onInfoTitle().check(matches(withText(R.string.info_addition_title)))
    }

    fun isSubtractionInfoTitleVisible() = also {
        onInfoTitle().check(matches(withText(R.string.info_subtraction_title)))
    }

    fun isMultiplicationInfoTitleVisible() = also {
        onInfoTitle().check(matches(withText(R.string.info_multiplication_title)))
    }

    fun isDivisionInfoTitleVisible() = also {
        onInfoTitle().check(matches(withText(R.string.info_division_title)))
    }

    fun isReduceInfoTitleVisible() = also {
        onInfoTitle().check(matches(withText(R.string.info_reduce_title)))
    }

    fun isHowToInfoContentVisible() = also {
        onHowToInfoContent().check(matches(isDisplayed()))
    }

    fun isEquationInfoContentVisible() = also {
        onEquationInfoContent().check(matches(isDisplayed()))
    }

    fun isOrderOfOperationsInfoContentVisible() = also {
        onOrderOfOperationsInfoContent().check(matches(isDisplayed()))
    }

    fun isAdditionInfoContentVisible() = also {
        onAdditionInfoContent().check(matches(isDisplayed()))
    }

    fun isSubtractionInfoContentVisible() = also {
        onSubtractionInfoContent().check(matches(isDisplayed()))
    }

    fun isMultiplicationInfoContentVisible() = also {
        onMultiplicationInfoContent().check(matches(isDisplayed()))
    }

    fun isDivisionInfoContentVisible() = also {
        onDivisionInfoContent().check(matches(isDisplayed()))
    }

    fun isReduceInfoContentVisible() = also {
        onReduceInfoContent().check(matches(isDisplayed()))
    }

    fun isMenuOverlayVisible() = also {
        onMenuOverlay().check(matches(isDisplayed()))
    }

    fun isMenuOverlayHidden() = also {
        onMenuOverlay().check(matches(not(isDisplayed())))
    }

    fun clickBackButton() = also {
        onBackButton().perform(click())
    }

    fun clickMenuButton() = also {
        onMenuButton().perform(click())
    }

    fun clickMenuOverlay() = also {
        onMenuOverlay().perform(click())
    }

    fun clickHowToMenuButton() = also {
        onHowToMenuButton().perform(click())
    }

    fun clickEquationMenuButton() = also {
        onEquationMenuButton().perform(click())
    }

    fun clickOrderOfOperationsMenuButton() = also {
        onOrderOfOperationsMenuButton().perform(click())
    }

    fun clickAdditionMenuButton() = also {
        onAdditionMenuButton().perform(click())
    }

    fun clickSubtractionMenuButton() = also {
        onSubtractionMenuButton().perform(click())
    }

    fun clickMultiplicationMenuButton() = also {
        onMultiplicationMenuButton().perform(click())
    }

    fun clickDivisionMenuButton() = also {
        onDivisionMenuButton().perform(click())
    }

    fun clickReduceMenuButton() = also {
        onReduceMenuButton().perform(click())
    }

    private fun onInfoPage() = onView(withId(R.id.infoPage))

    private fun onInfoTitle() = onView(withId(R.id.infoTitle))

    private fun onHowToInfoContent() = onView(withId(R.id.infoHowTo))

    private fun onEquationInfoContent() = onView(withId(R.id.infoEquation))

    private fun onOrderOfOperationsInfoContent() = onView(withId(R.id.infoOrderOfOperations))

    private fun onAdditionInfoContent() = onView(withId(R.id.infoAddition))

    private fun onSubtractionInfoContent() = onView(withId(R.id.infoSubtraction))

    private fun onMultiplicationInfoContent() = onView(withId(R.id.infoMultiplication))

    private fun onDivisionInfoContent() = onView(withId(R.id.infoDivision))

    private fun onReduceInfoContent() = onView(withId(R.id.infoReduce))

    private fun onBackButton() = onView(withId(R.id.backButton))

    private fun onMenuButton() = onView(withId(R.id.menuButton))

    private fun onMenuOverlay() = onView(withId(R.id.menuOverlay))

    private fun onHowToMenuButton() = onView(withId(R.id.howToMenuButton))

    private fun onEquationMenuButton() = onView(withId(R.id.equationMenuButton))

    private fun onOrderOfOperationsMenuButton() = onView(withId(R.id.orderOfOperationsMenuButton))

    private fun onAdditionMenuButton() = onView(withId(R.id.additionMenuButton))

    private fun onSubtractionMenuButton() = onView(withId(R.id.subtractionMenuButton))

    private fun onMultiplicationMenuButton() = onView(withId(R.id.multiplicationMenuButton))

    private fun onDivisionMenuButton() = onView(withId(R.id.divisionMenuButton))

    private fun onReduceMenuButton() = onView(withId(R.id.reduceMenuButton))
}