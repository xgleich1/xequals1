package com.dg.eqs.page.events

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.enveloping.HtmlRes
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EventItemDifferTest {
    @Mock
    private lateinit var exerciseA: AnyOrigin
    @Mock
    private lateinit var exerciseB: AnyOrigin
    @Mock
    private lateinit var playerScoreA: HtmlRes
    @Mock
    private lateinit var playerScoreB: HtmlRes
    @Mock
    private lateinit var topScoreA: HtmlRes
    @Mock
    private lateinit var topScoreB: HtmlRes


    @Test
    fun `Two event items with the same exercises are treated as the same when diffed`() {
        // GIVEN
        val itemA = EventItem(exerciseA, playerScoreA, topScoreA)
        val itemB = EventItem(exerciseA, playerScoreB, topScoreB)

        // THEN
        assertThat(EventItemDiffer.areItemsTheSame(itemA, itemB)).isTrue()
    }

    @Test
    fun `Two event items with different exercises are not treated as the same when diffed`() {
        // GIVEN
        val itemA = EventItem(exerciseA, playerScoreA, topScoreA)
        val itemB = EventItem(exerciseB, playerScoreA, topScoreA)

        // THEN
        assertThat(EventItemDiffer.areItemsTheSame(itemA, itemB)).isFalse()
    }

    @Test
    fun `Two event items with the same content are treated as the same when diffed`() {
        // GIVEN
        val itemA = EventItem(exerciseA, playerScoreA, topScoreA)
        val itemB = EventItem(exerciseA, playerScoreA, topScoreA)

        // THEN
        assertThat(EventItemDiffer.areContentsTheSame(itemA, itemB)).isTrue()
    }

    @Test
    fun `Two event items with different content are not treated as the same when diffed`() {
        // GIVEN
        val itemA = EventItem(exerciseA, playerScoreA, topScoreA)
        val itemB = EventItem(exerciseB, playerScoreB, topScoreB)

        // THEN
        assertThat(EventItemDiffer.areContentsTheSame(itemA, itemB)).isFalse()
    }
}