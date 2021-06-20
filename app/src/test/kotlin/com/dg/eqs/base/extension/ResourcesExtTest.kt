package com.dg.eqs.base.extension

import android.content.res.Resources
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ResourcesExtTest {
    @Test
    fun `Should provide a shortcut to retrieve a list of strings`() {
        // GIVEN
        val resources: Resources = mock {
            on { getStringArray(1) } doReturn arrayOf("a", "b")
        }

        // THEN
        assertThat(resources.getStrings(1)).isEqualTo(listOf("a", "b"))
    }
}