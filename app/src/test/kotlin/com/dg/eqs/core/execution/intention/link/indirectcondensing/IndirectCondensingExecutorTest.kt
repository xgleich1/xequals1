package com.dg.eqs.core.execution.intention.link.indirectcondensing

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingDueToBracketingInfo
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingDueToOrderOfOperationsInfo
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingInfo
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingReduceWithDashOperationInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.intention.link.indirectcondensing.IndirectCondensingEvent.*
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.CondensingReduceExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.NumeratorMultiplicationExecutor
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
class IndirectCondensingExecutorTest {
    @Mock
    private lateinit var indirectCondensingDetector: IndirectCondensingDetector
    @Mock
    private lateinit var condensingReduceExecutor: CondensingReduceExecutor
    @Mock
    private lateinit var numeratorMultiplicationExecutor: NumeratorMultiplicationExecutor
    @InjectMocks
    private lateinit var indirectCondensingExecutor: IndirectCondensingExecutor


    @Test
    fun `Should execute a condensing reduce`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(indirectCondensingDetector.detect(link)).thenReturn(CondensingReduce)

        whenever(condensingReduceExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(indirectCondensingExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should execute a numerator multiplication`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(indirectCondensingDetector.detect(link)).thenReturn(NumeratorMultiplication)

        whenever(numeratorMultiplicationExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(indirectCondensingExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should report when the execution of a indirect condensing is invalid due to a reduce with a dash operation`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(indirectCondensingDetector.detect(link)).thenReturn(InvalidIndirectCondensingDueToReduceWithDashOperation)

        // THEN
        assertThat(indirectCondensingExecutor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingReduceWithDashOperationInfo, origin))
    }

    @Test
    fun `Should report when the execution of a indirect condensing is invalid due to the order of operations`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(indirectCondensingDetector.detect(link)).thenReturn(InvalidIndirectCondensingDueToOrderOfOperations)

        // THEN
        assertThat(indirectCondensingExecutor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingDueToOrderOfOperationsInfo, origin))
    }

    @Test
    fun `Should report when the execution of a indirect condensing is invalid due to bracketing`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(indirectCondensingDetector.detect(link)).thenReturn(InvalidIndirectCondensingDueToBracketing)

        // THEN
        assertThat(indirectCondensingExecutor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingDueToBracketingInfo, origin))
    }

    @Test
    fun `Should report when the execution of a indirect condensing is invalid`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(indirectCondensingDetector.detect(link)).thenReturn(InvalidIndirectCondensing)

        // THEN
        assertThat(indirectCondensingExecutor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingInfo, origin))
    }
}