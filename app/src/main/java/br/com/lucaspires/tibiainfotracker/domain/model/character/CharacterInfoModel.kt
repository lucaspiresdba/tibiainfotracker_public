package br.com.lucaspires.tibiainfotracker.domain.model.character

data class CharacterInfoModel(
    val name: String?,
    val oldName: String? = null,
    val level: Int?,
    val sex: SexEnum?,
    val vocation: VocationEnum?,
    val world: String?,
    val oldWorld: String?,
    val residence: String?,
    val deaths: List<DeathModel>?,
    val house: HouseInfoModel?,
    val guild: GuildInfoModel?,
    val notFoundData: Boolean = false,
    val isFavorite: Boolean = false,
    val married: String?
)

data class HouseInfoModel(
    val houseName: String?,
    val houseTown: String?,
)

data class GuildInfoModel(
    val guildName: String?,
    val guildRank: String?
)