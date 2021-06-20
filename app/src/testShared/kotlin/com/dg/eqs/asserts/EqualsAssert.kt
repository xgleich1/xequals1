package com.dg.eqs.asserts

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue


fun <T> assert_equals_by_value_only(objectA: T?, objectB: T) {
    assertTrue(objectA !== objectB)
    assertEquals(objectB, objectA)
}