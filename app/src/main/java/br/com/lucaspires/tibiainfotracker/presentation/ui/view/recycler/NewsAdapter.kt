package br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.lucaspires.tibiainfotracker.databinding.RecyclerViewItemNewsBinding
import br.com.lucaspires.tibiainfotracker.domain.model.news.NewsModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.activity.WebView

internal class NewsAdapter : BaseAdapter<NewsModel, NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            RecyclerViewItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = listOfItems[position]
        with(holder.binding) {
            textViewNews.text = news.title
            news.url?.let { url ->
                root.setOnClickListener {
                    root.context.startActivity(
                        WebView.callActivity(
                            root.context,
                            url
                        )
                    )
                }
            }
        }
    }
}