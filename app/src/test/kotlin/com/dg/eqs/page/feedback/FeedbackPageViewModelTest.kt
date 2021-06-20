package com.dg.eqs.page.feedback

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
class FeedbackPageViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var viewModel: FeedbackPageViewModel

    @Mock
    private lateinit var navigateBackAction: () -> Unit


    @Before
    fun setUp() = observeViewModel()

    @Test
    fun `Should navigate back when back is pressed`() {
        // WHEN
        viewModel.onBackPressed()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should navigate back when the back button is clicked`() {
        // WHEN
        viewModel.onBackButtonClicked()

        // THEN
        verify(navigateBackAction).invoke()
    }

    private fun observeViewModel() {
        viewModel.navigateBack.observeForever(
                LiveDataCommandObserver(navigateBackAction))
    }
}