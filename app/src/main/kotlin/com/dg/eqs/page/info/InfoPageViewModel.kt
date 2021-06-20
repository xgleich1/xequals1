package com.dg.eqs.page.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dg.eqs.R
import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.base.observation.LiveDataCommand
import com.dg.eqs.base.observation.emit
import com.dg.eqs.page.info.InfoPageViewModel.Info.*


class InfoPageViewModel : ViewModel() {
    val showInfoTitle = MutableLiveData<StringRes>()
    val showInfoContent = MutableLiveData<LayoutRes>()
    val showMenuOverlay = MutableLiveData<Boolean>()
    val greyOutHowToMenuButton = MutableLiveData<Boolean>()
    val greyOutEquationMenuButton = MutableLiveData<Boolean>()
    val greyOutOrderOfOperationsMenuButton = MutableLiveData<Boolean>()
    val greyOutAdditionMenuButton = MutableLiveData<Boolean>()
    val greyOutSubtractionMenuButton = MutableLiveData<Boolean>()
    val greyOutMultiplicationMenuButton = MutableLiveData<Boolean>()
    val greyOutDivisionMenuButton = MutableLiveData<Boolean>()
    val greyOutReduceMenuButton = MutableLiveData<Boolean>()
    val navigateBack = MutableLiveData<LiveDataCommand>()


    init {
        onHowToMenuButtonClicked()
    }

    fun onBackPressed() =
            onBackButtonClicked()

    fun onBackButtonClicked() =
            if(showMenuOverlay.value == true) {
                emitHideMenuOverlay()
            } else {
                emitNavigateBack()
            }

    fun onMenuButtonClicked() =
            if(showMenuOverlay.value == false) {
                emitShowMenuOverlay()
            } else {
                emitHideMenuOverlay()
            }

    fun onMenuOverlayClicked() = emitHideMenuOverlay()

    fun onHowToMenuButtonClicked() = onMenuButtonClicked(HowToInfo)

    fun onEquationMenuButtonClicked() = onMenuButtonClicked(EquationInfo)

    fun onOrderOfOperationsMenuButtonClicked() = onMenuButtonClicked(OrderOfOperationsInfo)

    fun onAdditionMenuButtonClicked() = onMenuButtonClicked(AdditionInfo)

    fun onSubtractionMenuButtonClicked() = onMenuButtonClicked(SubtractionInfo)

    fun onMultiplicationMenuButtonClicked() = onMenuButtonClicked(MultiplicationInfo)

    fun onDivisionMenuButtonClicked() = onMenuButtonClicked(DivisionInfo)

    fun onReduceMenuButtonClicked() = onMenuButtonClicked(ReduceInfo)

    private fun onMenuButtonClicked(info: Info) {
        emitInfoTitle(info)
        emitInfoContent(info)
        emitHideMenuOverlay()
        emitGreyOutMenuButton(info)
    }

    private fun emitInfoTitle(info: Info) =
            showInfoTitle.emit(info.title)

    private fun emitInfoContent(info: Info) =
            with(info) {
                if(showInfoContent.value != content) {
                    showInfoContent.emit(content)
                }
            }

    private fun emitShowMenuOverlay() = showMenuOverlay.emit(true)

    private fun emitHideMenuOverlay() = showMenuOverlay.emit(false)

    private fun emitGreyOutMenuButton(info: Info) {
        greyOutHowToMenuButton.emit(info == HowToInfo)
        greyOutEquationMenuButton.emit(info == EquationInfo)
        greyOutOrderOfOperationsMenuButton.emit(info == OrderOfOperationsInfo)
        greyOutAdditionMenuButton.emit(info == AdditionInfo)
        greyOutSubtractionMenuButton.emit(info == SubtractionInfo)
        greyOutMultiplicationMenuButton.emit(info == MultiplicationInfo)
        greyOutDivisionMenuButton.emit(info == DivisionInfo)
        greyOutReduceMenuButton.emit(info == ReduceInfo)
    }

    private fun emitNavigateBack() = navigateBack.emit()

    private sealed class Info(
            val title: StringRes,
            val content: LayoutRes) {

        object HowToInfo : Info(
                StringRes(R.string.info_how_to_title),
                LayoutRes(R.layout.info_how_to))

        object EquationInfo : Info(
                StringRes(R.string.info_equation_title),
                LayoutRes(R.layout.info_equation))

        object OrderOfOperationsInfo : Info(
                StringRes(R.string.info_order_of_operations_title),
                LayoutRes(R.layout.info_order_of_operations))

        object AdditionInfo : Info(
                StringRes(R.string.info_addition_title),
                LayoutRes(R.layout.info_addition))

        object SubtractionInfo : Info(
                StringRes(R.string.info_subtraction_title),
                LayoutRes(R.layout.info_subtraction))

        object MultiplicationInfo : Info(
                StringRes(R.string.info_multiplication_title),
                LayoutRes(R.layout.info_multiplication))

        object DivisionInfo : Info(
                StringRes(R.string.info_division_title),
                LayoutRes(R.layout.info_division))

        object ReduceInfo : Info(
                StringRes(R.string.info_reduce_title),
                LayoutRes(R.layout.info_reduce))
    }
}