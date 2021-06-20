package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration

import com.dg.eqs.core.definition.term.Term
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionThroughZeroExceptionTest {
    @Test
    fun `A division through zero exception provides a meaningful message`() {
        // GIVEN
        val numerator: Term = mock { on { toString() } doReturn "1" }

        val exception = DivisionThroughZeroException(numerator)

        // THEN
        assertThat(exception.message).isEqualTo("Cannot divide 1 through zero")
    }
}