package com.dg.eqs.page.start

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dg.eqs.base.observation.LiveDataCommandObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify


@RunWith(MockitoJUnitRunner::class)
class StartPageViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var viewModel: StartPageViewModel

    @Mock
    private lateinit var navigateToOfflinePageAction: () -> Unit
    @Mock
    private lateinit var navigateToOnlinePageAction: () -> Unit
    @Mock
    private lateinit var navigateToInfoPageAction: () -> Unit
    @Mock
    private lateinit var navigateToAssistPageAction: () -> Unit
    @Mock
    private lateinit var navigateToFeedbackPageAction: () -> Unit


    @Before
    fun setUp() = observeViewModel()

    @Test
    fun `Should navigate to the offline page when the offline button is clicked`() {
        // WHEN
        viewModel.onOfflineButtonClicked()

        // THEN
        verify(navigateToOfflinePageAction).invoke()
    }

    @Test
    fun `Should navigate to the online page when the online button is clicked`() {
        // WHEN
        viewModel.onOnlineButtonClicked()

        // THEN
        verify(navigateToOnlinePageAction).invoke()
    }

    @Test
    fun `Should navigate to the info page when the info button is clicked`() {
        // WHEN
        viewModel.onInfoButtonClicked()

        // THEN
        verify(navigateToInfoPageAction).invoke()
    }

    @Test
    fun `Should navigate to the assist page when the assist button is clicked`() {
        // WHEN
        viewModel.onAssistButtonClicked()

        // THEN
        verify(navigateToAssistPageAction).invoke()
    }

    @Test
    fun `Should navigate to the feedback page when the feedback button is clicked`() {
        // WHEN
        viewModel.onFeedbackButtonClicked()

        // THEN
        verify(navigateToFeedbackPageAction).invoke()
    }

    private fun observeViewModel() {
        viewModel.navigateToOfflinePage.observeForever(
                LiveDataCommandObserver(navigateToOfflinePageAction))
        viewModel.navigateToOnlinePage.observeForever(
                LiveDataCommandObserver(navigateToOnlinePageAction))
        viewModel.navigateToInfoPage.observeForever(
                LiveDataCommandObserver(navigateToInfoPageAction))
        viewModel.navigateToAssistPage.observeForever(
                LiveDataCommandObserver(navigateToAssistPageAction))
        viewModel.navigateToFeedbackPage.observeForever(
                LiveDataCommandObserver(navigateToFeedbackPageAction))
    }
}