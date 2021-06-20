package com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensingindashoperation

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingInfo
import com.dg.eqs.core.information.valid.AdditionInfo
import com.dg.eqs.core.information.valid.SubtractionInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingCalculator
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensingindashoperation.MultiSelectionCondensingInDashOperationEvent.*
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
class MultiSelectionCondensingInDashOperationExecutorTest {
    @Mock
    private lateinit var detector: MultiSelectionCondensingInDashOperationDetector
    @Mock
    private lateinit var calculator: DirectCondensingCalculator
    @InjectMocks
    private lateinit var executor: MultiSelectionCondensingInDashOperationExecutor


    @Test
    fun `Should execute a multi selection condensing addition`() {
        // GIVEN
        val link: Link = mock()
        val result: AnyOrigin = mock()

        whenever(detector.detect(link)).thenReturn(ValidMultiSelectionCondensingAddition)

        whenever(calculator.calculate(link)).thenReturn(result)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(ValidStep(AdditionInfo, result))
    }

    @Test
    fun `Should execute a multi selection condensing subtraction`() {
        // GIVEN
        val link: Link = mock()
        val result: AnyOrigin = mock()

        whenever(detector.detect(link)).thenReturn(ValidMultiSelectionCondensingSubtraction)

        whenever(calculator.calculate(link)).thenReturn(result)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(ValidStep(SubtractionInfo, result))
    }

    @Test
    fun `Should report when the execution of a multi selection condensing addition is invalid`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(detector.detect(link)).thenReturn(InvalidMultiSelectionCondensingAddition)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingInfo, origin))
    }

    @Test
    fun `Should report when the execution of a multi selection condensing subtraction is invalid`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(detector.detect(link)).thenReturn(InvalidMultiSelectionCondensingSubtraction)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingInfo, origin))
    }
}