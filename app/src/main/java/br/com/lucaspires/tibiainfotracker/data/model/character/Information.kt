package br.com.lucaspires.tibiainfotracker.data.model.character

import com.google.gson.annotations.SerializedName

data class Information(

    @field:SerializedName("last_updated")
    val lastUpdated: String? = null,

    @field:SerializedName("api_version")
    val apiVersion: Int? = null,

    @field:SerializedName("execution_time")
    val executionTime: Double? = null,

    @field:SerializedName("timestamp")
    val timestamp: String? = null
)