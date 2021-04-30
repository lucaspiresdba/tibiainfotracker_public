package br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import br.com.lucaspires.tibiainfotracker.databinding.RecyclerViewFavoritesBinding
import br.com.lucaspires.tibiainfotracker.presentation.ui.fragments.FavoritesFragmentDirections

internal class FavoriteAdapter : BaseAdapter<String, FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            RecyclerViewFavoritesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        with(holder.binding) {
            textViewFavoiteChar.text = listOfItems[position]
            root.setOnClickListener {
                it.findNavController().navigate(
                    FavoritesFragmentDirections
                        .actionNavigationFavoritesToNavigationCharacter(listOfItems[position])
                )
            }
        }
    }
}