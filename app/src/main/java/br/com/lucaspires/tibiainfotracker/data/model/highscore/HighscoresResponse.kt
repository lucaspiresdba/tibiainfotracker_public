package br.com.lucaspires.tibiainfotracker.data.model.highscore

import br.com.lucaspires.tibiainfotracker.data.model.character.Information
import com.google.gson.annotations.SerializedName

data class HighscoresResponse(

    @field:SerializedName("information")
    val information: Information? = null,

    @field:SerializedName("highscores")
    val highscores: Highscores? = null
)