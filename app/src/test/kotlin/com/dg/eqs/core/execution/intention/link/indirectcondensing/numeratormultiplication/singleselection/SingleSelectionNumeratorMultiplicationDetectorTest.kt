package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection

import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductDetector
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationEvent.InvalidSingleSelectionNumeratorMultiplication
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationEvent.ValidSingleSelectionNumeratorMultiplication
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class SingleSelectionNumeratorMultiplicationDetectorTest {
    private lateinit var detector: SingleSelectionNumeratorMultiplicationDetector


    @Before
    fun setUp() {
        detector = SingleSelectionNumeratorMultiplicationDetector(SingleSelectionCondensingInProductDetector())
    }

    @Test
    fun `Single selection numerator multiplications are valid when the condensing multiplication of their left & right side is`() {
        // GIVEN
        val left = PositiveValue(2)
        val right = PositiveValue(3)

        // THEN
        assertThat(detector.detect(left, right)).isEqualTo(ValidSingleSelectionNumeratorMultiplication)
    }

    @Test
    fun `Single selection numerator multiplications are invalid when the condensing multiplication of their left & right side is`() {
        // GIVEN
        val left = PositiveVariable("x")
        val right = PositiveVariable("y")

        // THEN
        assertThat(detector.detect(left, right)).isEqualTo(InvalidSingleSelectionNumeratorMultiplication)
    }
}