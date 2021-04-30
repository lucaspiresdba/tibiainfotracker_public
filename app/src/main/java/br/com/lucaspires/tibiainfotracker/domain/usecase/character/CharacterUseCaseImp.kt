package br.com.lucaspires.tibiainfotracker.domain.usecase.character

import android.annotation.SuppressLint
import br.com.lucaspires.tibiainfotracker.domain.extension.toCharacterModel
import br.com.lucaspires.tibiainfotracker.domain.model.character.CharacterInfoModel
import br.com.lucaspires.tibiainfotracker.domain.repository.Repository
import io.reactivex.Single

@SuppressLint("DefaultLocale")
internal class CharacterUseCaseImp(
    private val repository: Repository,
) : CharacterUseCase {
    override fun getCharacter(name: String): Single<CharacterInfoModel> {
        return Single.zip(repository.isFavoritedChar(name.toLowerCase()),
            repository.getCharacter(name.replace(" ", "+"))
                .map { it.toCharacterModel() },
            { isFavorited: Boolean, model: CharacterInfoModel ->
                model.copy(isFavorite = isFavorited)
            })
    }

    override fun setFavorite(name: String) = repository.setFavorite(name.toLowerCase())
}