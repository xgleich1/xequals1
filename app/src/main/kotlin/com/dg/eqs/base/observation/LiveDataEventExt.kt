package com.dg.eqs.base.observation

import androidx.lifecycle.MutableLiveData


fun <T : Any> MutableLiveData<LiveDataEvent<T>>.emit(content: T) {
    value = LiveDataEvent(content)
}

fun <T : Any> MutableLiveData<LiveDataEvent<T>>.post(content: T) {
    postValue(LiveDataEvent(content))
}