package com.dg.eqs.core.competition

import com.dg.eqs.base.gamification.GoogleScoreBoardKey
import com.dg.eqs.core.progression.LevelKey.EventLevelKey
import com.dg.eqs.util.serialization.deserialize
import com.dg.eqs.util.serialization.serialize
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class EventTest {
    @Test
    fun should_serialize_and_deserialize_a_event() {
        // GIVEN
        val event = Event(1, EventLevelKey(0), GoogleScoreBoardKey("a"))

        // WHEN
        val deserializedEvent = deserialize<Event>(serialize(event))

        // THEN
        assertThat(deserializedEvent).isEqualTo(event)
    }
}