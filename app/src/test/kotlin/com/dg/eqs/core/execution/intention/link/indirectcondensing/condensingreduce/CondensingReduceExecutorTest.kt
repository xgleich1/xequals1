package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.CondensingReduceEvent.*
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.sourceindenominator.SingleSelectionReduceWithSourceInDenominatorExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.targetindenominator.SingleSelectionReduceWithTargetInDenominatorExecutor
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
class CondensingReduceExecutorTest {
    @Mock
    private lateinit var condensingReduceDetector: CondensingReduceDetector
    @Mock
    private lateinit var singleSelectionReduceWithSourceInDenominatorExecutor: SingleSelectionReduceWithSourceInDenominatorExecutor
    @Mock
    private lateinit var singleSelectionReduceWithTargetInDenominatorExecutor: SingleSelectionReduceWithTargetInDenominatorExecutor
    @InjectMocks
    private lateinit var condensingReduceExecutor: CondensingReduceExecutor


    @Test
    fun `Should execute a single selection reduce with the source in the denominator`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(condensingReduceDetector.detect(link)).thenReturn(SingleSelectionReduceWithSourceInDenominator)

        whenever(singleSelectionReduceWithSourceInDenominatorExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(condensingReduceExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should execute a single selection reduce with the target in the denominator`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(condensingReduceDetector.detect(link)).thenReturn(SingleSelectionReduceWithTargetInDenominator)

        whenever(singleSelectionReduceWithTargetInDenominatorExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(condensingReduceExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should report the invalidity of a multi selection reduce with the source in the denominator`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(condensingReduceDetector.detect(link)).thenReturn(MultiSelectionReduceWithSourceInDenominator)

        // THEN
        assertThat(condensingReduceExecutor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingInfo, origin))
    }

    @Test
    fun `Should report the invalidity of a multi selection reduce with the target in the denominator`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(condensingReduceDetector.detect(link)).thenReturn(MultiSelectionReduceWithTargetInDenominator)

        // THEN
        assertThat(condensingReduceExecutor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingInfo, origin))
    }
}