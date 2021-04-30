package br.com.lucaspires.tibiainfotracker.domain.usecase.news

import br.com.lucaspires.tibiainfotracker.domain.extension.toNewsModel
import br.com.lucaspires.tibiainfotracker.domain.model.news.NewsModel
import br.com.lucaspires.tibiainfotracker.domain.repository.Repository
import io.reactivex.Single

internal class NewsUseCaseImp(private val repository: Repository) : NewsUseCase {
    override fun getNews(): Single<List<NewsModel>> = repository.getNews()
        .map { it.newslist?.data.toNewsModel() }
}