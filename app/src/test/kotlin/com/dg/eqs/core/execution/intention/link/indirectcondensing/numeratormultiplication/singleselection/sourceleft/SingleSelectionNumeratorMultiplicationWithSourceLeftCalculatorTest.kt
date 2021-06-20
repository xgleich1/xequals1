package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.sourceleft

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationCalculator
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
class SingleSelectionNumeratorMultiplicationWithSourceLeftCalculatorTest {
    @Mock
    private lateinit var singleSelectionNumeratorMultiplicationCalculator: SingleSelectionNumeratorMultiplicationCalculator
    @InjectMocks
    private lateinit var calculator: SingleSelectionNumeratorMultiplicationWithSourceLeftCalculator

    @Mock
    private lateinit var singleSelectionNumeratorMultiplicationResult: AnyOrigin


    @Test
    fun `Should calculate a single selection numerator multiplication with the source on the left`() {
        // GIVEN
        val singleSource: Term = mock()
        val singleTarget: Term = mock()

        val link: Link = mock {
            on { it.firstSource } doReturn singleSource
            on { it.firstTarget } doReturn singleTarget
        }

        whenever(singleSelectionNumeratorMultiplicationCalculator
                .calculate(link, singleSource, singleTarget))
                .thenReturn(singleSelectionNumeratorMultiplicationResult)

        // THEN
        assertThat(calculator.calculate(link)).isEqualTo(singleSelectionNumeratorMultiplicationResult)
    }
}