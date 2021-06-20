package com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingCalculator
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation.ShiftingOutOfDashOperationEvent.ShiftingAdditionOutOfDashOperation
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation.ShiftingOutOfDashOperationEvent.ShiftingSubtractionOutOfDashOperation
import com.dg.eqs.core.information.valid.AdditionInfo
import com.dg.eqs.core.information.valid.SubtractionInfo
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ShiftingOutOfDashOperationExecutorTest {
    @Mock
    private lateinit var detector: ShiftingOutOfDashOperationDetector
    @Mock
    private lateinit var calculator: DirectShiftingCalculator
    @InjectMocks
    private lateinit var executor: ShiftingOutOfDashOperationExecutor


    @Test
    fun `Should execute a shifting addition out of a dash operation`() {
        // GIVEN
        val link: Link = mock()
        val result: Relation = mock()

        whenever(detector.detect(link)).thenReturn(ShiftingAdditionOutOfDashOperation)

        whenever(calculator.calculate(link)).thenReturn(result)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(ValidStep(AdditionInfo, result))
    }

    @Test
    fun `Should execute a shifting subtraction out of a dash operation`() {
        // GIVEN
        val link: Link = mock()
        val result: Relation = mock()

        whenever(detector.detect(link)).thenReturn(ShiftingSubtractionOutOfDashOperation)

        whenever(calculator.calculate(link)).thenReturn(result)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(ValidStep(SubtractionInfo, result))
    }
}