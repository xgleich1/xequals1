package com.dg.eqs.core.execution.intention.click

import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.Intention.Click.Invert
import com.dg.eqs.core.execution.intention.click.invert.InvertExecutor
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
class ClickIntentionExecutorTest {
    @Mock
    private lateinit var invertExecutor: InvertExecutor
    @InjectMocks
    private lateinit var clickIntentionExecutor: ClickIntentionExecutor


    @Test
    fun `Should execute a invert`() {
        // GIVEN
        val invert: Invert = mock()
        val step: ValidStep = mock()

        whenever(invertExecutor.execute(invert)) doReturn step

        // THEN
        assertThat(clickIntentionExecutor.execute(invert)).isEqualTo(step)
    }
}