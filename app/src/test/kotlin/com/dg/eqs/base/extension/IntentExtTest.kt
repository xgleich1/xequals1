package com.dg.eqs.base.extension

import android.content.Intent
import android.os.Parcelable
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlinx.android.parcel.Parcelize
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class IntentExtTest {
    @Test
    fun `Should provide a shortcut to retrieve a non null parcelable extra`() {
        // GIVEN
        val intent: Intent = mock {
            on { getParcelableExtra<TestParcelable>("test") } doReturn TestParcelable
        }

        // WHEN
        val extra = intent.getParcelableExtraOrThrow<TestParcelable>("test")

        // THEN
        assertThat(extra).isEqualTo(TestParcelable)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw an exception when a parcelable extra isn't available`() {
        // GIVEN
        val intent: Intent = mock {
            on { getParcelableExtra<TestParcelable>("test") } doReturn null
        }

        // WHEN
        intent.getParcelableExtraOrThrow<TestParcelable>("test")
    }

    @Parcelize
    private object TestParcelable : Parcelable
}