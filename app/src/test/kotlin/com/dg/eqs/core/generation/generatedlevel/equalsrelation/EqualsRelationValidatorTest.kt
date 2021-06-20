package com.dg.eqs.core.generation.generatedlevel.equalsrelation

import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EqualsRelationValidatorTest {
    @InjectMocks
    private lateinit var validator: EqualsRelationValidator


    //<editor-fold desc="Division through zero validation">
    @Test
    fun `A equals relation with the variable on the left and without a division through zero is valid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveVariable("x"),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(1)))
        // THEN
        assertTrue(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the right and without a division through zero is valid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(1)),
                PositiveVariable("x"))

        // THEN
        assertTrue(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the left and a direct division through zero is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveVariable("x"),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(0)))
        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the right and a direct division through zero is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(0)),
                PositiveVariable("x"))

        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the left and an indirect division through zero is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveVariable("x"),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveDashOperation(
                                PositiveValue(1),
                                NegativeValue(1))))
        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the right and an indirect division through zero is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveDashOperation(
                                PositiveValue(1),
                                NegativeValue(1))),
                PositiveVariable("x"))

        // THEN
        assertFalse(validator.validate(equalsRelation))
    }
    //</editor-fold>

    //<editor-fold desc="Side elimination validation">
    @Test
    fun `A equals relation with the variable on the left and a positive zero on the right is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        PositiveVariable("x"),
                        NegativeValue(1)),
                PositiveValue(0))

        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the right and a positive zero on the left is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveValue(0),
                PositiveDashOperation(
                        PositiveVariable("x"),
                        NegativeValue(1)))
        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the left and a negative zero on the right is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        PositiveVariable("x"),
                        NegativeValue(1)),
                NegativeValue(0))

        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the right and a negative zero on the left is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                NegativeValue(0),
                PositiveDashOperation(
                        PositiveVariable("x"),
                        NegativeValue(1)))
        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the left and a result of positive zero on the right is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        PositiveVariable("x"),
                        NegativeValue(1)),
                PositiveDashOperation(
                        PositiveValue(1),
                        NegativeValue(1)))
        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the right and a result of positive zero on the left is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        PositiveValue(1),
                        NegativeValue(1)),
                PositiveDashOperation(
                        PositiveVariable("x"),
                        NegativeValue(1)))
        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the left and a result of negative zero on the right is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        PositiveVariable("x"),
                        NegativeValue(1)),
                NegativeDashOperation(
                        PositiveValue(1),
                        NegativeValue(1)))
        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the right and a result of negative zero on the left is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                NegativeDashOperation(
                        PositiveValue(1),
                        NegativeValue(1)),
                PositiveDashOperation(
                        PositiveVariable("x"),
                        NegativeValue(1)))
        // THEN
        assertFalse(validator.validate(equalsRelation))
    }
    //</editor-fold>

    //<editor-fold desc="Variable elimination validation">
    @Test
    fun `A equals relation with the variable on the left and without it being eliminated is valid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveProduct(
                        PositiveVariable("x"),
                        PositiveValue(1)),
                PositiveValue(1))

        // THEN
        assertTrue(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the right and without it being eliminated is valid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveValue(1),
                PositiveProduct(
                        PositiveVariable("x"),
                        PositiveValue(1)))
        // THEN
        assertTrue(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the left and with it being directly eliminated is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveProduct(
                        PositiveVariable("x"),
                        PositiveValue(0)),
                PositiveValue(0))

        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the right and with it being directly eliminated is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveValue(0),
                PositiveProduct(
                        PositiveVariable("x"),
                        PositiveValue(0)))
        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the left and with it being indirectly eliminated is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveProduct(
                        PositiveVariable("x"),
                        PositiveDashOperation(
                                PositiveValue(1),
                                NegativeValue(1))),
                PositiveValue(0))

        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the right and with it being indirectly eliminated is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveValue(0),
                PositiveProduct(
                        PositiveVariable("x"),
                        PositiveDashOperation(
                                PositiveValue(1),
                                NegativeValue(1))))
        // THEN
        assertFalse(validator.validate(equalsRelation))
    }
    //</editor-fold>

    //<editor-fold desc="Variable in denominator validation">
    @Test
    fun `A equals relation with the variable on the left and with it being in the numerator is valid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveVariable("x"),
                        PositiveValue(1)),
                PositiveValue(1))

        // THEN
        assertTrue(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the right and with it being in the numerator is valid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveValue(1),
                PositiveDivision(
                        PositiveVariable("x"),
                        PositiveValue(1)))
        // THEN
        assertTrue(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the left and with it only being in numerators is valid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveDivision(
                                PositiveVariable("x"),
                                PositiveValue(1)),
                        PositiveValue(1)),
                PositiveValue(1))

        // THEN
        assertTrue(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the right and with it only being in numerators is valid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveValue(1),
                PositiveDivision(
                        PositiveDivision(
                                PositiveVariable("x"),
                                PositiveValue(1)),
                        PositiveValue(1)))
        // THEN
        assertTrue(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the left and with it being in the denominator is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveVariable("x")),
                PositiveValue(1))

        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the right and with it being in the denominator is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveValue(1),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveVariable("x")))
        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the left and with it being indirectly in a denominator is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveDivision(
                                PositiveVariable("x"),
                                PositiveValue(1))),
                PositiveValue(1))

        // THEN
        assertFalse(validator.validate(equalsRelation))
    }

    @Test
    fun `A equals relation with the variable on the right and with it being indirectly in a denominator is invalid`() {
        // GIVEN
        val equalsRelation = EqualsRelation(
                PositiveValue(1),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveDivision(
                                PositiveVariable("x"),
                                PositiveValue(1))))
        // THEN
        assertFalse(validator.validate(equalsRelation))
    }
    //</editor-fold>
}