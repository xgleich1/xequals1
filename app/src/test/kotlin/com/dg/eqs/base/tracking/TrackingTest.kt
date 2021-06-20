package com.dg.eqs.base.tracking

import com.google.firebase.analytics.FirebaseAnalytics
import org.mockito.kotlin.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TrackingTest {
    @Mock
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    @InjectMocks
    private lateinit var tracking: Tracking


    @Test
    fun `Should track an event by logging it`() {
        // WHEN
        tracking.trackEvent("test_event")

        // THEN
        verify(firebaseAnalytics).logEvent("test_event", null)
    }

    @Test
    fun `Should trim an event before logging it`() {
        // WHEN
        tracking.trackEvent(" test_event ")

        // THEN
        verify(firebaseAnalytics).logEvent("test_event", null)
    }

    @Test
    fun `Should limit an event before logging it`() {
        // WHEN
        tracking.trackEvent(
                "test_event_with_more_than_forty_characters")

        // THEN
        verify(firebaseAnalytics).logEvent(
                "test_event_with_more_than_forty_characte", null)
    }
}