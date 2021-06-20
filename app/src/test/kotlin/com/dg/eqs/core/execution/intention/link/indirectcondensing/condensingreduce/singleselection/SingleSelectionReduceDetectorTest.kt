package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection

import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindivision.SingleSelectionCondensingInDivisionDetector
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.SingleSelectionReduceEvent.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class SingleSelectionReduceDetectorTest {
    private lateinit var detector: SingleSelectionReduceDetector


    @Before
    fun setUp() {
        detector = SingleSelectionReduceDetector(SingleSelectionCondensingInDivisionDetector())
    }

    @Test
    fun `Single selection reduces are valid when the condensing division of their numerator & denominator is`() {
        // GIVEN
        val numerator = PositiveValue(2)
        val denominator = PositiveValue(4)

        // THEN
        assertThat(detector.detect(numerator, denominator)).isEqualTo(ValidSingleSelectionReduce)
    }

    @Test
    fun `Single selection reduces are invalid due to a division through zero when the condensing division of their numerator & denominator is`() {
        // GIVEN
        val numerator = PositiveValue(2)
        val denominator = PositiveValue(0)

        // THEN
        assertThat(detector.detect(numerator, denominator)).isEqualTo(InvalidSingleSelectionReduceThroughZero)
    }

    @Test
    fun `Single selection reduces are invalid when the condensing division of their numerator & denominator is`() {
        // GIVEN
        val numerator = PositiveVariable("x")
        val denominator = PositiveVariable("y")

        // THEN
        assertThat(detector.detect(numerator, denominator)).isEqualTo(InvalidSingleSelectionReduce)
    }
}