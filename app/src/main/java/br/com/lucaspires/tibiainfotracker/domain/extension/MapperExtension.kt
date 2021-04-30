package br.com.lucaspires.tibiainfotracker.domain.extension

import android.annotation.SuppressLint
import br.com.lucaspires.tibiainfotracker.data.model.character.CharacterResponse
import br.com.lucaspires.tibiainfotracker.data.model.character.DeathsItem
import br.com.lucaspires.tibiainfotracker.data.model.highscore.Highscores
import br.com.lucaspires.tibiainfotracker.data.model.houses.House
import br.com.lucaspires.tibiainfotracker.data.model.houses.Houses
import br.com.lucaspires.tibiainfotracker.data.model.news.DataItem
import br.com.lucaspires.tibiainfotracker.domain.model.character.*
import br.com.lucaspires.tibiainfotracker.domain.model.highscores.HighscoreCharacterData
import br.com.lucaspires.tibiainfotracker.domain.model.highscores.HighscoreModel
import br.com.lucaspires.tibiainfotracker.domain.model.houses.HouseDetailModel
import br.com.lucaspires.tibiainfotracker.domain.model.houses.HouseModel
import br.com.lucaspires.tibiainfotracker.domain.model.news.NewsModel

private const val ROYAL_PALADIN = "Royal Paladin"
private const val PALADIN = "Paladin"
private const val ELITE_KNIGHT = "Elite Knight"
private const val KNIGHT = "Knight"
private const val ELDER_DRUID = "Elder Druid"
private const val DRUID = "Druid"
private const val FEMALE = "female"
private const val HTML_TEMPLATE_MARRIED = "<i><u><font color=#26abff>"
private const val HOUSE_STATUS_RENTED_RETRIEVE = "rented"
private const val HOUSE_STATUS_AUCTIONED_NO_BID_RETRIEVE = "auctioned (no bid yet)"
private const val HOUSE_RENTED = "Alugada"
private const val HOUSE_FREE = "Desocupada"
private const val HOUSE_AUCTIONED = "Em leilão"

internal fun CharacterResponse.toCharacterModel(): CharacterInfoModel {
    return CharacterInfoModel(
        name = characters?.data?.name.orEmpty(),
        oldName = characters?.data?.formerNames?.firstOrNull(),
        world = characters?.data?.world,
        oldWorld = characters?.data?.former_world,
        residence = characters?.data?.residence,
        deaths = characters?.deaths.toDeathModel(),
        sex = toSex(characters?.data?.sex.orEmpty()),
        vocation = toVocation(characters?.data?.vocation.orEmpty()),
        level = characters?.data?.level,
        house = HouseInfoModel(
            houseName = characters?.data?.house?.name,
            houseTown = characters?.data?.house?.town,
        ),
        guild = GuildInfoModel(
            guildName = characters?.data?.guild?.name,
            guildRank = characters?.data?.guild?.rank
        ),
        married = characters?.data?.married?.let { HTML_TEMPLATE_MARRIED + it },
        //TODO repensar essa abordagem
        notFoundData = characters?.data?.name.isNullOrEmpty()
    )
}

internal fun List<DeathsItem?>?.toDeathModel(): List<DeathModel> {
    val deathList = arrayListOf<DeathModel>()
    this?.forEach { death ->
        deathList.add(DeathModel(death?.level, death?.reason))
    }
    return deathList
}

internal fun List<DataItem?>?.toNewsModel(): List<NewsModel> {
    val newsList = arrayListOf<NewsModel>()
    this?.forEach { news ->
        newsList.add(NewsModel(news?.news, news?.tibiaurl))
    }
    return newsList
}

private fun toSex(sex: String): SexEnum {
    return when (sex) {
        FEMALE -> SexEnum.F
        else -> SexEnum.M
    }
}

private fun toVocation(vocation: String): VocationEnum {
    return when (vocation) {
        ROYAL_PALADIN, PALADIN -> VocationEnum.RP
        ELITE_KNIGHT, KNIGHT -> VocationEnum.EK
        ELDER_DRUID, DRUID -> VocationEnum.ED
        else -> VocationEnum.MS
    }
}

fun Houses.toHouseModel(): List<HouseModel> {
    val newList = arrayListOf<HouseModel>()
    houses?.forEach { house ->
        newList.add(
            HouseModel(
                name = house?.name.orEmpty(),
                size = house?.size.orZero(),
                rent = house?.rent.orZero(),
                status = processStatus(house?.status.orEmpty()),
                houseId = house?.houseid.orZero(),
                world = world.orEmpty()
            )
        )
    }
    return newList
}

private fun processStatus(status: String): String {
    return when (status) {
        HOUSE_STATUS_RENTED_RETRIEVE -> HOUSE_RENTED
        HOUSE_STATUS_AUCTIONED_NO_BID_RETRIEVE -> HOUSE_FREE
        else -> HOUSE_AUCTIONED
    }
}

fun Int?.orZero() = this ?: 0

internal fun House.toModel(): HouseDetailModel {
    return HouseDetailModel(
        name = name,
        owner = status.ownerNow,
        beds = beds,
        rent = rent,
        size = size,
        imageUrl = img
    )
}

internal fun Highscores.toHighscoreModel(): HighscoreModel {
    val newList = arrayListOf<HighscoreCharacterData>()
    data?.forEach {
        newList.add(
            HighscoreCharacterData(
                vocation = it?.vocation,
                world = it?.world,
                level = it?.level?.toString() ?: "",
                name = it?.name,
                rank = it?.rank?.toString() ?: "",
                points = it?.points?.toString() ?: "",
                value = it?.value?.toString() ?: ""
            )
        )
    }
    return HighscoreModel(
        vocation = filters?.vocation,
        world = filters?.world,
        category = filters?.category,
        rankList = newList.take(100)
    )
}

@SuppressLint("DefaultLocale")
fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }