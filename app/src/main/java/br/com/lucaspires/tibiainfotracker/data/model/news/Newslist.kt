package br.com.lucaspires.tibiainfotracker.data.model.news

import com.google.gson.annotations.SerializedName

data class Newslist(

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("type")
    val type: String? = null
)