package com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.classes.*
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct.MultiSelectionCondensingInProductEvent.InvalidMultiSelectionCondensingMultiplication
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct.MultiSelectionCondensingInProductEvent.ValidMultiSelectionCondensingMultiplication
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class MultiSelectionCondensingInProductDetectorTest {
    private lateinit var detector: MultiSelectionCondensingInProductDetector


    @Before
    fun setUp() {
        detector = MultiSelectionCondensingInProductDetector()
    }

    //<editor-fold desc="Invalid condensing of a single value other than zero or one and not all zeros or ones">
    @Test
    fun `Multi selection condensing multiplications involving a single value other than zero or one as the target and not all zeros as the source are invalid`() {
        // GIVEN
        val source = termsOf(zero(), value())
        val target = termsOf(value())
        val product = product(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single value other than zero or one as the target and not all ones as the source are invalid`() {
        // GIVEN
        val source = termsOf(one(), value())
        val target = termsOf(value())
        val product = product(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single value other than zero or one as the source and not all zeros as the target are invalid`() {
        // GIVEN
        val source = termsOf(value())
        val target = termsOf(zero(), value())
        val product = product(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single value other than zero or one as the source and not all ones as the target are invalid`() {
        // GIVEN
        val source = termsOf(value())
        val target = termsOf(one(), value())
        val product = product(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidMultiSelectionCondensingMultiplication)
    }
    //</editor-fold>

    //<editor-fold desc="Invalid condensing of a single variable and not all zeros or ones">
    @Test
    fun `Multi selection condensing multiplications involving a single variable as the target and not all zeros as the source are invalid`() {
        // GIVEN
        val source = termsOf(zero(), value())
        val target = termsOf(variable())
        val product = product(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single variable as the target and not all ones as the source are invalid`() {
        // GIVEN
        val source = termsOf(one(), value())
        val target = termsOf(variable())
        val product = product(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single variable as the source and not all zeros as the target are invalid`() {
        // GIVEN
        val source = termsOf(variable())
        val target = termsOf(zero(), value())
        val product = product(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single variable as the source and not all ones as the target are invalid`() {
        // GIVEN
        val source = termsOf(variable())
        val target = termsOf(one(), value())
        val product = product(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidMultiSelectionCondensingMultiplication)
    }
    //</editor-fold>

    //<editor-fold desc="Invalid condensing of not all zeros or ones and not all zeros or ones">
    @Test
    fun `Multi selection condensing multiplications involving several operands as the target and not all zeros as the source are invalid`() {
        // GIVEN
        val source = termsOf(zero(), value())
        val target = termsOf(term(), term())
        val product = product(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving several operands as the target and not all ones as the source are invalid`() {
        // GIVEN
        val source = termsOf(one(), value())
        val target = termsOf(term(), term())
        val product = product(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving several operands as the source and not all zeros as the target are invalid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(zero(), value())
        val product = product(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving several operands as the source and not all ones as the target are invalid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(one(), value())
        val product = product(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(InvalidMultiSelectionCondensingMultiplication)
    }
    //</editor-fold>

    //<editor-fold desc="Valid condensing of a single zero or one and several operands">
    @Test
    fun `Multi selection condensing multiplications involving a single zero as the target and several operands as the source are valid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(zero())
        val product = product(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single one as the target and several operands as the source are valid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(one())
        val product = product(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single zero as the source and several operands as the target are valid`() {
        // GIVEN
        val source = termsOf(zero())
        val target = termsOf(term(), term())
        val product = product(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single one as the source and several operands as the target are valid`() {
        // GIVEN
        val source = termsOf(one())
        val target = termsOf(term(), term())
        val product = product(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }
    //</editor-fold>

    //<editor-fold desc="Valid condensing of a single value other than zero or one and all zeros or one">
    @Test
    fun `Multi selection condensing multiplications involving a single value other than zero or one as the target and all zeros as the source are valid`() {
        // GIVEN
        val source = termsOf(zero(), zero())
        val target = termsOf(value())
        val product = product(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single value other than zero or one as the target and all ones as the source are valid`() {
        val source = termsOf(one(), one())
        val target = termsOf(value())
        val product = product(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single value other than zero or one as the source and all zeros as the target are valid`() {
        // GIVEN
        val source = termsOf(value())
        val target = termsOf(zero(), zero())
        val product = product(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single value other than zero or one as the source and all ones as the target are valid`() {
        // GIVEN
        val source = termsOf(value())
        val target = termsOf(one(), one())
        val product = product(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }
    //</editor-fold>

    //<editor-fold desc="Valid condensing of a single variable and all zeros or ones">
    @Test
    fun `Multi selection condensing multiplications involving a single variable as the target and all zeros as the source are valid`() {
        // GIVEN
        val source = termsOf(zero(), zero())
        val target = termsOf(variable())
        val product = product(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single variable as the target and all ones as the source are valid`() {
        val source = termsOf(one(), one())
        val target = termsOf(variable())
        val product = product(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single variable as the source and all zeros as the target are valid`() {
        // GIVEN
        val source = termsOf(variable())
        val target = termsOf(zero(), zero())
        val product = product(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving a single variable as the source and all ones as the target are valid`() {
        // GIVEN
        val source = termsOf(variable())
        val target = termsOf(one(), one())
        val product = product(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }
    //</editor-fold>

    //<editor-fold desc="Valid condensing of several operands and all zeros or ones">
    @Test
    fun `Multi selection condensing multiplications involving several operands as the source and all zeros as the target are valid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(zero(), zero())
        val product = product(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving several operands as the source and all ones as the target are valid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(one(), one())
        val product = product(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving several operands as the target and all zeros as the source are valid`() {
        // GIVEN
        val source = termsOf(zero(), zero())
        val target = termsOf(term(), term())
        val product = product(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }

    @Test
    fun `Multi selection condensing multiplications involving several operands the target and all ones as the source are valid`() {
        // GIVEN
        val source = termsOf(one(), one())
        val target = termsOf(term(), term())
        val product = product(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(product, source, target)).isEqualTo(ValidMultiSelectionCondensingMultiplication)
    }
    //</editor-fold>

    private fun value() = value(2)

    private fun variable() = variable("x")

    private fun detect(origin: AnyOrigin, source: Terms, target: Terms) = detector.detect(Link(origin, source, target))
}