package com.dg.eqs.base.observation

import androidx.lifecycle.MutableLiveData


fun MutableLiveData<LiveDataCommand>.emit() {
    value = LiveDataCommand()
}

fun MutableLiveData<LiveDataCommand>.post() {
    postValue(LiveDataCommand())
}