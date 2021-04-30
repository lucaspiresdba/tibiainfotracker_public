package br.com.lucaspires.tibiainfotracker.data.model.news

import br.com.lucaspires.tibiainfotracker.data.model.character.Date
import com.google.gson.annotations.SerializedName

data class DataItem(

    @field:SerializedName("news")
    val news: String? = null,

    @field:SerializedName("date")
    val date: Date? = null,

    @field:SerializedName("apiurl")
    val apiurl: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("tibiaurl")
    val tibiaurl: String? = null
)