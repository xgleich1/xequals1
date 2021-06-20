package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.CondensingReduceEvent.*
import com.dg.eqs.classes.division
import com.dg.eqs.classes.product
import com.dg.eqs.classes.term
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class CondensingReduceDetectorTest {
    private lateinit var detector: CondensingReduceDetector


    @Before
    fun setUp() {
        detector = CondensingReduceDetector()
    }

    //<editor-fold desc="Single selection reduce with parents chain: product - division">
    @Test
    fun `Should detect a single selection reduce with the source in the denominator on a link spanning over a division with a product as the denominator and the source in it`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                target.single,
                product(
                        term(),
                        source.single))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(SingleSelectionReduceWithSourceInDenominator)
    }

    @Test
    fun `Should detect a single selection reduce with the target in the denominator on a link spanning over a division with a product as the numerator and the target as the denominator`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                product(
                        source.single,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(SingleSelectionReduceWithTargetInDenominator)
    }
    //</editor-fold>

    //<editor-fold desc="Single selection reduce with parents chain: division - product">
    @Test
    fun `Should detect a single selection reduce with the source in the denominator on a link spanning over a division with a product as the numerator and the source as the denominator`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                product(
                        target.single,
                        term()),
                source.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(SingleSelectionReduceWithSourceInDenominator)
    }

    @Test
    fun `Should detect a single selection reduce with the target in the denominator on a link spanning over a division with a product as the denominator and the target in it`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = division(
                source.single,
                product(
                        term(),
                        target.single))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(SingleSelectionReduceWithTargetInDenominator)
    }
    //</editor-fold>

    //<editor-fold desc="Single selection reduce with parents chain: product - division - product">
    @Test
    fun `Should detect a single selection reduce with the source in the denominator on a link spanning over a division with a product as the numerator and a product as the denominator and the source in it`() {
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
        assertThat(detect(origin, source, target)).isEqualTo(SingleSelectionReduceWithSourceInDenominator)
    }

    @Test
    fun `Should detect a single selection reduce with the target in the denominator on a link spanning over a division with a product as the numerator and a product as the denominator and the target in it`() {
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
        assertThat(detect(origin, source, target)).isEqualTo(SingleSelectionReduceWithTargetInDenominator)
    }
    //</editor-fold>

    //<editor-fold desc="Multi selection reduce with parents chain: product - division">
    @Test
    fun `Should detect a multi selection reduce with the source in the denominator on a link spanning over a division with a product as the denominator and multiple sources in it`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term())
        val origin = division(
                target.single,
                product(
                        term(),
                        source.first,
                        source.second))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(MultiSelectionReduceWithSourceInDenominator)
    }

    @Test
    fun `Should detect a multi selection reduce with the target in the denominator on a link spanning over a division with a product as the numerator and multiple sources in it`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term())
        val origin = division(
                product(
                        source.first,
                        source.second,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(MultiSelectionReduceWithTargetInDenominator)
    }
    //</editor-fold>

    //<editor-fold desc="Multi selection reduce with parents chain: division - product">
    @Test
    fun `Should detect a multi selection reduce with the source in the denominator on a link spanning over a division with a product as the numerator and multiple targets in it`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term(), term())
        val origin = division(
                product(
                        target.first,
                        target.second,
                        term()),
                source.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(MultiSelectionReduceWithSourceInDenominator)
    }

    @Test
    fun `Should detect a multi selection reduce with the target in the denominator on a link spanning over a division with a product as the denominator and multiple targets in it`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term(), term())
        val origin = division(
                source.single,
                product(
                        term(),
                        target.first,
                        target.second))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(MultiSelectionReduceWithTargetInDenominator)
    }
    //</editor-fold>

    //<editor-fold desc="Multi selection reduce with parents chain: product - division - product">
    @Test
    fun `Should detect a multi selection reduce with the source in the denominator on a link spanning over a division with a product as the numerator and a single target in it and a product as the denominator and multiple sources in it`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term())
        val origin = division(
                product(
                        term(),
                        target.single),
                product(
                        term(),
                        source.first,
                        source.second))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(MultiSelectionReduceWithSourceInDenominator)
    }

    @Test
    fun `Should detect a multi selection reduce with the source in the denominator on a link spanning over a division with a product as the numerator and multiple targets in it and a product as the denominator and a single source in it`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term(), term())
        val origin = division(
                product(
                        term(),
                        target.first,
                        target.second),
                product(
                        term(),
                        source.single))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(MultiSelectionReduceWithSourceInDenominator)
    }

    @Test
    fun `Should detect a multi selection reduce with the source in the denominator on a link spanning over a division with a product as the numerator and multiple targets in it and a product as the denominator and multiple sources in it`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term(), term())
        val origin = division(
                product(
                        term(),
                        target.first,
                        target.second),
                product(
                        term(),
                        source.first,
                        source.second))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(MultiSelectionReduceWithSourceInDenominator)
    }

    @Test
    fun `Should detect a multi selection reduce with the target in the denominator on a link spanning over a division with a product as the numerator and multiple sources in it and a product as the denominator and a single target in it`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term())
        val origin = division(
                product(
                        term(),
                        source.first,
                        source.second),
                product(
                        term(),
                        target.single))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(MultiSelectionReduceWithTargetInDenominator)
    }

    @Test
    fun `Should detect a multi selection reduce with the target in the denominator on a link spanning over a division with a product as the numerator and a single source in it and a product as the denominator and multiple targets in it`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term(), term())
        val origin = division(
                product(
                        term(),
                        source.single),
                product(
                        term(),
                        target.first,
                        target.second))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(MultiSelectionReduceWithTargetInDenominator)
    }

    @Test
    fun `Should detect a multi selection reduce with the target in the denominator on a link spanning over a division with a product as the numerator and multiple sources in it and a product as the denominator and multiple targets in it`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term(), term())
        val origin = division(
                product(
                        term(),
                        source.first,
                        source.second),
                product(
                        term(),
                        target.first,
                        target.second))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(MultiSelectionReduceWithTargetInDenominator)
    }
    //</editor-fold>

    private fun detect(origin: AnyOrigin, source: Terms, target: Terms) = detector.detect(Link(origin, source, target))
}