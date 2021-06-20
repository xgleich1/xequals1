package com.dg.eqs.core.mapping

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.NegativeProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class StringToOriginMapperTest {
    private lateinit var mapper: StringToOriginMapper


    @Before
    fun setUp() {
        mapper = StringToOriginMapper()
    }

    @Test
    fun `Should map a string representing an equals relation with items as sides to its origin`() {
        // GIVEN
        val string = "=[+1,+1]"

        // WHEN
        val origin: EqualsRelation = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = EqualsRelation(
                PositiveValue(1),
                PositiveValue(1))

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing an equals relation with operations as sides to its origin`() {
        // GIVEN
        val string = "=[+±[+±[+1,+2],+±[+3,+4]],+±[+5,+6]]"

        // WHEN
        val origin: EqualsRelation = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = EqualsRelation(
                PositiveDashOperation(
                        PositiveDashOperation(
                                PositiveValue(1),
                                PositiveValue(2)),
                        PositiveDashOperation(
                                PositiveValue(3),
                                PositiveValue(4))),
                PositiveDashOperation(
                        PositiveValue(5),
                        PositiveValue(6)))

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a single digit positive value to its origin`() {
        // GIVEN
        val string = "+1"

        // WHEN
        val origin: PositiveValue = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = PositiveValue(1)

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a multi digit positive value to its origin`() {
        // GIVEN
        val string = "+12"

        // WHEN
        val origin: PositiveValue = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = PositiveValue(12)

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a single digit negative value to its origin`() {
        // GIVEN
        val string = "-1"

        // WHEN
        val origin: NegativeValue = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = NegativeValue(1)

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a multi digit negative value to its origin`() {
        // GIVEN
        val string = "-12"

        // WHEN
        val origin: NegativeValue = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = NegativeValue(12)

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a single letter positive variable to its origin`() {
        // GIVEN
        val string = "+x"

        // WHEN
        val origin: PositiveVariable = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = PositiveVariable("x")

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a multi letter positive variable to its origin`() {
        // GIVEN
        val string = "+var"

        // WHEN
        val origin: PositiveVariable = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = PositiveVariable("var")

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a single letter negative variable to its origin`() {
        // GIVEN
        val string = "-x"

        // WHEN
        val origin: NegativeVariable = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = NegativeVariable("x")

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a multi letter negative variable to its origin`() {
        // GIVEN
        val string = "-var"

        // WHEN
        val origin: NegativeVariable = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = NegativeVariable("var")

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a positive dash operation with items as operands to its origin`() {
        // GIVEN
        val string = "+±[+1,+2,+3]"

        // WHEN
        val origin: PositiveDashOperation = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = PositiveDashOperation(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3))

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a positive dash operation with operations as operands to its origin`() {
        // GIVEN
        val string = "+±[+±[+1,+2],+±[+±[+3,+4],+±[+5,+6]],+±[+7,+8]]"

        // WHEN
        val origin: PositiveDashOperation = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = PositiveDashOperation(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDashOperation(
                        PositiveDashOperation(
                                PositiveValue(3),
                                PositiveValue(4)),
                        PositiveDashOperation(
                                PositiveValue(5),
                                PositiveValue(6))),
                PositiveDashOperation(
                        PositiveValue(7),
                        PositiveValue(8)))

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a negative dash operation with items as operands to its origin`() {
        // GIVEN
        val string = "-±[+1,+2,+3]"

        // WHEN
        val origin: NegativeDashOperation = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = NegativeDashOperation(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3))

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a negative dash operation with operations as operands to its origin`() {
        // GIVEN
        val string = "-±[-±[+1,+2],-±[-±[+3,+4],-±[+5,+6]],-±[+7,+8]]"

        // WHEN
        val origin: NegativeDashOperation = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = NegativeDashOperation(
                NegativeDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                NegativeDashOperation(
                        NegativeDashOperation(
                                PositiveValue(3),
                                PositiveValue(4)),
                        NegativeDashOperation(
                                PositiveValue(5),
                                PositiveValue(6))),
                NegativeDashOperation(
                        PositiveValue(7),
                        PositiveValue(8)))

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a positive division with items as operands to its origin`() {
        // GIVEN
        val string = "+/[+1,+2]"

        // WHEN
        val origin: PositiveDivision = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = PositiveDivision(
                PositiveValue(1),
                PositiveValue(2))

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a positive division with operations as operands to its origin`() {
        // GIVEN
        val string = "+/[+/[+/[+1,+2],+/[+3,+4]],+/[+5,+6]]"

        // WHEN
        val origin: PositiveDivision = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = PositiveDivision(
                PositiveDivision(
                        PositiveDivision(
                                PositiveValue(1),
                                PositiveValue(2)),
                        PositiveDivision(
                                PositiveValue(3),
                                PositiveValue(4))),
                PositiveDivision(
                        PositiveValue(5),
                        PositiveValue(6)))

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a negative division with items as operands to its origin`() {
        // GIVEN
        val string = "-/[+1,+2]"

        // WHEN
        val origin: NegativeDivision = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = NegativeDivision(
                PositiveValue(1),
                PositiveValue(2))

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a negative division with operations as operands to its origin`() {
        // GIVEN
        val string = "-/[-/[-/[+1,+2],-/[+3,+4]],-/[+5,+6]]"

        // WHEN
        val origin: NegativeDivision = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = NegativeDivision(
                NegativeDivision(
                        NegativeDivision(
                                PositiveValue(1),
                                PositiveValue(2)),
                        NegativeDivision(
                                PositiveValue(3),
                                PositiveValue(4))),
                NegativeDivision(
                        PositiveValue(5),
                        PositiveValue(6)))

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a positive product with items as operands to its origin`() {
        // GIVEN
        val string = "+*[+1,+2,+3]"

        // WHEN
        val origin: PositiveProduct = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = PositiveProduct(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3))

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a positive product with operations as operands to its origin`() {
        // GIVEN
        val string = "+*[+*[+1,+2],+*[+*[+3,+4],+*[+5,+6]],+*[+7,+8]]"

        // WHEN
        val origin: PositiveProduct = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = PositiveProduct(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveProduct(
                        PositiveProduct(
                                PositiveValue(3),
                                PositiveValue(4)),
                        PositiveProduct(
                                PositiveValue(5),
                                PositiveValue(6))),
                PositiveProduct(
                        PositiveValue(7),
                        PositiveValue(8)))

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a negative product with items as operands to its origin`() {
        // GIVEN
        val string = "-*[+1,+2,+3]"

        // WHEN
        val origin: NegativeProduct = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = NegativeProduct(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3))

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test
    fun `Should map a string representing a negative product with operations as operands to its origin`() {
        // GIVEN
        val string = "-*[-*[+1,+2],-*[-*[+3,+4],-*[+5,+6]],-*[+7,+8]]"

        // WHEN
        val origin: NegativeProduct = mapper.mapToOrigin(string)

        // THEN
        val expectedOrigin = NegativeProduct(
                NegativeProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                NegativeProduct(
                        NegativeProduct(
                                PositiveValue(3),
                                PositiveValue(4)),
                        NegativeProduct(
                                PositiveValue(5),
                                PositiveValue(6))),
                NegativeProduct(
                        PositiveValue(7),
                        PositiveValue(8)))

        assertThat(origin).isEqualTo(expectedOrigin)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw an exception when the given string isn't the representation of a relation`() {
        mapper.mapToOrigin<AnyOrigin>("[]")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw an exception when the given string isn't the representation of a term`() {
        mapper.mapToOrigin<AnyOrigin>("+[]")
    }
}