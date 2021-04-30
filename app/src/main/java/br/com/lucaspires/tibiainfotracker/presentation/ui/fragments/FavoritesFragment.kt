package br.com.lucaspires.tibiainfotracker.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.com.lucaspires.tibiainfotracker.R
import br.com.lucaspires.tibiainfotracker.databinding.FragmentFavoritesBinding
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.hide
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.onObserver
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.setToolbarTitle
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.show
import br.com.lucaspires.tibiainfotracker.presentation.ui.view.custom.TibiaFeedback
import br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler.FavoriteAdapter
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private val adapter by lazy { FavoriteAdapter() }

    private lateinit var binding: FragmentFavoritesBinding

    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservables()
    }

    private fun setupView() {
        setToolbarTitle(R.string.toolbar_title_favorite)
        with(binding) {
            recyclerViewFavorites.adapter = adapter
            feedbackFavorites.hide()
        }

    }

    private fun setupObservables() {
        with(binding) {
            onObserver(viewModel.observerFavorites()) {
                if (it.isEmpty()) {
                    with(feedbackFavorites) {
                        setFeedback(TibiaFeedback.NO_FAVORITE) {
                            root.findNavController().navigate(
                                FavoritesFragmentDirections
                                    .actionNavigationFavoritesToNavigationCharacter()
                            )
                        }
                        show()
                    }
                } else {
                    adapter.addItems(it)
                    recyclerViewFavorites.show()
                }
            }
        }
    }
}