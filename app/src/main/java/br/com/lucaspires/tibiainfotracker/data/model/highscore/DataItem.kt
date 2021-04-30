package br.com.lucaspires.tibiainfotracker.data.model.highscore

import com.google.gson.annotations.SerializedName

data class DataItem(

    @field:SerializedName("vocation")
    val vocation: String? = null,

    @field:SerializedName("world")
    val world: String? = null,

    @field:SerializedName("level")
    val level: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("rank")
    val rank: Int? = null,

    @field:SerializedName("points")
    val points: Long? = null,

    @field:SerializedName("value")
    val value: Int? = null
)