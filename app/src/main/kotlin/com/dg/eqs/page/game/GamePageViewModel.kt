package com.dg.eqs.page.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.TermDrafts
import com.dg.eqs.base.composition.Size
import com.dg.eqs.base.enveloping.ColorRes
import com.dg.eqs.base.enveloping.ColorRes.*
import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.base.observation.LiveDataCommand
import com.dg.eqs.base.observation.LiveDataEvent
import com.dg.eqs.base.observation.emit
import com.dg.eqs.core.information.Info
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.interaction.linking.LinkingCommand
import com.dg.eqs.core.interaction.linking.LinkingCommand.*
import com.dg.eqs.core.interaction.linking.LinkingCommander
import com.dg.eqs.core.interaction.scrolling.ScrollingCommand
import com.dg.eqs.core.interaction.scrolling.ScrollingCommand.*
import com.dg.eqs.core.interaction.scrolling.ScrollingCommander
import com.dg.eqs.core.orchestration.GameCommand
import com.dg.eqs.core.orchestration.GameCommand.*
import com.dg.eqs.core.orchestration.GameCommander


class GamePageViewModel(
        private val gameConfig: GameConfig,
        private val gameDrafter: GameDrafter,
        private val gameTracking: GameTracking,
        private val gameCommander: GameCommander,
        private val linkingCommander: LinkingCommander,
        private val scrollingCommander: ScrollingCommander
) : ViewModel() {

    val showRevertButton = MutableLiveData<Boolean>()
    val showInvertButton = MutableLiveData<Boolean>()
    val showSkipButton = MutableLiveData<Boolean>()
    val showDraft = MutableLiveData<LiveDataEvent<AnyDraft>>()
    val showSourceMark = MutableLiveData<LiveDataEvent<TermDrafts>>()
    val showTargetMark = MutableLiveData<LiveDataEvent<TermDrafts>>()
    val showSourceArrow = MutableLiveData<LiveDataEvent<Touch>>()
    val showGridLines = MutableLiveData<LiveDataCommand>()
    val showFinishedIcon = MutableLiveData<Boolean>()
    val showStatus = MutableLiveData<StringRes>()
    val showInfoContent = MutableLiveData<LayoutRes>()
    val showInfoOverlay = MutableLiveData<Boolean>()
    val hideSourceMark = MutableLiveData<LiveDataCommand>()
    val hideTargetMark = MutableLiveData<LiveDataCommand>()
    val hideSourceArrow = MutableLiveData<LiveDataCommand>()
    val hideGridLines = MutableLiveData<LiveDataCommand>()
    val scrollGridX = MutableLiveData<LiveDataEvent<Int>>()
    val scrollGridY = MutableLiveData<LiveDataEvent<Int>>()
    val colorInfoButton = MutableLiveData<ColorRes>()
    val colorSourceMark = MutableLiveData<LiveDataEvent<ColorRes>>()
    val colorTargetMark = MutableLiveData<LiveDataEvent<ColorRes>>()
    val colorSourceArrow = MutableLiveData<LiveDataEvent<ColorRes>>()
    val navigateBack = MutableLiveData<LiveDataCommand>()

    private var currentInfo: Info? = null
    private var currentDraft: AnyDraft? = null


    init {
        configureControls()

        startObservingCommands()

        gameCommander.onPageCreated()
    }

    fun onBackPressed() =
            if(showInfoOverlay.value == true) {
                onInfoOverlayClicked()
            } else {
                onBackButtonClicked()
            }

    fun onBackButtonClicked() = navigateBack.emit()

    fun onRevertButtonClicked() {
        gameTracking.onRevertButtonClicked()

        gameCommander.onRevertButtonClicked()

        linkingCommander.onRevertButtonClicked()
    }

    fun onInvertButtonClicked() {
        gameTracking.onInvertButtonClicked()

        gameCommander.onInvertButtonClicked()

        linkingCommander.onInvertButtonClicked()
    }

    fun onSkipButtonClicked() {
        gameTracking.onSkipButtonClicked()

        gameCommander.onSkipButtonClicked()

        linkingCommander.onSkipButtonClicked()
    }

    fun onFinishedIconClicked() {
        gameTracking.onFinishedIconClicked()

        gameCommander.onFinishedIconClicked()
    }

    fun onInfoButtonClicked() {
        gameTracking.onInfoButtonClicked()

        linkingCommander.onInfoButtonClicked()

        currentInfo?.let {
            if(showInfoContent.value != it.content) {
                showInfoContent.emit(it.content)
            }

            showInfoOverlay.emit(true)
        }
    }

    fun onInfoOverlayClicked() = showInfoOverlay.emit(false)

    fun onGameGridLaidOut(gameGridSize: Size) {
        scrollingCommander
                .onGridLaidOut(gameGridSize)

        currentDraft?.let {
            it.moveToCenter(gameGridSize)

            showDraft.emit(it)
        }
    }

    fun onGameGridTouched(gameGridTouch: Touch) {
        currentDraft?.let {
            linkingCommander
                    .onGridTouched(it, gameGridTouch)

            scrollingCommander
                    .onGridTouched(it, gameGridTouch)
        }
    }

    private fun GameCommand.execute() = when(this) {
        is ShowInfo -> {
            currentInfo = info

            if(info.isVital) {
                onInfoButtonClicked()
            }

            if(info.isValid) {
                colorInfoButton.emit(WHITE)
                hideSourceMark.emit()
                hideTargetMark.emit()
                hideSourceArrow.emit()
            } else {
                colorInfoButton.emit(RED)
                colorSourceMark.emit(RED)
                colorTargetMark.emit(RED)
                colorSourceArrow.emit(RED)
            }
        }
        is ShowOrigin -> {
            val newDraft = gameDrafter.draft(origin)

            currentDraft?.let { oldDraft ->
                newDraft.moveToCenter(oldDraft)

                showDraft.emit(newDraft)
            }

            currentDraft = newDraft
        }
        is ShowStatus -> showStatus.emit(status)
        is ShowFinishedLayout -> {
            showRevertButton.emit(false)
            showInvertButton.emit(false)
            showSkipButton.emit(false)
            showFinishedIcon.emit(true)
        }
        is HideFinishedLayout -> {
            if(gameConfig.showRevertButton) {
                showRevertButton.emit(true)
            }
            if(gameConfig.showInvertButton) {
                showInvertButton.emit(true)
            }
            if(gameConfig.showSkipButton) {
                showSkipButton.emit(true)
            }
            showFinishedIcon.emit(false)
        }
        is NavigateBack -> navigateBack.emit()
    }

    private fun LinkingCommand.execute() = when(this) {
        is ShowSourceMark -> {
            colorSourceMark.emit(WHITE)
            showSourceMark.emit(source)
        }
        is ShowTargetMark -> {
            colorTargetMark.emit(GREEN)
            showTargetMark.emit(target)
        }
        is ShowSourceArrow -> {
            colorSourceArrow.emit(GREEN)
            showSourceArrow.emit(touch)
        }
        is HandleLink -> gameCommander.onLinkCreated(link)
        is HideSourceMark -> hideSourceMark.emit()
        is HideTargetMark -> hideTargetMark.emit()
        is HideSourceArrow -> hideSourceArrow.emit()
    }

    private fun ScrollingCommand.execute() = when(this) {
        is ShowGridLines -> showGridLines.emit()
        is ScrollGridX -> {
            currentDraft?.moveX(amountX)
            scrollGridX.emit(amountX)
        }
        is ScrollGridY -> {
            currentDraft?.moveY(amountY)
            scrollGridY.emit(amountY)
        }
        is HideGridLines -> hideGridLines.emit()
    }

    private fun configureControls() {
        if(!gameConfig.showRevertButton) {
            showRevertButton.emit(false)
        }
        if(!gameConfig.showInvertButton) {
            showInvertButton.emit(false)
        }
        if(!gameConfig.showSkipButton) {
            showSkipButton.emit(false)
        }
    }

    private fun startObservingCommands() {
        gameCommander.commands.addObserver { execute() }
        linkingCommander.commands.addObserver { execute() }
        scrollingCommander.commands.addObserver { execute() }
    }

    private fun AnyDraft.moveToCenter(gridSize: Size) {
        moveX(gridSize.halfWidth - centerX)
        moveY(gridSize.halfHeight - centerY)
    }

    private fun AnyDraft.moveToCenter(oldDraft: AnyDraft) {
        moveX(oldDraft.centerX - centerX)
        moveY(oldDraft.centerY - centerY)
    }
}