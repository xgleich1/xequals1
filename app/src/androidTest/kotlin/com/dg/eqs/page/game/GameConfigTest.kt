package com.dg.eqs.page.game

import com.dg.eqs.core.progression.LevelKey.*
import com.dg.eqs.page.game.GameConfig.EndlessLevelGameConfig
import com.dg.eqs.page.game.GameConfig.SingleLevelGameConfig
import com.dg.eqs.util.serialization.deserialize
import com.dg.eqs.util.serialization.serialize
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class GameConfigTest {
    @Test
    fun should_serialize_and_deserialize_a_endless_level_game_config() {
        // GIVEN
        val config = EndlessLevelGameConfig

        // WHEN
        val deserializedConfig =
                deserialize<EndlessLevelGameConfig>(serialize(config))

        // THEN
        assertThat(deserializedConfig).isEqualTo(config)
    }

    @Test
    fun should_serialize_and_deserialize_a_single_event_level_game_config() {
        // GIVEN
        val config = SingleLevelGameConfig(EventLevelKey(1))

        // WHEN
        val deserializedConfig =
                deserialize<SingleLevelGameConfig>(serialize(config))

        // THEN
        assertThat(deserializedConfig).isEqualTo(config)
    }

    @Test
    fun should_serialize_and_deserialize_a_single_preset_level_game_config() {
        // GIVEN
        val config = SingleLevelGameConfig(PresetLevelKey(1))

        // WHEN
        val deserializedConfig =
                deserialize<SingleLevelGameConfig>(serialize(config))

        // THEN
        assertThat(deserializedConfig).isEqualTo(config)
    }

    @Test
    fun should_serialize_and_deserialize_a_single_generated_level_game_config() {
        // GIVEN
        val config = SingleLevelGameConfig(GeneratedLevelKey(1))

        // WHEN
        val deserializedConfig =
                deserialize<SingleLevelGameConfig>(serialize(config))

        // THEN
        assertThat(deserializedConfig).isEqualTo(config)
    }

    @Test
    fun should_compare_two_equal_single_level_game_configs() {
        // GIVEN
        val configA = SingleLevelGameConfig(PresetLevelKey(1))
        val configB = SingleLevelGameConfig(PresetLevelKey(1))

        // THEN
        assertThat(configA).isEqualTo(configB)
    }
}