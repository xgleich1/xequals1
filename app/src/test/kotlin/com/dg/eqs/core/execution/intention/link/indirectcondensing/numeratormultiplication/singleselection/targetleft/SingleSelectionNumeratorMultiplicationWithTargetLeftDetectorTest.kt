package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.targetleft

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationDetector
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationEvent
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
class SingleSelectionNumeratorMultiplicationWithTargetLeftDetectorTest {
    @Mock
    private lateinit var singleSelectionNumeratorMultiplicationDetector: SingleSelectionNumeratorMultiplicationDetector
    @InjectMocks
    private lateinit var detector: SingleSelectionNumeratorMultiplicationWithTargetLeftDetector

    @Mock
    private lateinit var singleSelectionNumeratorMultiplicationEvent: SingleSelectionNumeratorMultiplicationEvent


    @Test
    fun `Should detect the event of a single selection numerator multiplication with the target on the left`() {
        // GIVEN
        val singleSource: Term = mock()
        val singleTarget: Term = mock()

        val link: Link = mock {
            on { firstSource } doReturn singleSource
            on { firstTarget } doReturn singleTarget
        }

        whenever(singleSelectionNumeratorMultiplicationDetector
                .detect(singleTarget, singleSource))
                .thenReturn(singleSelectionNumeratorMultiplicationEvent)

        // THEN
        assertThat(detector.detect(link)).isEqualTo(singleSelectionNumeratorMultiplicationEvent)
    }
}