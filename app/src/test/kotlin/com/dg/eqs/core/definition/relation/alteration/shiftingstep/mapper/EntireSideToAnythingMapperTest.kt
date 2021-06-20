package com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper

import com.dg.eqs.core.definition.term.Term
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyZeroInteractions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class EntireSideToAnythingMapperTest {
    @Test
    fun `Should only alter the type of the source & target side`() {
        // GIVEN
        val sourceSide: Term = mock()
        val targetSide: Term = mock()

        // WHEN
        val (mappedSourceSide, mappedTargetSide) =
                map(sourceSide, targetSide)

        // THEN
        verifyZeroInteractions(sourceSide)
        verifyZeroInteractions(targetSide)

        assertThat(mappedSourceSide).isSameAs(sourceSide)
        assertThat(mappedTargetSide).isSameAs(targetSide)
    }

    private fun map(sourceSide: Term, targetSide: Term) =
            EntireSideToAnythingMapper.map(sourceSide, targetSide)
}