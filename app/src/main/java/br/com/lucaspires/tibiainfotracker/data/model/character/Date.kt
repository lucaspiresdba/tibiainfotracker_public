package br.com.lucaspires.tibiainfotracker.data.model.character

import com.google.gson.annotations.SerializedName

data class Date(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("timezone")
    val timezone: String? = null,

    @field:SerializedName("timezone_type")
    val timezoneType: Int? = null
)