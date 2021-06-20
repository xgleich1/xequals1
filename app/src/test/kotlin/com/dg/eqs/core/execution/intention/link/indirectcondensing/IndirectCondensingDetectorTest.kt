package com.dg.eqs.core.execution.intention.link.indirectcondensing

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.IndirectCondensingEvent.*
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.CondensingReduceDecider
import com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing.InvalidCondensingDueToBracketingDecider
import com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing.InvalidCondensingDueToOrderOfOperationsDecider
import com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing.InvalidCondensingDueToReduceWithDashOperationDecider
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.NumeratorMultiplicationDecider
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class IndirectCondensingDetectorTest {
    @Mock
    private lateinit var condensingReduceDecider: CondensingReduceDecider
    @Mock
    private lateinit var numeratorMultiplicationDecider: NumeratorMultiplicationDecider
    @Mock
    private lateinit var invalidCondensingDueToReduceWithDashOperationDecider: InvalidCondensingDueToReduceWithDashOperationDecider
    @Mock
    private lateinit var invalidCondensingDueToOrderOfOperationsDecider: InvalidCondensingDueToOrderOfOperationsDecider
    @Mock
    private lateinit var invalidCondensingDueToBracketingDecider: InvalidCondensingDueToBracketingDecider
    @InjectMocks
    private lateinit var detector: IndirectCondensingDetector


    @Test
    fun `Should detect a condensing reduce`() {
        // GIVEN
        val link: Link = mock()

        whenever(condensingReduceDecider.decide(link)).thenReturn(true)

        // THEN
        assertThat(detector.detect(link)).isEqualTo(CondensingReduce)
    }

    @Test
    fun `Should detect a numerator multiplication`() {
        // GIVEN
        val link: Link = mock()

        whenever(numeratorMultiplicationDecider.decide(link)).thenReturn(true)

        // THEN
        assertThat(detector.detect(link)).isEqualTo(NumeratorMultiplication)
    }

    @Test
    fun `Should detect a invalid indirect condensing due to a reduce with a dash operation`() {
        // GIVEN
        val link: Link = mock()

        whenever(invalidCondensingDueToReduceWithDashOperationDecider.decide(link)).thenReturn(true)

        // THEN
        assertThat(detector.detect(link)).isEqualTo(InvalidIndirectCondensingDueToReduceWithDashOperation)
    }

    @Test
    fun `Should detect a invalid indirect condensing due to the order of operations`() {
        // GIVEN
        val link: Link = mock()

        whenever(invalidCondensingDueToOrderOfOperationsDecider.decide(link)).thenReturn(true)

        // THEN
        assertThat(detector.detect(link)).isEqualTo(InvalidIndirectCondensingDueToOrderOfOperations)
    }

    @Test
    fun `Should detect a invalid indirect condensing due to bracketing`() {
        // GIVEN
        val link: Link = mock()

        whenever(invalidCondensingDueToBracketingDecider.decide(link)).thenReturn(true)

        // THEN
        assertThat(detector.detect(link)).isEqualTo(InvalidIndirectCondensingDueToBracketing)
    }

    @Test
    fun `Should default to a invalid indirect condensing when there are no other detections`() {
        // GIVEN
        val link: Link = mock()

        whenever(condensingReduceDecider.decide(link)).thenReturn(false)
        whenever(numeratorMultiplicationDecider.decide(link)).thenReturn(false)
        whenever(invalidCondensingDueToReduceWithDashOperationDecider.decide(link)).thenReturn(false)
        whenever(invalidCondensingDueToOrderOfOperationsDecider.decide(link)).thenReturn(false)
        whenever(invalidCondensingDueToBracketingDecider.decide(link)).thenReturn(false)

        // THEN
        assertThat(detector.detect(link)).isEqualTo(InvalidIndirectCondensing)
    }
}