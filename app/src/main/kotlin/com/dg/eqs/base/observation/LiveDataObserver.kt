package com.dg.eqs.base.observation

import androidx.lifecycle.Observer


class LiveDataObserver<T>(
        private val recipient: (T) -> Unit) : Observer<T> {

    override fun onChanged(value: T) = recipient(value)
}