package com.dg.eqs.page.events

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.enveloping.HtmlRes


data class EventItem(
        val exercise: AnyOrigin,
        val playerScore: HtmlRes,
        val topScore: HtmlRes)