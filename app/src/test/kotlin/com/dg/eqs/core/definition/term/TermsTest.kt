package com.dg.eqs.core.definition.term

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.classes.one
import com.dg.eqs.classes.zero
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.item.variable.Variable
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.DivisionThroughZeroException
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class TermsTest {
    @Test
    fun `Should compare two equal terms decorator`() {
        // GIVEN
        val termA: Term = mock()
        val termB: Term = mock()
        val termsA = termsOf(termA, termB)
        val termsB = termsOf(termA, termB)

        // THEN
        assertThat(termsA).isEqualTo(termsB)
    }

    @Test
    fun `Should convert a terms decorator to a string`() {
        // GIVEN
        val termA: Term = mock { on { toString() } doReturn "1" }
        val termB: Term = mock { on { toString() } doReturn "2" }
        val terms = termsOf(termA, termB)

        // THEN
        assertThat(terms).hasToString("[1, 2]")
    }

    @Test
    fun `Should provide a plus operator for a single term`() {
        // GIVEN
        val termA: Term = mock()
        val termB: Term = mock()
        val termC: Term = mock()
        val terms = termsOf(termA, termB)

        // THEN
        val expectedResult = termsOf(
                termA,
                termB,
                termC)

        assertThat(terms + termC).isEqualTo(expectedResult)
    }

    @Test
    fun `Should provide a plus operator for another terms decorator`() {
        // GIVEN
        val termA: Term = mock()
        val termB: Term = mock()
        val termC: Term = mock()
        val termD: Term = mock()
        val termsA = termsOf(termA, termB)
        val termsB = termsOf(termC, termD)

        // THEN
        val expectedResult = termsOf(
                termA,
                termB,
                termC,
                termD)

        assertThat(termsA + termsB).isEqualTo(expectedResult)
    }

    @Test
    fun `Should provide an contains operator for an individual term using identity`() {
        // GIVEN
        val termA = PositiveValue(1)
        val termB = PositiveValue(1)
        val terms = termsOf(termA)

        // THEN
        assertTrue(termA in terms)
        assertFalse(termB in terms)
    }

    @Test
    fun `Should provide an contains operator for another terms decorator using identity`() {
        // GIVEN
        val termA = PositiveValue(1)
        val termB = PositiveValue(1)
        val termC = PositiveValue(1)
        val terms = termsOf(termA, termB)

        // THEN
        assertTrue(termsOf(termA, termB) in terms)
        assertFalse(termsOf(termB, termC) in terms)
    }

    @Test
    fun `Should provide the index of an individual term using identity`() {
        // GIVEN
        val termA = PositiveValue(1)
        val termB = PositiveValue(1)
        val termC = PositiveValue(1)
        val terms = termsOf(termA, termB)

        // THEN
        assertThat(terms.indexOf(termA)).isEqualTo(0)
        assertThat(terms.indexOf(termB)).isEqualTo(1)
        assertThat(terms.indexOf(termC)).isEqualTo(-1)
    }

    @Test
    fun `Should provide the index of the first term of the provided type`() {
        // GIVEN
        val termA: Term = mock()
        val termB: Value = mock()
        val termC: Term = mock()
        val termD: Value = mock()
        val termE: Term = mock()
        val terms = termsOf(
                termA,
                termB,
                termC,
                termD,
                termE)

        // THEN
        assertThat(terms.indexOf<Value>()).isEqualTo(1)
        assertThat(terms.indexOf<Variable>()).isEqualTo(-1)
    }

    @Test
    fun `Should provide the index of the last term of the provided type`() {
        // GIVEN
        val termA: Term = mock()
        val termB: Value = mock()
        val termC: Term = mock()
        val termD: Value = mock()
        val termE: Term = mock()
        val terms = termsOf(
                termA,
                termB,
                termC,
                termD,
                termE)

        // THEN
        assertThat(terms.lastIndexOf<Value>()).isEqualTo(3)
        assertThat(terms.lastIndexOf<Variable>()).isEqualTo(-1)
    }

    @Test
    fun `Should provide the index of the first term matching the predicate`() {
        // GIVEN
        val termA: Term = mock { on { isPositive } doReturn false }
        val termB: Term = mock { on { isPositive } doReturn true }
        val termC: Term = mock { on { isPositive } doReturn false }
        val termD: Term = mock { on { isPositive } doReturn true }
        val termE: Term = mock { on { isPositive } doReturn false }
        val terms = termsOf(
                termA,
                termB,
                termC,
                termD,
                termE)

        // THEN
        assertThat(terms.indexOf(Term::isPositive)).isEqualTo(1)
        assertThat(terms.indexOf(Term::isNegative)).isEqualTo(-1)
    }

    @Test
    fun `Should provide the index of the last term matching the predicate`() {
        // GIVEN
        val termA: Term = mock { on { isPositive } doReturn false }
        val termB: Term = mock { on { isPositive } doReturn true }
        val termC: Term = mock { on { isPositive } doReturn false }
        val termD: Term = mock { on { isPositive } doReturn true }
        val termE: Term = mock { on { isPositive } doReturn false }
        val terms = termsOf(
                termA,
                termB,
                termC,
                termD,
                termE)

        // THEN
        assertThat(terms.lastIndexOf(Term::isPositive)).isEqualTo(3)
        assertThat(terms.lastIndexOf(Term::isNegative)).isEqualTo(-1)
    }

    @Test
    fun `Should insert another terms decorator at the beginning`() {
        // GIVEN
        val termA: Term = mock()
        val termB: Term = mock()
        val termC: Term = mock()
        val termD: Term = mock()
        val terms = termsOf(
                termA,
                termB,
                termC,
                termD)

        val toInsertA: Term = mock()
        val toInsertB: Term = mock()
        val toInsert = termsOf(
                toInsertA,
                toInsertB)

        // THEN
        val expectedResult = termsOf(
                toInsertA,
                toInsertB,
                termA,
                termB,
                termC,
                termD)

        assertThat(terms.insert(0, toInsert)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should insert another terms decorator in the middle`() {
        // GIVEN
        val termA: Term = mock()
        val termB: Term = mock()
        val termC: Term = mock()
        val termD: Term = mock()
        val terms = termsOf(
                termA,
                termB,
                termC,
                termD)

        val toInsertA: Term = mock()
        val toInsertB: Term = mock()
        val toInsert = termsOf(
                toInsertA,
                toInsertB)

        // THEN
        val expectedResult = termsOf(
                termA,
                termB,
                toInsertA,
                toInsertB,
                termC,
                termD)

        assertThat(terms.insert(2, toInsert)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should insert another terms decorator at the end`() {
        // GIVEN
        val termA: Term = mock()
        val termB: Term = mock()
        val termC: Term = mock()
        val termD: Term = mock()
        val terms = termsOf(
                termA,
                termB,
                termC,
                termD)

        val toInsertA: Term = mock()
        val toInsertB: Term = mock()
        val toInsert = termsOf(
                toInsertA,
                toInsertB)

        // THEN
        val expectedResult = termsOf(
                termA,
                termB,
                termC,
                termD,
                toInsertA,
                toInsertB)

        assertThat(terms.insert(4, toInsert)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should remove an individual term using identity`() {
        // GIVEN
        val termA = PositiveValue(1)
        val termB = PositiveValue(1)
        val terms = termsOf(termA, termB)

        // THEN
        val expectedResult = termsOf(termA)

        assertThat(terms.remove(termB)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should remove several terms using identity`() {
        // GIVEN
        val termA = PositiveValue(1)
        val termB = PositiveValue(1)
        val termC = PositiveValue(1)
        val termD = PositiveValue(1)
        val termE = PositiveValue(1)
        val terms = termsOf(
                termA,
                termB,
                termC,
                termD,
                termE)

        val toRemove = termsOf(
                termA,
                termC,
                termE)

        // THEN
        val expectedResult = termsOf(termB, termD)

        assertThat(terms.remove(toRemove)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should replace an individual term using identity`() {
        // GIVEN
        val termA = PositiveValue(1)
        val termB = PositiveValue(1)
        val terms = termsOf(termA, termB)

        val replacement: Term = mock()

        // THEN
        val expectedResult = termsOf(termA, replacement)

        assertThat(terms.replace(termB, replacement)).isEqualTo(expectedResult)
    }

    @Test
    fun `A terms decorator is able to map all of its terms`() {
        // GIVEN
        val mappedTermA: Term = mock()
        val mappedTermB: Term = mock()
        val termA: Term = mock()
        val termB: Term = mock()
        val terms = termsOf(termA, termB)

        // WHEN
        val mappedTerms = terms.map {
            when(it) {
                termA -> mappedTermA
                termB -> mappedTermB

                else -> it
            }
        }

        // THEN
        val expectedResult = termsOf(mappedTermA, mappedTermB)

        assertThat(mappedTerms).isEqualTo(expectedResult)
    }

    @Test
    fun `A terms decorator is able to remove in all of its terms`() {
        // GIVEN
        val toRemove: Term = mock()

        val alteredTermA: Term = mock()
        val alteredTermB: Term = mock()
        val termA: Term = mock { on { remove(toRemove) } doReturn alteredTermA }
        val termB: Term = mock { on { remove(toRemove) } doReturn alteredTermB }
        val terms = termsOf(termA, termB)

        // THEN
        val expectedResult = termsOf(alteredTermA, alteredTermB)

        assertThat(terms.mapRemove(toRemove)).isEqualTo(expectedResult)
    }

    @Test
    fun `A terms decorator is able to replace in all of its terms`() {
        // GIVEN
        val toReplace: Term = mock()
        val replacement: Term = mock()

        val alteredTermA: Term = mock()
        val alteredTermB: Term = mock()
        val termA: Term = mock { on { replace(toReplace, replacement) } doReturn alteredTermA }
        val termB: Term = mock { on { replace(toReplace, replacement) } doReturn alteredTermB }
        val terms = termsOf(termA, termB)

        // THEN
        val expectedResult = termsOf(alteredTermA, alteredTermB)

        assertThat(terms.mapReplace(toReplace, replacement)).isEqualTo(expectedResult)
    }

    @Test
    fun `A terms decorator is able to invert the signs of all its terms`() {
        // GIVEN
        val oppositeTermA: Term = mock()
        val oppositeTermB: Term = mock()
        val termA: Term = mock { on { invert() } doReturn oppositeTermA }
        val termB: Term = mock { on { invert() } doReturn oppositeTermB }
        val terms = termsOf(termA, termB)

        // THEN
        val expectedResult = termsOf(oppositeTermA, oppositeTermB)

        assertThat(terms.mapInvert()).isEqualTo(expectedResult)
    }

    @Test
    fun `A terms decorator is able to copy all of its terms`() {
        // GIVEN
        val copyTermA: Term = mock()
        val copyTermB: Term = mock()
        val termA: Term = mock { on { copy() } doReturn copyTermA }
        val termB: Term = mock { on { copy() } doReturn copyTermB }
        val terms = termsOf(termA, termB)

        // THEN
        val expectedResult = termsOf(copyTermA, copyTermB)

        assertThat(terms.mapCopy()).isEqualTo(expectedResult)
    }

    @Test(expected = DivisionThroughZeroException::class)
    fun `Should expose the exception when resolving a term throws one`() {
        // GIVEN
        val exception: DivisionThroughZeroException = mock()

        val term: Term = mock { on { resolve() } doThrow exception }
        val terms = termsOf(term)

        // WHEN
        terms.mapResolve()
    }

    @Test
    fun `A terms decorator is able to resolve all of its terms`() {
        // GIVEN
        val resolvedTermA: Term = mock()
        val resolvedTermB: Term = mock()
        val termA: Term = mock { on { resolve() } doReturn resolvedTermA }
        val termB: Term = mock { on { resolve() } doReturn resolvedTermB }
        val terms = termsOf(termA, termB)

        // THEN
        val expectedResult = termsOf(resolvedTermA, resolvedTermB)

        assertThat(terms.mapResolve()).isEqualTo(expectedResult)
    }

    @Test
    fun `A terms decorator with several terms but not all of them being one does not consist of only ones`() {
        assertFalse(termsOf(one(), mock<Term>()).allOnes())
    }

    @Test
    fun `A terms decorator with several values but not all of them being one does not consist of only ones`() {
        assertFalse(termsOf(one(), zero()).allOnes())
    }

    @Test
    fun `A terms decorator with several values and all of them being one consists of only ones`() {
        assertTrue(termsOf(one(), one()).allOnes())
    }

    @Test
    fun `A terms decorator with several terms but not all of them being zero does not consist of only zeros`() {
        assertFalse(termsOf(zero(), mock<Term>()).allZeros())
    }

    @Test
    fun `A terms decorator with several values but not all of them being zero does not consist of only zeros`() {
        assertFalse(termsOf(zero(), one()).allZeros())
    }

    @Test
    fun `A terms decorator with several values and all of them being zero consists of only zeros`() {
        assertTrue(termsOf(zero(), zero()).allZeros())
    }

    @Test
    fun `A terms decorator with several terms but not all of them being constant does not consist of only constants`() {
        // GIVEN
        val termA: Term = mock { on { isConstant() } doReturn true }
        val termB: Term = mock { on { isConstant() } doReturn false }

        // THEN
        assertFalse(termsOf(termA, termB).allConstants())
    }

    @Test
    fun `A terms decorator with several terms and all of them being constant consists of only constants`() {
        // GIVEN
        val termA: Term = mock { on { isConstant() } doReturn true }
        val termB: Term = mock { on { isConstant() } doReturn true }

        // THEN
        assertTrue(termsOf(termA, termB).allConstants())
    }
}