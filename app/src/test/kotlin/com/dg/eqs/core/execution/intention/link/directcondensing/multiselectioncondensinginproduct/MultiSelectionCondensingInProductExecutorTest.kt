package com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingInfo
import com.dg.eqs.core.information.valid.MultiplicationInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingCalculator
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct.MultiSelectionCondensingInProductEvent.InvalidMultiSelectionCondensingMultiplication
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct.MultiSelectionCondensingInProductEvent.ValidMultiSelectionCondensingMultiplication
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
class MultiSelectionCondensingInProductExecutorTest {
    @Mock
    private lateinit var detector: MultiSelectionCondensingInProductDetector
    @Mock
    private lateinit var calculator: DirectCondensingCalculator
    @InjectMocks
    private lateinit var executor: MultiSelectionCondensingInProductExecutor


    @Test
    fun `Should execute a multi selection condensing multiplication`() {
        // GIVEN
        val link: Link = mock()
        val result: AnyOrigin = mock()

        whenever(detector.detect(link)).thenReturn(ValidMultiSelectionCondensingMultiplication)

        whenever(calculator.calculate(link)).thenReturn(result)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(ValidStep(MultiplicationInfo, result))
    }

    @Test
    fun `Should report when the execution of a multi selection condensing multiplication is invalid`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(detector.detect(link)).thenReturn(InvalidMultiSelectionCondensingMultiplication)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingInfo, origin))
    }
}