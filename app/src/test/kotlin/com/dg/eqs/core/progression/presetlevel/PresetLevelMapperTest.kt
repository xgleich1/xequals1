package com.dg.eqs.core.progression.presetlevel

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.mapping.OriginToStringMapper
import com.dg.eqs.core.mapping.StringToOriginMapper
import com.dg.eqs.core.progression.Level.PresetLevel
import com.dg.eqs.core.progression.LevelKey.PresetLevelKey
import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomEntity
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
class PresetLevelMapperTest {
    private companion object {
        private const val EXERCISE = "+±[+1,+2,+3]"
    }

    @Mock
    private lateinit var stringToOriginMapper: StringToOriginMapper
    @Mock
    private lateinit var originToStringMapper: OriginToStringMapper
    @InjectMocks
    private lateinit var presetLevelMapper: PresetLevelMapper

    @Mock
    private lateinit var exercise: AnyOrigin


    @Before
    fun setUp() {
        whenever(stringToOriginMapper
                .mapToOrigin<AnyOrigin>(EXERCISE)) doReturn exercise

        whenever(originToStringMapper
                .mapToString(exercise)) doReturn EXERCISE
    }

    @Test
    fun `Should map a preset level room entity to a preset level`() {
        // GIVEN
        val entity = PresetLevelRoomEntity(1, EXERCISE, true, 3, 2)

        // THEN
        val expectedLevel = PresetLevel(PresetLevelKey(1), exercise, true, 3, 2)

        assertThat(presetLevelMapper.mapToLevel(entity)).isEqualTo(expectedLevel)
    }

    @Test
    fun `Should map a preset level to a preset level room entity`() {
        // GIVEN
        val level = PresetLevel(PresetLevelKey(1), exercise, true, 3, 2)

        // THEN
        val expectedEntity = PresetLevelRoomEntity(1, EXERCISE, true, 3, 2)

        assertThat(presetLevelMapper.mapToEntity(level)).isEqualTo(expectedEntity)
    }
}