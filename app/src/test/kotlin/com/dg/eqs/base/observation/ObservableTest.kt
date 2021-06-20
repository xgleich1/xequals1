package com.dg.eqs.base.observation

import org.mockito.kotlin.any
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ObservableTest {
    @Mock
    private lateinit var observerA: Int.() -> Unit
    @Mock
    private lateinit var observerB: Int.() -> Unit

    private lateinit var observable: Observable<Int>


    @Before
    fun setUp() {
        observable = Observable()
    }

    @Test
    fun `Should emit a value to all observers`() {
        // GIVEN
        observable.addObserver(observerA)
        observable.addObserver(observerB)

        // WHEN
        observable.emit(1)

        // THEN
        verify(observerA).invoke(1)
        verify(observerB).invoke(1)
    }

    @Test
    fun `Should not emit anything to a observer which was removed`() {
        // GIVEN
        observable.addObserver(observerA)

        observable.removeObserver(observerA)

        // WHEN
        observable.emit(1)

        // THEN
        verify(observerA, never()).invoke(any())
    }

    @Test
    fun `Should still emit a value to observers which were not removed`() {
        // GIVEN
        observable.addObserver(observerA)
        observable.addObserver(observerB)

        observable.removeObserver(observerA)

        // WHEN
        observable.emit(1)

        // THEN
        verify(observerB).invoke(1)
    }
}