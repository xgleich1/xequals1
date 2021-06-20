package com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.classes.*
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductEvent.InvalidSingleSelectionCondensingMultiplication
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductEvent.ValidSingleSelectionCondensingMultiplication
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class SingleSelectionCondensingInProductDetectorTest {
    private lateinit var detector: SingleSelectionCondensingInProductDetector


    @Before
    fun setUp() {
        detector = SingleSelectionCondensingInProductDetector()
    }

    @Test
    fun `Single selection condensing multiplications involving a variable as the target and a value other than zero or one as the source are invalid`() {
        // GIVEN
        val source = termsOf(value())
        val target = termsOf(variable())
        val product = product(source.first, target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidSingleSelectionCondensingMultiplication)
    }

    @Test
    fun `Single selection condensing multiplications involving a variable as the source and a value other than zero or one as the target are invalid`() {
        // GIVEN
        val source = termsOf(variable())
        val target = termsOf(value())
        val product = product(source.first, target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidSingleSelectionCondensingMultiplication)
    }

    @Test
    fun `Single selection condensing multiplications involving a variable as the source and a variable as the target are invalid`() {
        // GIVEN
        val source = termsOf(variable())
        val target = termsOf(variable())
        val product = product(source.first, target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidSingleSelectionCondensingMultiplication)
    }

    @Test
    fun `Single selection condensing multiplications involving a variable as the target and a zero as the source are valid`() {
        // GIVEN
        val source = termsOf(zero())
        val target = termsOf(variable())
        val product = product(source.first, target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidSingleSelectionCondensingMultiplication)
    }

    @Test
    fun `Single selection condensing multiplications involving a variable as the source and a zero as the target are valid`() {
        // GIVEN
        val source = termsOf(variable())
        val target = termsOf(zero())
        val product = product(source.first, target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidSingleSelectionCondensingMultiplication)
    }

    @Test
    fun `Single selection condensing multiplications involving a variable as the target and a one as the source are valid`() {
        // GIVEN
        val source = termsOf(one())
        val target = termsOf(variable())
        val product = product(source.first, target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidSingleSelectionCondensingMultiplication)
    }

    @Test
    fun `Single selection condensing multiplications involving a variable as the source and a one as the target are valid`() {
        // GIVEN
        val source = termsOf(variable())
        val target = termsOf(one())
        val product = product(source.first, target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidSingleSelectionCondensingMultiplication)
    }

    @Test
    fun `Single selection condensing multiplications involving a variable as the target and anything other than a value or variable as the source are valid`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(variable())
        val product = product(source.first, target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidSingleSelectionCondensingMultiplication)
    }

    @Test
    fun `Single selection condensing multiplications involving a variable as the source and anything other than a value or variable as the target are valid`() {
        // GIVEN
        val source = termsOf(variable())
        val target = termsOf(term())
        val product = product(source.first, target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidSingleSelectionCondensingMultiplication)
    }

    @Test
    fun `Single selection condensing multiplications involving anything other than a variable as the source and anything other than a variable as the target are valid`() {
        // GIVEN
        val source = termsOf(term())
        val target = termsOf(term())
        val product = product(source.first, target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidSingleSelectionCondensingMultiplication)
    }

    private fun value() = value(2)

    private fun variable() = variable("x")

    private fun detect(origin: AnyOrigin, source: Terms, target: Terms) = detector.detect(Link(origin, source, target))
}