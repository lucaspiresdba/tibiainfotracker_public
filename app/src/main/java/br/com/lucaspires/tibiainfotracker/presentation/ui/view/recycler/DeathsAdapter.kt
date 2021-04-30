package br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.lucaspires.tibiainfotracker.databinding.RecyclerViewItemDeathBinding
import br.com.lucaspires.tibiainfotracker.domain.model.character.DeathModel

internal class DeathsAdapter : BaseAdapter<DeathModel, DeathViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeathViewHolder {
        return DeathViewHolder(
            RecyclerViewItemDeathBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DeathViewHolder, position: Int) {
        val death = listOfItems[position]
        with(holder.binding) {
            textViewLevelDeath.setText(death.level.toString())
            textViewReasonDeath.setText(death.reason)
        }
    }
}