package br.com.lucaspires.tibiainfotracker.data.model.character

import com.google.gson.annotations.SerializedName

data class House(

    @field:SerializedName("world")
    val world: String? = null,

    @field:SerializedName("houseid")
    val houseid: Int? = null,

    @field:SerializedName("town")
    val town: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("paid")
    val paid: String? = null
)