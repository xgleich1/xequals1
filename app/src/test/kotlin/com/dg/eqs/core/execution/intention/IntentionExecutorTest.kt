package com.dg.eqs.core.execution.intention

import com.dg.eqs.core.execution.Step
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.Intention.Click
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.click.ClickIntentionExecutor
import com.dg.eqs.core.execution.intention.link.LinkIntentionExecutor
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
class IntentionExecutorTest {
    @Mock
    private lateinit var linkIntentionExecutor: LinkIntentionExecutor
    @Mock
    private lateinit var clickIntentionExecutor: ClickIntentionExecutor
    @InjectMocks
    private lateinit var intentionExecutor: IntentionExecutor


    @Test
    fun `Should execute a link intention`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(linkIntentionExecutor.execute(link)) doReturn step

        // THEN
        assertThat(intentionExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should execute a click intention`() {
        // GIVEN
        val click: Click = mock()
        val step: ValidStep = mock()

        whenever(clickIntentionExecutor.execute(click)) doReturn step

        // THEN
        assertThat(intentionExecutor.execute(click)).isEqualTo(step)
    }
}