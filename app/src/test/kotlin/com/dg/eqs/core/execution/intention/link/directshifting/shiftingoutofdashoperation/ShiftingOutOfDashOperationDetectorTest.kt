package com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation.ShiftingOutOfDashOperationEvent.ShiftingAdditionOutOfDashOperation
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation.ShiftingOutOfDashOperationEvent.ShiftingSubtractionOutOfDashOperation
import com.dg.eqs.classes.negativeTerm
import com.dg.eqs.classes.positiveTerm
import com.dg.eqs.classes.relation
import com.dg.eqs.classes.term
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class ShiftingOutOfDashOperationDetectorTest {
    private lateinit var detector: ShiftingOutOfDashOperationDetector


    @Before
    fun setUp() {
        detector = ShiftingOutOfDashOperationDetector()
    }

    @Test
    fun `Should detect a shifting addition out of a positive dash operation`() {
        // GIVEN
        val source = termsOf(negativeTerm())
        val target = termsOf(term())
        val origin = relation(
                PositiveDashOperation(
                        source.first,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ShiftingAdditionOutOfDashOperation)
    }

    @Test
    fun `Should detect a shifting addition out of a negative dash operation`() {
        // GIVEN
        val source = termsOf(positiveTerm())
        val target = termsOf(term())
        val origin = relation(
                NegativeDashOperation(
                        source.first,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ShiftingAdditionOutOfDashOperation)
    }

    @Test
    fun `Should detect a shifting subtraction out of a positive dash operation`() {
        // GIVEN
        val source = termsOf(positiveTerm())
        val target = termsOf(term())
        val origin = relation(
                PositiveDashOperation(
                        source.first,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ShiftingSubtractionOutOfDashOperation)
    }

    @Test
    fun `Should detect a shifting subtraction out of a negative dash operation`() {
        // GIVEN
        val source = termsOf(negativeTerm())
        val target = termsOf(term())
        val origin = relation(
                NegativeDashOperation(
                        source.first,
                        term()),
                target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ShiftingSubtractionOutOfDashOperation)
    }

    private fun detect(origin: AnyOrigin, source: Terms, target: Terms) =
            detector.detect(Link(origin, source, target))
}