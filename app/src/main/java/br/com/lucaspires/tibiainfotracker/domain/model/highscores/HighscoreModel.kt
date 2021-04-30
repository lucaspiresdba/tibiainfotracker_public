package br.com.lucaspires.tibiainfotracker.domain.model.highscores

data class HighscoreModel(
    val vocation: String? = null,
    val world: String? = null,
    val category: String? = null,
    val rankList: List<HighscoreCharacterData>? = emptyList(),
)
