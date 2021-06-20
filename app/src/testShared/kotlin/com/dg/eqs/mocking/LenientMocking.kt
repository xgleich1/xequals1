package com.dg.eqs.mocking

import org.mockito.kotlin.KStubbing
import org.mockito.kotlin.mock


inline fun <reified T : Any> lenientMock() =
        mock<T>(lenient = true)

inline fun <reified T : Any> lenientMock(stubbing: KStubbing<T>.(T) -> Unit) =
        mock(lenient = true, stubbing = stubbing)