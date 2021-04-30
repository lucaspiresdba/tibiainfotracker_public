package br.com.lucaspires.tibiainfotracker.domain.usecase.favorite

import androidx.lifecycle.LiveData

interface FavoriteUseCase {
    fun getAllFavorites(): LiveData<List<String>>
}
