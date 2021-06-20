package com.dg.eqs.core.orchestration

import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.base.observation.Observable
import com.dg.eqs.core.execution.intention.Intention
import com.dg.eqs.core.execution.intention.Intention.Click.Invert
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.IntentionExecutor
import com.dg.eqs.core.orchestration.GameCommand.*


abstract class GameCommander(
        private val levelRepository: GameLevelRepository,
        private val infoRepository: GameInfoRepository,
        private val statusRepository: GameStatusRepository,
        private val intentionExecutor: IntentionExecutor) {

    val commands = Observable<GameCommand>()

    private lateinit var level: AnyLevel

    private var steps = GameSteps()

    private val recentOrigin
        get() =
            if(steps.isEmpty()) {
                level.exercise
            } else {
                steps.lastOrigin
            }


    fun onPageCreated() {
        level = loadInitialLevel()

        emitShowInitialContent()
    }

    fun onLinkCreated(link: Link) {
        executeIntention(link)
    }

    fun onFinishedIconClicked() {
        steps = GameSteps()

        level = loadEnsuingLevel()
                ?: return emitNavigateBack()

        emitHideFinishedLayout()

        emitShowInitialContent()
    }

    fun onRevertButtonClicked() {
        steps = steps.revert()

        if(steps.isNotEmpty()) {
            emitShowEnsuingContent()
        } else {
            emitShowInitialContent()
        }
    }

    fun onInvertButtonClicked() {
        executeIntention(
                Invert(recentOrigin))
    }

    fun onSkipButtonClicked() {
        steps = GameSteps()

        finishCurrentLevel()

        level = loadEnsuingLevel()
                ?: return emitNavigateBack()

        emitShowInitialContent()
    }

    private fun executeIntention(intention: Intention) {
        steps += intentionExecutor.execute(intention)

        if(recentOrigin.isSolved()) {
            finishCurrentLevel()

            emitShowFinishedLayout()

            emitShowCeasingContent()
        } else {
            emitShowEnsuingContent()
        }
    }

    private fun emitShowInitialContent() {
        emitShowInitialInfo()
        emitShowRecentOrigin()
        emitShowInitialStatus()
    }

    private fun emitShowEnsuingContent() {
        emitShowEnsuingInfo()
        emitShowRecentOrigin()
        emitShowEnsuingStatus()
    }

    private fun emitShowCeasingContent() {
        emitShowEnsuingInfo()
        emitShowRecentOrigin()
        emitShowCeasingStatus()
    }

    private fun loadInitialLevel() = levelRepository.loadInitialLevel()

    private fun loadEnsuingLevel() = levelRepository.loadEnsuingLevel()

    private fun finishCurrentLevel() = levelRepository.finishLevel(level, steps)

    private fun loadInitialInfo() = infoRepository.loadInitialInfo(level)

    private fun loadInitialStatus() = statusRepository.loadInitialStatus(level)

    private fun loadEnsuingStatus() = statusRepository.loadEnsuingStatus(level, steps)

    private fun loadCeasingStatus() = statusRepository.loadCeasingStatus(level, steps)

    private fun emitShowInitialInfo() = commands.emit(ShowInfo(loadInitialInfo()))

    private fun emitShowEnsuingInfo() = commands.emit(ShowInfo(steps.lastInfo))

    private fun emitShowRecentOrigin() = commands.emit(ShowOrigin(recentOrigin))

    private fun emitShowInitialStatus() = commands.emit(ShowStatus(loadInitialStatus()))

    private fun emitShowEnsuingStatus() = commands.emit(ShowStatus(loadEnsuingStatus()))

    private fun emitShowCeasingStatus() = commands.emit(ShowStatus(loadCeasingStatus()))

    private fun emitShowFinishedLayout() = commands.emit(ShowFinishedLayout)

    private fun emitHideFinishedLayout() = commands.emit(HideFinishedLayout)

    private fun emitNavigateBack() = commands.emit(NavigateBack)
}