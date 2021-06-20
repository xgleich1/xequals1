package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.sourceindenominator

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.SingleSelectionReduceCalculator
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
class SingleSelectionReduceWithSourceInDenominatorCalculatorTest {
    @Mock
    private lateinit var singleSelectionReduceCalculator: SingleSelectionReduceCalculator
    @InjectMocks
    private lateinit var calculator: SingleSelectionReduceWithSourceInDenominatorCalculator

    @Mock
    private lateinit var singleSelectionReduceResult: AnyOrigin


    @Test
    fun `Should calculate the result of a single selection reduce with the source in the denominator`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val singleSource: Term = mock()
        val singleTarget: Term = mock()

        val link: Link = mock {
            on { it.origin } doReturn origin
            on { it.firstSource } doReturn singleSource
            on { it.firstTarget } doReturn singleTarget
        }

        whenever(singleSelectionReduceCalculator
                .calculate(origin, singleTarget, singleSource))
                .thenReturn(singleSelectionReduceResult)

        // THEN
        assertThat(calculator.calculate(link)).isEqualTo(singleSelectionReduceResult)
    }
}