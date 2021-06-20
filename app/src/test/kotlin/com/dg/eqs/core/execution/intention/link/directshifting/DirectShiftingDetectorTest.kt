package com.dg.eqs.core.execution.intention.link.directshifting

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.single
import com.dg.eqs.classes.*
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingEvent.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class DirectShiftingDetectorTest {
    private lateinit var detector: DirectShiftingDetector


    @Before
    fun setUp() {
        detector = DirectShiftingDetector()
    }

    @Test
    fun `Should detect a shifting of an entire side`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(source.single, target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ShiftingOfEntireSide)
    }

    @Test
    fun `Should detect a shifting out of a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                dashOperation(
                        source.first,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ShiftingOutOfDashOperation)
    }

    @Test
    fun `Should detect a shifting out of a division`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                division(
                        source.single,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ShiftingOutOfDivision)
    }

    @Test
    fun `Should detect a shifting out of a product`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                product(
                        source.first,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ShiftingOutOfProduct)
    }

    private fun detect(origin: AnyOrigin, source: Terms, target: Terms) =
            detector.detect(Link(origin, source, target))
}