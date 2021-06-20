package com.dg.eqs.core.execution.intention.link

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.single
import com.dg.eqs.classes.operation
import com.dg.eqs.classes.relation
import com.dg.eqs.classes.term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.LinkIntentionEvent.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class LinkIntentionDetectorTest {
    private lateinit var detector: LinkIntentionDetector


    @Before
    fun setUp() {
        detector = LinkIntentionDetector()
    }

    @Test
    fun `Should detect a direct shifting involving zero operations on either side of the relation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(source.single, target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(DirectShifting)
    }

    @Test
    fun `Should detect a direct shifting involving one operation at the left side of the relation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                operation(
                        source.first,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(DirectShifting)
    }

    @Test
    fun `Should detect a direct shifting involving one operation at the right side of the relation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                target.single,
                operation(
                        source.first,
                        term()))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(DirectShifting)
    }

    @Test
    fun `Should detect a indirect shifting involving at least two operations at the left side of the relation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                operation(
                        operation(
                                source.first,
                                term()),
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(IndirectShifting)
    }

    @Test
    fun `Should detect a indirect shifting involving at least two operations at the right side of the relation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = relation(
                target.single,
                operation(
                        term(),
                        operation(
                                source.first,
                                term())))
        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(IndirectShifting)
    }

    @Test
    fun `Should detect a direct condensing involving a single operation`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = operation(source.first, target.first)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(DirectCondensing)
    }

    @Test
    fun `Should detect a indirect condensing involving at least two operations`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = operation(
                operation(
                        source.first,
                        term()),
                target.first)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(IndirectCondensing)
    }

    @Test
    fun `Should always use identity to detect a indirect condensing`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val sourceParent = operation(source.first, term())
        val targetParent = operation(target.first, term())
        val origin = operation(sourceParent, targetParent)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(IndirectCondensing)
    }

    private fun detect(origin: AnyOrigin, source: Terms, target: Terms) =
            detector.detect(Link(origin, source, target))
}