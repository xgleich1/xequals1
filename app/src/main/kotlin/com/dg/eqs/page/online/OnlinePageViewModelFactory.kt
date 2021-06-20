package com.dg.eqs.page.online

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.dg.eqs.base.gamification.GoogleGamesService


class OnlinePageViewModelFactory(
        private val googleGamesService: GoogleGamesService
) : Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            OnlinePageViewModel(googleGamesService) as T
}