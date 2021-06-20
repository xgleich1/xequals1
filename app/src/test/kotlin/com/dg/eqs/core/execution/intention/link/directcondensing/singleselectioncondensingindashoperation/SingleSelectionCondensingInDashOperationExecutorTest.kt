package com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindashoperation

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingAdditionWithVariableInfo
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingSubtractionWithVariableInfo
import com.dg.eqs.core.information.valid.AdditionInfo
import com.dg.eqs.core.information.valid.SubtractionInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingCalculator
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindashoperation.SingleSelectionCondensingInDashOperationEvent.*
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
class SingleSelectionCondensingInDashOperationExecutorTest {
    @Mock
    private lateinit var detector: SingleSelectionCondensingInDashOperationDetector
    @Mock
    private lateinit var calculator: DirectCondensingCalculator
    @InjectMocks
    private lateinit var executor: SingleSelectionCondensingInDashOperationExecutor


    @Test
    fun `Should execute a single selection condensing addition`() {
        // GIVEN
        val link: Link = mock()
        val result: AnyOrigin = mock()

        whenever(detector.detect(link)).thenReturn(ValidSingleSelectionCondensingAddition)

        whenever(calculator.calculate(link)).thenReturn(result)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(ValidStep(AdditionInfo, result))
    }

    @Test
    fun `Should execute a single selection condensing subtraction`() {
        // GIVEN
        val link: Link = mock()
        val result: AnyOrigin = mock()

        whenever(detector.detect(link)).thenReturn(ValidSingleSelectionCondensingSubtraction)

        whenever(calculator.calculate(link)).thenReturn(result)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(ValidStep(SubtractionInfo, result))
    }

    @Test
    fun `Should report when the execution of a single selection condensing addition is invalid`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(detector.detect(link)).thenReturn(InvalidSingleSelectionCondensingAddition)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingAdditionWithVariableInfo, origin))
    }

    @Test
    fun `Should report when the execution of a single selection condensing subtraction is invalid`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(detector.detect(link)).thenReturn(InvalidSingleSelectionCondensingSubtraction)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingSubtractionWithVariableInfo, origin))
    }
}