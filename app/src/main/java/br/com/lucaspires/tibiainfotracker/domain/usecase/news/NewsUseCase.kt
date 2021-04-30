package br.com.lucaspires.tibiainfotracker.domain.usecase.news

import br.com.lucaspires.tibiainfotracker.domain.model.news.NewsModel
import io.reactivex.Single

interface NewsUseCase {
    fun getNews(): Single<List<NewsModel>>
}
