package br.com.lucaspires.tibiainfotracker.data.model.houses

import com.google.gson.annotations.SerializedName

data class Houses(

    @field:SerializedName("world")
    val world: String? = null,

    @field:SerializedName("town")
    val town: String? = null,

    @field:SerializedName("houses")
    val houses: List<HousesItem?>? = null,

    @field:SerializedName("type")
    val type: String? = null
)