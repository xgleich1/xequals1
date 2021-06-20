package com.dg.eqs.base.extension

import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.*
import org.junit.Test


class ListExtTest {
    @Test(expected = Exception::class)
    fun `Should throw an exception when the element provided by the property to access a single one is not single`() {
        listOf(1, 2).single
    }

    @Test
    fun `Should provide a property to access the single element of a list`() {
        assertThat(listOf(1).single).isEqualTo(1)
    }

    @Test
    fun `Should provide a property to access the first element of a list`() {
        assertThat(listOf(1, 2).first).isEqualTo(1)
    }

    @Test
    fun `Should provide a property to access the second element of a list`() {
        assertThat(listOf(1, 2, 3).second).isEqualTo(2)
    }

    @Test
    fun `Should provide a property to access the third element of a list`() {
        assertThat(listOf(1, 2, 3, 4).third).isEqualTo(3)
    }

    @Test
    fun `Should provide a property to access the fourth element of a list`() {
        assertThat(listOf(1, 2, 3, 4, 5).fourth).isEqualTo(4)
    }

    @Test
    fun `Should provide a property to access the last element of a list`() {
        assertThat(listOf(1, 2).last).isEqualTo(2)
    }

    @Test
    fun `A list only indicates that its a singleton when its containing exactly one element`() {
        // GIVEN
        val zeroElementsList = listOf<Int>()
        val singleElementList = listOf(1)
        val twoElementsList = listOf(1, 2)

        // THEN
        assertFalse(zeroElementsList.isSingle)
        assertTrue(singleElementList.isSingle)
        assertFalse(twoElementsList.isSingle)
    }

    @Test
    fun `A list indicates that its not a singleton every time its not containing exactly one element`() {
        // GIVEN
        val zeroElementsList = listOf<Int>()
        val singleElementList = listOf(1)
        val twoElementsList = listOf(1, 2)

        // THEN
        assertTrue(zeroElementsList.isNotSingle)
        assertFalse(singleElementList.isNotSingle)
        assertTrue(twoElementsList.isNotSingle)
    }

    @Test
    fun `Should mix two lists by inserting the elements of the second between the elements of the first`() {
        // GIVEN
        val listA = listOf(1, 2, 3)
        val listB = listOf(4, 5)

        // THEN
        val expectedResult = listOf(1, 4, 2, 5, 3)

        assertThat(listA.mix(listB)).isEqualTo(expectedResult)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw an exception when mixing two lists is not possible due to their incompatible sizes`() {
        // GIVEN
        val listA = listOf(1, 2)
        val listB = listOf(3, 4, 5)

        // WHEN
        listA.mix(listB)
    }

    @Test
    fun `Should find and return the searched distinct pair`() {
        // GIVEN
        val list = listOf("a", "b", "c", "d", "e")

        // WHEN
        val foundPair = list.findDistinctPair {
            left == "b"
        }

        // THEN
        assertThat(foundPair).isEqualTo("b" and "c")
    }

    @Test
    fun `Should return null when the searched distinct pair couldn't be found`() {
        // GIVEN
        val list = listOf("a", "b", "c", "d", "e")

        // WHEN
        val foundPair = list.findDistinctPair {
            left == "e"
        }

        // THEN
        assertNull(foundPair)
    }

    @Test
    fun `Should visit all distinct pairs to find the searched one`() {
        // GIVEN
        val list = listOf("a", "b", "c", "d", "e")

        val visitedPairs = ArrayList<Pair<String, String>>()

        // WHEN
        list.findDistinctPair {
            visitedPairs += this

            false
        }

        // THEN
        val expectedVisitedPairs = listOf(
                "a" and "b",
                "a" and "c",
                "a" and "d",
                "a" and "e",

                "b" and "c",
                "b" and "d",
                "b" and "e",

                "c" and "d",
                "c" and "e",

                "d" and "e")

        assertThat(visitedPairs).isEqualTo(expectedVisitedPairs)
    }

    @Test
    fun `A list indicates when its containing the searched distinct pair`() {
        // GIVEN
        val list = listOf("a", "b", "c", "d", "e")

        // THEN
        assertTrue(list.containsDistinctPair { left == "b" })
    }

    @Test
    fun `A list indicates when its not containing the searched distinct pair`() {
        // GIVEN
        val list = listOf("a", "b", "c", "d", "e")

        // THEN
        assertFalse(list.containsDistinctPair { left == "e" })
    }

    @Test
    fun `Should visit all distinct pairs to decide if the searched one is contained`() {
        // GIVEN
        val list = listOf("a", "b", "c", "d", "e")

        val visitedPairs = ArrayList<Pair<String, String>>()

        // WHEN
        list.containsDistinctPair {
            visitedPairs += this

            false
        }

        // THEN
        val expectedVisitedPairs = listOf(
                "a" and "b",
                "a" and "c",
                "a" and "d",
                "a" and "e",

                "b" and "c",
                "b" and "d",
                "b" and "e",

                "c" and "d",
                "c" and "e",

                "d" and "e")

        assertThat(visitedPairs).isEqualTo(expectedVisitedPairs)
    }

    @Test
    fun `Two lists contents are not equal by identity when one has less elements than the other`() {
        // GIVEN
        val elementA = PositiveValue(1)
        val elementB = PositiveValue(1)

        val listA = listOf(elementA, elementB)
        val listB = listOf(elementA)

        // THEN
        assertFalse(listA.contentEqualsByIdentity(listB))
    }

    @Test
    fun `Two lists contents are not equal by identity when one has more elements than the other`() {
        // GIVEN
        val elementA = PositiveValue(1)
        val elementB = PositiveValue(1)
        val elementC = PositiveValue(1)

        val listA = listOf(elementA, elementB)
        val listB = listOf(elementA, elementB, elementC)

        // THEN
        assertFalse(listA.contentEqualsByIdentity(listB))
    }

    @Test
    fun `Two lists contents are not equal by identity when their elements are differently ordered`() {
        // GIVEN
        val elementA = PositiveValue(1)
        val elementB = PositiveValue(1)

        val listA = listOf(elementA, elementB)
        val listB = listOf(elementB, elementA)

        // THEN
        assertFalse(listA.contentEqualsByIdentity(listB))
    }

    @Test
    fun `Two lists contents are not equal by identity when they differ by at least one element`() {
        // GIVEN
        val elementA = PositiveValue(1)
        val elementB = PositiveValue(1)
        val elementC = PositiveValue(1)

        val listA = listOf(elementA, elementB)
        val listB = listOf(elementA, elementC)

        // THEN
        assertFalse(listA.contentEqualsByIdentity(listB))
    }

    @Test
    fun `Two lists contents are equal by identity when all their elements are equal by identity`() {
        // GIVEN
        val elementA = PositiveValue(1)
        val elementB = PositiveValue(1)

        val listA = listOf(elementA, elementB)
        val listB = listOf(elementA, elementB)

        // THEN
        assertTrue(listA.contentEqualsByIdentity(listB))
    }

    @Test
    fun `A list is not a monad of an instance when its size is different than one`() {
        // GIVEN
        val list = listOf(NegativeValue(1), PositiveValue(2))

        // THEN
        assertFalse(list.isMonad<NegativeValue>())
    }

    @Test
    fun `A list is not a monad of the given instance when its instance differs`() {
        // GIVEN
        val list = listOf(NegativeValue(1))

        // THEN
        assertFalse(list.isMonad<PositiveValue>())
    }

    @Test
    fun `A list is a monad of the given instance when its instance matches`() {
        // GIVEN
        val list = listOf(NegativeValue(1))

        // THEN
        assertTrue(list.isMonad<NegativeValue>())
    }

    @Test
    fun `A list is not a dyad of instances when its size is different than two`() {
        // GIVEN
        val list = listOf(NegativeValue(1), PositiveValue(2), NegativeValue(3))

        // THEN
        assertFalse(list.isDyad<NegativeValue, PositiveValue>())
    }

    @Test
    fun `A list is not a dyad of the given instances when its instances differ`() {
        // GIVEN
        val list = listOf(NegativeValue(1), PositiveValue(2))

        // THEN
        assertFalse(list.isDyad<PositiveValue, NegativeValue>())
    }

    @Test
    fun `A list is a dyad of the given instances when its instances match`() {
        // GIVEN
        val list = listOf(NegativeValue(1), PositiveValue(2))

        // THEN
        assertTrue(list.isDyad<NegativeValue, PositiveValue>())
    }

    @Test
    fun `A list is not a triad of instances when its size is different than three`() {
        // GIVEN
        val list = listOf(NegativeValue(1), PositiveValue(2))

        // THEN
        assertFalse(list.isTriad<NegativeValue, PositiveValue, NegativeValue>())
    }

    @Test
    fun `A list is not a triad of the given instances when its instances differ`() {
        // GIVEN
        val list = listOf(NegativeValue(1), PositiveValue(2), NegativeValue(3))

        // THEN
        assertFalse(list.isTriad<PositiveValue, NegativeValue, PositiveValue>())
    }

    @Test
    fun `A list is a triad of the given instances when its instances match`() {
        // GIVEN
        val list = listOf(NegativeValue(1), PositiveValue(2), NegativeValue(3))

        // THEN
        assertTrue(list.isTriad<NegativeValue, PositiveValue, NegativeValue>())
    }

    @Test
    fun `A list indicates when none of its elements has the desired instance`() {
        // GIVEN
        val list = listOf(NegativeValue(1), NegativeValue(2))

        // THEN
        assertFalse(list.any<PositiveValue>())
    }

    @Test
    fun `A list indicates when one of its elements has the desired instance`() {
        // GIVEN
        val list = listOf(NegativeValue(1), PositiveValue(2))

        // THEN
        assertTrue(list.any<PositiveValue>())
    }

    @Test
    fun `A list indicates when not all of its elements have the desired instance`() {
        // GIVEN
        val list = listOf(NegativeValue(1), PositiveValue(2))

        // THEN
        assertFalse(list.all<PositiveValue>())
    }

    @Test
    fun `A list indicates when all of its elements have the desired instance`() {
        // GIVEN
        val list = listOf(PositiveValue(1), PositiveValue(2))

        // THEN
        assertTrue(list.all<PositiveValue>())
    }

    @Test(expected = Exception::class)
    fun `Should throw an exception when there is no element found having the desired instance`() {
        // GIVEN
        val list = listOf(PositiveValue(1), PositiveValue(2))

        // WHEN
        list.find<NegativeValue>()
    }

    @Test
    fun `Should return the first element that has the desired instance`() {
        // GIVEN
        val elementWithDesiredInstance = PositiveValue(2)

        val list = listOf(NegativeValue(1), elementWithDesiredInstance, PositiveValue(3))

        // THEN
        assertThat(list.find<PositiveValue>()).isEqualTo(elementWithDesiredInstance)
    }

    @Test
    fun `Should return the last element that has the desired instance`() {
        // GIVEN
        val elementWithDesiredInstance = PositiveValue(2)

        val list = listOf(PositiveValue(1), elementWithDesiredInstance, NegativeValue(3))

        // THEN
        assertThat(list.findLast<PositiveValue>()).isEqualTo(elementWithDesiredInstance)
    }

    @Test(expected = Exception::class)
    fun `Should throw an exception when the single element with the desired instance is not single`() {
        // GIVEN
        val list = listOf(NegativeValue(1), NegativeValue(1))

        // WHEN
        list.typedSingle<NegativeValue>()
    }

    @Test(expected = Exception::class)
    fun `Should throw an exception when the single element doesn't have the desired instance`() {
        // GIVEN
        val list = listOf(NegativeValue(1))

        // WHEN
        list.typedSingle<PositiveValue>()
    }

    @Test
    fun `Should provide a typed shortcut to access the single element of a list`() {
        // GIVEN
        val singleElement = NegativeValue(1)

        val list = listOf(singleElement)

        // THEN
        assertThat(list.typedSingle<NegativeValue>()).isEqualTo(singleElement)
    }

    @Test(expected = Exception::class)
    fun `Should throw an exception when the first element doesn't have the desired instance`() {
        // GIVEN
        val list = listOf(NegativeValue(1))

        // WHEN
        list.typedFirst<PositiveValue>()
    }

    @Test
    fun `Should provide a typed shortcut to access the first element of a list`() {
        // GIVEN
        val firstElement = NegativeValue(1)

        val list = listOf(firstElement)

        // THEN
        assertThat(list.typedFirst<NegativeValue>()).isEqualTo(firstElement)
    }

    @Test(expected = Exception::class)
    fun `Should throw an exception when the second element doesn't have the desired instance`() {
        // GIVEN
        val list = listOf(PositiveValue(1), NegativeValue(1))

        // WHEN
        list.typedSecond<PositiveValue>()
    }

    @Test
    fun `Should provide a typed shortcut to access the second element of a list`() {
        // GIVEN
        val secondElement = NegativeValue(1)

        val list = listOf(PositiveValue(1), secondElement)

        // THEN
        assertThat(list.typedSecond<NegativeValue>()).isEqualTo(secondElement)
    }
}