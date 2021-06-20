package com.dg.eqs.core.progression.eventlevel

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.mapping.OriginToStringMapper
import com.dg.eqs.core.mapping.StringToOriginMapper
import com.dg.eqs.core.progression.Level.EventLevel
import com.dg.eqs.core.progression.LevelKey.EventLevelKey
import com.dg.eqs.core.progression.eventlevel.roomdatabase.EventLevelRoomEntity
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
class EventLevelMapperTest {
    private companion object {
        private const val EXERCISE = "+±[+1,+2,+3]"
    }

    @Mock
    private lateinit var stringToOriginMapper: StringToOriginMapper
    @Mock
    private lateinit var originToStringMapper: OriginToStringMapper
    @InjectMocks
    private lateinit var eventLevelMapper: EventLevelMapper

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
    fun `Should map a event level room entity to a event level`() {
        // GIVEN
        val entity = EventLevelRoomEntity(1, EXERCISE, true, 3, 2)

        // THEN
        val expectedLevel = EventLevel(EventLevelKey(1), exercise, true, 3, 2)

        assertThat(eventLevelMapper.mapToLevel(entity)).isEqualTo(expectedLevel)
    }

    @Test
    fun `Should map a event level to a event level room entity`() {
        // GIVEN
        val level = EventLevel(EventLevelKey(1), exercise, true, 3, 2)

        // THEN
        val expectedEntity = EventLevelRoomEntity(1, EXERCISE, true, 3, 2)

        assertThat(eventLevelMapper.mapToEntity(level)).isEqualTo(expectedEntity)
    }
}