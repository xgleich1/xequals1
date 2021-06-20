package com.dg.eqs.core.execution.intention.link.indirectshifting.invalidshifting

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.single
import com.dg.eqs.classes.*
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class InvalidShiftingDueToBracketingDeciderTest {
    private lateinit var decider: InvalidShiftingDueToBracketingDecider


    @Before
    fun setUp() {
        decider = InvalidShiftingDueToBracketingDecider()
    }

    @Test
    fun `A link spanning spanning over exclusively dash operations is invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                dashOperation(
                        dashOperation(
                                source.first,
                                term()),
                        term()),
                target.single)

        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning spanning over exclusively products is invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                product(
                        product(
                                source.first,
                                term()),
                        term()),
                target.single)

        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning spanning over exclusively divisions is not invalid due to bracketing`() {
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
    fun `A link spanning over a dash operation and a division is not invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                division(
                        dashOperation(
                                source.first,
                                term()),
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a dash operation and a product is not invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                product(
                        dashOperation(
                                source.first,
                                term()),
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division and a dash operation is not invalid due to bracketing`() {
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
    fun `A link spanning over a division and a product is not invalid due to bracketing`() {
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
    fun `A link spanning over a product and a dash operation is not invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                dashOperation(
                        product(
                                source.first,
                                term()),
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a product and a division is not invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                division(
                        product(
                                source.first,
                                term()),
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    private fun decide(origin: AnyOrigin, source: Terms, target: Terms) =
            decider.decide(Link(origin, source, target))
}