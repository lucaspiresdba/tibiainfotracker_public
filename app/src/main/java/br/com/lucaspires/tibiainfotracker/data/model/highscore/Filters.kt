package br.com.lucaspires.tibiainfotracker.data.model.highscore

import com.google.gson.annotations.SerializedName

data class Filters(

    @field:SerializedName("vocation")
    val vocation: String? = null,

    @field:SerializedName("world")
    val world: String? = null,

    @field:SerializedName("category")
    val category: String? = null
)