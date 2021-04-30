package br.com.lucaspires.tibiainfotracker.data.model.houses

import br.com.lucaspires.tibiainfotracker.data.model.character.Information
import com.google.gson.annotations.SerializedName

data class HouseDetailsResponse(

    @field:SerializedName("information")
    val information: Information? = null,

    @field:SerializedName("house")
    val house: House? = null
)