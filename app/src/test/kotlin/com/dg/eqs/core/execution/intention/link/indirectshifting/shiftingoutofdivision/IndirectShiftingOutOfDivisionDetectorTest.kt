package com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.base.extension.single
import com.dg.eqs.classes.*
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision.IndirectShiftingOutOfDivisionEvent.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class IndirectShiftingOutOfDivisionDetectorTest {
    private lateinit var detector: IndirectShiftingOutOfDivisionDetector


    @Before
    fun setUp() {
        detector = IndirectShiftingOutOfDivisionDetector()
    }

    @Test
    fun `Single selection indirect shifting divisions out of a division involving a zero are invalid`() {
        // GIVEN
        val source = termsOf(value(0))
        val target = termsOf(term())
        val origin = relation(
                division(
                        product(
                                source.single,
                                term()),
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(InvalidIndirectShiftingDivisionOfZeroOutOfDivision)
    }

    @Test
    fun `Multi selection indirect shifting divisions out of a division involving a zero are invalid`() {
        // GIVEN
        val source = termsOf(term(), value(0))
        val target = termsOf(term())
        val origin = relation(
                division(
                        product(
                                source.first,
                                source.second,
                                term()),
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(InvalidIndirectShiftingDivisionOfZeroOutOfDivision)
    }

    @Test
    fun `Single selection indirect shifting multiplications out of a division involving a zero are invalid`() {
        // GIVEN
        val source = termsOf(value(0))
        val target = termsOf(term())
        val origin = relation(
                division(
                        term(),
                        product(
                                term(),
                                source.single)),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(InvalidIndirectShiftingMultiplicationOfZeroOutOfDivision)
    }

    @Test
    fun `Multi selection indirect shifting multiplications out of a division involving a zero are invalid`() {
        // GIVEN
        val source = termsOf(term(), value(0))
        val target = termsOf(term())
        val origin = relation(
                division(
                        term(),
                        product(
                                term(),
                                source.first,
                                source.second)),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(InvalidIndirectShiftingMultiplicationOfZeroOutOfDivision)
    }

    @Test
    fun `Single selection indirect shifting divisions out of a division involving anything other than a value are valid`() {
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
        assertThat(detect(origin, source, target)).isEqualTo(ValidIndirectShiftingDivisionOutOfDivision)
    }

    @Test
    fun `Multi selection indirect shifting divisions out of a division involving anything other than values are valid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term())
        val origin = relation(
                division(
                        product(
                                source.first,
                                source.second,
                                term()),
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ValidIndirectShiftingDivisionOutOfDivision)
    }

    @Test
    fun `Single selection indirect shifting divisions out of a division involving a value other than zero are valid`() {
        // GIVEN
        val source = termsOf(value(1))
        val target = termsOf(term())
        val origin = relation(
                division(
                        product(
                                source.single,
                                term()),
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ValidIndirectShiftingDivisionOutOfDivision)
    }

    @Test
    fun `Multi selection indirect shifting divisions out of a division involving a value other than zero are valid`() {
        // GIVEN
        val source = termsOf(term(), value(1))
        val target = termsOf(term())
        val origin = relation(
                division(
                        product(
                                source.first,
                                source.second,
                                term()),
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ValidIndirectShiftingDivisionOutOfDivision)
    }

    @Test
    fun `Single selection indirect shifting multiplications out of a division involving anything other than a value are valid`() {
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
        assertThat(detect(origin, source, target)).isEqualTo(ValidIndirectShiftingMultiplicationOutOfDivision)
    }

    @Test
    fun `Multi selection indirect shifting multiplications out of a division involving anything other than values are valid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term())
        val origin = relation(
                division(
                        term(),
                        product(
                                term(),
                                source.first,
                                source.second)),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ValidIndirectShiftingMultiplicationOutOfDivision)
    }

    @Test
    fun `Single selection indirect shifting multiplications out of a division involving a value other than zero are valid`() {
        // GIVEN
        val source = termsOf(value(1))
        val target = termsOf(term())
        val origin = relation(
                division(
                        term(),
                        product(
                                term(),
                                source.single)),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ValidIndirectShiftingMultiplicationOutOfDivision)
    }

    @Test
    fun `Multi selection indirect shifting multiplications out of a division involving a value other than zero are valid`() {
        // GIVEN
        val source = termsOf(term(), value(1))
        val target = termsOf(term())
        val origin = relation(
                division(
                        term(),
                        product(
                                term(),
                                source.first,
                                source.second)),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ValidIndirectShiftingMultiplicationOutOfDivision)
    }

    private fun detect(origin: AnyOrigin, source: Terms, target: Terms) = detector.detect(Link(origin, source, target))
}