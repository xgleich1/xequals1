package com.dg.eqs.core.interaction.linking.choosing.widening

import android.content.res.Resources
import com.dg.eqs.R


class WideningTiming(resources: Resources) {
    val delayInMs = resources.getInteger(R.integer.widening_delay_in_ms).toLong()
}