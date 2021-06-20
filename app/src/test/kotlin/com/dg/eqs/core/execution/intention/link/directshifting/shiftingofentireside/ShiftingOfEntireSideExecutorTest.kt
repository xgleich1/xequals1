package com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingCalculator
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside.ShiftingOfEntireSideEvent.ShiftingAdditionOfEntireSide
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside.ShiftingOfEntireSideEvent.ShiftingSubtractionOfEntireSide
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
class ShiftingOfEntireSideExecutorTest {
    @Mock
    private lateinit var detector: ShiftingOfEntireSideDetector
    @Mock
    private lateinit var calculator: DirectShiftingCalculator
    @InjectMocks
    private lateinit var executor: ShiftingOfEntireSideExecutor


    @Test
    fun `Should execute a shifting addition of an entire side`() {
        // GIVEN
        val link: Link = mock()
        val result: Relation = mock()

        whenever(detector.detect(link)).thenReturn(ShiftingAdditionOfEntireSide)

        whenever(calculator.calculate(link)).thenReturn(result)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(ValidStep(AdditionInfo, result))
    }

    @Test
    fun `Should execute a shifting subtraction of an entire side`() {
        // GIVEN
        val link: Link = mock()
        val result: Relation = mock()

        whenever(detector.detect(link)).thenReturn(ShiftingSubtractionOfEntireSide)

        whenever(calculator.calculate(link)).thenReturn(result)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(ValidStep(SubtractionInfo, result))
    }
}