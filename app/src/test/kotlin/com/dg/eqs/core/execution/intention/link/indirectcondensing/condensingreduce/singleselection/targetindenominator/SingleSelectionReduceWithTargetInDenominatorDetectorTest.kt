package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.targetindenominator

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.SingleSelectionReduceDetector
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.SingleSelectionReduceEvent
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
class SingleSelectionReduceWithTargetInDenominatorDetectorTest {
    @Mock
    private lateinit var singleSelectionReduceDetector: SingleSelectionReduceDetector
    @InjectMocks
    private lateinit var detector: SingleSelectionReduceWithTargetInDenominatorDetector

    @Mock
    private lateinit var singleSelectionReduceEvent: SingleSelectionReduceEvent


    @Test
    fun `Should detect the event of a single selection reduce with the target in the denominator`() {
        // GIVEN
        val singleSource: Term = mock()
        val singleTarget: Term = mock()

        val link: Link = mock {
            on { firstSource } doReturn singleSource
            on { firstTarget } doReturn singleTarget
        }

        whenever(singleSelectionReduceDetector
                .detect(singleSource, singleTarget))
                .thenReturn(singleSelectionReduceEvent)

        // THEN
        assertThat(detector.detect(link)).isEqualTo(singleSelectionReduceEvent)
    }
}