package br.com.lucaspires.tibiainfotracker.domain.usecase.support

import br.com.lucaspires.tibiainfotracker.domain.model.support.SupportModel
import br.com.lucaspires.tibiainfotracker.domain.repository.Repository
import io.reactivex.Single

internal class SupportUseCaseImp(private val repository: Repository) : SupportUseCase {
    override fun getSuppSites(): Single<List<SupportModel>> {
        return repository.getSuppSites()
    }
}