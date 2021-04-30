package br.com.lucaspires.tibiainfotracker.domain.usecase.support

import br.com.lucaspires.tibiainfotracker.domain.model.support.SupportModel
import io.reactivex.Single

interface SupportUseCase {
    fun getSuppSites(): Single<List<SupportModel>>
}
