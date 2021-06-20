package com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing

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


class InvalidCondensingDueToReduceWithDashOperationDeciderTest {
    private lateinit var decider: InvalidCondensingDueToReduceWithDashOperationDecider


    @Before
    fun setUp() {
        decider = InvalidCondensingDueToReduceWithDashOperationDecider()
    }

    //<editor-fold desc="Positive detection on parents chain: dash operation - division">
    @Test
    fun `A link spanning over a division with a dash operation as the denominator and the source in it is a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                target.single,
                dashOperation(
                        term(),
                        source.single))
        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a dash operation as the numerator and the source in it is a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                dashOperation(
                        source.single,
                        term()),
                target.single)

        // THEN
        assertTrue(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Positive detection on parents chain: division - dash operation">
    @Test
    fun `A link spanning over a division with a dash operation as the numerator and the target in it is a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                dashOperation(
                        target.single,
                        term()),
                source.single)

        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a dash operation as the denominator and the target in it is a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                source.single,
                dashOperation(
                        term(),
                        target.single))
        // THEN
        assertTrue(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Positive detection on parents chain: dash operation - division - product">
    @Test
    fun `A link spanning over a division with a product as the numerator and a dash operation as the denominator and the source in it is a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                product(
                        term(),
                        target.single),
                dashOperation(
                        term(),
                        source.single))
        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a dash operation as the numerator and a product as the denominator and the target in it is a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                dashOperation(
                        term(),
                        source.single),
                product(
                        term(),
                        target.single))
        // THEN
        assertTrue(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Positive detection on parents chain: product - division - dash operation">
    @Test
    fun `A link spanning over a division with a dash operation as the numerator and a product as the denominator and the source in it is a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                dashOperation(
                        term(),
                        target.single),
                product(
                        term(),
                        source.single))
        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a product as the numerator and a dash operation as the denominator and the target in it is a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                product(
                        term(),
                        source.single),
                dashOperation(
                        term(),
                        target.single))
        // THEN
        assertTrue(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Positive detection on parents chain: dash operation - division - dash operation">
    @Test
    fun `A link spanning over a division with a dash operation as the numerator and a dash operation as the denominator and the source in it is a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                dashOperation(
                        term(),
                        target.single),
                dashOperation(
                        term(),
                        source.single))
        // THEN
        assertTrue(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a dash operation as the numerator and a dash operation as the denominator and the target in it is a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                dashOperation(
                        term(),
                        source.single),
                dashOperation(
                        term(),
                        target.single))
        // THEN
        assertTrue(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Negative detection on parents chain: dash operation - division">
    @Test
    fun `A link spanning from an outside dash operation over a division with the target as the denominator is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                source.single,
                division(
                        term(),
                        target.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Negative detection on parents chain: division - dash operation">
    @Test
    fun `A link spanning from a division to an outside dash operation with the source as the denominator is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                target.single,
                division(
                        term(),
                        source.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Negative detection on parents chain: division - division">
    @Test
    fun `A link spanning over a division with a division as the denominator and the source in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                target.single,
                division(
                        term(),
                        source.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a division as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
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
    fun `A link spanning over a division with a division as the numerator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
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
    fun `A link spanning over a division with a division as the numerator and the source in it is not a invalid condensing due to a reduce with a dash operation`() {
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
    //</editor-fold>

    // <editor-fold desc="Negative detection on parents chain: product - division">
    @Test
    fun `A link spanning over a division with a product as the denominator and the source in it is not a invalid condensing due to a reduce with a dash operation`() {
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
    fun `A link spanning over a division with a product as the numerator and the source in it is not a invalid condensing due to a reduce with a dash operation`() {
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
    fun `A link spanning from an outside product over a division with the target as the denominator is not a invalid condensing due to a reduce with a dash operation`() {
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
    //</editor-fold>

    //<editor-fold desc="Negative detection on parents chain: division - product">
    @Test
    fun `A link spanning over a division with a product as the numerator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
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
    fun `A link spanning over a division with a product as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
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
    fun `A link spanning from a division to an outside product with the source as the denominator is not a invalid condensing due to a reduce with a dash operation`() {
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
    //</editor-fold>

    //<editor-fold desc="Negative detection on parents chain: dash operation - division - dash operation">
    @Test
    fun `A link spanning from a division with a dash operation as the denominator and the source in it to an outside dash operation is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                target.single,
                division(
                        term(),
                        dashOperation(
                                term(),
                                source.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a division with a dash operation as the denominator and the target in it to an outside dash operation is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                source.single,
                division(
                        term(),
                        dashOperation(
                                term(),
                                target.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Negative detection on parents chain: dash operation - division - division">
    @Test
    fun `A link spanning over a division with a division as the numerator and a dash operation as the denominator and the source in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                division(
                        term(),
                        target.single),
                dashOperation(
                        term(),
                        source.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a dash operation as the numerator and a division as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                dashOperation(
                        term(),
                        source.single),
                division(
                        term(),
                        target.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a division with a dash operation as the denominator and the source in it to an outside division is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                target.single,
                division(
                        term(),
                        dashOperation(
                                term(),
                                source.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from an outside dash operation over a division with a division as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                source.single,
                division(
                        term(),
                        division(
                                term(),
                                target.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Negative detection on parents chain: dash operation - division - product">
    @Test
    fun `A link spanning from a division with a dash operation as the denominator and the source in it to an outside product is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                target.single,
                division(
                        term(),
                        dashOperation(
                                term(),
                                source.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from an outside dash operation over a division with a product as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                source.single,
                division(
                        term(),
                        product(
                                term(),
                                target.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Negative detection on parents chain: division - division - dash operation">
    @Test
    fun `A link spanning over a division with a dash operation as the numerator and a division as the denominator and the source in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                dashOperation(
                        term(),
                        target.single),
                division(
                        term(),
                        source.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a division as the numerator and a dash operation as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                division(
                        term(),
                        source.single),
                dashOperation(
                        term(),
                        target.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a division with a division as the denominator and the source in it to an outside dash operation is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                target.single,
                division(
                        term(),
                        division(
                                term(),
                                source.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from an outside division over a division with a dash operation as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                source.single,
                division(
                        term(),
                        dashOperation(
                                term(),
                                target.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Negative detection on parents chain: division - division - division">
    @Test
    fun `A link spanning over a division with a division as the numerator and a division as the denominator and the source in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                division(
                        term(),
                        target.single),
                division(
                        term(),
                        source.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a division as the numerator and a division as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                division(
                        term(),
                        source.single),
                division(
                        term(),
                        target.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a division with a division as the denominator and the source in it to an outside division is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                target.single,
                division(
                        term(),
                        division(
                                term(),
                                source.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from an outside division over a division with a division as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                source.single,
                division(
                        term(),
                        division(
                                term(),
                                target.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Negative detection on parents chain: division - division - product">
    @Test
    fun `A link spanning over a division with a product as the numerator and a division as the denominator and the source in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                product(
                        term(),
                        target.single),
                division(
                        term(),
                        source.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a division as the numerator and a product as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                division(
                        term(),
                        source.single),
                product(
                        term(),
                        target.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a division with a division as the denominator and the source in it to an outside product is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                target.single,
                division(
                        term(),
                        division(
                                term(),
                                source.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from an outside division over a division with a product as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                source.single,
                division(
                        term(),
                        product(
                                term(),
                                target.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Negative detection on parents chain: product - division - dash operation">
    @Test
    fun `A link spanning from a division with a product as the denominator and the source in it to an outside dash operation is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = dashOperation(
                target.single,
                division(
                        term(),
                        product(
                                term(),
                                source.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from an outside product over a division with a dash operation as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                source.single,
                division(
                        term(),
                        dashOperation(
                                term(),
                                target.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Negative detection on parents chain: product - division - product">
    @Test
    fun `A link spanning over a division with a product as the numerator and a product as the denominator and the source in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                product(
                        term(),
                        target.single),
                product(
                        term(),
                        source.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a product as the numerator and a product as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                product(
                        term(),
                        source.single),
                product(
                        term(),
                        target.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a division with a product as the denominator and the source in it to an outside product is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                target.single,
                division(
                        term(),
                        product(
                                term(),
                                source.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from an outside product over a division with a product as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                source.single,
                division(
                        term(),
                        product(
                                term(),
                                target.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }
    //</editor-fold>

    //<editor-fold desc="Negative detection on parents chain: product - division - division">
    @Test
    fun `A link spanning over a division with a division as the numerator and a product as the denominator and the source in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                division(
                        term(),
                        target.single),
                product(
                        term(),
                        source.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning over a division with a product as the numerator and a division as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                product(
                        term(),
                        source.single),
                division(
                        term(),
                        target.single))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from a division with a product as the denominator and the source in it to an outside division is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                target.single,
                division(
                        term(),
                        product(
                                term(),
                                source.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }

    @Test
    fun `A link spanning from an outside product over a division with a division as the denominator and the target in it is not a invalid condensing due to a reduce with a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                source.single,
                division(
                        term(),
                        division(
                                term(),
                                target.single)))
        // THEN
        assertFalse(decide(origin, source, target))
    }
    //</editor-fold>

    private fun decide(origin: AnyOrigin, source: Terms, target: Terms) = decider.decide(Link(origin, source, target))
}