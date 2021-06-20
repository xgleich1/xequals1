package com.dg.eqs.page.info

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dg.eqs.R
import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataObserver
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class InfoPageViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var viewModel: InfoPageViewModel

    @Mock
    private lateinit var showInfoTitleAction: (StringRes) -> Unit
    @Mock
    private lateinit var showInfoContentAction: (LayoutRes) -> Unit
    @Mock
    private lateinit var showMenuOverlayAction: (Boolean) -> Unit
    @Mock
    private lateinit var greyOutHowToMenuButtonAction: (Boolean) -> Unit
    @Mock
    private lateinit var greyOutEquationMenuButtonAction: (Boolean) -> Unit
    @Mock
    private lateinit var greyOutOrderOfOperationsMenuButtonAction: (Boolean) -> Unit
    @Mock
    private lateinit var greyOutAdditionMenuButtonAction: (Boolean) -> Unit
    @Mock
    private lateinit var greyOutSubtractionMenuButtonAction: (Boolean) -> Unit
    @Mock
    private lateinit var greyOutMultiplicationMenuButtonAction: (Boolean) -> Unit
    @Mock
    private lateinit var greyOutDivisionMenuButtonAction: (Boolean) -> Unit
    @Mock
    private lateinit var greyOutReduceMenuButtonAction: (Boolean) -> Unit
    @Mock
    private lateinit var navigateBackAction: () -> Unit


    @Before
    fun setUp() {
        observeViewModel()
    }

    @Test
    fun `Should initially show the how to info title`() {
        verify(showInfoTitleAction).invoke(
                StringRes(R.string.info_how_to_title))
    }

    @Test
    fun `Should initially show the how to info content`() {
        verify(showInfoContentAction).invoke(
                LayoutRes(R.layout.info_how_to))
    }

    @Test
    fun `Should initially grey out the how to menu button`() {
        verifyOnlyHowToMenuButtonGreyedOut()
    }

    @Test
    fun `Should hide the menu overlay when its shown and back is pressed`() {
        // GIVEN
        clearInvocations(showMenuOverlayAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onBackPressed()

        // THEN
        verify(showMenuOverlayAction).invoke(false)
    }

    @Test
    fun `Should navigate back when the menu overlay is hidden and back is pressed`() {
        // WHEN
        viewModel.onBackPressed()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should hide the menu overlay when its shown and the back button is clicked`() {
        // GIVEN
        clearInvocations(showMenuOverlayAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onBackButtonClicked()

        // THEN
        verify(showMenuOverlayAction).invoke(false)
    }

    @Test
    fun `Should navigate back when the menu overlay is hidden and the back button is clicked`() {
        // WHEN
        viewModel.onBackButtonClicked()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should show the menu overlay when its hidden and the menu button is clicked`() {
        // WHEN
        viewModel.onMenuButtonClicked()

        // THEN
        verify(showMenuOverlayAction).invoke(true)
    }

    @Test
    fun `Should hide the menu overlay when its shown and the menu button is clicked`() {
        // GIVEN
        clearInvocations(showMenuOverlayAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onMenuButtonClicked()

        // THEN
        verify(showMenuOverlayAction).invoke(false)
    }

    @Test
    fun `Should hide the menu overlay when its clicked`() {
        // GIVEN
        clearInvocations(showMenuOverlayAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onMenuOverlayClicked()

        // THEN
        verify(showMenuOverlayAction).invoke(false)
    }

    //<editor-fold desc="How to menu button click">
    @Test
    fun `Should show the how to info title when the how to menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoTitleAction)

        viewModel.onMenuButtonClicked()
        viewModel.onEquationMenuButtonClicked()
        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onHowToMenuButtonClicked()

        // THEN
        verify(showInfoTitleAction).invoke(
                StringRes(R.string.info_how_to_title))
    }

    @Test
    fun `Should show the how to info content when the how to menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()
        viewModel.onEquationMenuButtonClicked()
        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onHowToMenuButtonClicked()

        // THEN
        verify(showInfoContentAction).invoke(
                LayoutRes(R.layout.info_how_to))
    }

    @Test
    fun `Should not show the how to info content again to keep the scroll position`() {
        // GIVEN
        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onHowToMenuButtonClicked()

        // THEN
        verify(showInfoContentAction, never()).invoke(any())
    }

    @Test
    fun `Should hide the menu overlay when the how to menu button is clicked`() {
        // GIVEN
        clearInvocations(showMenuOverlayAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onHowToMenuButtonClicked()

        // THEN
        verify(showMenuOverlayAction).invoke(false)
    }

    @Test
    fun `Should only grey out the how to menu button when its clicked`() {
        // GIVEN
        viewModel.onMenuButtonClicked()
        viewModel.onEquationMenuButtonClicked()

        clearGreyOutMenuButtonInvocations()

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onHowToMenuButtonClicked()

        // THEN
        verifyOnlyHowToMenuButtonGreyedOut()
    }
    //</editor-fold>

    //<editor-fold desc="Equation menu button click">
    @Test
    fun `Should show the equation info title when the equation menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoTitleAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onEquationMenuButtonClicked()

        // THEN
        verify(showInfoTitleAction).invoke(
                StringRes(R.string.info_equation_title))
    }

    @Test
    fun `Should show the equation info content when the equation menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onEquationMenuButtonClicked()

        // THEN
        verify(showInfoContentAction).invoke(
                LayoutRes(R.layout.info_equation))
    }

    @Test
    fun `Should not show the equation info content again to keep the scroll position`() {
        // GIVEN
        viewModel.onMenuButtonClicked()
        viewModel.onEquationMenuButtonClicked()

        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onEquationMenuButtonClicked()

        // THEN
        verify(showInfoContentAction, never()).invoke(any())
    }

    @Test
    fun `Should hide the menu overlay when the equation menu button is clicked`() {
        // GIVEN
        clearInvocations(showMenuOverlayAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onEquationMenuButtonClicked()

        // THEN
        verify(showMenuOverlayAction).invoke(false)
    }

    @Test
    fun `Should only grey out the equation menu button when its clicked`() {
        // GIVEN
        clearGreyOutMenuButtonInvocations()

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onEquationMenuButtonClicked()

        // THEN
        verifyOnlyEquationMenuButtonGreyedOut()
    }
    //</editor-fold>

    //<editor-fold desc="Order menu button click">
    @Test
    fun `Should show the order of operations info title when the order of operations menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoTitleAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onOrderOfOperationsMenuButtonClicked()

        // THEN
        verify(showInfoTitleAction).invoke(
                StringRes(R.string.info_order_of_operations_title))
    }

    @Test
    fun `Should show the order of operations info content when the order of operations menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onOrderOfOperationsMenuButtonClicked()

        // THEN
        verify(showInfoContentAction).invoke(
                LayoutRes(R.layout.info_order_of_operations))
    }

    @Test
    fun `Should not show the order of operations info content again to keep the scroll position`() {
        // GIVEN
        viewModel.onMenuButtonClicked()
        viewModel.onOrderOfOperationsMenuButtonClicked()

        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onOrderOfOperationsMenuButtonClicked()

        // THEN
        verify(showInfoContentAction, never()).invoke(any())
    }

    @Test
    fun `Should hide the menu overlay when the order of operations menu button is clicked`() {
        // GIVEN
        clearInvocations(showMenuOverlayAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onOrderOfOperationsMenuButtonClicked()

        // THEN
        verify(showMenuOverlayAction).invoke(false)
    }

    @Test
    fun `Should only grey out the order of operations menu button when its clicked`() {
        // GIVEN
        clearGreyOutMenuButtonInvocations()

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onOrderOfOperationsMenuButtonClicked()

        // THEN
        verifyOnlyOrderMenuButtonGreyedOut()
    }
    //</editor-fold>

    //<editor-fold desc="Addition menu button click">
    @Test
    fun `Should show the addition info title when the addition menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoTitleAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onAdditionMenuButtonClicked()

        // THEN
        verify(showInfoTitleAction).invoke(
                StringRes(R.string.info_addition_title))
    }

    @Test
    fun `Should show the addition info content when the addition menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onAdditionMenuButtonClicked()

        // THEN
        verify(showInfoContentAction).invoke(
                LayoutRes(R.layout.info_addition))
    }

    @Test
    fun `Should not show the addition info content again to keep the scroll position`() {
        // GIVEN
        viewModel.onMenuButtonClicked()
        viewModel.onAdditionMenuButtonClicked()

        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onAdditionMenuButtonClicked()

        // THEN
        verify(showInfoContentAction, never()).invoke(any())
    }

    @Test
    fun `Should hide the menu overlay when the addition menu button is clicked`() {
        // GIVEN
        clearInvocations(showMenuOverlayAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onAdditionMenuButtonClicked()

        // THEN
        verify(showMenuOverlayAction).invoke(false)
    }

    @Test
    fun `Should only grey out the addition menu button when its clicked`() {
        // GIVEN
        clearGreyOutMenuButtonInvocations()

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onAdditionMenuButtonClicked()

        // THEN
        verifyOnlyAdditionMenuButtonGreyedOut()
    }
    //</editor-fold>

    //<editor-fold desc="Subtraction menu button click">
    @Test
    fun `Should show the subtraction info title when the subtraction menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoTitleAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onSubtractionMenuButtonClicked()

        // THEN
        verify(showInfoTitleAction).invoke(
                StringRes(R.string.info_subtraction_title))
    }

    @Test
    fun `Should show the subtraction info content when the subtraction menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onSubtractionMenuButtonClicked()

        // THEN
        verify(showInfoContentAction).invoke(
                LayoutRes(R.layout.info_subtraction))
    }

    @Test
    fun `Should not show the subtraction info content again to keep the scroll position`() {
        // GIVEN
        viewModel.onMenuButtonClicked()
        viewModel.onSubtractionMenuButtonClicked()

        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onSubtractionMenuButtonClicked()

        // THEN
        verify(showInfoContentAction, never()).invoke(any())
    }

    @Test
    fun `Should hide the menu overlay when the subtraction menu button is clicked`() {
        // GIVEN
        clearInvocations(showMenuOverlayAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onSubtractionMenuButtonClicked()

        // THEN
        verify(showMenuOverlayAction).invoke(false)
    }

    @Test
    fun `Should only grey out the subtraction menu button when its clicked`() {
        // GIVEN
        clearGreyOutMenuButtonInvocations()

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onSubtractionMenuButtonClicked()

        // THEN
        verifyOnlySubtractionMenuButtonGreyedOut()
    }
    //</editor-fold>

    //<editor-fold desc="Multiplication menu button click">
    @Test
    fun `Should show the multiplication info title when the multiplication menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoTitleAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onMultiplicationMenuButtonClicked()

        // THEN
        verify(showInfoTitleAction).invoke(
                StringRes(R.string.info_multiplication_title))
    }

    @Test
    fun `Should show the multiplication info content when the multiplication menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onMultiplicationMenuButtonClicked()

        // THEN
        verify(showInfoContentAction).invoke(
                LayoutRes(R.layout.info_multiplication))
    }

    @Test
    fun `Should not show the multiplication info content again to keep the scroll position`() {
        // GIVEN
        viewModel.onMenuButtonClicked()
        viewModel.onMultiplicationMenuButtonClicked()

        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onMultiplicationMenuButtonClicked()

        // THEN
        verify(showInfoContentAction, never()).invoke(any())
    }

    @Test
    fun `Should hide the menu overlay when the multiplication menu button is clicked`() {
        // GIVEN
        clearInvocations(showMenuOverlayAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onMultiplicationMenuButtonClicked()

        // THEN
        verify(showMenuOverlayAction).invoke(false)
    }

    @Test
    fun `Should only grey out the multiplication menu button when its clicked`() {
        // GIVEN
        clearGreyOutMenuButtonInvocations()

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onMultiplicationMenuButtonClicked()

        // THEN
        verifyOnlyMultiplicationMenuButtonGreyedOut()
    }
    //</editor-fold>

    //<editor-fold desc="Division menu button click">
    @Test
    fun `Should show the division info title when the division menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoTitleAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onDivisionMenuButtonClicked()

        // THEN
        verify(showInfoTitleAction).invoke(
                StringRes(R.string.info_division_title))
    }

    @Test
    fun `Should show the division info content when the division menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onDivisionMenuButtonClicked()

        // THEN
        verify(showInfoContentAction).invoke(
                LayoutRes(R.layout.info_division))
    }

    @Test
    fun `Should not show the division info content again to keep the scroll position`() {
        // GIVEN
        viewModel.onMenuButtonClicked()
        viewModel.onDivisionMenuButtonClicked()

        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onDivisionMenuButtonClicked()

        // THEN
        verify(showInfoContentAction, never()).invoke(any())
    }

    @Test
    fun `Should hide the menu overlay when the division menu button is clicked`() {
        // GIVEN
        clearInvocations(showMenuOverlayAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onDivisionMenuButtonClicked()

        // THEN
        verify(showMenuOverlayAction).invoke(false)
    }

    @Test
    fun `Should only grey out the division menu button when its clicked`() {
        // GIVEN
        clearGreyOutMenuButtonInvocations()

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onDivisionMenuButtonClicked()

        // THEN
        verifyOnlyDivisionMenuButtonGreyedOut()
    }
    //</editor-fold>

    //<editor-fold desc="Reduce menu button click">
    @Test
    fun `Should show the reduce info title when the reduce menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoTitleAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onReduceMenuButtonClicked()

        // THEN
        verify(showInfoTitleAction).invoke(
                StringRes(R.string.info_reduce_title))
    }

    @Test
    fun `Should show the reduce info content when the reduce menu button is clicked`() {
        // GIVEN
        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onReduceMenuButtonClicked()

        // THEN
        verify(showInfoContentAction).invoke(
                LayoutRes(R.layout.info_reduce))
    }

    @Test
    fun `Should not show the reduce info content again to keep the scroll position`() {
        // GIVEN
        viewModel.onMenuButtonClicked()
        viewModel.onReduceMenuButtonClicked()

        clearInvocations(showInfoContentAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onReduceMenuButtonClicked()

        // THEN
        verify(showInfoContentAction, never()).invoke(any())
    }

    @Test
    fun `Should hide the menu overlay when the reduce menu button is clicked`() {
        // GIVEN
        clearInvocations(showMenuOverlayAction)

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onReduceMenuButtonClicked()

        // THEN
        verify(showMenuOverlayAction).invoke(false)
    }

    @Test
    fun `Should only grey out the reduce menu button when its clicked`() {
        // GIVEN
        clearGreyOutMenuButtonInvocations()

        viewModel.onMenuButtonClicked()

        // WHEN
        viewModel.onReduceMenuButtonClicked()

        // THEN
        verifyOnlyReduceMenuButtonGreyedOut()
    }
    //</editor-fold>

    private fun observeViewModel() {
        viewModel.showInfoTitle.observeForever(
                LiveDataObserver(showInfoTitleAction))
        viewModel.showInfoContent.observeForever(
                LiveDataObserver(showInfoContentAction))
        viewModel.showMenuOverlay.observeForever(
                LiveDataObserver(showMenuOverlayAction))
        viewModel.greyOutHowToMenuButton.observeForever(
                LiveDataObserver(greyOutHowToMenuButtonAction))
        viewModel.greyOutEquationMenuButton.observeForever(
                LiveDataObserver(greyOutEquationMenuButtonAction))
        viewModel.greyOutOrderOfOperationsMenuButton.observeForever(
                LiveDataObserver(greyOutOrderOfOperationsMenuButtonAction))
        viewModel.greyOutAdditionMenuButton.observeForever(
                LiveDataObserver(greyOutAdditionMenuButtonAction))
        viewModel.greyOutSubtractionMenuButton.observeForever(
                LiveDataObserver(greyOutSubtractionMenuButtonAction))
        viewModel.greyOutMultiplicationMenuButton.observeForever(
                LiveDataObserver(greyOutMultiplicationMenuButtonAction))
        viewModel.greyOutDivisionMenuButton.observeForever(
                LiveDataObserver(greyOutDivisionMenuButtonAction))
        viewModel.greyOutReduceMenuButton.observeForever(
                LiveDataObserver(greyOutReduceMenuButtonAction))
        viewModel.navigateBack.observeForever(
                LiveDataCommandObserver(navigateBackAction))
    }

    private fun clearGreyOutMenuButtonInvocations() = clearInvocations(
            greyOutHowToMenuButtonAction,
            greyOutEquationMenuButtonAction,
            greyOutOrderOfOperationsMenuButtonAction,
            greyOutAdditionMenuButtonAction,
            greyOutSubtractionMenuButtonAction,
            greyOutMultiplicationMenuButtonAction,
            greyOutDivisionMenuButtonAction,
            greyOutReduceMenuButtonAction)

    private fun verifyOnlyHowToMenuButtonGreyedOut() {
        verify(greyOutHowToMenuButtonAction).invoke(true)
        verify(greyOutEquationMenuButtonAction).invoke(false)
        verify(greyOutOrderOfOperationsMenuButtonAction).invoke(false)
        verify(greyOutAdditionMenuButtonAction).invoke(false)
        verify(greyOutSubtractionMenuButtonAction).invoke(false)
        verify(greyOutMultiplicationMenuButtonAction).invoke(false)
        verify(greyOutDivisionMenuButtonAction).invoke(false)
        verify(greyOutReduceMenuButtonAction).invoke(false)
    }

    private fun verifyOnlyEquationMenuButtonGreyedOut() {
        verify(greyOutHowToMenuButtonAction).invoke(false)
        verify(greyOutEquationMenuButtonAction).invoke(true)
        verify(greyOutOrderOfOperationsMenuButtonAction).invoke(false)
        verify(greyOutAdditionMenuButtonAction).invoke(false)
        verify(greyOutSubtractionMenuButtonAction).invoke(false)
        verify(greyOutMultiplicationMenuButtonAction).invoke(false)
        verify(greyOutDivisionMenuButtonAction).invoke(false)
        verify(greyOutReduceMenuButtonAction).invoke(false)
    }

    private fun verifyOnlyOrderMenuButtonGreyedOut() {
        verify(greyOutHowToMenuButtonAction).invoke(false)
        verify(greyOutEquationMenuButtonAction).invoke(false)
        verify(greyOutOrderOfOperationsMenuButtonAction).invoke(true)
        verify(greyOutAdditionMenuButtonAction).invoke(false)
        verify(greyOutSubtractionMenuButtonAction).invoke(false)
        verify(greyOutMultiplicationMenuButtonAction).invoke(false)
        verify(greyOutDivisionMenuButtonAction).invoke(false)
        verify(greyOutReduceMenuButtonAction).invoke(false)
    }

    private fun verifyOnlyAdditionMenuButtonGreyedOut() {
        verify(greyOutHowToMenuButtonAction).invoke(false)
        verify(greyOutEquationMenuButtonAction).invoke(false)
        verify(greyOutOrderOfOperationsMenuButtonAction).invoke(false)
        verify(greyOutAdditionMenuButtonAction).invoke(true)
        verify(greyOutSubtractionMenuButtonAction).invoke(false)
        verify(greyOutMultiplicationMenuButtonAction).invoke(false)
        verify(greyOutDivisionMenuButtonAction).invoke(false)
        verify(greyOutReduceMenuButtonAction).invoke(false)
    }

    private fun verifyOnlySubtractionMenuButtonGreyedOut() {
        verify(greyOutHowToMenuButtonAction).invoke(false)
        verify(greyOutEquationMenuButtonAction).invoke(false)
        verify(greyOutOrderOfOperationsMenuButtonAction).invoke(false)
        verify(greyOutAdditionMenuButtonAction).invoke(false)
        verify(greyOutSubtractionMenuButtonAction).invoke(true)
        verify(greyOutMultiplicationMenuButtonAction).invoke(false)
        verify(greyOutDivisionMenuButtonAction).invoke(false)
        verify(greyOutReduceMenuButtonAction).invoke(false)
    }

    private fun verifyOnlyMultiplicationMenuButtonGreyedOut() {
        verify(greyOutHowToMenuButtonAction).invoke(false)
        verify(greyOutEquationMenuButtonAction).invoke(false)
        verify(greyOutOrderOfOperationsMenuButtonAction).invoke(false)
        verify(greyOutAdditionMenuButtonAction).invoke(false)
        verify(greyOutSubtractionMenuButtonAction).invoke(false)
        verify(greyOutMultiplicationMenuButtonAction).invoke(true)
        verify(greyOutDivisionMenuButtonAction).invoke(false)
        verify(greyOutReduceMenuButtonAction).invoke(false)
    }

    private fun verifyOnlyDivisionMenuButtonGreyedOut() {
        verify(greyOutHowToMenuButtonAction).invoke(false)
        verify(greyOutEquationMenuButtonAction).invoke(false)
        verify(greyOutOrderOfOperationsMenuButtonAction).invoke(false)
        verify(greyOutAdditionMenuButtonAction).invoke(false)
        verify(greyOutSubtractionMenuButtonAction).invoke(false)
        verify(greyOutMultiplicationMenuButtonAction).invoke(false)
        verify(greyOutDivisionMenuButtonAction).invoke(true)
        verify(greyOutReduceMenuButtonAction).invoke(false)
    }

    private fun verifyOnlyReduceMenuButtonGreyedOut() {
        verify(greyOutHowToMenuButtonAction).invoke(false)
        verify(greyOutEquationMenuButtonAction).invoke(false)
        verify(greyOutOrderOfOperationsMenuButtonAction).invoke(false)
        verify(greyOutAdditionMenuButtonAction).invoke(false)
        verify(greyOutSubtractionMenuButtonAction).invoke(false)
        verify(greyOutMultiplicationMenuButtonAction).invoke(false)
        verify(greyOutDivisionMenuButtonAction).invoke(false)
        verify(greyOutReduceMenuButtonAction).invoke(true)
    }
}