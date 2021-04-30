package br.com.lucaspires.tibiainfotracker.data.model.worlds

import com.google.gson.annotations.SerializedName

data class Worlds(

    @field:SerializedName("allworlds")
    val allworlds: List<AllworldsItem?>? = null,

    @field:SerializedName("online")
    val online: Int? = null
)