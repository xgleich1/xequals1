package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration

import com.dg.eqs.base.abbreviation.AnyCondensingStep
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.alteration.DefaultOperandsCondensing
import com.dg.eqs.classes.term
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DivisionOperandsCondensingTest {
    @Mock
    private lateinit var defaultOperandsCondensing: DefaultOperandsCondensing
    @InjectMocks
    private lateinit var condensing: DivisionOperandsCondensing

    @Mock
    private lateinit var stepA: AnyCondensingStep
    @Mock
    private lateinit var stepB: AnyCondensingStep
    @Mock
    private lateinit var source: Terms
    @Mock
    private lateinit var target: Terms
    @Mock
    private lateinit var result: Terms


    @Test
    fun `Should construct a division operands condensing taking steps`() {
        // WHEN
        val condensing = DivisionOperandsCondensing(stepA, stepB)

        // THEN
        val expectedCondensing = DivisionOperandsCondensing(
                DefaultOperandsCondensing(stepA, stepB))

        assertThat(condensing).isEqualTo(expectedCondensing)
    }

    @Test
    fun `Should compare two equal division operands condensings`() {
        // GIVEN
        val condensingA = DivisionOperandsCondensing(stepA, stepB)
        val condensingB = DivisionOperandsCondensing(stepA, stepB)

        // THEN
        assertThat(condensingA).isEqualTo(condensingB)
    }

    @Test(expected = DivisionThroughZeroException::class)
    fun `A division through zeros operands cannot be condensed`() {
        // GIVEN
        val operands = termsOf(term(), zero())

        // WHEN
        condensing.condense(operands)
    }

    @Test
    fun `Should condense a divisions operands`() {
        // GIVEN
        val operands = termsOf(term(), term())

        whenever(defaultOperandsCondensing
                .condense(operands)) doReturn result

        // THEN
        assertThat(condensing.condense(operands)).isEqualTo(result)
    }

    @Test(expected = DivisionThroughZeroException::class)
    fun `A division through zero cannot condense source & target`() {
        // GIVEN
        val operands = termsOf(term(), zero())

        // WHEN
        condensing.condense(operands, source, target)
    }

    @Test
    fun `Should condense source & target inside a divisions operands`() {
        // GIVEN
        val operands = termsOf(term(), term())

        whenever(defaultOperandsCondensing
                .condense(operands, source, target)) doReturn result

        // THEN
        assertThat(condensing.condense(operands, source, target)).isEqualTo(result)
    }

    private fun zero(): Value = mock { on { isZero } doReturn true }
}