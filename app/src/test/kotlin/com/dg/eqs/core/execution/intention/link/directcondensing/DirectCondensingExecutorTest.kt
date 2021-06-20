package com.dg.eqs.core.execution.intention.link.directcondensing

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingEvent.*
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensingindashoperation.MultiSelectionCondensingInDashOperationExecutor
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct.MultiSelectionCondensingInProductExecutor
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindashoperation.SingleSelectionCondensingInDashOperationExecutor
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindivision.SingleSelectionCondensingInDivisionExecutor
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductExecutor
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DirectCondensingExecutorTest {
    @Mock
    private lateinit var directCondensingDetector: DirectCondensingDetector
    @Mock
    private lateinit var singleSelectionCondensingInDashOperationExecutor: SingleSelectionCondensingInDashOperationExecutor
    @Mock
    private lateinit var singleSelectionCondensingInDivisionExecutor: SingleSelectionCondensingInDivisionExecutor
    @Mock
    private lateinit var singleSelectionCondensingInProductExecutor: SingleSelectionCondensingInProductExecutor
    @Mock
    private lateinit var multiSelectionCondensingInDashOperationExecutor: MultiSelectionCondensingInDashOperationExecutor
    @Mock
    private lateinit var multiSelectionCondensingInProductExecutor: MultiSelectionCondensingInProductExecutor
    @InjectMocks
    private lateinit var directCondensingExecutor: DirectCondensingExecutor


    @Test
    fun `Should execute a single selection condensing in a dash operation`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(directCondensingDetector.detect(link)).thenReturn(SingleSelectionCondensingInDashOperation)

        whenever(singleSelectionCondensingInDashOperationExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(directCondensingExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should execute a single selection condensing in a division`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(directCondensingDetector.detect(link)).thenReturn(SingleSelectionCondensingInDivision)

        whenever(singleSelectionCondensingInDivisionExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(directCondensingExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should execute a single selection condensing in a product`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(directCondensingDetector.detect(link)).thenReturn(SingleSelectionCondensingInProduct)

        whenever(singleSelectionCondensingInProductExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(directCondensingExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should execute a multi selection condensing in a dash operation`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(directCondensingDetector.detect(link)).thenReturn(MultiSelectionCondensingInDashOperation)

        whenever(multiSelectionCondensingInDashOperationExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(directCondensingExecutor.execute(link)).isEqualTo(step)
    }

    @Test
    fun `Should execute a multi selection condensing in a product`() {
        // GIVEN
        val link: Link = mock()
        val step: Step = mock()

        whenever(directCondensingDetector.detect(link)).thenReturn(MultiSelectionCondensingInProduct)

        whenever(multiSelectionCondensingInProductExecutor.execute(link)).thenReturn(step)

        // THEN
        assertThat(directCondensingExecutor.execute(link)).isEqualTo(step)
    }
}