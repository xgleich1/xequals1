package com.dg.eqs.base.injection.module

import android.app.Application
import android.content.res.Resources
import com.dg.eqs.base.concurrency.BackgroundExecutor
import com.dg.eqs.base.concurrency.BackgroundExecutorImpl
import com.dg.eqs.base.gamification.GoogleGames
import com.dg.eqs.base.gamification.GoogleGamesService
import com.dg.eqs.base.gamification.GoogleSignIn
import com.dg.eqs.base.gamification.GoogleTracking
import com.dg.eqs.base.injection.scope.ApplicationScope
import com.dg.eqs.base.lifecycle.ApplicationStartHook
import com.dg.eqs.base.persistence.offline.OfflinePersistence
import com.dg.eqs.base.persistence.offline.sharedpreferences.SharedPreferencesBuilder
import com.dg.eqs.base.persistence.offline.sharedpreferences.SharedPreferencesBuilderImpl
import com.dg.eqs.base.purchasing.BillingClientBuilder
import com.dg.eqs.base.purchasing.BillingParamsBuilder
import com.dg.eqs.base.purchasing.BillingResultTracking
import com.dg.eqs.base.purchasing.BillingService
import com.dg.eqs.base.tracking.Tracking
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides


@Module
open class ApplicationModule(private val application: Application) {
    @Provides
    @ApplicationScope
    open fun provideApplication(): Application {
        return application
    }

    @Provides
    @ApplicationScope
    open fun provideResources(application: Application): Resources {
        return application.resources
    }

    @Provides
    @ApplicationScope
    open fun provideOfflinePersistence(
            sharedPreferencesBuilder: SharedPreferencesBuilder): OfflinePersistence {

        return OfflinePersistence(sharedPreferencesBuilder)
    }

    @Provides
    @ApplicationScope
    open fun provideSharedPreferencesBuilder(application: Application): SharedPreferencesBuilder {
        return SharedPreferencesBuilderImpl(application)
    }

    @Provides
    @ApplicationScope
    open fun provideBackgroundExecutor(): BackgroundExecutor {
        return BackgroundExecutorImpl()
    }

    @Provides
    @ApplicationScope
    open fun provideApplicationStartHook(billingService: BillingService): ApplicationStartHook {
        return ApplicationStartHook(billingService)
    }

    @Provides
    @ApplicationScope
    open fun provideTracking(firebaseAnalytics: FirebaseAnalytics): Tracking {
        return Tracking(firebaseAnalytics)
    }

    @Provides
    @ApplicationScope
    open fun provideFirebaseAnalytics(application: Application): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(application)
    }

    @Provides
    @ApplicationScope
    open fun provideBillingService(
            billingClientBuilder: BillingClientBuilder,
            billingParamsBuilder: BillingParamsBuilder,
            billingResultTracking: BillingResultTracking): BillingService {

        return BillingService(
                billingClientBuilder,
                billingParamsBuilder,
                billingResultTracking)
    }

    @Provides
    @ApplicationScope
    open fun provideBillingClientBuilder(application: Application): BillingClientBuilder {
        return BillingClientBuilder(application)
    }

    @Provides
    @ApplicationScope
    open fun provideBillingParamsBuilder(resources: Resources): BillingParamsBuilder {
        return BillingParamsBuilder(resources)
    }

    @Provides
    @ApplicationScope
    open fun provideBillingResultTracking(tracking: Tracking): BillingResultTracking {
        return BillingResultTracking(tracking)
    }

    @Provides
    @ApplicationScope
    open fun provideGoogleGamesService(
            googleGames: GoogleGames,
            googleSignIn: GoogleSignIn,
            googleTracking: GoogleTracking): GoogleGamesService {

        return GoogleGamesService(
                googleGames,
                googleSignIn,
                googleTracking)
    }

    @Provides
    @ApplicationScope
    open fun provideGoogleGames(application: Application): GoogleGames {
        return GoogleGames(application)
    }

    @Provides
    @ApplicationScope
    open fun provideGoogleSignIn(application: Application): GoogleSignIn {
        return GoogleSignIn(application)
    }

    @Provides
    @ApplicationScope
    open fun provideGoogleTracking(tracking: Tracking): GoogleTracking {
        return GoogleTracking(tracking)
    }
}