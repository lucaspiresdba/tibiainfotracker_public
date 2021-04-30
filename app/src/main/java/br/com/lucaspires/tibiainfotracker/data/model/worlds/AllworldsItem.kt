package br.com.lucaspires.tibiainfotracker.data.model.worlds

import com.google.gson.annotations.SerializedName

data class AllworldsItem(

    @field:SerializedName("worldtype")
    val worldtype: String? = null,

    @field:SerializedName("additional")
    val additional: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("online")
    val online: Int? = null,

    @field:SerializedName("location")
    val location: String? = null
)