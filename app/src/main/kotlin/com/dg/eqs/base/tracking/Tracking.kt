package com.dg.eqs.base.tracking

import androidx.annotation.Size
import com.google.firebase.analytics.FirebaseAnalytics


class Tracking(
        private val firebaseAnalytics: FirebaseAnalytics) {

    fun trackEvent(@Size(min = 1L, max = 40L) event: String) =
            firebaseAnalytics.logEvent(event.trim().take(40), null)
}