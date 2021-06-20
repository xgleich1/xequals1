package com.dg.eqs.core.orchestration.singlelevelgame

import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.core.orchestration.singlelevelgame.info.SingleLevelGameHowToInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SingleLevelGameInfoRepositoryTest {
    @InjectMocks
    private lateinit var repository: SingleLevelGameInfoRepository

    @Mock
    private lateinit var level: AnyLevel


    @Test
    fun `Should load the how to info for all level`() {
        // WHEN
        val info = repository.loadInitialInfo(level)

        // THEN
        assertThat(info).isEqualTo(SingleLevelGameHowToInfo)
    }
}