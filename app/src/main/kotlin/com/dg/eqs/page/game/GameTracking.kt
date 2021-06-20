package com.dg.eqs.page.game

import com.dg.eqs.base.tracking.Tracking


class GameTracking(private val tracking: Tracking) {
    fun onRevertButtonClicked() = tracking.trackEvent("revert_button_clicked")

    fun onInvertButtonClicked() = tracking.trackEvent("invert_button_clicked")

    fun onSkipButtonClicked() = tracking.trackEvent("skip_button_clicked")

    fun onFinishedIconClicked() = tracking.trackEvent("finished_icon_clicked")

    fun onInfoButtonClicked() = tracking.trackEvent("info_button_clicked")
}