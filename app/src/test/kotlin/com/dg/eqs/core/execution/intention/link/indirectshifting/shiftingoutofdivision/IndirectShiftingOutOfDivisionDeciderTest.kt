package com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.single
import com.dg.eqs.classes.*
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class IndirectShiftingOutOfDivisionDeciderTest {
    private lateinit var decider: IndirectShiftingOutOfDivisionDecider


    @Before
    fun setUp() {
        decider = IndirectShiftingOutOfDivisionDecider()
    }

    @Test
    fun `A link spanning from a product over a division with the product as the numerator is a indirect shifting out of a division`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                division(
                        product(
                                source.single,
                                term()),
                        term()),
                target.single)

        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a product over a division with the product as the denominator is a indirect shifting out of a division`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                division(
                        term(),
                        product(
                                term(),
                                source.single)),
                target.single)

        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a dash operation over another dash operation is not a indirect shifting out of a division`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                dashOperation(
                        dashOperation(
                                source.single,
                                term()),
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a dash operation over a division with the dash operation as the numerator is not a indirect shifting out of a division`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                division(
                        dashOperation(
                                source.single,
                                term()),
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a dash operation over a division with the dash operation as the denominator is not a indirect shifting out of a division`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                division(
                        term(),
                        dashOperation(
                                term(),
                                source.single)),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a dash operation over a product is not a indirect shifting out of a division`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                product(
                        dashOperation(
                                source.single,
                                term()),
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a division over a dash operation is not a indirect shifting out of a division`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                dashOperation(
                        division(
                                source.single,
                                term()),
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a division over another division with the division as the numerator is not a indirect shifting out of a division`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                division(
                        division(
                                source.single,
                                term()),
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a division over another division with the division as the denominator is not a indirect shifting out of a division`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                division(
                        term(),
                        division(
                                term(),
                                source.single)),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a division over a product is not a indirect shifting out of a division`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                product(
                        division(
                                source.single,
                                term()),
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a product over a dash operation is not a indirect shifting out of a division`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                dashOperation(
                        product(
                                source.single,
                                term()),
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a product over another product is not a indirect shifting out of a division`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                product(
                        product(
                                source.single,
                                term()),
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    private fun decide(origin: AnyOrigin, source: Terms, target: Terms) = decider.decide(Link(origin, source, target))
}