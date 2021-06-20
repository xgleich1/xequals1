package com.dg.eqs.core.progression

import com.dg.eqs.core.progression.LevelKey.*
import com.dg.eqs.util.serialization.deserialize
import com.dg.eqs.util.serialization.serialize
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class LevelKeyTest {
    @Test
    fun should_serialize_and_deserialize_a_event_level_key() {
        // GIVEN
        val levelKey = EventLevelKey(1)

        // WHEN
        val deserializedLevelKey =
                deserialize<EventLevelKey>(serialize(levelKey))

        // THEN
        assertThat(deserializedLevelKey).isEqualTo(levelKey)
    }

    @Test
    fun should_serialize_and_deserialize_a_preset_level_key() {
        // GIVEN
        val levelKey = PresetLevelKey(1)

        // WHEN
        val deserializedLevelKey =
                deserialize<PresetLevelKey>(serialize(levelKey))

        // THEN
        assertThat(deserializedLevelKey).isEqualTo(levelKey)
    }

    @Test
    fun should_serialize_and_deserialize_a_generated_level_key() {
        // GIVEN
        val levelKey = GeneratedLevelKey(1)

        // WHEN
        val deserializedLevelKey =
                deserialize<GeneratedLevelKey>(serialize(levelKey))

        // THEN
        assertThat(deserializedLevelKey).isEqualTo(levelKey)
    }
}