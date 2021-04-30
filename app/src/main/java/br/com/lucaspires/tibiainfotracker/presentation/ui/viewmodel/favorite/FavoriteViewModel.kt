package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.favorite

import androidx.lifecycle.ViewModel
import br.com.lucaspires.tibiainfotracker.domain.usecase.favorite.FavoriteUseCase

internal class FavoriteViewModel(private val useCase: FavoriteUseCase) : ViewModel() {
    fun observerFavorites() = useCase.getAllFavorites()
}