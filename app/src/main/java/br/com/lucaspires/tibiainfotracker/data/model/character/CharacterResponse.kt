package br.com.lucaspires.tibiainfotracker.data.model.character

import com.google.gson.annotations.SerializedName

data class CharacterResponse(

    @field:SerializedName("characters")
    val characters: Characters? = null,

    @field:SerializedName("information")
    val information: Information? = null
)