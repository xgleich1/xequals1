package com.dg.eqs.page.start

import com.dg.eqs.page.assist.AssistPageRobot
import com.dg.eqs.page.feedback.FeedbackPageRobot
import com.dg.eqs.page.info.InfoPageRobot
import com.dg.eqs.page.offline.OfflinePageRobot
import com.dg.eqs.page.online.OnlinePageRobot
import org.junit.Before
import org.junit.Test


class StartPageTest {
    @Before
    fun setUp() {
        StartPageRobot().launch()
    }

    @Test
    fun should_navigate_to_the_offline_page_when_the_offline_button_is_clicked() {
        // WHEN
        StartPageRobot().clickOfflineButton()

        // THEN
        OfflinePageRobot().isVisible()
    }

    @Test
    fun should_navigate_to_the_online_page_when_the_online_button_is_clicked() {
        // WHEN
        StartPageRobot().clickOnlineButton()

        // THEN
        OnlinePageRobot().isVisible()
    }

    @Test
    fun should_navigate_to_the_info_page_when_the_info_button_is_clicked() {
        // WHEN
        StartPageRobot().clickInfoButton()

        // THEN
        InfoPageRobot().isVisible()
    }

    @Test
    fun should_navigate_to_the_assist_page_when_the_assist_button_is_clicked() {
        // WHEN
        StartPageRobot().clickAssistButton()

        // THEN
        AssistPageRobot().isVisible()
    }

    @Test
    fun should_navigate_to_the_feedback_page_when_the_feedback_button_is_clicked() {
        // WHEN
        StartPageRobot().clickFeedbackButton()

        // THEN
        FeedbackPageRobot().isVisible()
    }
}