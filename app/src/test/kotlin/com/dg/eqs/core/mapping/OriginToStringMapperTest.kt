package com.dg.eqs.core.mapping

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
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class OriginToStringMapperTest {
    private lateinit var mapper: OriginToStringMapper


    @Before
    fun setUp() {
        mapper = OriginToStringMapper()
    }

    @Test
    fun `Should map a equals relation to its string representation`() {
        // GIVEN
        val origin = EqualsRelation(
                PositiveValue(1),
                PositiveValue(1))

        // THEN
        assertThat(mapper.mapToString(origin)).isEqualTo("=[+1,+1]")
    }

    @Test
    fun `Should map a positive value to its string representation`() {
        // GIVEN
        val origin = PositiveValue(1)

        // THEN
        assertThat(mapper.mapToString(origin)).isEqualTo("+1")
    }

    @Test
    fun `Should map a negative value to its string representation`() {
        // GIVEN
        val origin = NegativeValue(1)

        // THEN
        assertThat(mapper.mapToString(origin)).isEqualTo("-1")
    }

    @Test
    fun `Should map a positive variable to its string representation`() {
        // GIVEN
        val origin = PositiveVariable("x")

        // THEN
        assertThat(mapper.mapToString(origin)).isEqualTo("+x")
    }

    @Test
    fun `Should map a negative variable to its string representation`() {
        // GIVEN
        val origin = NegativeVariable("x")

        // THEN
        assertThat(mapper.mapToString(origin)).isEqualTo("-x")
    }

    @Test
    fun `Should map a positive dash operation to its string representation`() {
        // GIVEN
        val origin = PositiveDashOperation(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3))

        // THEN
        assertThat(mapper.mapToString(origin)).isEqualTo("+±[+1,+2,+3]")
    }

    @Test
    fun `Should map a negative dash operation to its string representation`() {
        // GIVEN
        val origin = NegativeDashOperation(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3))

        // THEN
        assertThat(mapper.mapToString(origin)).isEqualTo("-±[+1,+2,+3]")
    }

    @Test
    fun `Should map a positive division to its string representation`() {
        // GIVEN
        val origin = PositiveDivision(
                PositiveValue(1),
                PositiveValue(2))

        // THEN
        assertThat(mapper.mapToString(origin)).isEqualTo("+/[+1,+2]")
    }

    @Test
    fun `Should map a negative division to its string representation`() {
        // GIVEN
        val origin = NegativeDivision(
                PositiveValue(1),
                PositiveValue(2))

        // THEN
        assertThat(mapper.mapToString(origin)).isEqualTo("-/[+1,+2]")
    }

    @Test
    fun `Should map a positive product to its string representation`() {
        // GIVEN
        val origin = PositiveProduct(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3))

        // THEN
        assertThat(mapper.mapToString(origin)).isEqualTo("+*[+1,+2,+3]")
    }

    @Test
    fun `Should map a negative product to its string representation`() {
        // GIVEN
        val origin = NegativeProduct(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3))

        // THEN
        assertThat(mapper.mapToString(origin)).isEqualTo("-*[+1,+2,+3]")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw an exception when there's no string representation for a origin`() {
        mapper.mapToString(mock())
    }
}