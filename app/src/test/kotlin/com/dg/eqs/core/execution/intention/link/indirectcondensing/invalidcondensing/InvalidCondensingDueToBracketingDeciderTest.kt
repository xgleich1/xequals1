package com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
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


class InvalidCondensingDueToBracketingDeciderTest {
    private lateinit var decider: InvalidCondensingDueToBracketingDecider


    @Before
    fun setUp() {
        decider = InvalidCondensingDueToBracketingDecider()
    }

    @Test
    fun `A link spanning over exclusively dash operations is invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                source.first,
                dashOperation(
                        term(),
                        target.first))
        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over exclusively products is invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                source.first,
                product(
                        term(),
                        target.first))
        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over exclusively divisions is not invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                source.single,
                division(
                        term(),
                        target.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a dash operation and a division is not invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                source.first,
                division(
                        term(),
                        target.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a dash operation and a product is not invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                source.first,
                product(
                        term(),
                        target.first))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division and a dash operation is not invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                source.single,
                dashOperation(
                        term(),
                        target.first))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division and a product is not invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                source.single,
                product(
                        term(),
                        target.first))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a product and a dash operation is not invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                source.first,
                dashOperation(
                        term(),
                        target.first))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a product and a division is not invalid due to bracketing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                source.first,
                division(
                        term(),
                        target.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    private fun decide(origin: AnyOrigin, source: Terms, target: Terms) = decider.decide(Link(origin, source, target))
}