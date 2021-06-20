package com.dg.eqs.page.events

import com.dg.eqs.base.gamification.GoogleGamesService
import com.dg.eqs.core.competition.firebasedatabase.EventFirebaseDatabase
import com.dg.eqs.core.competition.firebasedatabase.EventFirebaseEntity
import com.dg.eqs.page.event.EventPageRobot
import com.dg.eqs.page.game.GamePageRobot
import com.dg.eqs.util.dagger.TestDaggerMockRule
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doReturnConsecutively
import org.mockito.kotlin.whenever


class EventsPageTest {
    @get:Rule
    val daggerRule = TestDaggerMockRule()

    @Mock
    private lateinit var eventFirebaseDatabase: EventFirebaseDatabase
    @Mock
    private lateinit var googleGamesService: GoogleGamesService


    @After
    fun tearDown() = deleteAllEventLevelEntities()

    @Test
    fun should_show_the_download_hint_when_there_are_no_events() {
        // GIVEN
        setupLoadEventEntities(emptyList())

        EventsPageRobot().launch()

        // THEN
        EventsPageRobot().isDownloadHintVisible()
    }

    @Test
    fun should_show_the_empty_hint_when_there_is_at_least_one_event() {
        // GIVEN
        setupLoadEventEntities(listOf(
                buildEventEntity(1, "=[+x,+±[+1,+2]]", "a")))

        setupLoadPlayerScores(3)
        setupLoadTopScores(1)

        EventsPageRobot().launch()

        // THEN
        EventsPageRobot().isEmptyHintVisible()
    }

    @Test
    fun should_navigate_to_the_event_page_when_a_event_is_clicked() {
        // GIVEN
        setupLoadEventEntities(listOf(
                buildEventEntity(1, "=[+x,+±[+1,+2]]", "a")))

        setupLoadPlayerScores(3)
        setupLoadTopScores(1)

        EventsPageRobot().launch()

        // WHEN
        EventsPageRobot().clickFirstEvent()

        // THEN
        EventPageRobot().isVisible()
    }

    @Test
    fun should_update_the_scores_of_a_event_when_its_played() {
        // GIVEN
        setupLoadEventEntities(listOf(
                buildEventEntity(1, "=[+x,+±[+1,+2]]", "a")))

        setupLoadPlayerScores(3, 1)
        setupLoadTopScores(3, 1)

        EventsPageRobot()
                .launch()
                .clickFirstEvent()

        EventPageRobot().clickPlayButton()

        // WHEN
        GamePageRobot()
                .linkSymbols("1", "2")
                .clickFinishedIcon()

        EventPageRobot().clickBackButton()

        // THEN
        EventsPageRobot()
                .isFirstEventPlayerScoreVisible("1")
                .isFirstEventTopScoreVisible("1")
    }

    private fun buildEventEntity(number: Int, exercise: String, scoreBoardKey: String) =
            EventFirebaseEntity().apply {
                this.number = number
                this.exercise = exercise
                this.scoreBoardKey = scoreBoardKey
            }

    private fun setupLoadEventEntities(entities: List<EventFirebaseEntity>) = runBlocking {
        whenever(eventFirebaseDatabase.loadEntities()) doReturn entities
    }

    private fun setupLoadPlayerScores(vararg scores: Int) = runBlocking {
        whenever(googleGamesService.loadPlayerScore(any())) doReturnConsecutively scores.toList()
    }

    private fun setupLoadTopScores(vararg scores: Int) = runBlocking {
        whenever(googleGamesService.loadTopScore(any())) doReturnConsecutively scores.toList()
    }

    private fun deleteAllEventLevelEntities() =
            daggerRule.testEventLevelRoomDao.deleteAllEntities()
}