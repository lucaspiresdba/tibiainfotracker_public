package br.com.lucaspires.tibiainfotracker.domain.usecase.character

import br.com.lucaspires.tibiainfotracker.domain.model.character.CharacterInfoModel
import io.reactivex.Completable
import io.reactivex.Single

interface CharacterUseCase {
    fun getCharacter(name: String): Single<CharacterInfoModel>
    fun setFavorite(name: String): Completable
}
