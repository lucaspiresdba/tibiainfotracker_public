package br.com.lucaspires.tibiainfotracker.data.model.character

import com.google.gson.annotations.SerializedName

data class OtherCharactersItem(

    @field:SerializedName("world")
    val world: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)