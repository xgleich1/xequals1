package com.dg.eqs.core.progression

import com.dg.eqs.asserts.assert_equals_by_value_only
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.progression.Level.*
import com.dg.eqs.core.progression.LevelKey.*
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LevelTest {
    @Mock
    private lateinit var exerciseA: AnyOrigin
    @Mock
    private lateinit var exerciseB: AnyOrigin


    //<editor-fold desc="Event Level">
    @Test
    fun `Should compare two equal event level`() {
        // GIVEN
        val levelA = EventLevel(EventLevelKey(1), exerciseA, false, 0, 2)
        val levelB = EventLevel(EventLevelKey(1), exerciseA, false, 0, 2)

        // THEN
        assertThat(levelA).isEqualTo(levelB)
    }

    @Test
    fun `Should convert a event level to a string`() {
        // GIVEN
        whenever(exerciseA.toString()) doReturn "+±[+1,+2,+3]"

        val level = EventLevel(EventLevelKey(1), exerciseA, false, 0, 2)

        // THEN
        val expectedString = "EventLevel(" +
                "EventLevelKey(rawKey=1), +±[+1,+2,+3], false, 0, 2)"

        assertThat(level).hasToString(expectedString)
    }

    @Test
    fun `Should copy a event level`() {
        // GIVEN
        val level = EventLevel(EventLevelKey(1), exerciseA, false, 0, 2)

        // THEN
        assert_equals_by_value_only(level.copy(), level)
    }

    @Test
    fun `Should copy a event level providing new values`() {
        // GIVEN
        val level = EventLevel(EventLevelKey(1), exerciseA, false, 4, 2)

        // WHEN
        val copy = level.copy(
                levelKey = EventLevelKey(3),
                exercise = exerciseB,
                finished = true,
                gameSteps = 2,
                bestSteps = 1)

        // THEN
        val expectedCopy = EventLevel(EventLevelKey(3), exerciseB, true, 2, 1)

        assertThat(copy).isEqualTo(expectedCopy)
    }
    //</editor-fold>

    //<editor-fold desc="Preset Level">
    @Test
    fun `Should compare two equal preset level`() {
        // GIVEN
        val levelA = PresetLevel(PresetLevelKey(1), exerciseA, false, 0, 2)
        val levelB = PresetLevel(PresetLevelKey(1), exerciseA, false, 0, 2)

        // THEN
        assertThat(levelA).isEqualTo(levelB)
    }

    @Test
    fun `Should convert a preset level to a string`() {
        // GIVEN
        whenever(exerciseA.toString()) doReturn "+±[+1,+2,+3]"

        val level = PresetLevel(PresetLevelKey(1), exerciseA, false, 0, 2)

        // THEN
        val expectedString = "PresetLevel(" +
                "PresetLevelKey(rawKey=1), +±[+1,+2,+3], false, 0, 2)"

        assertThat(level).hasToString(expectedString)
    }

    @Test
    fun `Should copy a preset level`() {
        // GIVEN
        val level = PresetLevel(PresetLevelKey(1), exerciseA, false, 0, 2)

        // THEN
        assert_equals_by_value_only(level.copy(), level)
    }

    @Test
    fun `Should copy a preset level providing new values`() {
        // GIVEN
        val level = PresetLevel(PresetLevelKey(1), exerciseA, false, 4, 2)

        // WHEN
        val copy = level.copy(
                levelKey = PresetLevelKey(3),
                exercise = exerciseB,
                finished = true,
                gameSteps = 2,
                bestSteps = 1)

        // THEN
        val expectedCopy = PresetLevel(PresetLevelKey(3), exerciseB, true, 2, 1)

        assertThat(copy).isEqualTo(expectedCopy)
    }
    //</editor-fold>

    //<editor-fold desc="Generated Level">
    @Test
    fun `Should compare two equal generated level`() {
        // GIVEN
        val levelA = GeneratedLevel(GeneratedLevelKey(1), exerciseA, false, 0, 2)
        val levelB = GeneratedLevel(GeneratedLevelKey(1), exerciseA, false, 0, 2)

        // THEN
        assertThat(levelA).isEqualTo(levelB)
    }

    @Test
    fun `Should convert a generated level to a string`() {
        // GIVEN
        whenever(exerciseA.toString()) doReturn "+±[+1,+2,+3]"

        val level = GeneratedLevel(GeneratedLevelKey(1), exerciseA, false, 0, 2)

        // THEN
        val expectedString = "GeneratedLevel(" +
                "GeneratedLevelKey(rawKey=1), +±[+1,+2,+3], false, 0, 2)"

        assertThat(level).hasToString(expectedString)
    }

    @Test
    fun `Should copy a generated level`() {
        // GIVEN
        val level = GeneratedLevel(GeneratedLevelKey(1), exerciseA, false, 0, 2)

        // THEN
        assert_equals_by_value_only(level.copy(), level)
    }

    @Test
    fun `Should copy a generated level providing new values`() {
        // GIVEN
        val level = GeneratedLevel(GeneratedLevelKey(1), exerciseA, false, 4, 2)

        // WHEN
        val copy = level.copy(
                levelKey = GeneratedLevelKey(3),
                exercise = exerciseB,
                finished = true,
                gameSteps = 2,
                bestSteps = 1)

        // THEN
        val expectedCopy = GeneratedLevel(GeneratedLevelKey(3), exerciseB, true, 2, 1)

        assertThat(copy).isEqualTo(expectedCopy)
    }
    //</editor-fold>
}