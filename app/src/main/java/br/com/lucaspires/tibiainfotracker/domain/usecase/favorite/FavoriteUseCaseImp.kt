package br.com.lucaspires.tibiainfotracker.domain.usecase.favorite

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import br.com.lucaspires.tibiainfotracker.domain.extension.capitalizeWords
import br.com.lucaspires.tibiainfotracker.domain.repository.Repository

@SuppressLint("DefaultLocale")
internal class FavoriteUseCaseImp(private val repository: Repository) : FavoriteUseCase {
    override fun getAllFavorites(): LiveData<List<String>> =
        Transformations.map(repository.getAllFavorites()) { list ->
            list.map { it.capitalizeWords() }
        }
}