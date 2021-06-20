package com.dg.eqs.base.persistence.offline

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class OfflinePersistenceKeyTest {
    @Test
    fun `Should compare two equal offline persistence keys`() {
        // GIVEN
        val keyA = key("test_key")
        val keyB = key("test_key")

        // THEN
        assertThat(keyA).isEqualTo(keyB)
    }

    @Test
    fun `Should convert a offline persistence key to a string`() {
        // GIVEN
        val key = key("test_key")

        // THEN
        assertThat(key).hasToString("OfflinePersistenceKey(test_key)")
    }

    private fun key(rawKey: String) = object : OfflinePersistenceKey(rawKey) {}
}