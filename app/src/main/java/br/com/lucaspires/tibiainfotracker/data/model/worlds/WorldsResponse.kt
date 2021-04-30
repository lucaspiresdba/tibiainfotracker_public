package br.com.lucaspires.tibiainfotracker.data.model.worlds

import br.com.lucaspires.tibiainfotracker.data.model.character.Information
import com.google.gson.annotations.SerializedName

data class WorldsResponse(

    @field:SerializedName("worlds")
    val worlds: Worlds? = null,

    @field:SerializedName("information")
    val information: Information? = null
)