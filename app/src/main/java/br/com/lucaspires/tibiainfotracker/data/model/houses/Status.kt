package br.com.lucaspires.tibiainfotracker.data.model.houses

import com.google.gson.annotations.SerializedName

data class Status(
    @field:SerializedName("owner_now")
    val ownerNow: String? = null,
)