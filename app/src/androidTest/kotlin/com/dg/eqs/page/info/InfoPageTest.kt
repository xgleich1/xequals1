package com.dg.eqs.page.info

import androidx.test.espresso.Espresso.pressBack
import org.junit.Test


class InfoPageTest {
    @Test
    fun should_initially_show_the_how_to_info_title() {
        // GIVEN
        InfoPageRobot().launch()

        // THEN
        InfoPageRobot().isHowToInfoTitleVisible()
    }

    @Test
    fun should_initially_show_the_how_to_info_content() {
        // GIVEN
        InfoPageRobot().launch()

        // THEN
        InfoPageRobot().isHowToInfoContentVisible()
    }

    @Test
    fun should_hide_the_menu_overlay_when_its_shown_and_back_is_pressed() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        pressBack()

        // THEN
        InfoPageRobot().isMenuOverlayHidden()
    }

    @Test
    fun should_hide_the_menu_overlay_when_its_shown_and_the_back_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickBackButton()

        // THEN
        InfoPageRobot().isMenuOverlayHidden()
    }

    @Test
    fun should_show_the_menu_overlay_when_its_hidden_and_the_menu_button_is_clicked() {
        // WHEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // THEN
        InfoPageRobot().isMenuOverlayVisible()
    }

    @Test
    fun should_hide_the_menu_overlay_when_its_shown_and_the_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickMenuButton()

        // THEN
        InfoPageRobot().isMenuOverlayHidden()
    }

    @Test
    fun should_hide_the_menu_overlay_when_its_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickMenuOverlay()

        // THEN
        InfoPageRobot().isMenuOverlayHidden()
    }

    @Test
    fun should_show_the_how_to_info_title_when_the_how_to_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()
        InfoPageRobot().clickEquationMenuButton()
        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickHowToMenuButton()

        // THEN
        InfoPageRobot().isHowToInfoTitleVisible()
    }

    @Test
    fun should_show_the_how_to_info_content_when_the_how_to_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()
        InfoPageRobot().clickEquationMenuButton()
        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickHowToMenuButton()

        // THEN
        InfoPageRobot().isHowToInfoContentVisible()
    }

    @Test
    fun should_show_the_equation_info_title_when_the_equation_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickEquationMenuButton()

        // THEN
        InfoPageRobot().isEquationInfoTitleVisible()
    }

    @Test
    fun should_show_the_equation_info_content_when_the_equation_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickEquationMenuButton()

        // THEN
        InfoPageRobot().isEquationInfoContentVisible()
    }

    @Test
    fun should_show_the_order_of_operations_info_title_when_the_order_of_operations_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickOrderOfOperationsMenuButton()

        // THEN
        InfoPageRobot().isOrderOfOperationsInfoTitleVisible()
    }

    @Test
    fun should_show_the_order_of_operations_info_content_when_the_order_of_operations_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickOrderOfOperationsMenuButton()

        // THEN
        InfoPageRobot().isOrderOfOperationsInfoContentVisible()
    }

    @Test
    fun should_show_the_addition_info_title_when_the_addition_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickAdditionMenuButton()

        // THEN
        InfoPageRobot().isAdditionInfoTitleVisible()
    }

    @Test
    fun should_show_the_addition_info_content_when_the_addition_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickAdditionMenuButton()

        // THEN
        InfoPageRobot().isAdditionInfoContentVisible()
    }

    @Test
    fun should_show_the_subtraction_info_title_when_the_subtraction_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickSubtractionMenuButton()

        // THEN
        InfoPageRobot().isSubtractionInfoTitleVisible()
    }

    @Test
    fun should_show_the_subtraction_info_content_when_the_subtraction_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickSubtractionMenuButton()

        // THEN
        InfoPageRobot().isSubtractionInfoContentVisible()
    }

    @Test
    fun should_show_the_multiplication_info_title_when_the_multiplication_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickMultiplicationMenuButton()

        // THEN
        InfoPageRobot().isMultiplicationInfoTitleVisible()
    }

    @Test
    fun should_show_the_multiplication_info_content_when_the_multiplication_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickMultiplicationMenuButton()

        // THEN
        InfoPageRobot().isMultiplicationInfoContentVisible()
    }

    @Test
    fun should_show_the_division_info_title_when_the_division_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickDivisionMenuButton()

        // THEN
        InfoPageRobot().isDivisionInfoTitleVisible()
    }

    @Test
    fun should_show_the_division_info_content_when_the_division_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickDivisionMenuButton()

        // THEN
        InfoPageRobot().isDivisionInfoContentVisible()
    }

    @Test
    fun should_show_the_reduce_info_title_when_the_reduce_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickReduceMenuButton()

        // THEN
        InfoPageRobot().isReduceInfoTitleVisible()
    }

    @Test
    fun should_show_the_reduce_info_content_when_the_reduce_menu_button_is_clicked() {
        // GIVEN
        InfoPageRobot().launch()

        InfoPageRobot().clickMenuButton()

        // WHEN
        InfoPageRobot().clickReduceMenuButton()

        // THEN
        InfoPageRobot().isReduceInfoContentVisible()
    }
}