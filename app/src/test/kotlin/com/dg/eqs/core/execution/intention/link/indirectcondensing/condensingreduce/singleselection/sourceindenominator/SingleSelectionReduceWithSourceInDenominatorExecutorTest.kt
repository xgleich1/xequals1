package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.sourceindenominator

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingDivisionThroughZeroInfo
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingInfo
import com.dg.eqs.core.information.valid.ReduceInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.SingleSelectionReduceEvent.*
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
class SingleSelectionReduceWithSourceInDenominatorExecutorTest {
    @Mock
    private lateinit var detector: SingleSelectionReduceWithSourceInDenominatorDetector
    @Mock
    private lateinit var calculator: SingleSelectionReduceWithSourceInDenominatorCalculator
    @InjectMocks
    private lateinit var executor: SingleSelectionReduceWithSourceInDenominatorExecutor


    @Test
    fun `Should execute a single selection reduce`() {
        // GIVEN
        val link: Link = mock()
        val result: AnyOrigin = mock()

        whenever(detector.detect(link)).thenReturn(ValidSingleSelectionReduce)

        whenever(calculator.calculate(link)).thenReturn(result)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(ValidStep(ReduceInfo, result))
    }

    @Test
    fun `Should report a invalid single selection reduce through zero`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(detector.detect(link)).thenReturn(InvalidSingleSelectionReduceThroughZero)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingDivisionThroughZeroInfo, origin))
    }

    @Test
    fun `Should report when the execution of a single selection reduce is invalid`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(detector.detect(link)).thenReturn(InvalidSingleSelectionReduce)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingInfo, origin))
    }
}