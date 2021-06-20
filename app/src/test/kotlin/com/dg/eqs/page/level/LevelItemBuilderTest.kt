package com.dg.eqs.page.level

import com.dg.eqs.R
import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.enveloping.StringRes
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LevelItemBuilderTest {
    @InjectMocks
    private lateinit var builder: LevelItemBuilder

    @Mock
    private lateinit var level: AnyLevel
    @Mock
    private lateinit var levelExercise: AnyOrigin


    @Before
    fun setUp() {
        whenever(level.exercise) doReturn levelExercise
    }

    @Test
    fun `Should build a level item from a level finished with no steps`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 0

        // WHEN
        val item = builder.buildLevelItem(level)

        // THEN
        assertThat(item).isEqualTo(LevelItem(
                levelExercise,
                StringRes(R.string.level_item_no_steps)))
    }

    @Test
    fun `Should build a level item from a level finished in one step`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 1

        // WHEN
        val item = builder.buildLevelItem(level)

        // THEN
        assertThat(item).isEqualTo(LevelItem(
                levelExercise,
                StringRes(R.string.level_item_one_step)))
    }

    @Test
    fun `Should build a level item from a level finished in some steps`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 99

        // WHEN
        val item = builder.buildLevelItem(level)

        // THEN
        assertThat(item).isEqualTo(LevelItem(
                levelExercise,
                StringRes(R.string.level_item_some_steps, 99)))
    }

    @Test
    fun `Should build a level item from a level finished in many steps`() {
        // GIVEN
        whenever(level.bestSteps) doReturn 100

        // WHEN
        val item = builder.buildLevelItem(level)

        // THEN
        assertThat(item).isEqualTo(LevelItem(
                levelExercise,
                StringRes(R.string.level_item_many_steps)))
    }
}