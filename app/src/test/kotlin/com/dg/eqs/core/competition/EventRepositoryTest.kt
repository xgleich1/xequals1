package com.dg.eqs.core.competition

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.gamification.GoogleGamesService
import com.dg.eqs.base.gamification.GoogleScoreBoardKey
import com.dg.eqs.base.gamification.result.ScoreBoardResult
import com.dg.eqs.core.competition.firebasedatabase.EventFirebaseDatabase
import com.dg.eqs.core.competition.firebasedatabase.EventFirebaseEntity
import com.dg.eqs.core.mapping.StringToOriginMapper
import com.dg.eqs.core.progression.Level.EventLevel
import com.dg.eqs.core.progression.LevelKey.EventLevelKey
import com.dg.eqs.core.progression.eventlevel.EventLevelDatabase
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EventRepositoryTest {
    @Mock
    private lateinit var eventFirebaseDatabase: EventFirebaseDatabase
    @Mock
    private lateinit var stringToOriginMapper: StringToOriginMapper
    @Mock
    private lateinit var googleGamesService: GoogleGamesService
    @Mock
    private lateinit var eventLevelDatabase: EventLevelDatabase
    @InjectMocks
    private lateinit var eventRepository: EventRepository

    @Mock
    private lateinit var levelExerciseA: AnyOrigin
    @Mock
    private lateinit var levelExerciseB: AnyOrigin

    @Mock
    private lateinit var event: Event
    @Mock
    private lateinit var eventLevelKey: EventLevelKey
    @Mock
    private lateinit var eventScoreBoardKey: GoogleScoreBoardKey
    @Mock
    private lateinit var eventScoreBoardResult: ScoreBoardResult


    @Before
    fun setUp() = setupEventKeysAvailable()

    @Test
    fun `Should return the mapped events from the remote database`() = runBlocking<Unit> {
        // GIVEN
        setupLoadEventEntities(
                buildEventEntity(2, "=[+±[+x,+1],+2]", "a"),
                buildEventEntity(3, "=[+±[+x,+3],+4]", "b"))

        setupMapStringToOrigin("=[+±[+x,+1],+2]" to levelExerciseA)
        setupLoadPlayerScore(GoogleScoreBoardKey("a"), score = 4)
        setupLoadTopScore(GoogleScoreBoardKey("a"), score = 2)

        setupMapStringToOrigin("=[+±[+x,+3],+4]" to levelExerciseB)
        setupLoadPlayerScore(GoogleScoreBoardKey("b"), score = 4)
        setupLoadTopScore(GoogleScoreBoardKey("b"), score = 2)

        // WHEN
        val events = eventRepository.loadEvents()

        // THEN
        assertThat(events).containsExactly(
                Event(2, EventLevelKey(0), GoogleScoreBoardKey("a")),
                Event(3, EventLevelKey(1), GoogleScoreBoardKey("b")))
    }

    @Test
    fun `Should save a level for each event from the remote database`() = runBlocking {
        // GIVEN
        setupLoadEventEntities(
                buildEventEntity(2, "=[+±[+x,+1],+2]", "a"),
                buildEventEntity(3, "=[+±[+x,+3],+4]", "b"))

        setupMapStringToOrigin("=[+±[+x,+1],+2]" to levelExerciseA)
        setupLoadPlayerScore(GoogleScoreBoardKey("a"), score = 4)
        setupLoadTopScore(GoogleScoreBoardKey("a"), score = 2)

        setupMapStringToOrigin("=[+±[+x,+3],+4]" to levelExerciseB)
        setupLoadPlayerScore(GoogleScoreBoardKey("b"), score = 8)
        setupLoadTopScore(GoogleScoreBoardKey("b"), score = 6)

        // WHEN
        eventRepository.loadEvents()

        // THEN
        verify(eventLevelDatabase).saveLevel(
                EventLevel(EventLevelKey(0), levelExerciseA, true, 4, 2))

        verify(eventLevelDatabase).saveLevel(
                EventLevel(EventLevelKey(1), levelExerciseB, true, 8, 6))
    }

    @Test
    fun `Should load the level for a event`() {
        // GIVEN
        setupLoadEventLevel(
                EventLevel(eventLevelKey, levelExerciseA, true, 4, 2))

        // WHEN
        val level = eventRepository.loadLevel(event)

        // THEN
        assertThat(level).isEqualTo(
                EventLevel(eventLevelKey, levelExerciseA, true, 4, 2))
    }

    @Test
    fun `Should refresh the level for a event`() = runBlocking {
        // GIVEN
        setupLoadEventLevel(
                EventLevel(eventLevelKey, levelExerciseA, true, 8, 6))

        setupLoadPlayerScore(eventScoreBoardKey, score = 4)
        setupLoadTopScore(eventScoreBoardKey, score = 2)

        // WHEN
        eventRepository.refreshLevel(event)

        // THEN
        verify(eventLevelDatabase).saveLevel(
                EventLevel(eventLevelKey, levelExerciseA, true, 4, 2))
    }

    @Test
    fun `Should load the score board for a event`() = runBlocking<Unit> {
        // GIVEN
        setupLoadScoreBoard(eventScoreBoardKey, eventScoreBoardResult)

        // WHEN
        val scoreBoardResult = eventRepository.loadScoreBoard(event)

        // THEN
        assertThat(scoreBoardResult).isEqualTo(eventScoreBoardResult)
    }

    @Test
    fun `Should submit the score for a event`() = runBlocking<Unit> {
        // GIVEN
        setupLoadEventLevel(
                EventLevel(eventLevelKey, levelExerciseA, true, 8, 6))

        // WHEN
        eventRepository.submitScore(event)

        // THEN
        verify(googleGamesService).submitScore(eventScoreBoardKey, 8)
    }

    private fun buildEventEntity(
            number: Int, exercise: String, scoreBoardKey: String
    ): EventFirebaseEntity {

        return EventFirebaseEntity().apply {
            this.number = number
            this.exercise = exercise
            this.scoreBoardKey = scoreBoardKey
        }
    }

    private fun setupEventKeysAvailable() {
        whenever(event.levelKey) doReturn eventLevelKey
        whenever(event.scoreBoardKey) doReturn eventScoreBoardKey
    }

    private suspend fun setupLoadEventEntities(vararg entities: EventFirebaseEntity) =
            whenever(eventFirebaseDatabase.loadEntities()) doReturn entities.toList()

    private fun setupMapStringToOrigin(mapping: Pair<String, AnyOrigin>) =
            whenever(stringToOriginMapper.mapToOrigin<AnyOrigin>(mapping.first)) doReturn mapping.second

    private suspend fun setupLoadPlayerScore(key: GoogleScoreBoardKey, score: Int) =
            whenever(googleGamesService.loadPlayerScore(key)) doReturn score

    private suspend fun setupLoadTopScore(key: GoogleScoreBoardKey, score: Int) =
            whenever(googleGamesService.loadTopScore(key)) doReturn score

    private suspend fun setupLoadScoreBoard(key: GoogleScoreBoardKey, result: ScoreBoardResult) =
            whenever(googleGamesService.loadScoreBoard(key)) doReturn result

    private fun setupLoadEventLevel(eventLevel: EventLevel) =
            whenever(eventLevelDatabase.loadLevel(eventLevelKey)) doReturn eventLevel
}