package com.dg.eqs.core.orchestration

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.execution.Step
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.information.Info
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class GameStepsTest {
    @Test
    fun `Should provide the last info`() {
        // GIVEN
        val infoA: Info = mock()
        val infoB: Info = mock()
        val stepA = step(infoA)
        val stepB = step(infoB)
        val gameSteps = GameSteps(stepA, stepB)

        // THEN
        assertThat(gameSteps.lastInfo).isSameAs(infoB)
    }

    @Test
    fun `Should provide the last origin`() {
        // GIVEN
        val originA: AnyOrigin = mock()
        val originB: AnyOrigin = mock()
        val stepA = step(origin = originA)
        val stepB = step(origin = originB)
        val gameSteps = GameSteps(stepA, stepB)

        // THEN
        assertThat(gameSteps.lastOrigin).isSameAs(originB)
    }

    @Test
    fun `Should provide the count of valid steps`() {
        // GIVEN
        val stepA = validStep()
        val stepB = invalidStep()
        val stepC = validStep()
        val stepD = invalidStep()
        val stepE = validStep()
        val gameSteps = GameSteps(
                stepA,
                stepB,
                stepC,
                stepD,
                stepE)

        // THEN
        assertThat(gameSteps.validStepsCount).isEqualTo(3)
    }

    @Test
    fun `Should compare two equal game steps decorator`() {
        // GIVEN
        val stepA = step()
        val stepB = step()
        val gameStepsA = GameSteps(stepA, stepB)
        val gameStepsB = GameSteps(stepA, stepB)

        // THEN
        assertThat(gameStepsA).isEqualTo(gameStepsB)
    }

    @Test
    fun `Should convert a game steps decorator to a string`() {
        // GIVEN
        val stepA: Step = mock { on { toString() } doReturn "stepA" }
        val stepB: Step = mock { on { toString() } doReturn "stepB" }
        val gameSteps = GameSteps(stepA, stepB)

        // THEN
        assertThat(gameSteps).hasToString("[stepA, stepB]")
    }

    @Test
    fun `Should provide a plus operator for a game step`() {
        // GIVEN
        val stepA = step()
        val stepB = step()
        val stepC = step()
        val gameSteps = GameSteps(stepA, stepB)

        // THEN
        val expectedResult = GameSteps(stepA, stepB, stepC)

        assertThat(gameSteps + stepC).isEqualTo(expectedResult)
    }

    @Test
    fun `Should revert a game steps decorator containing zero steps`() {
        // GIVEN
        val gameSteps = GameSteps()

        // THEN
        assertThat(gameSteps.revert()).isEqualTo(GameSteps())
    }

    @Test
    fun `Should revert a game steps decorator containing a single valid step`() {
        // GIVEN
        val step = validStep()
        val gameSteps = GameSteps(step)

        // THEN
        assertThat(gameSteps.revert()).isEqualTo(GameSteps())
    }

    @Test
    fun `Should revert a game steps decorator containing a single invalid step`() {
        // GIVEN
        val step = invalidStep()
        val gameSteps = GameSteps(step)

        // THEN
        assertThat(gameSteps.revert()).isEqualTo(GameSteps())
    }

    @Test
    fun `Should revert a game steps decorator containing several valid steps`() {
        // GIVEN
        val stepA = validStep()
        val stepB = validStep()
        val stepC = validStep()
        val gameSteps = GameSteps(stepA, stepB, stepC)

        // THEN
        assertThat(gameSteps.revert()).isEqualTo(GameSteps(stepA, stepB))
    }

    @Test
    fun `Should revert a game steps decorator containing several invalid steps`() {
        // GIVEN
        val stepA = invalidStep()
        val stepB = invalidStep()
        val stepC = invalidStep()
        val gameSteps = GameSteps(stepA, stepB, stepC)

        // THEN
        assertThat(gameSteps.revert()).isEqualTo(GameSteps())
    }

    @Test
    fun `Should revert a game steps decorator containing several valid and invalid steps`() {
        // GIVEN
        val stepA = validStep()
        val stepB = validStep()
        val stepC = invalidStep()
        val stepD = invalidStep()
        val stepE = validStep()
        val stepF = invalidStep()
        val stepG = invalidStep()
        val gameSteps = GameSteps(
                stepA,
                stepB,
                stepC,
                stepD,
                stepE,
                stepF,
                stepG)

        // THEN
        assertThat(gameSteps.revert()).isEqualTo(GameSteps(stepA, stepB))
    }

    private fun step(info: Info = mock(), origin: AnyOrigin = mock()): Step =
            mock {
                on { it.info } doReturn info
                on { it.origin } doReturn origin
            }

    private fun validStep() = ValidStep(mock(), mock())

    private fun invalidStep() = InvalidStep(mock(), mock())
}