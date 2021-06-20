package com.dg.eqs.core.execution.intention.link.indirectshifting

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.information.invalid.shifting.InvalidShiftingDueToBracketingInfo
import com.dg.eqs.core.information.invalid.shifting.InvalidShiftingDueToOrderOfOperationsInfo
import com.dg.eqs.core.information.invalid.shifting.InvalidShiftingInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.intention.link.indirectshifting.IndirectShiftingEvent.*
import com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision.IndirectShiftingOutOfDivisionExecutor
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
class IndirectShiftingExecutorTest {
    @Mock
    private lateinit var indirectShiftingDetector: IndirectShiftingDetector
    @Mock
    private lateinit var indirectShiftingOutOfDivisionExecutor: IndirectShiftingOutOfDivisionExecutor
    @InjectMocks
    private lateinit var indirectShiftingExecutor: IndirectShiftingExecutor


    @Test
    fun `Should execute a indirect shifting out of a division`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(indirectShiftingDetector.detect(link)).thenReturn(IndirectShiftingOutOfDivision)

        whenever(indirectShiftingOutOfDivisionExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(indirectShiftingExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should report when the execution of a indirect shifting is invalid due to the order of operations`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(indirectShiftingDetector.detect(link)).thenReturn(InvalidIndirectShiftingDueToOrderOfOperations)

        // THEN
        assertThat(indirectShiftingExecutor.execute(link)).isEqualTo(InvalidStep(InvalidShiftingDueToOrderOfOperationsInfo, origin))
    }

    @Test
    fun `Should report when the execution of a indirect shifting is invalid due to bracketing`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(indirectShiftingDetector.detect(link)).thenReturn(InvalidIndirectShiftingDueToBracketing)

        // THEN
        assertThat(indirectShiftingExecutor.execute(link)).isEqualTo(InvalidStep(InvalidShiftingDueToBracketingInfo, origin))
    }

    @Test
    fun `Should report when the execution of a indirect shifting is invalid`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(indirectShiftingDetector.detect(link)).thenReturn(InvalidIndirectShifting)

        // THEN
        assertThat(indirectShiftingExecutor.execute(link)).isEqualTo(InvalidStep(InvalidShiftingInfo, origin))
    }
}