package com.dg.eqs.core.generation

import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doReturnConsecutively
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random


@RunWith(MockitoJUnitRunner::class)
class GenerationSelectorTest {
    @Mock
    private lateinit var random: Random
    @InjectMocks
    private lateinit var selector: GenerationSelector


    @Test
    fun `Should select a option from a list of options`() {
        // GIVEN
        whenever(random.nextInt(2)) doReturn 1

        // WHEN
        val option = selector
                .selectOption(listOf("+", "-"))

        // THEN
        assertThat(option).isEqualTo("-")
    }

    @Test
    fun `Should roll a value & select it when it isn't rare`() {
        // GIVEN
        whenever(random.nextInt(10)) doReturn 5

        // WHEN
        val value = selector.selectValue(setOf(4))

        // THEN
        assertThat(value).isEqualTo(5)
    }

    @Test
    fun `Should roll a value & select it even when it's rare`() {
        // GIVEN
        whenever(random.nextInt(10)) doReturn 4

        whenever(random.nextDouble(1.0)) doReturn 0.0

        // WHEN
        val value = selector.selectValue(setOf(4))

        // THEN
        assertThat(value).isEqualTo(4)
    }

    @Test
    fun `Should roll values & select the first which isn't rare`() {
        // GIVEN
        whenever(random.nextInt(10)) doReturnConsecutively listOf(4, 5)

        whenever(random.nextDouble(1.0)) doReturn 1.0

        // WHEN
        val value = selector.selectValue(setOf(4))

        // THEN
        assertThat(value).isEqualTo(5)
    }

    @Test
    fun `Should roll values & select one even when it's rare`() {
        // GIVEN
        whenever(random.nextInt(10)) doReturnConsecutively listOf(4, 5)

        whenever(random.nextDouble(1.0)) doReturnConsecutively listOf(1.0, 0.0)

        // WHEN
        val value = selector.selectValue(setOf(4, 5))

        // THEN
        assertThat(value).isEqualTo(5)
    }
}