package com.dg.eqs.core.visualization.symbolization.text.item.variable

import com.dg.eqs.core.definition.term.item.variable.Variable
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class NegativeVariableDraftTest {
    @Test
    fun `A negative variable draft is the negative representation of a variable`() {
        // GIVEN
        val variable: Variable = mock { on { unsignedName } doReturn "x" }

        // THEN
        assertThat(NegativeVariableDraft(variable).text).isEqualTo("-x")
    }
}