package com.dg.eqs.core.competition.firebasedatabase

import com.dg.eqs.base.tracking.Tracking
import com.google.firebase.database.DatabaseError
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EventFirebaseTrackingTest {
    @Mock
    private lateinit var tracking: Tracking
    @InjectMocks
    private lateinit var eventFirebaseTracking: EventFirebaseTracking


    @Test
    fun `Should track an event when entities are missing`() {
        // WHEN
        eventFirebaseTracking.trackEntitiesMissing()

        // THEN
        verify(tracking).trackEvent(
                "event_firebase_entities_missing")
    }

    @Test
    fun `Should track an event when there's a database error`() {
        // GIVEN
        val databaseError: DatabaseError = mock {
            on { code } doReturn 404
        }

        // WHEN
        eventFirebaseTracking.trackDatabaseError(databaseError)

        // THEN
        verify(tracking).trackEvent(
                "event_firebase_database_error_404")
    }
}