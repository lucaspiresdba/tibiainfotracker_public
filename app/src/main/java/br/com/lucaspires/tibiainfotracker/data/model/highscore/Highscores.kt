package br.com.lucaspires.tibiainfotracker.data.model.highscore

import com.google.gson.annotations.SerializedName

data class Highscores(

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("filters")
    val filters: Filters? = null
)