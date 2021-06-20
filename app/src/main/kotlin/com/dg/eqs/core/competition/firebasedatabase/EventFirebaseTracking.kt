package com.dg.eqs.core.competition.firebasedatabase

import com.dg.eqs.base.tracking.Tracking
import com.google.firebase.database.DatabaseError


class EventFirebaseTracking(private val tracking: Tracking) {
    fun trackEntitiesMissing() = tracking
            .trackEvent("event_firebase_entities_missing")

    fun trackDatabaseError(error: DatabaseError) = tracking
            .trackEvent("event_firebase_database_error_${error.code}")
}