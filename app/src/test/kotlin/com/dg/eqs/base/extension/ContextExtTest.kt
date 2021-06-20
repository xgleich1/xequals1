package com.dg.eqs.base.extension

import android.content.Context
import android.content.res.Resources
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ContextExtTest {
    @Test
    fun `Should provide a shortcut to retrieve a dimension in int`() {
        // GIVEN
        val resources: Resources = mock {
            on { getDimensionPixelSize(1) } doReturn 50
        }

        val context: Context = mock {
            on { it.resources } doReturn resources
        }

        // THEN
        assertThat(context.getDimensionInInt(1)).isEqualTo(50)
    }

    @Test
    fun `Should provide a shortcut to retrieve a dimension in float`() {
        // GIVEN
        val resources: Resources = mock {
            on { getDimension(1) } doReturn 50f
        }

        val context: Context = mock {
            on { it.resources } doReturn resources
        }

        // THEN
        assertThat(context.getDimensionInFloat(1)).isEqualTo(50f)
    }
}