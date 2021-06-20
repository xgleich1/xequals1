package com.dg.eqs.core.orchestration

import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.core.execution.Step
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.Intention.Click.Invert
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.IntentionExecutor
import com.dg.eqs.core.information.valid.ValidInfo
import com.dg.eqs.core.orchestration.GameCommand.*
import org.mockito.kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GameCommanderTest {
    @Mock
    private lateinit var levelRepository: GameLevelRepository
    @Mock
    private lateinit var infoRepository: GameInfoRepository
    @Mock
    private lateinit var statusRepository: GameStatusRepository
    @Mock
    private lateinit var intentionExecutor: IntentionExecutor

    private lateinit var gameCommander: GameCommander

    @Mock
    private lateinit var observer: (GameCommand) -> Unit

    @Mock
    private lateinit var initialLevel: AnyLevel
    @Mock
    private lateinit var ensuingLevel: AnyLevel

    @Mock
    private lateinit var initialInfoA: ValidInfo
    @Mock
    private lateinit var initialInfoB: ValidInfo

    @Mock
    private lateinit var ensuingInfoA: ValidInfo
    @Mock
    private lateinit var ensuingInfoB: ValidInfo

    @Mock
    private lateinit var ceasingInfoA: ValidInfo
    @Mock
    private lateinit var ceasingInfoB: ValidInfo

    @Mock
    private lateinit var initialStatusA: StringRes
    @Mock
    private lateinit var initialStatusB: StringRes

    @Mock
    private lateinit var ensuingStatusA: StringRes
    @Mock
    private lateinit var ensuingStatusB: StringRes

    @Mock
    private lateinit var ceasingStatusA: StringRes
    @Mock
    private lateinit var ceasingStatusB: StringRes

    @Mock
    private lateinit var initialOriginA: AnyOrigin
    @Mock
    private lateinit var initialOriginB: AnyOrigin

    @Mock
    private lateinit var ensuingOriginA: AnyOrigin
    @Mock
    private lateinit var ensuingOriginB: AnyOrigin

    @Mock
    private lateinit var ceasingOriginA: AnyOrigin
    @Mock
    private lateinit var ceasingOriginB: AnyOrigin

    @Mock
    private lateinit var linkA: Link
    @Mock
    private lateinit var linkB: Link
    @Mock
    private lateinit var linkC: Link


    @Before
    fun setUp() {
        gameCommander = createGameCommander()
        gameCommander.commands.addObserver(observer)
    }

    @Test
    fun `Should play the initial level until a link finishes it`() {
        // GIVEN
        setupPlayInitialLevelUntilLinkFinishesIt()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onLinkCreated(linkB)
        }

        // THEN
        verifyCommandsEmitted(
                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA),

                ShowInfo(ensuingInfoA),
                ShowOrigin(ensuingOriginA),
                ShowStatus(ensuingStatusA),

                ShowFinishedLayout,

                ShowInfo(ceasingInfoA),
                ShowOrigin(ceasingOriginA),
                ShowStatus(ceasingStatusA))
    }

    @Test
    fun `Should play the initial level until a invert finishes it`() {
        // GIVEN
        setupPlayInitialLevelUntilInvertFinishesIt()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onInvertButtonClicked()

            onLinkCreated(linkA)

            onInvertButtonClicked()
        }

        // THEN
        verifyCommandsEmitted(
                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA),

                ShowInfo(ensuingInfoA),
                ShowOrigin(ensuingOriginA),
                ShowStatus(ensuingStatusA),

                ShowInfo(ensuingInfoB),
                ShowOrigin(ensuingOriginB),
                ShowStatus(ensuingStatusB),

                ShowFinishedLayout,

                ShowInfo(ceasingInfoA),
                ShowOrigin(ceasingOriginA),
                ShowStatus(ceasingStatusA))
    }

    @Test
    fun `Should play the ensuing level until a link finishes it`() {
        // GIVEN
        setupPlayEnsuingLevelUntilLinkFinishesIt()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onFinishedIconClicked()

            onLinkCreated(linkB)

            onLinkCreated(linkC)
        }

        // THEN
        verifyCommandsEmitted(
                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA),

                ShowFinishedLayout,

                ShowInfo(ceasingInfoA),
                ShowOrigin(ceasingOriginA),
                ShowStatus(ceasingStatusA),

                HideFinishedLayout,

                ShowInfo(initialInfoB),
                ShowOrigin(initialOriginB),
                ShowStatus(initialStatusB),

                ShowInfo(ensuingInfoA),
                ShowOrigin(ensuingOriginA),
                ShowStatus(ensuingStatusA),

                ShowFinishedLayout,

                ShowInfo(ceasingInfoB),
                ShowOrigin(ceasingOriginB),
                ShowStatus(ceasingStatusB))
    }

    @Test
    fun `Should play the ensuing level until a invert finishes it`() {
        // GIVEN
        setupPlayEnsuingLevelUntilInvertFinishesIt()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onFinishedIconClicked()

            onInvertButtonClicked()

            onLinkCreated(linkB)

            onInvertButtonClicked()
        }

        // THEN
        verifyCommandsEmitted(
                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA),

                ShowFinishedLayout,

                ShowInfo(ceasingInfoA),
                ShowOrigin(ceasingOriginA),
                ShowStatus(ceasingStatusA),

                HideFinishedLayout,

                ShowInfo(initialInfoB),
                ShowOrigin(initialOriginB),
                ShowStatus(initialStatusB),

                ShowInfo(ensuingInfoA),
                ShowOrigin(ensuingOriginA),
                ShowStatus(ensuingStatusA),

                ShowInfo(ensuingInfoB),
                ShowOrigin(ensuingOriginB),
                ShowStatus(ensuingStatusB),

                ShowFinishedLayout,

                ShowInfo(ceasingInfoB),
                ShowOrigin(ceasingOriginB),
                ShowStatus(ceasingStatusB))
    }

    @Test
    fun `Should navigate back when there is no ensuing level to play`() {
        // GIVEN
        setupNavigateBackWhenThereIsNoEnsuingLevelToPlay()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onFinishedIconClicked()
        }

        // THEN
        verifyCommandsEmitted(
                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA),

                ShowFinishedLayout,

                ShowInfo(ceasingInfoA),
                ShowOrigin(ceasingOriginA),
                ShowStatus(ceasingStatusA),

                NavigateBack)
    }

    @Test
    fun `Should revert to the initial content when there's no intention executed yet`() {
        // GIVEN
        setupRevertToInitialContentWhenThereIsNoIntentionExecutedYet()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onRevertButtonClicked()
        }

        // THEN
        verifyCommandsEmitted(
                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA),

                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA))
    }

    @Test
    fun `Should revert to the initial content when one link intention was executed`() {
        // GIVEN
        setupRevertToInitialContentWhenOneLinkIntentionWasExecuted()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onRevertButtonClicked()
        }

        // THEN
        verifyCommandsEmitted(
                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA),

                ShowInfo(ensuingInfoA),
                ShowOrigin(ensuingOriginA),
                ShowStatus(ensuingStatusA),

                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA))
    }

    @Test
    fun `Should revert to the previous content when several link intentions were executed`() {
        // GIVEN
        setupRevertToPreviousContentWhenSeveralLinkIntentionsWereExecuted()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onLinkCreated(linkB)

            onRevertButtonClicked()
        }

        // THEN
        verifyCommandsEmitted(
                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA),

                ShowInfo(ensuingInfoA),
                ShowOrigin(ensuingOriginA),
                ShowStatus(ensuingStatusA),

                ShowInfo(ensuingInfoB),
                ShowOrigin(ensuingOriginB),
                ShowStatus(ensuingStatusB),

                ShowInfo(ensuingInfoA),
                ShowOrigin(ensuingOriginA),
                ShowStatus(ensuingStatusA))
    }

    @Test
    fun `Should revert to the initial content when one invert intention was executed`() {
        // GIVEN
        setupRevertToInitialContentWhenOneInvertIntentionWasExecuted()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onInvertButtonClicked()

            onRevertButtonClicked()
        }

        // THEN
        verifyCommandsEmitted(
                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA),

                ShowInfo(ensuingInfoA),
                ShowOrigin(ensuingOriginA),
                ShowStatus(ensuingStatusA),

                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA))
    }

    @Test
    fun `Should revert to the previous content when several invert intentions were executed`() {
        // GIVEN
        setupRevertToPreviousContentWhenSeveralInvertIntentionsWereExecuted()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onInvertButtonClicked()

            onInvertButtonClicked()

            onRevertButtonClicked()
        }

        // THEN
        verifyCommandsEmitted(
                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA),

                ShowInfo(ensuingInfoA),
                ShowOrigin(ensuingOriginA),
                ShowStatus(ensuingStatusA),

                ShowInfo(ensuingInfoB),
                ShowOrigin(ensuingOriginB),
                ShowStatus(ensuingStatusB),

                ShowInfo(ensuingInfoA),
                ShowOrigin(ensuingOriginA),
                ShowStatus(ensuingStatusA))
    }

    @Test
    fun `Should skip to the ensuing level when there is one to skip to`() {
        // GIVEN
        setupSkipToEnsuingLevelWhenThereIsOneToSkipTo()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onSkipButtonClicked()
        }

        // THEN
        verifyCommandsEmitted(
                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA),

                ShowInfo(initialInfoB),
                ShowOrigin(initialOriginB),
                ShowStatus(initialStatusB))
    }

    @Test
    fun `Should navigate back when there is no ensuing level to skip to`() {
        // GIVEN
        setupNavigateBackWhenThereIsNoEnsuingLevelToSkipTo()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onSkipButtonClicked()
        }

        // THEN
        verifyCommandsEmitted(
                ShowInfo(initialInfoA),
                ShowOrigin(initialOriginA),
                ShowStatus(initialStatusA),

                NavigateBack)
    }

    @Test
    fun `Should load the initial info with the initial level`() {
        // GIVEN
        setupLoadInitialInfoWithInitialLevel()

        // WHEN
        gameCommander.onPageCreated()

        // THEN
        verify(infoRepository).loadInitialInfo(initialLevel)
    }

    @Test
    fun `Should load the initial status with the initial level`() {
        // GIVEN
        setupLoadInitialStatusWithInitialLevel()

        // WHEN
        gameCommander.onPageCreated()

        // THEN
        verify(statusRepository).loadInitialStatus(initialLevel)
    }

    @Test
    fun `Should execute a link intention with the given link`() {
        // GIVEN
        setupExecuteLinkIntentionWithGivenLink()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)
        }

        // THEN
        verify(intentionExecutor).execute(linkA)
    }

    @Test
    fun `Should execute a intention which inverts the exercise`() {
        // GIVEN
        setupExecuteInvertIntentionWhichInvertsExercise()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onInvertButtonClicked()
        }

        // THEN
        verify(intentionExecutor).execute(Invert(initialOriginA))
    }

    @Test
    fun `Should execute a intention which inverts the recent origin`() {
        // GIVEN
        setupExecuteInvertIntentionWhichInvertsRecentOrigin()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onInvertButtonClicked()
        }

        // THEN
        verify(intentionExecutor).execute(Invert(ensuingOriginA))
    }

    @Test
    fun `Should finish the initial level providing the steps`() {
        // GIVEN
        setupFinishInitialLevelProvidingSteps()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onInvertButtonClicked()
        }

        // THEN
        verify(levelRepository).finishLevel(initialLevel, GameSteps(
                ValidStep(ensuingInfoA, ensuingOriginA),
                ValidStep(ceasingInfoA, ceasingOriginA)))
    }

    @Test
    fun `Should load the ceasing status with the initial level & the steps`() {
        // GIVEN
        setupLoadCeasingStatusWithInitialLevelAndSteps()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onInvertButtonClicked()
        }

        // THEN
        verify(statusRepository).loadCeasingStatus(initialLevel, GameSteps(
                ValidStep(ensuingInfoA, ensuingOriginA),
                ValidStep(ceasingInfoA, ceasingOriginA)))
    }

    @Test
    fun `Should load the ensuing status with the initial level and the steps`() {
        // GIVEN
        setupLoadEnsuingStatusWithInitialLevelAndSteps()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onInvertButtonClicked()
        }

        // THEN
        verify(statusRepository).loadEnsuingStatus(initialLevel, GameSteps(
                ValidStep(ensuingInfoA, ensuingOriginA),
                ValidStep(ensuingInfoB, ensuingOriginB)))
    }

    @Test
    fun `Should load the initial info with the ensuing level`() {
        // GIVEN
        setupLoadInitialInfoWithEnsuingLevel()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onFinishedIconClicked()
        }

        // THEN
        verify(infoRepository).loadInitialInfo(ensuingLevel)
    }

    @Test
    fun `Should load the initial status with the ensuing level`() {
        // GIVEN
        setupLoadInitialStatusWithEnsuingLevel()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onFinishedIconClicked()
        }

        // THEN
        verify(statusRepository).loadInitialStatus(ensuingLevel)
    }

    @Test
    fun `Should finish the ensuing level providing the steps`() {
        // GIVEN
        setupFinishEnsuingLevelProvidingSteps()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onFinishedIconClicked()

            onLinkCreated(linkB)

            onInvertButtonClicked()
        }

        // THEN
        verify(levelRepository).finishLevel(ensuingLevel, GameSteps(
                ValidStep(ensuingInfoA, ensuingOriginA),
                ValidStep(ceasingInfoB, ceasingOriginB)))
    }

    @Test
    fun `Should load the ceasing status with the ensuing level and the steps`() {
        // GIVEN
        setupLoadCeasingStatusWithEnsuingLevelAndSteps()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onFinishedIconClicked()

            onLinkCreated(linkB)

            onInvertButtonClicked()
        }

        // THEN
        verify(statusRepository).loadCeasingStatus(ensuingLevel, GameSteps(
                ValidStep(ensuingInfoA, ensuingOriginA),
                ValidStep(ceasingInfoB, ceasingOriginB)))
    }

    @Test
    fun `Should load the ensuing status with the ensuing level and the steps`() {
        // GIVEN
        setupLoadEnsuingStatusWithEnsuingLevelAndSteps()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onFinishedIconClicked()

            onLinkCreated(linkB)

            onInvertButtonClicked()
        }

        // THEN
        verify(statusRepository).loadEnsuingStatus(ensuingLevel, GameSteps(
                ValidStep(ensuingInfoA, ensuingOriginA),
                ValidStep(ensuingInfoB, ensuingOriginB)))
    }

    @Test
    fun `Should load the initial info with the skipped to ensuing level`() {
        // GIVEN
        setupLoadInitialInfoWithSkippedToEnsuingLevel()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onSkipButtonClicked()
        }

        // THEN
        verify(infoRepository).loadInitialInfo(ensuingLevel)
    }

    @Test
    fun `Should load the initial status with the skipped to ensuing level`() {
        // GIVEN
        setupLoadInitialStatusWithSkippedToEnsuingLevel()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onSkipButtonClicked()
        }

        // THEN
        verify(statusRepository).loadInitialStatus(ensuingLevel)
    }

    @Test
    fun `Should finish the skipped level discarding the steps`() {
        // GIVEN
        setupFinishSkippedLevelDiscardingSteps()

        // WHEN
        gameCommander.run {
            onPageCreated()

            onLinkCreated(linkA)

            onInvertButtonClicked()

            onSkipButtonClicked()
        }

        // THEN
        verify(levelRepository).finishLevel(initialLevel, GameSteps())
    }

    private fun createGameCommander() = object : GameCommander(
            levelRepository,
            infoRepository,
            statusRepository,
            intentionExecutor) {}

    private fun setupPlayInitialLevelUntilLinkFinishesIt() {
        setupIsSolved(ceasingOriginA)

        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
        setupEnsuingStatus(ensuingStatusA)
        setupCeasingStatus(ceasingStatusA)

        setupLinkIntentionExecutions(
                ValidStep(ensuingInfoA, ensuingOriginA),
                ValidStep(ceasingInfoA, ceasingOriginA))
    }

    private fun setupPlayInitialLevelUntilInvertFinishesIt() {
        setupIsSolved(ceasingOriginA)

        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
        setupEnsuingStatus(ensuingStatusA, ensuingStatusB)
        setupCeasingStatus(ceasingStatusA)

        setupLinkIntentionExecutions(
                ValidStep(ensuingInfoB, ensuingOriginB))
        setupInvertIntentionExecutions(
                ValidStep(ensuingInfoA, ensuingOriginA),
                ValidStep(ceasingInfoA, ceasingOriginA))
    }

    private fun setupPlayEnsuingLevelUntilLinkFinishesIt() {
        setupIsSolved(ceasingOriginA)
        setupIsSolved(ceasingOriginB)

        setupInitialLevel()
        setupEnsuingLevel()

        setupInitialInfo(initialInfoA, initialInfoB)

        setupInitialStatus(initialStatusA, initialStatusB)
        setupEnsuingStatus(ensuingStatusA)
        setupCeasingStatus(ceasingStatusA, ceasingStatusB)

        setupLinkIntentionExecutions(
                ValidStep(ceasingInfoA, ceasingOriginA),
                ValidStep(ensuingInfoA, ensuingOriginA),
                ValidStep(ceasingInfoB, ceasingOriginB))
    }

    private fun setupPlayEnsuingLevelUntilInvertFinishesIt() {
        setupIsSolved(ceasingOriginA)
        setupIsSolved(ceasingOriginB)

        setupInitialLevel()
        setupEnsuingLevel()

        setupInitialInfo(initialInfoA, initialInfoB)

        setupInitialStatus(initialStatusA, initialStatusB)
        setupEnsuingStatus(ensuingStatusA, ensuingStatusB)
        setupCeasingStatus(ceasingStatusA, ceasingStatusB)

        setupLinkIntentionExecutions(
                ValidStep(ceasingInfoA, ceasingOriginA),
                ValidStep(ensuingInfoB, ensuingOriginB))
        setupInvertIntentionExecutions(
                ValidStep(ensuingInfoA, ensuingOriginA),
                ValidStep(ceasingInfoB, ceasingOriginB))
    }

    private fun setupNavigateBackWhenThereIsNoEnsuingLevelToPlay() {
        setupIsSolved(ceasingOriginA)

        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
        setupCeasingStatus(ceasingStatusA)

        setupLinkIntentionExecutions(
                ValidStep(ceasingInfoA, ceasingOriginA))
    }

    private fun setupRevertToInitialContentWhenThereIsNoIntentionExecutedYet() {
        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
    }

    private fun setupRevertToInitialContentWhenOneLinkIntentionWasExecuted() {
        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
        setupEnsuingStatus(ensuingStatusA)

        setupLinkIntentionExecutions(
                ValidStep(ensuingInfoA, ensuingOriginA))
    }

    private fun setupRevertToPreviousContentWhenSeveralLinkIntentionsWereExecuted() {
        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
        setupEnsuingStatus(ensuingStatusA, ensuingStatusB, ensuingStatusA)

        setupLinkIntentionExecutions(
                ValidStep(ensuingInfoA, ensuingOriginA),
                ValidStep(ensuingInfoB, ensuingOriginB))
    }

    private fun setupRevertToInitialContentWhenOneInvertIntentionWasExecuted() {
        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
        setupEnsuingStatus(ensuingStatusA)

        setupInvertIntentionExecutions(
                ValidStep(ensuingInfoA, ensuingOriginA))
    }

    private fun setupRevertToPreviousContentWhenSeveralInvertIntentionsWereExecuted() {
        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
        setupEnsuingStatus(ensuingStatusA, ensuingStatusB, ensuingStatusA)

        setupInvertIntentionExecutions(
                ValidStep(ensuingInfoA, ensuingOriginA),
                ValidStep(ensuingInfoB, ensuingOriginB))
    }

    private fun setupSkipToEnsuingLevelWhenThereIsOneToSkipTo() {
        setupInitialLevel()
        setupEnsuingLevel()

        setupInitialInfo(initialInfoA, initialInfoB)

        setupInitialStatus(initialStatusA, initialStatusB)
    }

    private fun setupNavigateBackWhenThereIsNoEnsuingLevelToSkipTo() {
        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
    }

    private fun setupLoadInitialInfoWithInitialLevel() {
        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
    }

    private fun setupLoadInitialStatusWithInitialLevel() {
        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
    }

    private fun setupExecuteLinkIntentionWithGivenLink() {
        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
        setupEnsuingStatus(ensuingStatusA)

        setupLinkIntentionExecutions(
                ValidStep(ensuingInfoA, ensuingOriginA))
    }

    private fun setupExecuteInvertIntentionWhichInvertsExercise() {
        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
        setupEnsuingStatus(ensuingStatusA)

        setupInvertIntentionExecutions(
                ValidStep(ensuingInfoA, ensuingOriginA))
    }

    private fun setupExecuteInvertIntentionWhichInvertsRecentOrigin() {
        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
        setupEnsuingStatus(ensuingStatusA, ensuingStatusB)

        setupLinkIntentionExecutions(
                ValidStep(ensuingInfoA, ensuingOriginA))
        setupInvertIntentionExecutions(
                ValidStep(ensuingInfoB, ensuingOriginB))
    }

    private fun setupFinishInitialLevelProvidingSteps() {
        setupIsSolved(ceasingOriginA)

        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
        setupEnsuingStatus(ensuingStatusA)
        setupCeasingStatus(ceasingStatusA)

        setupLinkIntentionExecutions(
                ValidStep(ensuingInfoA, ensuingOriginA))
        setupInvertIntentionExecutions(
                ValidStep(ceasingInfoA, ceasingOriginA))
    }

    private fun setupLoadCeasingStatusWithInitialLevelAndSteps() {
        setupIsSolved(ceasingOriginA)

        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
        setupEnsuingStatus(ensuingStatusA)
        setupCeasingStatus(ceasingStatusA)

        setupLinkIntentionExecutions(
                ValidStep(ensuingInfoA, ensuingOriginA))
        setupInvertIntentionExecutions(
                ValidStep(ceasingInfoA, ceasingOriginA))
    }

    private fun setupLoadEnsuingStatusWithInitialLevelAndSteps() {
        setupInitialLevel()

        setupInitialInfo(initialInfoA)

        setupInitialStatus(initialStatusA)
        setupEnsuingStatus(ensuingStatusA, ensuingStatusB)

        setupLinkIntentionExecutions(
                ValidStep(ensuingInfoA, ensuingOriginA))
        setupInvertIntentionExecutions(
                ValidStep(ensuingInfoB, ensuingOriginB))
    }

    private fun setupLoadInitialInfoWithEnsuingLevel() {
        setupIsSolved(ceasingOriginA)

        setupInitialLevel()
        setupEnsuingLevel()

        setupInitialInfo(initialInfoA, initialInfoB)

        setupInitialStatus(initialStatusA, initialStatusB)
        setupCeasingStatus(ceasingStatusA)

        setupLinkIntentionExecutions(
                ValidStep(ceasingInfoA, ceasingOriginA))
    }

    private fun setupLoadInitialStatusWithEnsuingLevel() {
        setupIsSolved(ceasingOriginA)

        setupInitialLevel()
        setupEnsuingLevel()

        setupInitialInfo(initialInfoA, initialInfoB)

        setupInitialStatus(initialStatusA, initialStatusB)
        setupCeasingStatus(ceasingStatusA)

        setupLinkIntentionExecutions(
                ValidStep(ceasingInfoA, ceasingOriginA))
    }

    private fun setupFinishEnsuingLevelProvidingSteps() {
        setupIsSolved(ceasingOriginA)
        setupIsSolved(ceasingOriginB)

        setupInitialLevel()
        setupEnsuingLevel()

        setupInitialInfo(initialInfoA, initialInfoB)

        setupInitialStatus(initialStatusA, initialStatusB)
        setupEnsuingStatus(ensuingStatusA)
        setupCeasingStatus(ceasingStatusA, ceasingStatusB)

        setupLinkIntentionExecutions(
                ValidStep(ceasingInfoA, ceasingOriginA),
                ValidStep(ensuingInfoA, ensuingOriginA))
        setupInvertIntentionExecutions(
                ValidStep(ceasingInfoB, ceasingOriginB))
    }

    private fun setupLoadCeasingStatusWithEnsuingLevelAndSteps() {
        setupIsSolved(ceasingOriginA)
        setupIsSolved(ceasingOriginB)

        setupInitialLevel()
        setupEnsuingLevel()

        setupInitialInfo(initialInfoA, initialInfoB)

        setupInitialStatus(initialStatusA, initialStatusB)
        setupEnsuingStatus(ensuingStatusA)
        setupCeasingStatus(ceasingStatusA, ceasingStatusB)

        setupLinkIntentionExecutions(
                ValidStep(ceasingInfoA, ceasingOriginA),
                ValidStep(ensuingInfoA, ensuingOriginA))
        setupInvertIntentionExecutions(
                ValidStep(ceasingInfoB, ceasingOriginB))
    }

    private fun setupLoadEnsuingStatusWithEnsuingLevelAndSteps() {
        setupIsSolved(ceasingOriginA)

        setupInitialLevel()
        setupEnsuingLevel()

        setupInitialInfo(initialInfoA, initialInfoB)

        setupInitialStatus(initialStatusA, initialStatusB)
        setupEnsuingStatus(ensuingStatusA)
        setupCeasingStatus(ceasingStatusA)

        setupLinkIntentionExecutions(
                ValidStep(ceasingInfoA, ceasingOriginA),
                ValidStep(ensuingInfoA, ensuingOriginA))
        setupInvertIntentionExecutions(
                ValidStep(ensuingInfoB, ensuingOriginB))
    }

    private fun setupLoadInitialInfoWithSkippedToEnsuingLevel() {
        setupInitialLevel()
        setupEnsuingLevel()

        setupInitialInfo(initialInfoA, initialInfoB)

        setupInitialStatus(initialStatusA, initialStatusB)
    }

    private fun setupLoadInitialStatusWithSkippedToEnsuingLevel() {
        setupInitialLevel()
        setupEnsuingLevel()

        setupInitialInfo(initialInfoA, initialInfoB)

        setupInitialStatus(initialStatusA, initialStatusB)
    }

    private fun setupFinishSkippedLevelDiscardingSteps() {
        setupInitialLevel()

        setupInitialInfo(initialInfoA, initialInfoB)

        setupInitialStatus(initialStatusA, initialStatusB)
        setupEnsuingStatus(ensuingStatusA, ensuingStatusB)

        setupLinkIntentionExecutions(
                ValidStep(ensuingInfoA, ensuingOriginA))
        setupInvertIntentionExecutions(
                ValidStep(ensuingInfoB, ensuingOriginB))
    }

    private fun setupIsSolved(origin: AnyOrigin) {
        whenever(origin.isSolved()) doReturn true
    }

    private fun setupInitialLevel() {
        whenever(initialLevel.exercise) doReturn initialOriginA

        whenever(levelRepository.loadInitialLevel()) doReturn initialLevel
    }

    private fun setupEnsuingLevel() {
        whenever(ensuingLevel.exercise) doReturn initialOriginB

        whenever(levelRepository.loadEnsuingLevel()) doReturn ensuingLevel
    }

    private fun setupInitialInfo(first: ValidInfo, vararg following: ValidInfo) =
            whenever(infoRepository.loadInitialInfo(any())).doReturn(first, *following)

    private fun setupInitialStatus(first: StringRes, vararg following: StringRes) =
            whenever(statusRepository.loadInitialStatus(any())).doReturn(first, *following)

    private fun setupEnsuingStatus(first: StringRes, vararg following: StringRes) =
            whenever(statusRepository.loadEnsuingStatus(any(), any())).doReturn(first, *following)

    private fun setupCeasingStatus(first: StringRes, vararg following: StringRes) =
            whenever(statusRepository.loadCeasingStatus(any(), any())).doReturn(first, *following)

    private fun setupLinkIntentionExecutions(first: Step, vararg following: Step) =
            whenever(intentionExecutor.execute(any<Link>())).doReturn(first, *following)

    private fun setupInvertIntentionExecutions(first: ValidStep, vararg following: ValidStep) =
            whenever(intentionExecutor.execute(any<Invert>())).doReturn(first, *following)

    private fun verifyCommandsEmitted(vararg commands: GameCommand) =
            argumentCaptor<GameCommand> {
                verify(observer, atLeastOnce()).invoke(capture())

                assertThat(allValues).isEqualTo(commands.toList())
            }
}