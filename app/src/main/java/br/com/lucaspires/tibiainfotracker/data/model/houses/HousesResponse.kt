package br.com.lucaspires.tibiainfotracker.data.model.houses

import br.com.lucaspires.tibiainfotracker.data.model.character.Information
import com.google.gson.annotations.SerializedName

data class HousesResponse(

    @field:SerializedName("houses")
    val houses: Houses? = null,

    @field:SerializedName("information")
    val information: Information? = null
)