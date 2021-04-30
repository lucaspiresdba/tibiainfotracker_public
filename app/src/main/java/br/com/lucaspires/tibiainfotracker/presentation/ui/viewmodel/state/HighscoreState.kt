package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.state

import br.com.lucaspires.tibiainfotracker.domain.model.highscores.HighscoreModel

data class HighscoreState(
    val highscores: HighscoreModel? = null,
    val worldsList: List<String>? = emptyList()
)
