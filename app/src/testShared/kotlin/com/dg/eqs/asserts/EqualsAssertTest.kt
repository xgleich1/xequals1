package com.dg.eqs.asserts

import org.junit.Test


class EqualsAssertTest {
    @Test
    fun a_equals_by_value_only_assert_succeeds_when_two_objects_are_equal_by_value_only() {
        // GIVEN
        val objectA = Integer(1)
        val objectB = Integer(1)

        // THEN
        assert_equals_by_value_only(objectA, objectB)
    }

    @Test(expected = AssertionError::class)
    fun a_equals_by_value_only_assert_fails_when_two_objects_are_not_equal_by_value() {
        // GIVEN
        val objectA = Integer(1)
        val objectB = Integer(2)

        // WHEN
        assert_equals_by_value_only(objectA, objectB)
    }

    @Test(expected = AssertionError::class)
    fun a_equals_by_value_only_assert_fails_when_two_objects_are_equal_by_reference() {
        // GIVEN
        val objectA = Integer(1)

        // WHEN
        assert_equals_by_value_only(objectA, objectA)
    }
}