package com.dg.eqs.page.events

import com.dg.eqs.R
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.enveloping.HtmlRes
import com.dg.eqs.core.progression.Level.EventLevel
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
class EventItemBuilderTest {
    @InjectMocks
    private lateinit var builder: EventItemBuilder

    @Mock
    private lateinit var level: EventLevel
    @Mock
    private lateinit var levelExercise: AnyOrigin


    @Before
    fun setUp() {
        whenever(level.exercise) doReturn levelExercise
    }

    @Test
    fun `Should build a event item with no player score`() {
        // GIVEN
        whenever(level.gameSteps) doReturn 0
        whenever(level.bestSteps) doReturn 1

        // WHEN
        val item = builder.buildEventItem(level)

        // THEN
        assertThat(item).isEqualTo(EventItem(
                levelExercise,
                HtmlRes(R.string.events_item_no_player_score),
                HtmlRes(R.string.events_item_set_top_score, 1)))
    }

    @Test
    fun `Should build a event item with a worse player score`() {
        // GIVEN
        whenever(level.gameSteps) doReturn 2
        whenever(level.bestSteps) doReturn 1

        // WHEN
        val item = builder.buildEventItem(level)

        // THEN
        assertThat(item).isEqualTo(EventItem(
                levelExercise,
                HtmlRes(R.string.events_item_worse_player_score, 2),
                HtmlRes(R.string.events_item_set_top_score, 1)))
    }

    @Test
    fun `Should build a event item with a better player score`() {
        // GIVEN
        whenever(level.gameSteps) doReturn 1
        whenever(level.bestSteps) doReturn 2

        // WHEN
        val item = builder.buildEventItem(level)

        // THEN
        assertThat(item).isEqualTo(EventItem(
                levelExercise,
                HtmlRes(R.string.events_item_better_player_score, 1),
                HtmlRes(R.string.events_item_set_top_score, 2)))
    }

    @Test
    fun `Should build a event item with a equaling player score`() {
        // GIVEN
        whenever(level.gameSteps) doReturn 1
        whenever(level.bestSteps) doReturn 1

        // WHEN
        val item = builder.buildEventItem(level)

        // THEN
        assertThat(item).isEqualTo(EventItem(
                levelExercise,
                HtmlRes(R.string.events_item_better_player_score, 1),
                HtmlRes(R.string.events_item_set_top_score, 1)))
    }

    @Test
    fun `Should build a event item with no top score`() {
        // GIVEN
        whenever(level.gameSteps) doReturn 0
        whenever(level.bestSteps) doReturn 0

        // WHEN
        val item = builder.buildEventItem(level)

        // THEN
        assertThat(item).isEqualTo(EventItem(
                levelExercise,
                HtmlRes(R.string.events_item_no_player_score),
                HtmlRes(R.string.events_item_no_top_score)))
    }

    @Test
    fun `Should build a event item with a set top score`() {
        // GIVEN
        whenever(level.gameSteps) doReturn 0
        whenever(level.bestSteps) doReturn 1

        // WHEN
        val item = builder.buildEventItem(level)

        // THEN
        assertThat(item).isEqualTo(EventItem(levelExercise,
                HtmlRes(R.string.events_item_no_player_score),
                HtmlRes(R.string.events_item_set_top_score, 1)))
    }
}