package com.dg.eqs.page.game

import android.view.View
import androidx.core.view.children
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press.FINGER
import androidx.test.espresso.action.Swipe.FAST
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dg.eqs.R
import com.dg.eqs.base.extension.halfHeight
import com.dg.eqs.base.extension.halfWidth
import com.dg.eqs.core.visualization.symbolization.SymbolView


class GamePageRobot {
    fun isVisible() = also {
        onGamePage().check(matches(isDisplayed()))
    }

    fun clickBackButton() = also {
        onBackButton().perform(click())
    }

    fun clickFinishedIcon() = also {
        onFinishedIcon().perform(click())
    }

    fun linkSymbols(
            sourceSymbolText: String,
            targetSymbolText: String) = also {

        onGameGrid().perform(object : ViewAction {
            override fun getConstraints() = isAssignableFrom(GameGrid::class.java)

            override fun getDescription() = "Link $sourceSymbolText and $targetSymbolText"

            override fun perform(uiController: UiController, view: View) {
                val gameGrid = view as GameGrid

                val sourceSymbol = gameGrid.children.find {
                    it is SymbolView && it.text == sourceSymbolText
                }
                val targetSymbol = gameGrid.children.find {
                    it is SymbolView && it.text == targetSymbolText
                }

                requireNotNull(sourceSymbol)
                requireNotNull(targetSymbol)

                val sourceCoordinatesProvider = CoordinatesProvider {
                    val coordinates = IntArray(2)

                    sourceSymbol.getLocationOnScreen(coordinates)

                    floatArrayOf(
                            (coordinates[0] + sourceSymbol.halfWidth).toFloat(),
                            (coordinates[1] + sourceSymbol.halfHeight).toFloat())
                }
                val targetCoordinatesProvider = CoordinatesProvider {
                    val coordinates = IntArray(2)

                    targetSymbol.getLocationOnScreen(coordinates)

                    floatArrayOf(
                            (coordinates[0] + sourceSymbol.halfWidth).toFloat(),
                            (coordinates[1] + sourceSymbol.halfHeight).toFloat())
                }

                val swipe = GeneralSwipeAction(
                        FAST,
                        sourceCoordinatesProvider,
                        targetCoordinatesProvider,
                        FINGER)

                swipe.perform(uiController, gameGrid)
            }
        })
    }

    private fun onGamePage() = onView(withId(R.id.gamePage))

    private fun onBackButton() = onView(withId(R.id.backButton))

    private fun onFinishedIcon() = onView(withId(R.id.finishedIcon))

    private fun onGameGrid() = onView(withId(R.id.gameGrid))
}