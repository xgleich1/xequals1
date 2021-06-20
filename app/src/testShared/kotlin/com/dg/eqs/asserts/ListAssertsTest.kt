package com.dg.eqs.asserts

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ListAssertsTest {
    @Test
    fun a_list_content_equal_by_identity_assert_succeeds_when_the_content_of_two_lists_is_equal_by_identity() {
        // GIVEN
        val elementA = Integer(1)
        val elementB = Integer(1)
        val listA = listOf(elementA, elementB)
        val listB = listOf(elementA, elementB)

        // THEN
        assertThat(listA).isContentEqualByIdentityTo(listB)
    }

    @Test(expected = AssertionError::class)
    fun a_list_content_equal_by_identity_assert_fails_when_the_content_of_two_lists_differs_in_quantity() {
        // GIVEN
        val elementA = Integer(1)
        val elementB = Integer(1)
        val listA = listOf(elementA)
        val listB = listOf(elementA, elementB)

        // THEN
        assertThat(listA).isContentEqualByIdentityTo(listB)
    }

    @Test(expected = AssertionError::class)
    fun a_list_content_equals_by_identity_assert_fails_when_the_content_of_two_lists_is_equal_but_not_by_identity() {
        // GIVEN
        val elementA = Integer(1)
        val elementB = Integer(1)
        val listA = listOf(elementA, elementB)
        val listB = listOf(elementA, Integer(1))

        // THEN
        assertThat(listA).isContentEqualByIdentityTo(listB)
    }
}