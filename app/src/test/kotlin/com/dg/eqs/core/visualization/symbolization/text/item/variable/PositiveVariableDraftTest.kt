package com.dg.eqs.core.visualization.symbolization.text.item.variable

import com.dg.eqs.core.definition.term.item.variable.Variable
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class PositiveVariableDraftTest {
    @Test
    fun `A positive variable draft is the positive representation of a variable`() {
        // GIVEN
        val variable: Variable = mock { on { unsignedName } doReturn "x" }

        // THEN
        assertThat(PositiveVariableDraft(variable).text).isEqualTo("x")
    }
}