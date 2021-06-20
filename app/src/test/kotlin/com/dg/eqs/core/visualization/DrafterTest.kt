package com.dg.eqs.core.visualization

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.mapping.OriginToDraftMapper
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DrafterTest {
    @Mock
    private lateinit var mapper: OriginToDraftMapper
    @Mock
    private lateinit var pencil: Pencil

    private lateinit var drafter: Drafter

    @Mock
    private lateinit var origin: AnyOrigin
    @Mock
    private lateinit var draft: AnyDraft


    @Before
    fun setUp() {
        drafter = createDrafter()
    }

    @Test
    fun `Should map and draft the provided origin`() {
        // GIVEN
        whenever(mapper.mapToDraft(origin)) doReturn draft

        whenever(draft.draft(pencil)) doReturn draft

        // THEN
        assertThat(drafter.draft(origin)).isEqualTo(draft)
    }

    private fun createDrafter() = object : Drafter(mapper, pencil) {}
}