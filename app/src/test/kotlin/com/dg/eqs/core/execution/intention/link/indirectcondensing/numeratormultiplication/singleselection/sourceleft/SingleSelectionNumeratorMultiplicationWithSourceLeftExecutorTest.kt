package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.sourceleft

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingMultiplicationWithVariableInfo
import com.dg.eqs.core.information.valid.MultiplicationInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationEvent.InvalidSingleSelectionNumeratorMultiplication
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationEvent.ValidSingleSelectionNumeratorMultiplication
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
class SingleSelectionNumeratorMultiplicationWithSourceLeftExecutorTest {
    @Mock
    private lateinit var detector: SingleSelectionNumeratorMultiplicationWithSourceLeftDetector
    @Mock
    private lateinit var calculator: SingleSelectionNumeratorMultiplicationWithSourceLeftCalculator
    @InjectMocks
    private lateinit var executor: SingleSelectionNumeratorMultiplicationWithSourceLeftExecutor


    @Test
    fun `Should execute a single selection numerator multiplication with the source on the left`() {
        // GIVEN
        val link: Link = mock()
        val result: AnyOrigin = mock()

        whenever(detector.detect(link)).thenReturn(ValidSingleSelectionNumeratorMultiplication)

        whenever(calculator.calculate(link)).thenReturn(result)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(ValidStep(MultiplicationInfo, result))
    }

    @Test
    fun `Should report when the execution of a single selection numerator multiplication with the source on the left is invalid`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(detector.detect(link)).thenReturn(InvalidSingleSelectionNumeratorMultiplication)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingMultiplicationWithVariableInfo, origin))
    }
}