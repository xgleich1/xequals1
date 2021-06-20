package com.dg.eqs.core.execution.intention

import com.dg.eqs.asserts.isContentEqualByIdentityTo
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.classes.draft
import com.dg.eqs.classes.operation
import com.dg.eqs.classes.term
import com.dg.eqs.classes.termDraft
import com.dg.eqs.core.execution.intention.Intention.Link
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class IntentionTest {
    @Test(expected = IllegalStateException::class)
    fun `A link throws an exception when its source is empty`() {
        // GIVEN
        val source = termsOf()
        val target = termsOf(term())
        val origin = operation(term(), target.first)

        // WHEN
        Link(origin, source, target)
    }

    @Test(expected = IllegalStateException::class)
    fun `A link throws an exception when its target is empty`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf()
        val origin = operation(source.first, term())

        // WHEN
        Link(origin, source, target)
    }

    @Test(expected = IllegalStateException::class)
    fun `A link throws an exception when its source has no parent`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = operation(term(), target.first)

        // WHEN
        Link(origin, source, target)
    }

    @Test(expected = IllegalStateException::class)
    fun `A link throws an exception when its target has no parent`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = operation(source.first, term())

        // WHEN
        Link(origin, source, target)
    }

    @Test(expected = IllegalStateException::class)
    fun `A link throws an exception when its source & target have no mutual parent`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        operation(term(), source.first)
        operation(term(), target.first)
        val origin = operation(term(), term())

        // WHEN
        Link(origin, source, target)
    }

    @Test
    fun `A link provides its first source`() {
        // GIVEN
        val firstSource = term()
        val secondSource = term()
        val firstTarget = term()
        val secondTarget = term()

        val source = termsOf(firstSource, secondSource)
        val target = termsOf(firstTarget, secondTarget)
        val origin = operation(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(Link(origin, source, target).firstSource).isSameAs(firstSource)
    }

    @Test
    fun `A link provides its first target`() {
        // GIVEN
        val firstSource = term()
        val secondSource = term()
        val firstTarget = term()
        val secondTarget = term()

        val source = termsOf(firstSource, secondSource)
        val target = termsOf(firstTarget, secondTarget)
        val origin = operation(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(Link(origin, source, target).firstTarget).isSameAs(firstTarget)
    }

    @Test
    fun `A link provides the parent of its source`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val sourceParent = operation(term(), source.first)
        val origin = operation(sourceParent, target.first)

        // THEN
        assertThat(Link(origin, source, target).sourceParent).isSameAs(sourceParent)
    }

    @Test
    fun `A link provides the parent of its target`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val targetParent = operation(term(), target.first)
        val origin = operation(source.first, targetParent)

        // THEN
        assertThat(Link(origin, source, target).targetParent).isSameAs(targetParent)
    }

    @Test
    fun `A link provides the mutual parent of its source and target`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val sourceParent = operation(term(), source.first)
        val targetParent = operation(term(), target.first)
        val mutualParent = operation(sourceParent, targetParent)
        val origin = operation(mutualParent, term())

        // THEN
        assertThat(Link(origin, source, target).mutualParent).isSameAs(mutualParent)
    }

    @Test
    fun `A link provides the parents chain from its source to its target`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val sourceParent = operation(term(), source.first)
        val targetParent = operation(term(), target.first)
        val mutualParent = operation(sourceParent, targetParent)
        val origin = operation(mutualParent, term())

        // WHEN
        val parentsChain = Link(origin, source, target).parentsChain

        // THEN
        val expectedParentsChain = listOf(sourceParent, mutualParent, targetParent)

        assertThat(parentsChain).isContentEqualByIdentityTo(expectedParentsChain)
    }

    @Test
    fun `A link provides a secondary constructor taking individual terms`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = operation(source.first, target.first)

        // WHEN
        val link = Link(origin, source.first, target.first)

        // THEN
        assertThat(link.origin).isSameAs(origin)
        assertThat(link.source).isContentEqualByIdentityTo(source)
        assertThat(link.target).isContentEqualByIdentityTo(target)
    }

    @Test
    fun `A link provides a secondary constructor taking individual term drafts`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = operation(source.first, target.first)

        // WHEN
        val link = Link(
                draft(origin),
                termDraft(source.first),
                termDraft(target.first))

        // THEN
        assertThat(link.origin).isSameAs(origin)
        assertThat(link.source).isContentEqualByIdentityTo(source)
        assertThat(link.target).isContentEqualByIdentityTo(target)
    }

    @Test
    fun `A link provides a secondary constructor taking a list of term drafts`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(term(), term())
        val origin = operation(
                source.first,
                source.second,
                target.first,
                target.second)

        // WHEN
        val link = Link(
                draft(origin),
                draftsOf(termDraft(source.first), termDraft(source.second)),
                draftsOf(termDraft(target.first), termDraft(target.second)))

        // THEN
        assertThat(link.origin).isSameAs(origin)
        assertThat(link.source).isContentEqualByIdentityTo(source)
        assertThat(link.target).isContentEqualByIdentityTo(target)
    }

    @Test
    fun `Should compare two equal links`() {
        val source = termsOf(term())
        val target = termsOf(term())
        val origin = operation(source.first, target.first)

        val linkA = Link(origin, source, target)
        val linkB = Link(origin, source, target)

        // THEN
        assertThat(linkA).isEqualTo(linkB)
    }
}