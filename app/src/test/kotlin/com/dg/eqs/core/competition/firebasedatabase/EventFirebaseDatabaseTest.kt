package com.dg.eqs.core.competition.firebasedatabase

import com.google.firebase.database.*
import org.mockito.kotlin.*
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EventFirebaseDatabaseTest {
    @Mock
    private lateinit var firebaseDatabase: FirebaseDatabase
    @Mock
    private lateinit var eventFirebaseTracking: EventFirebaseTracking
    @InjectMocks
    private lateinit var eventFirebaseDatabase: EventFirebaseDatabase

    @Mock
    private lateinit var databaseReference: DatabaseReference
    @Mock
    private lateinit var dataSnapshot: DataSnapshot
    @Mock
    private lateinit var databaseError: DatabaseError

    @Mock
    private lateinit var entityA: EventFirebaseEntity
    @Mock
    private lateinit var entityB: EventFirebaseEntity


    @Before
    fun setUp() {
        whenever(firebaseDatabase
                .getReference(any())) doReturn databaseReference
    }

    @Test
    fun `Should return all entities when some are received`() = runBlocking<Unit> {
        // GIVEN
        setupEntitiesReceived(listOf(entityA, entityB))

        // WHEN
        val entities = eventFirebaseDatabase.loadEntities()

        // THEN
        assertThat(entities).containsExactly(entityA, entityB)
    }

    @Test
    fun `Should return zero entities when zero are received`() = runBlocking {
        // GIVEN
        setupEntitiesReceived(emptyList())

        // WHEN
        val entities = eventFirebaseDatabase.loadEntities()

        // THEN
        assertThat(entities).isEmpty()
    }

    @Test
    fun `Should return zero entities when none are received`() = runBlocking {
        // GIVEN
        setupEntitiesReceived(null)

        // WHEN
        val entities = eventFirebaseDatabase.loadEntities()

        // THEN
        assertThat(entities).isEmpty()
    }

    @Test
    fun `Should track when zero entities are received`() = runBlocking {
        // GIVEN
        setupEntitiesReceived(emptyList())

        // WHEN
        eventFirebaseDatabase.loadEntities()

        // THEN
        verify(eventFirebaseTracking).trackEntitiesMissing()
    }

    @Test
    fun `Should track when no entities are received`() = runBlocking {
        // GIVEN
        setupEntitiesReceived(null)

        // WHEN
        eventFirebaseDatabase.loadEntities()

        // THEN
        verify(eventFirebaseTracking).trackEntitiesMissing()
    }

    @Test
    fun `Should not track anything when some entities are received`() = runBlocking {
        // GIVEN
        setupEntitiesReceived(listOf(entityA, entityB))

        // WHEN
        eventFirebaseDatabase.loadEntities()

        // THEN
        verifyZeroInteractions(eventFirebaseTracking)
    }

    @Test
    fun `Should return zero entities when an database error occurred`() = runBlocking {
        // GIVEN
        setupDatabaseErrorOccurred()

        // WHEN
        val entities = eventFirebaseDatabase.loadEntities()

        // THEN
        assertThat(entities).isEmpty()
    }

    @Test
    fun `Should track the database error when one occurred`() = runBlocking {
        // GIVEN
        setupDatabaseErrorOccurred()

        // WHEN
        eventFirebaseDatabase.loadEntities()

        // THEN
        verify(eventFirebaseTracking).trackDatabaseError(databaseError)
    }

    private fun setupEntitiesReceived(entities: List<EventFirebaseEntity>?) =
            argumentCaptor<ValueEventListener>().apply {
                whenever(dataSnapshot.getValue(
                        any<GenericTypeIndicator<List<EventFirebaseEntity>>>())) doReturn entities

                whenever(databaseReference.addListenerForSingleValueEvent(capture())) doAnswer {
                    lastValue.onDataChange(dataSnapshot)
                }
            }

    private fun setupDatabaseErrorOccurred() =
            argumentCaptor<ValueEventListener>().apply {
                whenever(databaseReference.addListenerForSingleValueEvent(capture())) doAnswer {
                    lastValue.onCancelled(databaseError)
                }
            }
}