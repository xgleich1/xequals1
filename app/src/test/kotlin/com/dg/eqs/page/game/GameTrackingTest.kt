package com.dg.eqs.page.game

import com.dg.eqs.base.tracking.Tracking
import org.mockito.kotlin.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GameTrackingTest {
    @Mock
    private lateinit var tracking: Tracking
    @InjectMocks
    private lateinit var gameTracking: GameTracking


    @Test
    fun `Should track an event when the revert button is clicked`() {
        // WHEN
        gameTracking.onRevertButtonClicked()

        // THEN
        verify(tracking).trackEvent("revert_button_clicked")
    }

    @Test
    fun `Should track an event when the invert button is clicked`() {
        // WHEN
        gameTracking.onInvertButtonClicked()

        // THEN
        verify(tracking).trackEvent("invert_button_clicked")
    }

    @Test
    fun `Should track an event when the skip button is clicked`() {
        // WHEN
        gameTracking.onSkipButtonClicked()

        // THEN
        verify(tracking).trackEvent("skip_button_clicked")
    }

    @Test
    fun `Should track an event when the finished icon is clicked`() {
        // WHEN
        gameTracking.onFinishedIconClicked()

        // THEN
        verify(tracking).trackEvent("finished_icon_clicked")
    }

    @Test
    fun `Should track an event when the info button is clicked`() {
        // WHEN
        gameTracking.onInfoButtonClicked()

        // THEN
        verify(tracking).trackEvent("info_button_clicked")
    }
}