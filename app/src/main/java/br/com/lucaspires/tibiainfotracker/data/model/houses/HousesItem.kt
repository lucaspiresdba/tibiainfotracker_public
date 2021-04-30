package br.com.lucaspires.tibiainfotracker.data.model.houses

import com.google.gson.annotations.SerializedName

data class HousesItem(

    @field:SerializedName("houseid")
    val houseid: Int? = null,

    @field:SerializedName("size")
    val size: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("rent")
    val rent: Int? = null,

    @field:SerializedName("status")
    val status: String? = null
)