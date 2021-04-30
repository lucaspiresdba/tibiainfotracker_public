package br.com.lucaspires.tibiainfotracker.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.lucaspires.tibiainfotracker.R
import br.com.lucaspires.tibiainfotracker.databinding.FragmentNewsBinding
import br.com.lucaspires.tibiainfotracker.domain.model.news.NewsModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.*
import br.com.lucaspires.tibiainfotracker.presentation.ui.view.custom.TibiaFeedback
import br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler.NewsAdapter
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.news.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class NewsFragment : Fragment() {

    private val adapter by lazy { NewsAdapter() }
    private val viewModel: NewsViewModel by viewModel()
    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables()
        setupView()
        viewModel.getNews()
    }

    private fun setupView() {
        setToolbarTitle(R.string.toolbar_title_news)
        with(binding) {
            recyclerViewNews.adapter = adapter
            swipeNews.run {
                setOnRefreshListener {
                    viewModel.getNews()
                    recyclerViewNews.hide()
                    swipeNews.hide()
                }
            }
        }
    }

    private fun setupObservables() {
        onObserver(viewModel.observableAction) {
            when (it) {
                TibiaAction.GenericError -> setFeedback(TibiaFeedback.ERROR)
                TibiaAction.NoInternet -> setFeedback(TibiaFeedback.CONNECTION)
                TibiaAction.NotFoundData -> setFeedback(TibiaFeedback.NOT_FOUND)
            }
        }

        onObserver(viewModel.observableStatus) {
            configureNews(it)
        }

        onObserver(viewModel.observableLoading) {
            configureLoading(it)
        }
    }

    private fun configureNews(list: List<NewsModel>) {
        with(binding) {
            swipeNews.isRefreshing = false
            adapter.addItems(list)
            recyclerViewNews.show()
            swipeNews.show()
        }
    }

    private fun setFeedback(feedbackError: TibiaFeedback) {
        with(binding) {
            swipeNews.isRefreshing = false
            customFeedbackNews.show()
            customFeedbackNews.setFeedback(feedbackError) {
                viewModel.getNews()
            }
        }
    }

    private fun configureLoading(loading: Boolean) {
        binding.animationViewNews.showAnimation(loading)
    }
}