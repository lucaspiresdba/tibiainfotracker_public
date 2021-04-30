package br.com.lucaspires.tibiainfotracker.data.model.character

import com.google.gson.annotations.SerializedName

data class Guild(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("rank")
    val rank: String? = null
)