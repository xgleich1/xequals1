package com.dg.eqs.core.execution.intention.link.indirectshifting.invalidshifting

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.classes.dashOperation
import com.dg.eqs.classes.dotOperation
import com.dg.eqs.classes.relation
import com.dg.eqs.classes.term
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class InvalidShiftingDueToOrderOfOperationsDeciderTest {
    private lateinit var decider: InvalidShiftingDueToOrderOfOperationsDecider


    @Before
    fun setUp() {
        decider = InvalidShiftingDueToOrderOfOperationsDecider()
    }

    @Test
    fun `A link spanning over a dash and a dot operation is invalid due to the order of operations`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                dotOperation(
                        dashOperation(
                                source.first,
                                term()),
                        term()),
                target.single)

        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a dot and a dash operation is invalid due to the order of operations`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                dashOperation(
                        dotOperation(
                                source.first,
                                term()),
                        term()),
                target.single)

        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over exclusively dash operations is not invalid due to the order of operations`() {
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
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over exclusively dot operations is not invalid due to the order of operations`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                dotOperation(
                        dotOperation(
                                source.first,
                                term()),
                        term()),
                target.single)

        // THEN
        assertFalse(decide(origin, source, target))
    }

    private fun decide(origin: AnyOrigin, source: Terms, target: Terms) = decider.decide(Link(origin, source, target))
}