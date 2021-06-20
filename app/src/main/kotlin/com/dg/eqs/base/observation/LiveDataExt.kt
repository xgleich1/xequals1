package com.dg.eqs.base.observation

import androidx.lifecycle.MutableLiveData


fun <T : Any> MutableLiveData<T>.emit(value: T) {
    this.value = value
}

fun <T : Any> MutableLiveData<T>.post(value: T) {
    postValue(value)
}