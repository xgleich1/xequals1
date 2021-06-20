package com.dg.eqs.core.orchestration.endlesslevelgame

import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.base.persistence.offline.OfflinePersistence
import com.dg.eqs.core.orchestration.endlesslevelgame.info.EndlessLevelGameAppReviewInfo
import com.dg.eqs.core.orchestration.endlesslevelgame.info.EndlessLevelGameAppReviewInfoLoadedKey
import com.dg.eqs.core.orchestration.endlesslevelgame.info.EndlessLevelGameHowToInfo
import com.dg.eqs.core.orchestration.endlesslevelgame.info.EndlessLevelGameHowToInfoMadeVitalKey
import com.dg.eqs.core.progression.Level.GeneratedLevel
import org.mockito.kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EndlessLevelGameInfoRepositoryTest {
    @Mock
    private lateinit var offlinePersistence: OfflinePersistence
    @InjectMocks
    private lateinit var repository: EndlessLevelGameInfoRepository

    @Mock
    private lateinit var level: AnyLevel
    @Mock
    private lateinit var generatedLevel: GeneratedLevel


    @Test
    fun `Should load the how to info for all level & make it vital once`() {
        // GIVEN
        whenever(offlinePersistence.loadBoolean(EndlessLevelGameHowToInfoMadeVitalKey, false))
                .thenReturn(false, true)

        // WHEN
        val firstInfo = repository.loadInitialInfo(level)
        val secondInfo = repository.loadInitialInfo(level)

        // THEN
        assertThat(firstInfo).isEqualTo(EndlessLevelGameHowToInfo(isVital = true))
        assertThat(secondInfo).isEqualTo(EndlessLevelGameHowToInfo(isVital = false))
    }

    @Test
    fun `Should persist when the how to info is made vital to only do it once`() {
        // GIVEN
        whenever(offlinePersistence.loadBoolean(EndlessLevelGameHowToInfoMadeVitalKey, false))
                .thenReturn(false)

        // WHEN
        repository.loadInitialInfo(level)

        // THEN
        verify(offlinePersistence).saveBoolean(EndlessLevelGameHowToInfoMadeVitalKey, true)
    }

    @Test
    fun `Should not persist anything when the how to info was already vital`() {
        // GIVEN
        whenever(offlinePersistence.loadBoolean(EndlessLevelGameHowToInfoMadeVitalKey, false))
                .thenReturn(true)

        // WHEN
        repository.loadInitialInfo(level)

        // THEN
        verify(offlinePersistence, never())
                .saveBoolean(eq(EndlessLevelGameHowToInfoMadeVitalKey), any())
    }

    @Test
    fun `Should load the app review info for a generated level & do it only once`() {
        // GIVEN
        whenever(offlinePersistence.loadBoolean(EndlessLevelGameAppReviewInfoLoadedKey, false))
                .thenReturn(false, true)

        // WHEN
        val firstInfo = repository.loadInitialInfo(generatedLevel)
        val secondInfo = repository.loadInitialInfo(generatedLevel)

        // THEN
        assertThat(firstInfo).isEqualTo(EndlessLevelGameAppReviewInfo())
        assertThat(secondInfo).isEqualTo(EndlessLevelGameHowToInfo(true))
    }

    @Test
    fun `Should persist when the app review info is loaded to only do it once`() {
        // GIVEN
        whenever(offlinePersistence.loadBoolean(EndlessLevelGameAppReviewInfoLoadedKey, false))
                .thenReturn(false)

        // WHEN
        repository.loadInitialInfo(generatedLevel)

        // THEN
        verify(offlinePersistence).saveBoolean(EndlessLevelGameAppReviewInfoLoadedKey, true)
    }

    @Test
    fun `Should not persist anything when the app review info was already loaded`() {
        // GIVEN
        whenever(offlinePersistence.loadBoolean(EndlessLevelGameAppReviewInfoLoadedKey, false))
                .thenReturn(true)

        // WHEN
        repository.loadInitialInfo(generatedLevel)

        // THEN
        verify(offlinePersistence, never())
                .saveBoolean(eq(EndlessLevelGameAppReviewInfoLoadedKey), any())
    }
}