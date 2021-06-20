package com.dg.eqs.core.competition

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.gamification.GoogleGamesService
import com.dg.eqs.base.gamification.GoogleScoreBoardKey
import com.dg.eqs.core.competition.firebasedatabase.EventFirebaseDatabase
import com.dg.eqs.core.mapping.StringToOriginMapper
import com.dg.eqs.core.progression.Level.EventLevel
import com.dg.eqs.core.progression.LevelKey.EventLevelKey
import com.dg.eqs.core.progression.eventlevel.EventLevelDatabase


class EventRepository(
        private val eventFirebaseDatabase: EventFirebaseDatabase,
        private val stringToOriginMapper: StringToOriginMapper,
        private val googleGamesService: GoogleGamesService,
        private val eventLevelDatabase: EventLevelDatabase) {

    suspend fun loadEvents(): List<Event> {
        val entities = eventFirebaseDatabase.loadEntities()

        return entities.mapIndexed { entityIndex, entity ->
            val scoreBoardKey = GoogleScoreBoardKey(entity.scoreBoardKey)

            val eventLevelKey = EventLevelKey(entityIndex.toLong())

            val eventExercise = stringToOriginMapper
                    .mapToOrigin<AnyOrigin>(entity.exercise)

            val playerScore = googleGamesService
                    .loadPlayerScore(scoreBoardKey)

            val topScore = googleGamesService
                    .loadTopScore(scoreBoardKey)

            eventLevelDatabase.saveLevel(
                    EventLevel(
                            eventLevelKey,
                            eventExercise,
                            playerScore != 0,
                            playerScore,
                            topScore))

            Event(entity.number,
                  eventLevelKey,
                  scoreBoardKey)
        }
    }

    fun loadLevel(event: Event) =
            eventLevelDatabase
                    .loadLevel(event.levelKey)

    suspend fun refreshLevel(event: Event) {
        val level = loadLevel(event)

        val playerScore = googleGamesService
                .loadPlayerScore(event.scoreBoardKey)

        val topScore = googleGamesService
                .loadTopScore(event.scoreBoardKey)

        eventLevelDatabase.saveLevel(level.copy(
                gameSteps = playerScore,
                bestSteps = topScore))
    }

    suspend fun loadScoreBoard(event: Event) =
            googleGamesService
                    .loadScoreBoard(event.scoreBoardKey)

    suspend fun submitScore(event: Event) {
        val score = loadLevel(event).gameSteps

        googleGamesService
                .submitScore(event.scoreBoardKey, score)
    }
}