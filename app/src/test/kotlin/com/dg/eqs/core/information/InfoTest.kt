package com.dg.eqs.core.information

import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.classes.info
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class InfoTest {
    @Mock
    private lateinit var content: LayoutRes


    @Test
    fun `Should compare two equal info`() {
        // GIVEN
        val infoA = info(content, false, true)
        val infoB = info(content, false, true)


        // THEN
        assertThat(infoA).isEqualTo(infoB)
    }

    @Test
    fun `Should convert a info to a string`() {
        assertThat(info()).hasToString("TestInfo")
    }
}