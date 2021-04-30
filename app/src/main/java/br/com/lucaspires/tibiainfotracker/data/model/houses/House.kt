package br.com.lucaspires.tibiainfotracker.data.model.houses

import com.google.gson.annotations.SerializedName

data class House(

    @field:SerializedName("img")
    val img: String,

    @field:SerializedName("houseid")
    val houseid: Int,

    @field:SerializedName("world")
    val world: String,

    @field:SerializedName("size")
    val size: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("beds")
    val beds: Int,

    @field:SerializedName("rent")
    val rent: Int,

    @field:SerializedName("status")
    val status: Status
)