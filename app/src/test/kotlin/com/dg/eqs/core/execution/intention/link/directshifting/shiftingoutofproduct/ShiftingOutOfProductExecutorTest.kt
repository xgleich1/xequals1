package com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingCalculator
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct.ShiftingOutOfProductEvent.InvalidShiftingDivisionOfZeroOutOfProduct
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct.ShiftingOutOfProductEvent.ValidShiftingDivisionOutOfProduct
import com.dg.eqs.core.information.invalid.shifting.InvalidShiftingDivisionOfZeroInfo
import com.dg.eqs.core.information.valid.DivisionInfo
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ShiftingOutOfProductExecutorTest {
    @Mock
    private lateinit var detector: ShiftingOutOfProductDetector
    @Mock
    private lateinit var calculator: DirectShiftingCalculator
    @InjectMocks
    private lateinit var executor: ShiftingOutOfProductExecutor


    @Test
    fun `Should execute a shifting division out of a product`() {
        // GIVEN
        val link: Link = mock()
        val result: Relation = mock()

        whenever(detector.detect(link)).thenReturn(ValidShiftingDivisionOutOfProduct)

        whenever(calculator.calculate(link)).thenReturn(result)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(ValidStep(DivisionInfo, result))
    }

    @Test
    fun `Should report a invalid shifting division of zero out of a product`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val link: Link = mock { on { it.origin } doReturn origin }

        whenever(detector.detect(link)).thenReturn(InvalidShiftingDivisionOfZeroOutOfProduct)

        // THEN
        assertThat(executor.execute(link)).isEqualTo(InvalidStep(InvalidShiftingDivisionOfZeroInfo, origin))
    }
}