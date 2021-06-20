package com.dg.eqs.page.events

import com.dg.eqs.R
import com.dg.eqs.base.enveloping.HtmlRes
import com.dg.eqs.core.progression.Level.EventLevel


class EventItemBuilder {
    fun buildEventItem(level: EventLevel) =
            EventItem(level.exercise, level.playerScore, level.topScore)

    private val EventLevel.playerScore
        get() = when {
            gameSteps == 0 ->
                HtmlRes(R.string.events_item_no_player_score)

            gameSteps > bestSteps ->
                HtmlRes(R.string.events_item_worse_player_score, gameSteps)

            else ->
                HtmlRes(R.string.events_item_better_player_score, gameSteps)
        }

    private val EventLevel.topScore
        get() = if(bestSteps == 0) {
            HtmlRes(R.string.events_item_no_top_score)
        } else {
            HtmlRes(R.string.events_item_set_top_score, bestSteps)
        }
}