package com.dg.eqs.base.injection.module

import com.dg.eqs.base.gamification.GoogleGamesService
import com.dg.eqs.base.injection.scope.ApplicationScope
import com.dg.eqs.base.tracking.Tracking
import com.dg.eqs.core.competition.EventRepository
import com.dg.eqs.core.competition.firebasedatabase.EventFirebaseDatabase
import com.dg.eqs.core.competition.firebasedatabase.EventFirebaseTracking
import com.dg.eqs.core.mapping.StringToOriginMapper
import com.dg.eqs.core.progression.eventlevel.EventLevelDatabase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides


@Module
open class CompetitionModule {
    @Provides
    @ApplicationScope
    open fun provideEventRepository(
            eventFirebaseDatabase: EventFirebaseDatabase,
            stringToOriginMapper: StringToOriginMapper,
            googleGamesService: GoogleGamesService,
            eventLevelDatabase: EventLevelDatabase
    ): EventRepository {

        return EventRepository(
                eventFirebaseDatabase,
                stringToOriginMapper,
                googleGamesService,
                eventLevelDatabase)
    }

    @Provides
    @ApplicationScope
    open fun provideEventFirebaseDatabase(
            firebaseDatabase: FirebaseDatabase,
            eventFirebaseTracking: EventFirebaseTracking
    ): EventFirebaseDatabase {

        return EventFirebaseDatabase(
                firebaseDatabase,
                eventFirebaseTracking)
    }

    @Provides
    @ApplicationScope
    open fun provideFirebaseDatabase(): FirebaseDatabase {
        return Firebase.database
    }

    @Provides
    @ApplicationScope
    open fun provideEventFirebaseTracking(tracking: Tracking): EventFirebaseTracking {
        return EventFirebaseTracking(tracking)
    }
}