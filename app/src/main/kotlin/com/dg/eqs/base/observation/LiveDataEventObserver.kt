package com.dg.eqs.base.observation

import androidx.lifecycle.Observer


class LiveDataEventObserver<T : Any>(
        private val recipient: (T) -> Unit) : Observer<LiveDataEvent<T>> {

    override fun onChanged(liveDataEvent: LiveDataEvent<T>) {
        liveDataEvent.getContentOnce()?.let(recipient)
    }
}