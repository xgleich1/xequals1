package com.dg.eqs.classes

import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.core.information.Info
import org.mockito.kotlin.mock


fun info(
        content: LayoutRes = mock(),
        isVital: Boolean = false,
        isValid: Boolean = false): Info =
        TestInfo(
                content,
                isVital,
                isValid)

private class TestInfo(
        override val content: LayoutRes,
        override val isVital: Boolean,
        override val isValid: Boolean) : Info()