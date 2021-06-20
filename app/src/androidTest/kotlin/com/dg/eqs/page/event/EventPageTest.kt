package com.dg.eqs.page.event

import com.dg.eqs.base.gamification.GoogleGamesService
import com.dg.eqs.base.gamification.GoogleScoreBoardKey
import com.dg.eqs.core.competition.Event
import com.dg.eqs.core.progression.LevelKey.EventLevelKey
import com.dg.eqs.core.progression.eventlevel.roomdatabase.EventLevelRoomEntity
import com.dg.eqs.page.game.GamePageRobot
import com.dg.eqs.util.dagger.TestDaggerMockRule
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock


class EventPageTest {
    @get:Rule
    val testDaggerMockRule = TestDaggerMockRule()

    @Mock
    private lateinit var googleGamesService: GoogleGamesService


    @Before
    fun setUp() {
        saveEventLevelEntity()
        setupScoresAvailable()
    }

    @After
    fun tearDown() = deleteAllEventLevelEntities()

    @Test
    fun should_navigate_to_the_game_page_when_the_play_button_is_clicked() {
        // GIVEN
        EventPageRobot().launch(EVENT)

        // WHEN
        EventPageRobot().clickPlayButton()

        // THEN
        GamePageRobot().isVisible()
    }

    private fun saveEventLevelEntity() {
        val entity = EventLevelRoomEntity(
                EVENT.levelKey.rawKey, "+±[+1,+2]", true, 1, 1)

        testDaggerMockRule.testEventLevelRoomDao.saveEntity(entity)
    }

    private fun setupScoresAvailable() = runBlocking {
        whenever(googleGamesService.loadPlayerScore(any())) doReturn 1
        whenever(googleGamesService.loadTopScore(any())) doReturn 1
    }

    private fun deleteAllEventLevelEntities() =
            testDaggerMockRule.testEventLevelRoomDao.deleteAllEntities()

    private companion object {
        private val EVENT = Event(1, EventLevelKey(0), GoogleScoreBoardKey("a"))
    }
}