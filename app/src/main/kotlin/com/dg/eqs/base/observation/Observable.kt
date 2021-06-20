package com.dg.eqs.base.observation


class Observable<T> {
    private val observers = arrayListOf<T.() -> Unit>()


    fun emit(value: T) = observers.forEach {
        it(value)
    }

    fun addObserver(observer: T.() -> Unit) {
        observers += observer
    }

    fun removeObserver(observer: T.() -> Unit) {
        observers -= observer
    }
}