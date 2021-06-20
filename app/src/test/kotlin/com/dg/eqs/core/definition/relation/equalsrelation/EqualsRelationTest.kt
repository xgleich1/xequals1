package com.dg.eqs.core.definition.relation.equalsrelation

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class EqualsRelationTest {
    @Test
    fun `Should convert a equals relation to a string`() {
        // GIVEN
        val left: Term = mock { on { toString() } doReturn "x" }
        val right: Term = mock { on { toString() } doReturn "1" }
        val equalsRelation = EqualsRelation(left, right)

        // THEN
        assertThat(equalsRelation).hasToString("=[x, 1]")
    }

    @Test
    fun `A equals relation can remove a term from itself or its sides`() {
        // GIVEN
        val left: Term = mock()
        val right: Term = mock()
        val equalsRelation = EqualsRelation(left, right)

        // THEN
        val expectedResult = EqualsRelation(left, PositiveValue(0))

        assertThat(equalsRelation.remove(right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can replace a term in itself or in its sides`() {
        // GIVEN
        val new: Term = mock()

        val left: Term = mock()
        val right: Term = mock()
        val equalsRelation = EqualsRelation(left, right)

        // THEN
        val expectedResult = EqualsRelation(left, new)

        assertThat(equalsRelation.replace(right, new)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should recreate a equals relation with the new sides`() {
        // GIVEN
        val equalsRelation = EqualsRelation(mock(), mock())

        val newLeft: Term = mock()
        val newRight: Term = mock()

        // THEN
        val expectedRecreation = EqualsRelation(newLeft, newRight)

        assertThat(equalsRelation.recreate(newLeft, newRight)).isEqualTo(expectedRecreation)
    }

    //<editor-fold desc="Shifting step">
    @Test
    fun `A equals relation can shift its entire left side to a zero on the right`() {
        // GIVEN
        val source = termsOf(NegativeVariable("x"))

        val equalsRelation = EqualsRelation(
                source.single,
                PositiveValue(0))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(0),
                PositiveVariable("x"))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift its entire right side to a zero on the left`() {
        // GIVEN
        val source = termsOf(NegativeVariable("x"))

        val equalsRelation = EqualsRelation(
                PositiveValue(0),
                source.single)

        // THEN
        val expectedResult = EqualsRelation(
                PositiveVariable("x"),
                PositiveValue(0))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift its entire left side to a positive dash operation on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(3))

        val equalsRelation = EqualsRelation(
                source.single,
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(0),
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2),
                        NegativeValue(3)))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift its entire right side to a positive dash operation on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(3))

        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                source.single)

        // THEN
        val expectedResult = EqualsRelation(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2),
                        NegativeValue(3)),
                PositiveValue(0))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift its entire left side to anything on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))

        val equalsRelation = EqualsRelation(
                source.single,
                PositiveValue(1))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(0),
                PositiveDashOperation(
                        PositiveValue(1),
                        NegativeValue(1)))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift its entire right side to anything on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))

        val equalsRelation = EqualsRelation(
                PositiveValue(1),
                source.single)

        // THEN
        val expectedResult = EqualsRelation(
                PositiveDashOperation(
                        PositiveValue(1),
                        NegativeValue(1)),
                PositiveValue(0))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a negative dash operation on the left to a zero on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))

        val equalsRelation = EqualsRelation(
                NegativeDashOperation(
                        source.first,
                        NegativeValue(1)),
                PositiveValue(0))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(1),
                PositiveValue(1))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a negative dash operation on the right to a zero on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))

        val equalsRelation = EqualsRelation(
                PositiveValue(0),
                NegativeDashOperation(
                        source.first,
                        NegativeValue(1)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(1),
                PositiveValue(1))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a negative dash operation on the left to a positive dash operation on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(2))

        val equalsRelation = EqualsRelation(
                NegativeDashOperation(
                        source.first,
                        NegativeValue(3)),
                PositiveDashOperation(
                        PositiveValue(0),
                        PositiveValue(1)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(3),
                PositiveDashOperation(
                        PositiveValue(0),
                        PositiveValue(1),
                        PositiveValue(2)))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a negative dash operation on the right to a positive dash operation on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(2))

        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        PositiveValue(0),
                        PositiveValue(1)),
                NegativeDashOperation(
                        source.first,
                        NegativeValue(3)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveDashOperation(
                        PositiveValue(0),
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a negative dash operation on the left to anything on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(2))

        val equalsRelation = EqualsRelation(
                NegativeDashOperation(
                        source.first,
                        NegativeValue(3)),
                PositiveValue(1))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(3),
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a negative dash operation on the right to anything on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(2))

        val equalsRelation = EqualsRelation(
                PositiveValue(1),
                NegativeDashOperation(
                        source.first,
                        NegativeValue(3)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a positive dash operation on the left to a zero on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))

        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        source.first,
                        NegativeValue(1)),
                PositiveValue(0))

        // THEN
        val expectedResult = EqualsRelation(
                NegativeValue(1),
                NegativeValue(1))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a positive dash operation on the right to a zero on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))

        val equalsRelation = EqualsRelation(
                PositiveValue(0),
                PositiveDashOperation(
                        source.first,
                        NegativeValue(1)))
        // THEN
        val expectedResult = EqualsRelation(
                NegativeValue(1),
                NegativeValue(1))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a positive dash operation on the left to a positive dash operation on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(2))

        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        source.first,
                        NegativeValue(1)),
                PositiveDashOperation(
                        PositiveValue(0),
                        PositiveValue(1)))
        // THEN
        val expectedResult = EqualsRelation(
                NegativeValue(1),
                PositiveDashOperation(
                        PositiveValue(0),
                        PositiveValue(1),
                        NegativeValue(2)))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a positive dash operation on the right to a positive dash operation on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(2))

        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        PositiveValue(0),
                        PositiveValue(1)),
                PositiveDashOperation(
                        source.first,
                        NegativeValue(1)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveDashOperation(
                        PositiveValue(0),
                        PositiveValue(1),
                        NegativeValue(2)),
                NegativeValue(1))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a positive dash operation on the left to anything on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))

        val equalsRelation = EqualsRelation(
                PositiveDashOperation(
                        source.first,
                        PositiveValue(2)),
                PositiveValue(3))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(2),
                PositiveDashOperation(
                        PositiveValue(3),
                        NegativeValue(1)))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a positive dash operation on the right to anything on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))

        val equalsRelation = EqualsRelation(
                PositiveValue(3),
                PositiveDashOperation(
                        source.first,
                        PositiveValue(2)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveDashOperation(
                        PositiveValue(3),
                        NegativeValue(1)),
                PositiveValue(2))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift the numerator out of a division on the left to anything on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(4))

        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        source.single,
                        PositiveValue(2)),
                PositiveValue(2))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(4)))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift the numerator out of a division on the right to anything on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(4))

        val equalsRelation = EqualsRelation(
                PositiveValue(2),
                PositiveDivision(
                        source.single,
                        PositiveValue(2)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(4)),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a product as the numerator of a division on the left to anything on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(4))

        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveProduct(
                                source.first,
                                PositiveValue(1)),
                        PositiveValue(2)),
                PositiveValue(2))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(4)))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a product as the numerator of a division on the right to anything on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(4))

        val equalsRelation = EqualsRelation(
                PositiveValue(2),
                PositiveDivision(
                        PositiveProduct(
                                source.first,
                                PositiveValue(1)),
                        PositiveValue(2)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(4)),
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift the denominator out of a division on the left to a positive product on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(3))

        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveValue(6),
                        source.single),
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(6),
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2),
                        PositiveValue(3)))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift the denominator out of a division on the right to a positive product on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(3))

        val equalsRelation = EqualsRelation(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(6),
                        source.single))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2),
                        PositiveValue(3)),
                PositiveValue(6))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift the denominator out of a division on the left to anything on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(3))

        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveValue(6),
                        source.single),
                PositiveValue(2))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(6),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift the denominator out of a division on the right to anything on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(3))

        val equalsRelation = EqualsRelation(
                PositiveValue(2),
                PositiveDivision(
                        PositiveValue(6),
                        source.single))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)),
                PositiveValue(6))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a product as the denominator of a division on the left to a positive product on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(3))

        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveValue(6),
                        PositiveProduct(
                                source.first,
                                PositiveValue(1))),
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveDivision(
                        PositiveValue(6),
                        PositiveValue(1)),
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2),
                        PositiveValue(3)))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a product as the denominator of a division on the right to a positive product on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(3))

        val equalsRelation = EqualsRelation(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(6),
                        PositiveProduct(
                                source.first,
                                PositiveValue(1))))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2),
                        PositiveValue(3)),
                PositiveDivision(
                        PositiveValue(6),
                        PositiveValue(1)))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a product as the denominator of a division on the left to anything on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(3))

        val equalsRelation = EqualsRelation(
                PositiveDivision(
                        PositiveValue(6),
                        PositiveProduct(
                                source.first,
                                PositiveValue(1))),
                PositiveValue(2))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveDivision(
                        PositiveValue(6),
                        PositiveValue(1)),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a product as the denominator of a division on the right to anything on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(3))

        val equalsRelation = EqualsRelation(
                PositiveValue(2),
                PositiveDivision(
                        PositiveValue(6),
                        PositiveProduct(
                                source.first,
                                PositiveValue(1))))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)),
                PositiveDivision(
                        PositiveValue(6),
                        PositiveValue(1)))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a product on the left to anything on the right`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))

        val equalsRelation = EqualsRelation(
                PositiveProduct(
                        source.first,
                        PositiveValue(2)),
                PositiveValue(2))

        // THEN
        val expectedResult = EqualsRelation(
                PositiveValue(2),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)))

        assertThat(equalsRelation.shift(source, equalsRelation.right)).isEqualTo(expectedResult)
    }

    @Test
    fun `A equals relation can shift out of a product on the right to anything on the left`() {
        // GIVEN
        val source = termsOf(PositiveValue(1))

        val equalsRelation = EqualsRelation(
                PositiveValue(2),
                PositiveProduct(
                        source.first,
                        PositiveValue(2)))
        // THEN
        val expectedResult = EqualsRelation(
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)),
                PositiveValue(2))

        assertThat(equalsRelation.shift(source, equalsRelation.left)).isEqualTo(expectedResult)
    }
    //</editor-fold>
}