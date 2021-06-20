package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.NumeratorMultiplicationEvent.*
import com.dg.eqs.classes.division
import com.dg.eqs.classes.product
import com.dg.eqs.classes.term
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class NumeratorMultiplicationDetectorTest {
    private lateinit var detector: NumeratorMultiplicationDetector


    @Before
    fun setUp() {
        detector = NumeratorMultiplicationDetector()
    }

    @Test
    fun `Should detect a single selection numerator multiplication with the source on the left on a link spanning from an outside product over a division with the target as the numerator`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                source.single,
                division(
                        target.single,
                        term()))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(SingleSelectionNumeratorMultiplicationWithSourceLeft)
    }

    @Test
    fun `Should detect a single selection numerator multiplication with the source on the left on a link spanning from a division with the source as the numerator to an outside product`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                division(
                        source.single,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(SingleSelectionNumeratorMultiplicationWithSourceLeft)
    }

    @Test
    fun `Should detect a single selection numerator multiplication with the target on the left on a link spanning from a division with the source as the numerator to an outside product`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                target.single,
                division(
                        source.single,
                        term()))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(SingleSelectionNumeratorMultiplicationWithTargetLeft)
    }

    @Test
    fun `Should detect a single selection numerator multiplication with the target on the left on a link spanning from an outside product over a division with the target as the numerator`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = product(
                division(
                        target.single,
                        term()),
                source.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(SingleSelectionNumeratorMultiplicationWithTargetLeft)
    }

    @Test
    fun `Should detect a multi selection numerator multiplication with the source on the left on a link spanning from an outside product over a division with the target as the numerator`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term())
        val origin = product(
                source.first,
                source.second,
                division(
                        target.single,
                        term()))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(MultiSelectionNumeratorMultiplicationWithSourceLeft)
    }

    @Test
    fun `Should detect a multi selection numerator multiplication with the source on the left on a link spanning from a division with the source as the numerator to an outside product`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term(), term())
        val origin = product(
                division(
                        source.single,
                        term()),
                target.first,
                target.second)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(MultiSelectionNumeratorMultiplicationWithSourceLeft)
    }

    @Test
    fun `Should detect a multi selection numerator multiplication with the target on the left on a link spanning from a division with the source as the numerator to an outside product`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term(), term())
        val origin = product(
                target.first,
                target.second,
                division(
                        source.single,
                        term()))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(MultiSelectionNumeratorMultiplicationWithTargetLeft)
    }

    @Test
    fun `Should detect a multi selection numerator multiplication with the target on the left on a link spanning from an outside product over a division with the target as the numerator`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term())
        val origin = product(
                division(
                        target.single,
                        term()),
                source.first,
                source.second)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(MultiSelectionNumeratorMultiplicationWithTargetLeft)
    }

    private fun detect(origin: AnyOrigin, source: Terms, target: Terms) = detector.detect(Link(origin, source, target))
}