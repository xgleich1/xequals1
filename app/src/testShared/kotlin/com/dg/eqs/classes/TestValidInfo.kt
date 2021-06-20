package com.dg.eqs.classes

import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.core.information.valid.ValidInfo
import org.mockito.kotlin.mock


fun validInfo(
        content: LayoutRes = mock(),
        isVital: Boolean = false): ValidInfo =
        TestValidInfo(
                content,
                isVital)

private class TestValidInfo(
        override val content: LayoutRes,
        override val isVital: Boolean) : ValidInfo()