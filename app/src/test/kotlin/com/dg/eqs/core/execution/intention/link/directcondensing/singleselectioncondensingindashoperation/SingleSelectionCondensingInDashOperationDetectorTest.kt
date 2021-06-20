package com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindashoperation

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.operation.dotoperation.product.NegativeProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindashoperation.SingleSelectionCondensingInDashOperationEvent.*
import com.dg.eqs.classes.dashOperation
import com.dg.eqs.classes.negativeTerm
import com.dg.eqs.classes.positiveTerm
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class SingleSelectionCondensingInDashOperationDetectorTest {
    private lateinit var detector: SingleSelectionCondensingInDashOperationDetector


    @Before
    fun setUp() {
        detector = SingleSelectionCondensingInDashOperationDetector()
    }

    //<editor-fold desc="Invalid condensing of a variable and a value other than zero">
    @Test
    fun `Single selection condensing additions involving a variable as the target and a value other than zero as the source are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(1),
                PositiveVariable("x"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a variable as the target and a value other than zero as the source are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeValue(1),
                NegativeVariable("x"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingSubtraction)
    }

    @Test
    fun `Single selection condensing additions involving a variable as the source and a value other than zero as the target are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(1),
                PositiveVariable("x"))

        val source = termsOf(dashOperation.second)
        val target = termsOf(dashOperation.first)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a variable as the source and a value other than zero as the target are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeValue(1),
                NegativeVariable("x"))

        val source = termsOf(dashOperation.second)
        val target = termsOf(dashOperation.first)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Invalid condensing of a variable and a product">
    @Test
    fun `Single selection condensing additions involving a variable as the target and a product as the source are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveProduct(termA(), termB()),
                PositiveVariable("x"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a variable as the target and a product as the source are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeProduct(termA(), termB()),
                NegativeVariable("x"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingSubtraction)
    }

    @Test
    fun `Single selection condensing additions involving a variable as the source and a product as the target are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveProduct(termA(), termB()),
                PositiveVariable("x"))

        val source = termsOf(dashOperation.second)
        val target = termsOf(dashOperation.first)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a variable as the source and a product as the target are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeProduct(termA(), termB()),
                NegativeVariable("x"))

        val source = termsOf(dashOperation.second)
        val target = termsOf(dashOperation.first)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Invalid condensing of a product and a value other than zero">
    @Test
    fun `Single selection condensing additions involving a product as the target and a value other than zero as the source are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(1),
                PositiveProduct(termA(), termB()))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a product as the target and a value other than zero as the source are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeValue(1),
                NegativeProduct(termA(), termB()))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingSubtraction)
    }

    @Test
    fun `Single selection condensing additions involving a product as the source and a value other than zero as the target are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(1),
                PositiveProduct(termA(), termB()))

        val source = termsOf(dashOperation.second)
        val target = termsOf(dashOperation.first)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a product as the source and a value other than zero as the target are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeValue(1),
                NegativeProduct(termA(), termB()))

        val source = termsOf(dashOperation.second)
        val target = termsOf(dashOperation.first)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Invalid condensing of unlikely variables">
    @Test
    fun `Single selection condensing additions involving a positive variable and a unlikely other positive variable are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveVariable("x"),
                PositiveVariable("y"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a negative variable and a unlikely other negative variable are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeVariable("x"),
                NegativeVariable("y"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingSubtraction)
    }

    @Test
    fun `Single selection condensing additions involving a negative variable and a unlikely other positive variable are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeVariable("x"),
                PositiveVariable("y"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a positive variable and a unlikely other negative variable are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveVariable("x"),
                NegativeVariable("y"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Invalid condensing of unlikely products">
    @Test
    fun `Single selection condensing additions involving a positive product and a unlikely other positive product are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveProduct(termA(), termB()),
                PositiveProduct(termB(), termA()))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a negative product and a unlikely other negative product are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeProduct(termA(), termB()),
                NegativeProduct(termB(), termA()))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingSubtraction)
    }

    @Test
    fun `Single selection condensing additions involving a negative product and a unlikely other positive product are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeProduct(termA(), termB()),
                PositiveProduct(termB(), termA()))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a positive product and a unlikely other negative product are invalid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveProduct(termA(), termB()),
                NegativeProduct(termB(), termA()))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(InvalidSingleSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Valid condensing of a variable and a zero">
    @Test
    fun `Single selection condensing additions involving a variable as the target and a zero as the source are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(0),
                PositiveVariable("x"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a variable as the target and a zero as the source are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeValue(0),
                NegativeVariable("x"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingSubtraction)
    }

    @Test
    fun `Single selection condensing additions involving a variable as the source and a zero as the target are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(0),
                PositiveVariable("x"))

        val source = termsOf(dashOperation.second)
        val target = termsOf(dashOperation.first)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a variable as the source and a zero as the target are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeValue(0),
                NegativeVariable("x"))

        val source = termsOf(dashOperation.second)
        val target = termsOf(dashOperation.first)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Valid condensing of a product and a zero">
    @Test
    fun `Single selection condensing additions involving a product as the target and a zero as the source are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(0),
                PositiveProduct(termA(), termB()))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a product as the target and a zero as the source are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeValue(0),
                NegativeProduct(termA(), termB()))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingSubtraction)
    }

    @Test
    fun `Single selection condensing additions involving a product as the source and a zero as the target are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveValue(0),
                PositiveProduct(termA(), termB()))

        val source = termsOf(dashOperation.second)
        val target = termsOf(dashOperation.first)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a product as the source and a zero as the target are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeValue(0),
                NegativeProduct(termA(), termB()))

        val source = termsOf(dashOperation.second)
        val target = termsOf(dashOperation.first)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Valid condensing of likely variables">
    @Test
    fun `Single selection condensing additions involving a positive variable and a likely other positive variable are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveVariable("x"),
                PositiveVariable("x"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a negative variable and a likely other negative variable are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeVariable("x"),
                NegativeVariable("x"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingSubtraction)
    }

    @Test
    fun `Single selection condensing additions involving a negative variable and a likely other positive variable are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeVariable("x"),
                PositiveVariable("x"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a positive variable and a likely other negative variable are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveVariable("x"),
                NegativeVariable("x"))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingSubtraction)
    }
    //</editor-fold>

    //<editor-fold desc="Valid condensing of likely products">
    @Test
    fun `Single selection condensing additions involving a positive product and a likely other positive product are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveProduct(termA(), termA()),
                PositiveProduct(termA(), termA()))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a negative product and a likely other negative product are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeProduct(termA(), termA()),
                NegativeProduct(termA(), termA()))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingSubtraction)
    }

    @Test
    fun `Single selection condensing additions involving a negative product and a likely other positive product are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                NegativeProduct(termA(), termA()),
                PositiveProduct(termA(), termA()))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingAddition)
    }

    @Test
    fun `Single selection condensing subtractions involving a positive product and a likely other negative product are valid`() {
        // GIVEN
        val dashOperation = dashOperation(
                PositiveProduct(termA(), termA()),
                NegativeProduct(termA(), termA()))

        val source = termsOf(dashOperation.first)
        val target = termsOf(dashOperation.second)

        // THEN
        assertThat(detect(dashOperation, source, target)).isEqualTo(ValidSingleSelectionCondensingSubtraction)
    }
    //</editor-fold>

    private fun termA() = positiveTerm()

    private fun termB() = negativeTerm()

    private fun detect(origin: AnyOrigin, source: Terms, target: Terms) =
            detector.detect(Link(origin, source, target))
}