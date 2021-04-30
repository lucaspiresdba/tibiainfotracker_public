package br.com.lucaspires.tibiainfotracker.data.model.character

import com.google.gson.annotations.SerializedName

data class Data(

    @field:SerializedName("former_names")
    val formerNames: List<String?>? = null,

    @field:SerializedName("level")
    val level: Int? = null,

    @field:SerializedName("last_login")
    val lastLogin: List<LastLoginItem?>? = null,

    @field:SerializedName("sex")
    val sex: String? = null,

    @field:SerializedName("achievement_points")
    val achievementPoints: Int? = null,

    @field:SerializedName("account_status")
    val accountStatus: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("house")
    val house: House? = null,

    @field:SerializedName("vocation")
    val vocation: String? = null,

    @field:SerializedName("guild")
    val guild: Guild? = null,

    @field:SerializedName("world")
    val world: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("residence")
    val residence: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("married_to")
    val married: String? = null,

    @field:SerializedName("former_world")
    val former_world: String? = null
)