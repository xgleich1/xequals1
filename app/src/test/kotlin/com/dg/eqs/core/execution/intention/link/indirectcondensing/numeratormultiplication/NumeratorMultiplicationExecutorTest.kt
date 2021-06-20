package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.NumeratorMultiplicationEvent.*
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.sourceleft.SingleSelectionNumeratorMultiplicationWithSourceLeftExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.targetleft.SingleSelectionNumeratorMultiplicationWithTargetLeftExecutor
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
class NumeratorMultiplicationExecutorTest {
    @Mock
    private lateinit var numeratorMultiplicationDetector: NumeratorMultiplicationDetector
    @Mock
    private lateinit var singleSelectionNumeratorMultiplicationWithSourceLeftExecutor: SingleSelectionNumeratorMultiplicationWithSourceLeftExecutor
    @Mock
    private lateinit var singleSelectionNumeratorMultiplicationWithTargetLeftExecutor: SingleSelectionNumeratorMultiplicationWithTargetLeftExecutor
    @InjectMocks
    private lateinit var numeratorMultiplicationExecutor: NumeratorMultiplicationExecutor


    @Test
    fun `Should execute a single selection numerator multiplication with the source on the left`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(numeratorMultiplicationDetector.detect(link)).thenReturn(SingleSelectionNumeratorMultiplicationWithSourceLeft)

        whenever(singleSelectionNumeratorMultiplicationWithSourceLeftExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(numeratorMultiplicationExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should execute a single selection numerator multiplication with the target on the left`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(numeratorMultiplicationDetector.detect(link)).thenReturn(SingleSelectionNumeratorMultiplicationWithTargetLeft)

        whenever(singleSelectionNumeratorMultiplicationWithTargetLeftExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(numeratorMultiplicationExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should report the invalidity of a multi selection numerator multiplication with the source on the left`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(numeratorMultiplicationDetector.detect(link)).thenReturn(MultiSelectionNumeratorMultiplicationWithSourceLeft)

        // THEN
        assertThat(numeratorMultiplicationExecutor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingInfo, origin))
    }

    @Test
    fun `Should report the invalidity of a multi selection numerator multiplication with the target on the left`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(numeratorMultiplicationDetector.detect(link)).thenReturn(MultiSelectionNumeratorMultiplicationWithTargetLeft)

        // THEN
        assertThat(numeratorMultiplicationExecutor.execute(link)).isEqualTo(InvalidStep(InvalidCondensingInfo, origin))
    }
}