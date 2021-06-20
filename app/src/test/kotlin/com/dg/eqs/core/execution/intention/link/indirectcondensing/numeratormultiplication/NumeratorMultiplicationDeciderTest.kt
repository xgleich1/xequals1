package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.classes.dashOperation
import com.dg.eqs.classes.division
import com.dg.eqs.classes.product
import com.dg.eqs.classes.term
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class NumeratorMultiplicationDeciderTest {
    private lateinit var decider: NumeratorMultiplicationDecider


    @Before
    fun setUp() {
        decider = NumeratorMultiplicationDecider()
    }

    @Test
    fun `A link spanning from an outside product over a division with the target as the numerator is a numerator multiplication`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                source.single,
                division(
                        target.single,
                        term()))
        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a division with the source as the numerator to an outside product is a numerator multiplication`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                target.single,
                division(
                        source.single,
                        term()))
        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from an outside dash operation over a division with the target as the numerator is not a numerator multiplication`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                source.single,
                division(
                        target.single,
                        term()))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with another division as the numerator and the target in it is not a numerator multiplication`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                division(
                        target.single,
                        term()),
                source.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with another division as the denominator and the target in it is not a numerator multiplication`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                source.single,
                division(
                        target.single,
                        term()))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a division with the source as the numerator to an outside dash operation is not a numerator multiplication`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                target.single,
                division(
                        source.single,
                        term()))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with another division as the numerator and the source in it is not a numerator multiplication`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                division(
                        source.single,
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with another division as the denominator and the source in it is not a numerator multiplication`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                target.single,
                division(
                        source.single,
                        term()))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a product as the numerator and the source in it is not a numerator multiplication`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                product(
                        source.single,
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a product as the denominator and the source in it is not a numerator multiplication`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                target.single,
                product(
                        term(),
                        source.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a product as the numerator and the target in it is not a numerator multiplication`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                product(
                        target.single,
                        term()),
                source.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a product as the denominator and the target in it is not a numerator multiplication`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                source.single,
                product(
                        term(),
                        target.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from an outside product over a division with the target as the denominator is not a numerator multiplication`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                source.single,
                division(
                        term(),
                        target.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a division with the source as the denominator to an outside product is not a numerator multiplication`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                target.single,
                division(
                        term(),
                        source.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    private fun decide(origin: AnyOrigin, source: Terms, target: Terms) = decider.decide(Link(origin, source, target))
}