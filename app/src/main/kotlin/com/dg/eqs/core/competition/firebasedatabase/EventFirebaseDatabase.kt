package com.dg.eqs.core.competition.firebasedatabase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


class EventFirebaseDatabase(
        private val firebaseDatabase: FirebaseDatabase,
        private val eventFirebaseTracking: EventFirebaseTracking) {

    suspend fun loadEntities(): List<EventFirebaseEntity> =
            suspendCancellableCoroutine { continuation ->
                firebaseDatabase
                        .getReference(EVENTS_PATH)
                        .addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if(continuation.isActive) {
                                    val entities = snapshot
                                            .getValue<List<EventFirebaseEntity>>()
                                            .orEmpty()

                                    if(entities.isEmpty()) {
                                        eventFirebaseTracking
                                                .trackEntitiesMissing()
                                    }

                                    continuation.resume(entities)
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                if(continuation.isActive) {
                                    eventFirebaseTracking
                                            .trackDatabaseError(error)

                                    continuation.resume(emptyList())
                                }
                            }
                        })
            }

    private companion object {
        private const val EVENTS_PATH = "/eqs/events"
    }
}