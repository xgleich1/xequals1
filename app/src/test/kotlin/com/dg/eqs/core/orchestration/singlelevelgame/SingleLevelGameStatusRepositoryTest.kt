package com.dg.eqs.core.orchestration.singlelevelgame

import com.dg.eqs.R
import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.core.orchestration.GameSteps
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SingleLevelGameStatusRepositoryTest {
    @InjectMocks
    private lateinit var repository: SingleLevelGameStatusRepository

    @Mock
    private lateinit var level: AnyLevel
    @Mock
    private lateinit var steps: GameSteps


    @Test
    fun `Should load the initial status for a level finished with no steps`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 0

        // WHEN
        val status = repository.loadInitialStatus(level)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_initial_status_no_steps))
    }

    @Test
    fun `Should load the initial status for a level finished in one step`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 1

        // WHEN
        val status = repository.loadInitialStatus(level)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_initial_status_one_step))
    }

    @Test
    fun `Should load the initial status for a level finished in some steps`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 99

        // WHEN
        val status = repository.loadInitialStatus(level)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_initial_status_some_steps, 99))
    }

    @Test
    fun `Should load the initial status for a level finished in many steps`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 100

        // WHEN
        val status = repository.loadInitialStatus(level)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_initial_status_many_steps))
    }

    @Test
    fun `Should load the initial status as long as there are no steps`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 1
        whenever(steps.validStepsCount) doReturn 0

        // WHEN
        val status = repository.loadEnsuingStatus(level, steps)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_initial_status_one_step))
    }

    @Test
    fun `Should load the ensuing status when there's at least one step`() {
        // GIVEN
        whenever(steps.validStepsCount) doReturn 1

        // WHEN
        val status = repository.loadEnsuingStatus(level, steps)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_ensuing_status_some_steps, 1))
    }

    @Test
    fun `Should load the ensuing status when there are some steps`() {
        // GIVEN
        whenever(steps.validStepsCount) doReturn 99

        // WHEN
        val status = repository.loadEnsuingStatus(level, steps)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_ensuing_status_some_steps, 99))
    }

    @Test
    fun `Should load the ensuing status when there are many steps`() {
        // GIVEN
        whenever(steps.validStepsCount) doReturn 100

        // WHEN
        val status = repository.loadEnsuingStatus(level, steps)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_ensuing_status_many_steps))
    }

    @Test
    fun `Should load the ceasing status for a level finished in one step`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 0
        whenever(steps.validStepsCount) doReturn 1

        // WHEN
        val status = repository.loadCeasingStatus(level, steps)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_ceasing_status_one_step))
    }

    @Test
    fun `Should load the ceasing status for a level finished in some steps`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 0
        whenever(steps.validStepsCount) doReturn 99

        // WHEN
        val status = repository.loadCeasingStatus(level, steps)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_ceasing_status_some_steps, 99))
    }

    @Test
    fun `Should load the ceasing status for a level finished in many steps`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 0
        whenever(steps.validStepsCount) doReturn 100

        // WHEN
        val status = repository.loadCeasingStatus(level, steps)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_ceasing_status_many_steps))
    }

    @Test
    fun `Should load the ceasing status for a level finished in one step less`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 2
        whenever(steps.validStepsCount) doReturn 1

        // WHEN
        val status = repository.loadCeasingStatus(level, steps)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_ceasing_status_one_step_less))
    }

    @Test
    fun `Should load the ceasing status for a level finished in some steps less`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 100
        whenever(steps.validStepsCount) doReturn 1

        // WHEN
        val status = repository.loadCeasingStatus(level, steps)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_ceasing_status_some_steps_less, 99))
    }

    @Test
    fun `Should load the ceasing status for a level finished in many steps less`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 101
        whenever(steps.validStepsCount) doReturn 1

        // WHEN
        val status = repository.loadCeasingStatus(level, steps)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_ceasing_status_many_steps_less))
    }

    @Test
    fun `Should load the ceasing status for a level finished in one step more`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 1
        whenever(steps.validStepsCount) doReturn 2

        // WHEN
        val status = repository.loadCeasingStatus(level, steps)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_ceasing_status_one_step_more))
    }

    @Test
    fun `Should load the ceasing status for a level finished in some steps more`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 1
        whenever(steps.validStepsCount) doReturn 100

        // WHEN
        val status = repository.loadCeasingStatus(level, steps)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_ceasing_status_some_steps_more, 99))
    }

    @Test
    fun `Should load the ceasing status for a level finished in many steps more`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 1
        whenever(steps.validStepsCount) doReturn 101

        // WHEN
        val status = repository.loadCeasingStatus(level, steps)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_ceasing_status_many_steps_more))
    }

    @Test
    fun `Should load the ceasing status for a level finished in equal steps`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 1
        whenever(steps.validStepsCount) doReturn 1

        // WHEN
        val status = repository.loadCeasingStatus(level, steps)

        // THEN
        assertThat(status).isEqualTo(StringRes(
                R.string.game_ceasing_status_equal_steps))
    }
}