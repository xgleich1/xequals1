package com.dg.eqs.core.execution.intention.link.indirectshifting

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectshifting.IndirectShiftingEvent.*
import com.dg.eqs.core.execution.intention.link.indirectshifting.invalidshifting.InvalidShiftingDueToBracketingDecider
import com.dg.eqs.core.execution.intention.link.indirectshifting.invalidshifting.InvalidShiftingDueToOrderOfOperationsDecider
import com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision.IndirectShiftingOutOfDivisionDecider
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class IndirectShiftingDetectorTest {
    @Mock
    private lateinit var indirectShiftingOutOfDivisionDecider: IndirectShiftingOutOfDivisionDecider
    @Mock
    private lateinit var invalidShiftingDueToOrderOfOperationsDecider: InvalidShiftingDueToOrderOfOperationsDecider
    @Mock
    private lateinit var invalidShiftingDueToBracketingDecider: InvalidShiftingDueToBracketingDecider
    @InjectMocks
    private lateinit var detector: IndirectShiftingDetector


    @Test
    fun `Should detect a indirect shifting out of a division`() {
        // GIVEN
        val link: Link = mock()

        whenever(indirectShiftingOutOfDivisionDecider.decide(link)).thenReturn(true)

        // THEN
        assertThat(detector.detect(link)).isEqualTo(IndirectShiftingOutOfDivision)
    }

    @Test
    fun `Should detect a invalid indirect shifting due to the order of operations`() {
        // GIVEN
        val link: Link = mock()

        whenever(invalidShiftingDueToOrderOfOperationsDecider.decide(link)).thenReturn(true)

        // THEN
        assertThat(detector.detect(link)).isEqualTo(InvalidIndirectShiftingDueToOrderOfOperations)
    }

    @Test
    fun `Should detect a invalid indirect shifting due to bracketing`() {
        // GIVEN
        val link: Link = mock()

        whenever(invalidShiftingDueToBracketingDecider.decide(link)).thenReturn(true)

        // THEN
        assertThat(detector.detect(link)).isEqualTo(InvalidIndirectShiftingDueToBracketing)
    }

    @Test
    fun `Should default to a invalid indirect shifting when there are no other detections`() {
        // GIVEN
        val link: Link = mock()

        whenever(indirectShiftingOutOfDivisionDecider.decide(link)).thenReturn(false)
        whenever(invalidShiftingDueToOrderOfOperationsDecider.decide(link)).thenReturn(false)
        whenever(invalidShiftingDueToBracketingDecider.decide(link)).thenReturn(false)

        // THEN
        assertThat(detector.detect(link)).isEqualTo(InvalidIndirectShifting)
    }
}