package com.dg.eqs.page.level

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.enveloping.StringRes
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LevelItemDifferTest {
    @Mock
    private lateinit var exerciseA: AnyOrigin
    @Mock
    private lateinit var exerciseB: AnyOrigin
    @Mock
    private lateinit var stepsA: StringRes
    @Mock
    private lateinit var stepsB: StringRes


    @Test
    fun `Two level items with the same exercises are treated as the same when diffed`() {
        // GIVEN
        val itemA = LevelItem(exerciseA, stepsA)
        val itemB = LevelItem(exerciseA, stepsB)

        // THEN
        assertThat(LevelItemDiffer.areItemsTheSame(itemA, itemB)).isTrue()
    }

    @Test
    fun `Two level items with different exercises are not treated as the same when diffed`() {
        // GIVEN
        val itemA = LevelItem(exerciseA, stepsA)
        val itemB = LevelItem(exerciseB, stepsA)

        // THEN
        assertThat(LevelItemDiffer.areItemsTheSame(itemA, itemB)).isFalse()
    }

    @Test
    fun `Two level items with the same content are treated as the same when diffed`() {
        // GIVEN
        val itemA = LevelItem(exerciseA, stepsA)
        val itemB = LevelItem(exerciseA, stepsA)

        // THEN
        assertThat(LevelItemDiffer.areContentsTheSame(itemA, itemB)).isTrue()
    }

    @Test
    fun `Two level items with different content are not treated as the same when diffed`() {
        // GIVEN
        val itemA = LevelItem(exerciseA, stepsA)
        val itemB = LevelItem(exerciseB, stepsB)

        // THEN
        assertThat(LevelItemDiffer.areContentsTheSame(itemA, itemB)).isFalse()
    }
}