package com.dg.eqs.page.feedback

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dg.eqs.base.observation.LiveDataCommand
import com.dg.eqs.base.observation.emit


class FeedbackPageViewModel : ViewModel() {
    val navigateBack = MutableLiveData<LiveDataCommand>()


    fun onBackPressed() = emitNavigateBack()

    fun onBackButtonClicked() = emitNavigateBack()

    private fun emitNavigateBack() = navigateBack.emit()
}