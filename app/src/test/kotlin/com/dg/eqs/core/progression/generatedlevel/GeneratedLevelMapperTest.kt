package com.dg.eqs.core.progression.generatedlevel

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.mapping.OriginToStringMapper
import com.dg.eqs.core.mapping.StringToOriginMapper
import com.dg.eqs.core.progression.Level.GeneratedLevel
import com.dg.eqs.core.progression.LevelKey.GeneratedLevelKey
import com.dg.eqs.core.progression.generatedlevel.roomdatabase.GeneratedLevelRoomEntity
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
class GeneratedLevelMapperTest {
    private companion object {
        private const val EXERCISE = "+±[+1,+2,+3]"
    }

    @Mock
    private lateinit var stringToOriginMapper: StringToOriginMapper
    @Mock
    private lateinit var originToStringMapper: OriginToStringMapper
    @InjectMocks
    private lateinit var generatedLevelMapper: GeneratedLevelMapper

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
    fun `Should map a generated level room entity to a generated level`() {
        // GIVEN
        val entity = GeneratedLevelRoomEntity(1, EXERCISE, true, 3, 2)

        // THEN
        val expectedLevel = GeneratedLevel(GeneratedLevelKey(1), exercise, true, 3, 2)

        assertThat(generatedLevelMapper.mapToLevel(entity)).isEqualTo(expectedLevel)
    }

    @Test
    fun `Should map a generated level to a generated level room entity`() {
        // GIVEN
        val level = GeneratedLevel(GeneratedLevelKey(1), exercise, true, 3, 2)

        // THEN
        val expectedEntity = GeneratedLevelRoomEntity(1, EXERCISE, true, 3, 2)

        assertThat(generatedLevelMapper.mapToEntity(level)).isEqualTo(expectedEntity)
    }
}