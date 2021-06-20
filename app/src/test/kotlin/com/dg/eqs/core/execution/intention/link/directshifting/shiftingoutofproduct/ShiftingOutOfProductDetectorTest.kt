package com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.base.extension.single
import com.dg.eqs.classes.*
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct.ShiftingOutOfProductEvent.InvalidShiftingDivisionOfZeroOutOfProduct
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct.ShiftingOutOfProductEvent.ValidShiftingDivisionOutOfProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class ShiftingOutOfProductDetectorTest {
    private lateinit var detector: ShiftingOutOfProductDetector


    @Before
    fun setUp() {
        detector = ShiftingOutOfProductDetector()
    }

    @Test
    fun `Single selection shifting divisions out of a product involving a zero are invalid`() {
        // GIVEN
        val source = termsOf(zero())
        val target = termsOf(term())
        val origin = relation(
                product(
                        source.single,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(InvalidShiftingDivisionOfZeroOutOfProduct)
    }

    @Test
    fun `Multi selection shifting divisions out of a product involving a zero are invalid`() {
        // GIVEN
        val source = termsOf(term(), zero())
        val target = termsOf(term())
        val origin = relation(
                product(
                        source.first,
                        source.second,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(InvalidShiftingDivisionOfZeroOutOfProduct)
    }

    @Test
    fun `Single selection shifting divisions out of a product involving anything other than a value are valid`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                product(
                        source.single,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ValidShiftingDivisionOutOfProduct)
    }

    @Test
    fun `Multi selection shifting divisions out of a product involving anything other than values are valid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term())
        val origin = relation(
                product(
                        source.first,
                        source.second,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ValidShiftingDivisionOutOfProduct)
    }

    @Test
    fun `Single selection shifting divisions out of a product involving a value other than zero are valid`() {
        // GIVEN
        val source = termsOf(value())
        val target = termsOf(term())
        val origin = relation(
                product(
                        source.single,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ValidShiftingDivisionOutOfProduct)
    }

    @Test
    fun `Multi selection shifting divisions out of a product involving a value other than zero are valid`() {
        // GIVEN
        val source = termsOf(term(), value())
        val target = termsOf(term())
        val origin = relation(
                product(
                        source.first,
                        source.second,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ValidShiftingDivisionOutOfProduct)
    }

    private fun value() = value(2)

    private fun detect(origin: AnyOrigin, source: Terms, target: Terms) = detector.detect(Link(origin, source, target))
}