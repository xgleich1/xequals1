package com.dg.eqs.classes

import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.core.information.invalid.InvalidInfo
import org.mockito.kotlin.mock


fun invalidInfo(
        content: LayoutRes = mock(),
        isVital: Boolean = false): InvalidInfo =
        TestInvalidInfo(
                content,
                isVital)

private class TestInvalidInfo(
        override val content: LayoutRes,
        override val isVital: Boolean) : InvalidInfo()