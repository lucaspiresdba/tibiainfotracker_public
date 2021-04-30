package br.com.lucaspires.tibiainfotracker.data.model.news

import br.com.lucaspires.tibiainfotracker.data.model.character.Information
import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @field:SerializedName("information")
    val information: Information? = null,

    @field:SerializedName("newslist")
    val newslist: Newslist? = null
)