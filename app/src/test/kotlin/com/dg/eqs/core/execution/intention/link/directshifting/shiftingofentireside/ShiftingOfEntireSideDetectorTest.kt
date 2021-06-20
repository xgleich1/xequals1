package com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside.ShiftingOfEntireSideEvent.ShiftingAdditionOfEntireSide
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside.ShiftingOfEntireSideEvent.ShiftingSubtractionOfEntireSide
import com.dg.eqs.classes.negativeTerm
import com.dg.eqs.classes.positiveTerm
import com.dg.eqs.classes.relation
import com.dg.eqs.classes.term
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class ShiftingOfEntireSideDetectorTest {
    private lateinit var detector: ShiftingOfEntireSideDetector


    @Before
    fun setUp() {
        detector = ShiftingOfEntireSideDetector()
    }

    @Test
    fun `Should detect a shifting addition of an entire side`() {
        // GIVEN
        val source = termsOf(negativeTerm())
        val target = termsOf(term())
        val origin = relation(source.single, target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ShiftingAdditionOfEntireSide)
    }

    @Test
    fun `Should detect a shifting subtraction of an entire side`() {
        // GIVEN
        val source = termsOf(positiveTerm())
        val target = termsOf(term())
        val origin = relation(source.single, target.single)

        // THEN
        assertThat(detect(origin, source, target)).isEqualTo(ShiftingSubtractionOfEntireSide)
    }

    private fun detect(origin: AnyOrigin, source: Terms, target: Terms) =
            detector.detect(Link(origin, source, target))
}