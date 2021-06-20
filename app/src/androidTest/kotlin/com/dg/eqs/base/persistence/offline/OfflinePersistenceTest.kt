package com.dg.eqs.base.persistence.offline

import com.dg.eqs.util.classes.TestSharedPreferencesBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class OfflinePersistenceTest {
    private lateinit var persistence: OfflinePersistence


    @Before
    fun setUp() {
        persistence = OfflinePersistence(
                TestSharedPreferencesBuilder())
    }

    @After
    fun tearDown() = persistence.deleteAllValues()

    @Test
    fun should_persist_a_boolean() {
        // WHEN
        persistence.saveBoolean(TestKeyA, true)

        // THEN
        assertTrue(persistence.loadBoolean(TestKeyA, false))
    }

    @Test
    fun should_return_the_default_when_a_boolean_is_not_persisted() {
        assertTrue(persistence.loadBoolean(TestKeyA, true))
        assertFalse(persistence.loadBoolean(TestKeyA, false))
    }

    @Test
    fun should_persist_an_integer() {
        // WHEN
        persistence.saveInteger(TestKeyA, 1)

        // THEN
        assertThat(persistence.loadInteger(TestKeyA, 0)).isEqualTo(1)
    }

    @Test
    fun should_return_the_default_when_an_integer_is_not_persisted() {
        assertThat(persistence.loadInteger(TestKeyA, 0)).isEqualTo(0)
        assertThat(persistence.loadInteger(TestKeyA, 1)).isEqualTo(1)
    }

    @Test
    fun should_delete_all_persisted_values() {
        // GIVEN
        persistence.saveBoolean(TestKeyA, true)
        persistence.saveBoolean(TestKeyB, true)

        // WHEN
        persistence.deleteAllValues()

        // THEN
        assertFalse(persistence.loadBoolean(TestKeyA, false))
        assertFalse(persistence.loadBoolean(TestKeyB, false))
    }

    private object TestKeyA : OfflinePersistenceKey("test_key_a")

    private object TestKeyB : OfflinePersistenceKey("test_key_b")
}