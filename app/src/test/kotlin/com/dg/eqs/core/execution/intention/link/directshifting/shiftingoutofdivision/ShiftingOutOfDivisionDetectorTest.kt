package com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdivision

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdivision.ShiftingOutOfDivisionEvent.*
import com.dg.eqs.classes.division
import com.dg.eqs.classes.relation
import com.dg.eqs.classes.term
import com.dg.eqs.classes.value
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class ShiftingOutOfDivisionDetectorTest {
    private lateinit var detector: ShiftingOutOfDivisionDetector


    @Before
    fun setUp() {
        detector = ShiftingOutOfDivisionDetector()
    }

    @Test
    fun `Shifting divisions out of a division with a zero as the numerator are invalid`() {
        // GIVEN
        val source = termsOf(value(0))
        val target = termsOf(term())
        val origin = relation(
                division(
                        source.single,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(InvalidShiftingDivisionOfZeroOutOfDivision)
    }

    @Test
    fun `Shifting multiplications out of a division with a zero as the denominator are invalid`() {
        // GIVEN
        val source = termsOf(value(0))
        val target = termsOf(term())
        val origin = relation(
                division(
                        term(),
                        source.single),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(InvalidShiftingMultiplicationOfZeroOutOfDivision)
    }

    @Test
    fun `Shifting divisions out of a division with anything other than a value as the numerator are valid`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                division(
                        source.single,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ValidShiftingDivisionOutOfDivision)
    }

    @Test
    fun `Shifting multiplications out of a division with anything other than a value as the denominator are valid`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                division(
                        term(),
                        source.single),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ValidShiftingMultiplicationOutOfDivision)
    }

    @Test
    fun `Shifting divisions out of a division with a value other than zero as the numerator are valid`() {
        // GIVEN
        val source = termsOf(value(1))
        val target = termsOf(term())
        val origin = relation(
                division(
                        source.single,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ValidShiftingDivisionOutOfDivision)
    }

    @Test
    fun `Shifting multiplications out of a division with a value other than zero as the denominator are valid`() {
        // GIVEN
        val source = termsOf(value(1))
        val target = termsOf(term())
        val origin = relation(
                division(
                        term(),
                        source.single),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ValidShiftingMultiplicationOutOfDivision)
    }

    private fun detect(origin: AnyOrigin, source: Terms, target: Terms) =
            detector.detect(Link(origin, source, target))
}