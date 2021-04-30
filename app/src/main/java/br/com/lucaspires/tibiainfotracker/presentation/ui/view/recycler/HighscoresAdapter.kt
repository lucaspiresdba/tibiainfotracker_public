package br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.lucaspires.tibiainfotracker.databinding.RecyclerViewItemHighscoreBinding
import br.com.lucaspires.tibiainfotracker.domain.model.highscores.HighscoreCharacterData

internal class HighscoresAdapter : BaseAdapter<HighscoreCharacterData, HighscoresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighscoresViewHolder {
        return HighscoresViewHolder(
            RecyclerViewItemHighscoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HighscoresViewHolder, position: Int) {
        val highscore = listOfItems[position]
        with(holder.binding) {
            textViewCharName.setMainText(highscore.name.orEmpty())
            textViewCharRank.setMainText(highscore.rank.toString())
            textViewCharWorld.setMainText(highscore.world.orEmpty())
            textViewCharVoc.setMainText(highscore.vocation.orEmpty())
            textViewCharExp.setMainText(highscore.points.toString())
            textViewCharScorePoints.setMainText(highscore.value.toString())
            textViewCharLevel.setMainText(highscore.level)
        }
    }
}