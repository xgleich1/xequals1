package com.dg.eqs.core.execution.intention.click.invert

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.Intention.Click.Invert
import com.dg.eqs.core.information.valid.MultiplicationInfo
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
class InvertExecutorTest {
    @Mock
    private lateinit var calculator: InvertCalculator
    @InjectMocks
    private lateinit var executor: InvertExecutor


    @Test
    fun `Should execute a invert`() {
        // GIVEN
        val invert: Invert = mock()
        val result: AnyOrigin = mock()

        whenever(calculator.calculate(invert)) doReturn result

        // THEN
        assertThat(executor.execute(invert))
                .isEqualTo(ValidStep(MultiplicationInfo, result))
    }
}