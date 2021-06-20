package com.dg.eqs.asserts

import org.assertj.core.api.ListAssert


fun <E> ListAssert<E>.isContentEqualByIdentityTo(expected: List<E>): ListAssert<E> {
    hasSize(expected.size)

    expected.forEachIndexed { index, expectedElement ->
        element(index).isSameAs(expectedElement)
    }

    return this
}