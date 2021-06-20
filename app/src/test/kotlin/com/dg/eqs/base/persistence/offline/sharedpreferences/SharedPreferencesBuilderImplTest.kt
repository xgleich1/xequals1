package com.dg.eqs.base.persistence.offline.sharedpreferences

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*


@RunWith(MockitoJUnitRunner::class)
class SharedPreferencesBuilderImplTest {
    @Mock
    private lateinit var application: Application
    @InjectMocks
    private lateinit var sharedPreferencesBuilder: SharedPreferencesBuilderImpl

    @Mock
    private lateinit var sharedPreferences: SharedPreferences


    @Before
    fun setUp() {
        whenever(application.getSharedPreferences(
                any(), any())) doReturn sharedPreferences
    }

    @Test
    fun `Should build the shared preferences of the application`() {
        // WHEN
        val buildSharedPreferences = sharedPreferencesBuilder.build()

        // THEN
        assertThat(buildSharedPreferences).isEqualTo(sharedPreferences)
    }

    @Test
    fun `Should use new shared preferences for eqs version two`() {
        // WHEN
        sharedPreferencesBuilder.build()

        // THEN
        verify(application).getSharedPreferences(eq("eqs_v2_shared_preferences"), any())
    }

    @Test
    fun `Should restrict the shared preferences to the application`() {
        // WHEN
        sharedPreferencesBuilder.build()

        // THEN
        verify(application).getSharedPreferences(any(), eq(MODE_PRIVATE))
    }
}