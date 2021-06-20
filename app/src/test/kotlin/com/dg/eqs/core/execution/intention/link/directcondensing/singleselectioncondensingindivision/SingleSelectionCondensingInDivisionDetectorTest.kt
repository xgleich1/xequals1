package com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindivision

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.NegativeProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindivision.SingleSelectionCondensingInDivisionEvent.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class SingleSelectionCondensingInDivisionDetectorTest {
    private lateinit var detector: SingleSelectionCondensingInDivisionDetector


    @Before
    fun setUp() {
        detector = SingleSelectionCondensingInDivisionDetector()
    }

    //<editor-fold desc="Invalid division with a value as the numerator">
    @Test
    fun `Single selection condensing divisions with a positive value as the numerator and a positive zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveValue(1),
                PositiveValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a negative value as the numerator and a negative zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                NegativeValue(1),
                NegativeValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a positive value as the numerator and a negative zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveValue(1),
                NegativeValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a negative value as the numerator and a positive zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                NegativeValue(1),
                PositiveValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a positive value as the numerator and a positive value as the denominator having an gcd of one are invalid`() {
        // GIVEN
        val division = division(
                PositiveValue(1),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive value other than zero as the numerator and a positive variable as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveValue(1),
                PositiveVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive value other than zero as the numerator and a positive dash operation as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveValue(1),
                PositiveDashOperation(PositiveValue(2), PositiveValue(3)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive value other than zero as the numerator and a positive product as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveValue(1),
                PositiveProduct(PositiveValue(2), PositiveValue(3)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Invalid division with a variable as the numerator">
    @Test
    fun `Single selection condensing divisions with a positive variable as the numerator and a positive zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                PositiveValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a negative variable as the numerator and a negative zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                NegativeValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a positive variable as the numerator and a negative zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                NegativeValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a negative variable as the numerator and a zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                PositiveValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a positive variable as the numerator and a positive value other than one as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive variable as the numerator and a different positive variable as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                PositiveVariable("y"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive variable as the numerator and a positive dash operation as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive variable as the numerator and a positive product as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                PositiveProduct(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Invalid division with a dash operation as the numerator">
    @Test
    fun `Single selection condensing divisions with a positive dash operation as the numerator and a positive zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)),
                PositiveValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a negative dash operation as the numerator and a negative zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)),
                NegativeValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a positive dash operation as the numerator and a negative zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)),
                NegativeValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a negative dash operation as the numerator and a zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)),
                PositiveValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a positive dash operation as the numerator and a positive value other than one as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive dash operation as the numerator and a positive variable as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)),
                PositiveVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive dash operation as the numerator and a different positive dash operation as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)),
                PositiveDashOperation(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive dash operation as the numerator and a positive product as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)),
                PositiveProduct(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Invalid division with a product as the numerator">
    @Test
    fun `Single selection condensing divisions with a positive product as the numerator and a positive zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(1), PositiveValue(2)),
                PositiveValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a negative product as the numerator and a negative zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                NegativeProduct(PositiveValue(1), PositiveValue(2)),
                NegativeValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a positive product as the numerator and a negative zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(1), PositiveValue(2)),
                NegativeValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a negative product as the numerator and a positive zero as the denominator are invalid`() {
        // GIVEN
        val division = division(
                NegativeProduct(PositiveValue(1), PositiveValue(2)),
                PositiveValue(0))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivisionThroughZero)
    }

    @Test
    fun `Single selection condensing divisions with a positive product as the numerator and a positive value other than one as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(1), PositiveValue(2)),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive product as the numerator and a positive variable as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(1), PositiveValue(2)),
                PositiveVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive product as the numerator and a positive dash operation as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(1), PositiveValue(2)),
                PositiveDashOperation(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive product as the numerator and a different positive product as the denominator are invalid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(1), PositiveValue(2)),
                PositiveProduct(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(InvalidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a value as the numerator and a value as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive zero as the numerator and a positive value as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative zero as the numerator and a negative value as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(0),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive zero as the numerator and a negative value as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative zero as the numerator and a positive value as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(0),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive one as the denominator and a positive value as the numerator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(2),
                PositiveValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative one as the denominator and a negative value as the numerator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(2),
                NegativeValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative one as the denominator and a positive value as the numerator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(2),
                NegativeValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive one as the denominator and a negative value as the numerator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(2),
                PositiveValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive value as the numerator and an equal value as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(2),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative value as the numerator and an equal value as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(2),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive value as the numerator and a positive value as the denominator having an gcd greater than one are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(4),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative value as the numerator and a negative value as the denominator having an gcd greater than one are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(4),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive value as the numerator and a negative value as the denominator having an gcd greater than one are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(4),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative value as the numerator and a positive value as the denominator having an gcd greater than one are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(4),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative value as the numerator and a negative value as the denominator having an gcd of one are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(1),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive value as the numerator and a negative value as the denominator having an gcd of one are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(1),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative value as the numerator and a positive value as the denominator having an gcd of one are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(1),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a value as the numerator and a variable as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive zero as the numerator and a positive variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                PositiveVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative zero as the numerator and a negative variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(0),
                NegativeVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive zero as the numerator and a negative variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                NegativeVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative zero as the numerator and a positive variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(0),
                PositiveVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative value other than zero as the numerator and a negative variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(1),
                NegativeVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive value other than zero as the numerator and a negative variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(1),
                NegativeVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative value other than zero as the numerator and a positive variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(1),
                PositiveVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a value as the numerator and a dash operation as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive zero as the numerator and a positive dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative zero as the numerator and a negative dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(0),
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive zero as the numerator and a negative dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative zero as the numerator and a positive dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(0),
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative value other than zero as the numerator and a negative dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(1),
                NegativeDashOperation(PositiveValue(2), PositiveValue(3)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive value other than zero as the numerator and a negative dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(1),
                NegativeDashOperation(PositiveValue(2), PositiveValue(3)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative value other than zero as the numerator and a positive dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(1),
                PositiveDashOperation(PositiveValue(2), PositiveValue(3)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a value as the numerator and a division as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive zero as the numerator and a positive division as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                PositiveDivision(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative zero as the numerator and a negative division as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(0),
                NegativeDivision(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive zero as the numerator and a negative division as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                NegativeDivision(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative zero as the numerator and a positive division as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(0),
                PositiveDivision(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive value other than zero as the numerator and a positive division as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(1),
                PositiveDivision(PositiveValue(2), PositiveValue(3)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative value other than zero as the numerator and a negative division as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(1),
                NegativeDivision(PositiveValue(2), PositiveValue(3)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive value other than zero as the numerator and a negative division as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(1),
                NegativeDivision(PositiveValue(2), PositiveValue(3)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative value other than zero as the numerator and a positive division as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(1),
                PositiveDivision(PositiveValue(2), PositiveValue(3)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a value as the numerator and a product as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive zero as the numerator and a positive product as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                PositiveProduct(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative zero as the numerator and a negative product as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(0),
                NegativeProduct(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive zero as the numerator and a negative product as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(0),
                NegativeProduct(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative zero as the numerator and a positive product as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(0),
                PositiveProduct(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative value other than zero as the numerator and a negative product as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(1),
                NegativeProduct(PositiveValue(2), PositiveValue(3)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive value other than zero as the numerator and a negative product as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveValue(1),
                NegativeProduct(PositiveValue(2), PositiveValue(3)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative value other than zero as the numerator and a positive product as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeValue(1),
                PositiveProduct(PositiveValue(2), PositiveValue(3)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a variable as the numerator and a value as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive one as the denominator and a positive variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                PositiveValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative one as the denominator and a negative variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                NegativeValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative one as the denominator and a positive variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                NegativeValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive one as the denominator and a negative variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                PositiveValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative variable as the numerator and a negative value other than one as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive variable as the numerator and a negative value other than one as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative variable as the numerator and a positive value other than one as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a variable as the numerator and a variable as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive variable as the numerator and an equal variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                PositiveVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative variable as the numerator and a different negative variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                NegativeVariable("y"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive variable as the numerator and a different negative variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                NegativeVariable("y"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative variable as the numerator and a different positive variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                PositiveVariable("y"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a variable as the numerator and a dash operation as the denominator">
    @Test
    fun `Single selection condensing divisions with a negative variable as the numerator and a negative dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive variable as the numerator and a negative dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative variable as the numerator and a positive dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a variable as the numerator and a division as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive variable as the numerator and a positive division as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                PositiveDivision(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative variable as the numerator and a negative division as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                NegativeDivision(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive variable as the numerator and a negative division as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                NegativeDivision(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative variable as the numerator and a positive division as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                PositiveDivision(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a variable as the numerator and a product as the denominator">
    @Test
    fun `Single selection condensing divisions with a negative variable as the numerator and a negative product as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                NegativeProduct(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive variable as the numerator and a negative product as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveVariable("x"),
                NegativeProduct(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative variable as the numerator and a positive product as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeVariable("x"),
                PositiveProduct(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a dash operation as the numerator and a value as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive one as the denominator and a positive dash operation as the numerator are valid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(2), PositiveValue(3)),
                PositiveValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative one as the denominator and a negative dash operation as the numerator are valid`() {
        // GIVEN
        val division = division(
                NegativeDashOperation(PositiveValue(2), PositiveValue(3)),
                NegativeValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative one as the denominator and a positive dash operation as the numerator are valid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(2), PositiveValue(3)),
                NegativeValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive one as the denominator and a negative dash operation as the numerator are valid`() {
        // GIVEN
        val division = division(
                NegativeDashOperation(PositiveValue(2), PositiveValue(3)),
                PositiveValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative dash operation as the numerator and a negative value other than one as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive dash operation as the numerator and a negative value other than one as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative dash operation as the numerator and a positive value other than one as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a dash operation as the numerator and a variable as the denominator">
    @Test
    fun `Single selection condensing divisions with a negative dash operation as the numerator and a negative variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)),
                NegativeVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive dash operation as the numerator and a negative variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)),
                NegativeVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative dash operation as the numerator and a positive variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)),
                PositiveVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a dash operation as the numerator and a dash operation as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive dash operation as the numerator and an equal positive dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)),
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative dash operation as the numerator and a different negative dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)),
                NegativeDashOperation(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive dash operation as the numerator and a different negative dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)),
                NegativeDashOperation(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative dash operation as the numerator and a different positive dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)),
                PositiveDashOperation(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a dash operation as the numerator and a division as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive dash operation as the numerator and a positive division as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)),
                PositiveDivision(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative dash operation as the numerator and a negative division as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)),
                NegativeDivision(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive dash operation as the numerator and a negative division as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)),
                NegativeDivision(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative dash operation as the numerator and a positive division as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)),
                PositiveDivision(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a dash operation as the numerator and a product as the denominator">
    @Test
    fun `Single selection condensing divisions with a negative dash operation as the numerator and a negative product as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)),
                NegativeProduct(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive dash operation as the numerator and a negative product as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDashOperation(PositiveValue(1), PositiveValue(2)),
                NegativeProduct(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative dash operation as the numerator and a positive product as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDashOperation(PositiveValue(1), PositiveValue(2)),
                PositiveProduct(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a division as the numerator and a value as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive one as the denominator and a positive division as the numerator are valid`() {
        // GIVEN
        val division = division(
                PositiveDivision(PositiveValue(2), PositiveValue(3)),
                PositiveValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative one as the denominator and a negative division as the numerator are valid`() {
        // GIVEN
        val division = division(
                NegativeDivision(PositiveValue(2), PositiveValue(3)),
                NegativeValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative one as the denominator and a positive division as the numerator are valid`() {
        // GIVEN
        val division = division(
                PositiveDivision(PositiveValue(2), PositiveValue(3)),
                NegativeValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive one as the denominator and a negative division as the numerator are valid`() {
        // GIVEN
        val division = division(
                NegativeDivision(PositiveValue(2), PositiveValue(3)),
                PositiveValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive division as the numerator and a positive value other than one as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDivision(PositiveValue(1), PositiveValue(2)),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative division as the numerator and a negative value as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDivision(PositiveValue(1), PositiveValue(2)),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive division as the numerator and a negative value other than one as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDivision(PositiveValue(1), PositiveValue(2)),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative division as the numerator and a positive value other than one as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDivision(PositiveValue(1), PositiveValue(2)),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a division as the numerator and a variable as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive division as the numerator and a positive variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDivision(PositiveValue(1), PositiveValue(2)),
                PositiveVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative division as the numerator and a negative variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDivision(PositiveValue(1), PositiveValue(2)),
                NegativeVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive division as the numerator and a negative variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDivision(PositiveValue(1), PositiveValue(2)),
                NegativeVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative division as the numerator and a positive variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDivision(PositiveValue(1), PositiveValue(2)),
                PositiveVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a division as the numerator and a dash operation as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive division as the numerator and a positive dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDivision(PositiveValue(1), PositiveValue(2)),
                PositiveDashOperation(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative division as the numerator and a negative dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDivision(PositiveValue(1), PositiveValue(2)),
                NegativeDashOperation(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive division as the numerator and a negative dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDivision(PositiveValue(1), PositiveValue(2)),
                NegativeDashOperation(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative division as the numerator and a positive dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDivision(PositiveValue(1), PositiveValue(2)),
                PositiveDashOperation(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a division as the numerator and a division as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive division as the numerator and a positive division as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDivision(PositiveValue(1), PositiveValue(2)),
                PositiveDivision(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative division as the numerator and a negative division as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDivision(PositiveValue(1), PositiveValue(2)),
                NegativeDivision(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive division as the numerator and a negative division as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDivision(PositiveValue(1), PositiveValue(2)),
                NegativeDivision(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative division as the numerator and a positive division as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDivision(PositiveValue(1), PositiveValue(2)),
                PositiveDivision(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a division as the numerator and a product as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive division as the numerator and a positive product as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDivision(PositiveValue(1), PositiveValue(2)),
                PositiveProduct(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative division as the numerator and a negative product as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDivision(PositiveValue(1), PositiveValue(2)),
                NegativeProduct(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive division as the numerator and a negative product as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveDivision(PositiveValue(1), PositiveValue(2)),
                NegativeProduct(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative division as the numerator and a positive product as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeDivision(PositiveValue(1), PositiveValue(2)),
                PositiveProduct(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a product as the numerator and a value as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive one as the denominator and a positive product as the numerator are valid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(2), PositiveValue(3)),
                PositiveValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative one as the denominator and a negative product as the numerator are valid`() {
        // GIVEN
        val division = division(
                NegativeProduct(PositiveValue(2), PositiveValue(3)),
                NegativeValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative one as the denominator and a positive product as the numerator are valid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(2), PositiveValue(3)),
                NegativeValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive one as the denominator and a negative product as the numerator are valid`() {
        // GIVEN
        val division = division(
                NegativeProduct(PositiveValue(2), PositiveValue(3)),
                PositiveValue(1))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative product as the numerator and a negative value other than one as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeProduct(PositiveValue(1), PositiveValue(2)),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive product as the numerator and a negative value other than one as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(1), PositiveValue(2)),
                NegativeValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative product as the numerator and a positive value other than one as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeProduct(PositiveValue(1), PositiveValue(2)),
                PositiveValue(2))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a product as the numerator and a variable as the denominator">
    @Test
    fun `Single selection condensing divisions with a negative product as the numerator and a negative variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeProduct(PositiveValue(1), PositiveValue(2)),
                NegativeVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive product as the numerator and a negative variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(1), PositiveValue(2)),
                NegativeVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative product as the numerator and a positive variable as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeProduct(PositiveValue(1), PositiveValue(2)),
                PositiveVariable("x"))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a product as the numerator and a dash operation as the denominator">
    @Test
    fun `Single selection condensing divisions with a negative product as the numerator and a negative dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeProduct(PositiveValue(1), PositiveValue(2)),
                NegativeDashOperation(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive product as the numerator and a negative dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(1), PositiveValue(2)),
                NegativeDashOperation(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative product as the numerator and a positive dash operation as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeProduct(PositiveValue(1), PositiveValue(2)),
                PositiveDashOperation(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a product as the numerator and a division as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive product as the numerator and a positive division as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(1), PositiveValue(2)),
                PositiveDivision(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative product as the numerator and a negative division as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeProduct(PositiveValue(1), PositiveValue(2)),
                NegativeDivision(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive product as the numerator and a negative division as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(1), PositiveValue(2)),
                NegativeDivision(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative product as the numerator and a positive division as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeProduct(PositiveValue(1), PositiveValue(2)),
                PositiveDivision(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    //<editor-fold desc="Valid division with a product as the numerator and a product as the denominator">
    @Test
    fun `Single selection condensing divisions with a positive product as the numerator and an equal positive product as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(1), PositiveValue(2)),
                PositiveProduct(PositiveValue(1), PositiveValue(2)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative product as the numerator and a different negative product as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeProduct(PositiveValue(1), PositiveValue(2)),
                NegativeProduct(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a positive product as the numerator and a different negative product as the denominator are valid`() {
        // GIVEN
        val division = division(
                PositiveProduct(PositiveValue(1), PositiveValue(2)),
                NegativeProduct(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }

    @Test
    fun `Single selection condensing divisions with a negative product as the numerator and a different positive product as the denominator are valid`() {
        // GIVEN
        val division = division(
                NegativeProduct(PositiveValue(1), PositiveValue(2)),
                PositiveProduct(PositiveValue(3), PositiveValue(4)))

        val source = termsOf(division.numerator)
        val target = termsOf(division.denominator)

        // THEN
        assertThat(detect(division, source, target)).isEqualTo(ValidSingleSelectionCondensingDivision)
    }
    //</editor-fold>

    private fun division(numerator: Term, denominator: Term) = object : Division(numerator, denominator) {
        override val isNegative get() = TODO("not implemented")


        override fun invert() = TODO("not implemented")

        override fun recreate(newNumerator: Term, newDenominator: Term) = TODO("not implemented")

        override fun applySign() = TODO("not implemented")
    }

    private fun detect(division: AnyOrigin, source: Terms, target: Terms) = detector.detect(Link(division, source, target))
}