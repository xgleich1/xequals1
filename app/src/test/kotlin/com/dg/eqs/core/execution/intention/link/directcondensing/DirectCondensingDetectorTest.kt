package com.dg.eqs.core.execution.intention.link.directcondensing

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingEvent.*
import com.dg.eqs.classes.dashOperation
import com.dg.eqs.classes.division
import com.dg.eqs.classes.product
import com.dg.eqs.classes.term
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class DirectCondensingDetectorTest {
    private lateinit var detector: DirectCondensingDetector


    @Before
    fun setUp() {
        detector = DirectCondensingDetector()
    }

    @Test
    fun `Should detect a single selection condensing in a dash operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val dashOperation = dashOperation(
                source.first,
                target.first)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(SingleSelectionCondensingInDashOperation)
    }

    @Test
    fun `Should detect a single selection condensing in a division`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val division = division(
                source.first,
                target.first)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(SingleSelectionCondensingInDivision)
    }

    @Test
    fun `Should detect a single selection condensing in a product`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val product = product(
                source.first,
                target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(SingleSelectionCondensingInProduct)
    }

    @Test
    fun `Should detect a multi selection condensing in a dash operation with multiple sources`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term())
        val dashOperation = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(MultiSelectionCondensingInDashOperation)
    }

    @Test
    fun `Should detect a multi selection condensing in a dash operation with multiple targets`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term(), term())
        val dashOperation = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(MultiSelectionCondensingInDashOperation)
    }

    @Test
    fun `Should detect a multi selection condensing in a dash operation with multiple sources and targets`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term(), term())
        val dashOperation = dashOperation(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(MultiSelectionCondensingInDashOperation)
    }

    @Test
    fun `Should detect a multi selection condensing in a product with multiple sources`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term())
        val product = product(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(MultiSelectionCondensingInProduct)
    }

    @Test
    fun `Should detect a multi selection condensing in a product with multiple targets`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term(), term())
        val product = product(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(MultiSelectionCondensingInProduct)
    }

    @Test
    fun `Should detect a multi selection condensing in a product with multiple sources and targets`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term(), term())
        val product = product(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(MultiSelectionCondensingInProduct)
    }

    private fun detect(origin: AnyOrigin, source: Terms, target: Terms) = detector.detect(Link(origin, source, target))
}