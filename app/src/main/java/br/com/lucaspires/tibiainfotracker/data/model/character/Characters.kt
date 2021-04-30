package br.com.lucaspires.tibiainfotracker.data.model.character

import com.google.gson.annotations.SerializedName

data class Characters(

    @field:SerializedName("achievements")
    val achievements: List<Any?>? = null,

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("other_characters")
    val otherCharacters: List<OtherCharactersItem?>? = null,

    @field:SerializedName("deaths")
    val deaths: List<DeathsItem?>? = null,

    @field:SerializedName("error")
    val error: String? = null
)