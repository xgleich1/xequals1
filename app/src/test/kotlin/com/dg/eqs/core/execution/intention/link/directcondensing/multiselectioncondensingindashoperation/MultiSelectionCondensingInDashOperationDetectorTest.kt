package com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensingindashoperation

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.classes.*
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensingindashoperation.MultiSelectionCondensingInDashOperationEvent.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class MultiSelectionCondensingInDashOperationDetectorTest {
    private lateinit var detector: MultiSelectionCondensingInDashOperationDetector


    @Before
    fun setUp() {
        detector = MultiSelectionCondensingInDashOperationDetector()
    }

    //<editor-fold desc="Invalid condensing of a single value other than zero and not all zeros">
    @Test
    fun `Multi selection condensing additions involving a single value other than zero as the target and not all zeros as the source are invalid`() {
        // GIVEN
        val source = termsOf(zero(), value())
        val target = termsOf(positiveValue())
        val addition = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(InvalidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving a single value other than zero as the target and not all zeros as the source are invalid`() {
        // GIVEN
        val source = termsOf(zero(), value())
        val target = termsOf(negativeValue())
        val subtraction = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(InvalidMultiSelectionCondensingSubtraction)
    }

    @Test
    fun `Multi selection condensing additions involving a single value other than zero as the source and not all zeros as the target are invalid`() {
        // GIVEN
        val source = termsOf(value())
        val target = termsOf(positiveZero(), value())
        val addition = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(InvalidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving a single value other than zero as the source and not all zeros as the target are invalid`() {
        // GIVEN
        val source = termsOf(value())
        val target = termsOf(negativeZero(), value())
        val subtraction = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(InvalidMultiSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Invalid condensing of a single variable and not all zeros">
    @Test
    fun `Multi selection condensing additions involving a single variable as the target and not all zeros as the source are invalid`() {
        // GIVEN
        val source = termsOf(zero(), value())
        val target = termsOf(positiveVariable())
        val addition = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(InvalidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving a single variable as the target and not all zeros as the source are invalid`() {
        // GIVEN
        val source = termsOf(zero(), value())
        val target = termsOf(negativeVariable())
        val subtraction = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(InvalidMultiSelectionCondensingSubtraction)
    }

    @Test
    fun `Multi selection condensing additions involving a single variable as the source and not all zeros as the target are invalid`() {
        // GIVEN
        val source = termsOf(variable())
        val target = termsOf(positiveZero(), value())
        val addition = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(InvalidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving a single variable as the source and not all zeros as the target are invalid`() {
        // GIVEN
        val source = termsOf(variable())
        val target = termsOf(negativeZero(), value())
        val subtraction = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(InvalidMultiSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Invalid condensing of a single product and not all zeros">
    @Test
    fun `Multi selection condensing additions involving a single product as the target and not all zeros as the source are invalid`() {
        // GIVEN
        val source = termsOf(zero(), value())
        val target = termsOf(positiveProduct())
        val addition = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(InvalidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving a single product as the target and not all zeros as the source are invalid`() {
        // GIVEN
        val source = termsOf(zero(), value())
        val target = termsOf(negativeProduct())
        val subtraction = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(InvalidMultiSelectionCondensingSubtraction)
    }

    @Test
    fun `Multi selection condensing additions involving a single product as the source and not all zeros as the target are invalid`() {
        // GIVEN
        val source = termsOf(product())
        val target = termsOf(positiveZero(), value())
        val addition = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(InvalidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving a single product as the source and not all zeros as the target are invalid`() {
        // GIVEN
        val source = termsOf(product())
        val target = termsOf(negativeZero(), value())
        val subtraction = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(InvalidMultiSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Invalid condensing of not all zeros and not all zeros">
    @Test
    fun `Multi selection condensing additions involving several operands as the target and not all zeros as the source are invalid`() {
        // GIVEN
        val source = termsOf(zero(), value())
        val target = termsOf(positiveTerm(), term())
        val addition = dashOperation(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(InvalidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving several operands as the target and not all zeros as the source are invalid`() {
        // GIVEN
        val source = termsOf(zero(), value())
        val target = termsOf(negativeTerm(), term())
        val subtraction = dashOperation(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(InvalidMultiSelectionCondensingSubtraction)
    }

    @Test
    fun `Multi selection condensing additions involving several operands as the source and not all zeros as the target are invalid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(positiveZero(), value())
        val addition = dashOperation(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(InvalidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving several operands as the source and not all zeros as the target are invalid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(negativeZero(), value())
        val subtraction = dashOperation(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(InvalidMultiSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Valid condensing of a single zero and several operands">
    @Test
    fun `Multi selection condensing additions involving a single zero as the target and several operands as the source are valid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(positiveZero())
        val addition = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(ValidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving a single zero as the target and several operands as the source are valid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(negativeZero())
        val subtraction = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(ValidMultiSelectionCondensingSubtraction)
    }

    @Test
    fun `Multi selection condensing additions involving a single zero as the source and several operands as the target are valid`() {
        // GIVEN
        val source = termsOf(zero())
        val target = termsOf(positiveTerm(), term())
        val addition = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(ValidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving a single zero as the source and several operands as the target are valid`() {
        // GIVEN
        val source = termsOf(zero())
        val target = termsOf(negativeTerm(), term())
        val subtraction = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(ValidMultiSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Valid condensing of a single value other than zero and all zeros">
    @Test
    fun `Multi selection condensing additions involving a single value other than zero as the target and all zeros as the source are valid`() {
        // GIVEN
        val source = termsOf(zero(), zero())
        val target = termsOf(positiveValue())
        val addition = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(ValidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving a single value other than zero as the target and all zeros as the source are valid`() {
        val source = termsOf(zero(), zero())
        val target = termsOf(negativeValue())
        val subtraction = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(ValidMultiSelectionCondensingSubtraction)
    }

    @Test
    fun `Multi selection condensing additions involving a single value other than zero as the source and all zeros as the target are valid`() {
        // GIVEN
        val source = termsOf(value())
        val target = termsOf(positiveZero(), zero())
        val addition = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(ValidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving a single value other than zero as the source and all zeros as the target are valid`() {
        // GIVEN
        val source = termsOf(value())
        val target = termsOf(negativeZero(), zero())
        val subtraction = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(ValidMultiSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Valid condensing of a single variable and all zeros">
    @Test
    fun `Multi selection condensing additions involving a single variable as the target and all zeros as the source are valid`() {
        // GIVEN
        val source = termsOf(zero(), zero())
        val target = termsOf(positiveVariable())
        val addition = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(ValidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving a single variable as the target and all zeros as the source are valid`() {
        val source = termsOf(zero(), zero())
        val target = termsOf(negativeVariable())
        val subtraction = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(ValidMultiSelectionCondensingSubtraction)
    }

    @Test
    fun `Multi selection condensing additions involving a single variable as the source and all zeros as the target are valid`() {
        // GIVEN
        val source = termsOf(variable())
        val target = termsOf(positiveZero(), zero())
        val addition = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(ValidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving a single variable as the source and all zeros as the target are valid`() {
        // GIVEN
        val source = termsOf(variable())
        val target = termsOf(negativeZero(), zero())
        val subtraction = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(ValidMultiSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Valid condensing of a single product and all zeros">
    @Test
    fun `Multi selection condensing additions involving a single product as the target and all zeros as the source are valid`() {
        // GIVEN
        val source = termsOf(zero(), zero())
        val target = termsOf(positiveProduct())
        val addition = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(ValidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving a single product as the target and all zeros as the source are valid`() {
        val source = termsOf(zero(), zero())
        val target = termsOf(negativeProduct())
        val subtraction = dashOperation(
                source.first,
                source.second,
                target.first)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(ValidMultiSelectionCondensingSubtraction)
    }

    @Test
    fun `Multi selection condensing additions involving a single product as the source and all zeros as the target are valid`() {
        // GIVEN
        val source = termsOf(product())
        val target = termsOf(positiveZero(), zero())
        val addition = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(ValidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving a single product as the source and all zeros as the target are valid`() {
        // GIVEN
        val source = termsOf(product())
        val target = termsOf(negativeZero(), zero())
        val subtraction = dashOperation(
                source.first,
                target.first,
                target.second)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(ValidMultiSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Valid condensing of several operands and all zeros">
    @Test
    fun `Multi selection condensing additions involving several operands as the source and all zeros as the target are valid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(positiveZero(), zero())
        val addition = dashOperation(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(ValidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving several operands as the source and all zeros as the target are valid`() {
        // GIVEN
        val source = termsOf(term(), term())
        val target = termsOf(negativeZero(), zero())
        val subtraction = dashOperation(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(ValidMultiSelectionCondensingSubtraction)
    }

    @Test
    fun `Multi selection condensing additions involving several operands as the target and all zeros as the source are valid`() {
        // GIVEN
        val source = termsOf(zero(), zero())
        val target = termsOf(positiveTerm(), term())
        val addition = dashOperation(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(addition, source, target)).isEqualTo(ValidMultiSelectionCondensingAddition)
    }

    @Test
    fun `Multi selection condensing subtractions involving several operands the target and all zeros as the source are valid`() {
        // GIVEN
        val source = termsOf(zero(), zero())
        val target = termsOf(negativeTerm(), term())
        val subtraction = dashOperation(
                source.first,
                source.second,
                target.first,
                target.second)

        // THEN
        assertThat(detect(subtraction, source, target)).isEqualTo(ValidMultiSelectionCondensingSubtraction)
    }
    //</editor-fold>

    private fun value() = value(2)

    private fun positiveValue() = positiveValue(2)

    private fun negativeValue() = negativeValue(2)

    private fun variable() = variable("x")

    private fun positiveVariable() = positiveVariable("x")

    private fun negativeVariable() = negativeVariable("x")

    private fun product() = product(term(), term())

    private fun positiveProduct() = positiveProduct(term(), term())

    private fun negativeProduct() = negativeProduct(term(), term())

    private fun detect(origin: AnyOrigin, source: Terms, target: Terms) = detector.detect(Link(origin, source, target))
}