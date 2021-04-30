package br.com.lucaspires.tibiainfotracker.data.model.character

import com.google.gson.annotations.SerializedName

data class DeathsItem(

    @field:SerializedName("date")
    val date: Date? = null,

    @field:SerializedName("reason")
    val reason: String? = null,

    @field:SerializedName("involved")
    val involved: List<Any?>? = null,

    @field:SerializedName("level")
    val level: Int? = null
)