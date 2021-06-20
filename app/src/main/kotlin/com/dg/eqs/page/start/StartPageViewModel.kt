package com.dg.eqs.page.start

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dg.eqs.base.observation.LiveDataCommand
import com.dg.eqs.base.observation.emit


class StartPageViewModel : ViewModel() {
    val navigateToOfflinePage = MutableLiveData<LiveDataCommand>()
    val navigateToOnlinePage = MutableLiveData<LiveDataCommand>()
    val navigateToInfoPage = MutableLiveData<LiveDataCommand>()
    val navigateToAssistPage = MutableLiveData<LiveDataCommand>()
    val navigateToFeedbackPage = MutableLiveData<LiveDataCommand>()


    fun onOfflineButtonClicked() = navigateToOfflinePage.emit()

    fun onOnlineButtonClicked() = navigateToOnlinePage.emit()

    fun onInfoButtonClicked() = navigateToInfoPage.emit()

    fun onAssistButtonClicked() = navigateToAssistPage.emit()

    fun onFeedbackButtonClicked() = navigateToFeedbackPage.emit()
}