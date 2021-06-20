package com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.classes.dashOperation
import com.dg.eqs.classes.dotOperation
import com.dg.eqs.classes.term
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class InvalidCondensingDueToOrderOfOperationsDeciderTest {
    private lateinit var decider: InvalidCondensingDueToOrderOfOperationsDecider


    @Before
    fun setUp() {
        decider = InvalidCondensingDueToOrderOfOperationsDecider()
    }

    @Test
    fun `A link spanning over a dash and a dot operation is invalid due to the order of operations`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                source.single,
                dotOperation(
                        term(),
                        target.single))
        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a dot and a dash operation is invalid due to the order of operations`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dotOperation(
                source.single,
                dashOperation(
                        term(),
                        target.single))
        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over exclusively dash operations is not invalid due to the order of operations`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                source.single,
                dashOperation(
                        term(),
                        target.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over exclusively dot operations is not invalid due to the order of operations`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dotOperation(
                source.single,
                dotOperation(
                        term(),
                        target.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    private fun decide(origin: AnyOrigin, source: Terms, target: Terms) = decider.decide(Link(origin, source, target))
}