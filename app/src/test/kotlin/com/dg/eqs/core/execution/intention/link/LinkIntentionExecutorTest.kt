package com.dg.eqs.core.execution.intention.link

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step
import com.dg.eqs.core.execution.intention.link.LinkIntentionEvent.*
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingExecutor
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.IndirectCondensingExecutor
import com.dg.eqs.core.execution.intention.link.indirectshifting.IndirectShiftingExecutor
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LinkIntentionExecutorTest {
    @Mock
    private lateinit var linkIntentionDetector: LinkIntentionDetector
    @Mock
    private lateinit var directShiftingExecutor: DirectShiftingExecutor
    @Mock
    private lateinit var indirectShiftingExecutor: IndirectShiftingExecutor
    @Mock
    private lateinit var directCondensingExecutor: DirectCondensingExecutor
    @Mock
    private lateinit var indirectCondensingExecutor: IndirectCondensingExecutor
    @InjectMocks
    private lateinit var linkIntentionExecutor: LinkIntentionExecutor


    @Test
    fun `Should execute a direct shifting`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(linkIntentionDetector.detect(link)).thenReturn(DirectShifting)

        whenever(directShiftingExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(linkIntentionExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should execute a indirect shifting`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(linkIntentionDetector.detect(link)).thenReturn(IndirectShifting)

        whenever(indirectShiftingExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(linkIntentionExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should execute a direct condensing`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(linkIntentionDetector.detect(link)).thenReturn(DirectCondensing)

        whenever(directCondensingExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(linkIntentionExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should execute a indirect condensing`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(linkIntentionDetector.detect(link)).thenReturn(IndirectCondensing)

        whenever(indirectCondensingExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(linkIntentionExecutor.execute(link)).isEqualTo(step)
    }
}