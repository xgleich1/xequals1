package com.dg.eqs.core.execution.intention.link.directshifting

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingEvent.*
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside.ShiftingOfEntireSideExecutor
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation.ShiftingOutOfDashOperationExecutor
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdivision.ShiftingOutOfDivisionExecutor
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct.ShiftingOutOfProductExecutor
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DirectShiftingExecutorTest {
    @Mock
    private lateinit var directShiftingDetector: DirectShiftingDetector
    @Mock
    private lateinit var shiftingOfEntireSideExecutor: ShiftingOfEntireSideExecutor
    @Mock
    private lateinit var shiftingOutOfDashOperationExecutor: ShiftingOutOfDashOperationExecutor
    @Mock
    private lateinit var shiftingOutOfDivisionExecutor: ShiftingOutOfDivisionExecutor
    @Mock
    private lateinit var shiftingOutOfProductExecutor: ShiftingOutOfProductExecutor
    @InjectMocks
    private lateinit var directShiftingExecutor: DirectShiftingExecutor


    @Test
    fun `Should execute a shifting of an entire side`() {
        // GIVEN
        val link: Link = mock()
        val step: ValidStep = mock()

        whenever(directShiftingDetector.detect(link)).thenReturn(ShiftingOfEntireSide)

        whenever(shiftingOfEntireSideExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(directShiftingExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should execute a shifting out of a dash operation`() {
        // GIVEN
        val link: Link = mock()
        val step: ValidStep = mock()

        whenever(directShiftingDetector.detect(link)).thenReturn(ShiftingOutOfDashOperation)

        whenever(shiftingOutOfDashOperationExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(directShiftingExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should execute a shifting out of a division`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(directShiftingDetector.detect(link)).thenReturn(ShiftingOutOfDivision)

        whenever(shiftingOutOfDivisionExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(directShiftingExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should execute a shifting out of a product`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(directShiftingDetector.detect(link)).thenReturn(ShiftingOutOfProduct)

        whenever(shiftingOutOfProductExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(directShiftingExecutor.execute(link)).isEqualTo(step)
    }
}