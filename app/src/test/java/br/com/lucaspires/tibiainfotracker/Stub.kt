package br.com.lucaspires.tibiainfotracker

import br.com.lucaspires.tibiainfotracker.data.model.character.CharacterResponse
import br.com.lucaspires.tibiainfotracker.data.model.character.Characters
import br.com.lucaspires.tibiainfotracker.data.model.character.Data
import br.com.lucaspires.tibiainfotracker.data.model.highscore.DataItem
import br.com.lucaspires.tibiainfotracker.data.model.highscore.Highscores
import br.com.lucaspires.tibiainfotracker.data.model.highscore.HighscoresResponse
import br.com.lucaspires.tibiainfotracker.data.model.houses.*
import br.com.lucaspires.tibiainfotracker.data.model.news.NewsResponse
import br.com.lucaspires.tibiainfotracker.data.model.news.Newslist
import br.com.lucaspires.tibiainfotracker.domain.model.character.*
import br.com.lucaspires.tibiainfotracker.domain.model.highscores.HighscoreCharacterData
import br.com.lucaspires.tibiainfotracker.domain.model.highscores.HighscoreModel
import br.com.lucaspires.tibiainfotracker.domain.model.houses.HouseDetailModel
import br.com.lucaspires.tibiainfotracker.domain.model.houses.HouseModel
import br.com.lucaspires.tibiainfotracker.domain.model.news.NewsModel
import br.com.lucaspires.tibiainfotracker.domain.model.support.SupportModel
import br.com.lucaspires.tibiainfotracker.data.model.news.DataItem as NewsData


object Stub {

    fun getCharacterModel() =
        CharacterInfoModel(
            name = "Teste 123",
            oldName = null,
            level = null,
            sex = SexEnum.M,
            vocation = VocationEnum.MS,
            world = null,
            oldWorld = null,
            residence = null,
            deaths = emptyList(),
            house = HouseInfoModel(null, null),
            guild = GuildInfoModel(null, null),
            notFoundData = false,
            married = null,
            isFavorite = true
        )

    fun getCharacterResponse() =
        CharacterResponse(characters = Characters(data = Data(name = "Teste 123")))

    fun getHighscoreResponse() = HighscoresResponse(
        highscores = Highscores(
            data = listOf(
                DataItem("Elite Knight"),
                DataItem("Paladin")
            )
        )
    )

    fun getHighscoreModelList() = HighscoreModel(
        rankList = listOf(
            HighscoreCharacterData("Elite Knight", level = "", rank = "", points = "", value = ""),
            HighscoreCharacterData("Paladin", level = "", rank = "", points = "", value = "")
        )
    )

    fun getHousesResponse() = HousesResponse(
        houses = Houses(
            houses = listOf(
                HousesItem(name = "House 1"),
                HousesItem(name = "House 2")
            )
        )
    )

    fun getHouseModelList() = listOf(
        HouseModel(name = "House 1", status = "Em leilão", world = ""),
        HouseModel(name = "House 2", status = "Em leilão", world = "")
    )

    fun getHouseDetailResponse() =
        HouseDetailsResponse(
            house = House(
                name = "House 1",
                status = Status(ownerNow = "Eu"),
                beds = 2,
                size = 10,
                img = "url",
                rent = 1000,
                houseid = 1,
                world = "K",
                type = "1"
            )
        )

    fun getHouseDetailModel() = HouseDetailModel(
        name = "House 1",
        owner = "Eu",
        beds = 2,
        rent = 1000,
        size = 10,
        imageUrl = "url"
    )

    fun getNewsResponse() =
        NewsResponse(
            newslist = Newslist(
                data = listOf(
                    NewsData(
                        news = "No Corona",
                        tibiaurl = "url"
                    ), NewsData(news = "We Wins", tibiaurl = "url")
                )
            )
        )

    fun getNewsModel() =
        listOf(NewsModel("No Corona", url = "url"), NewsModel("We Wins", url = "url"))

    fun getListOfCities() = listOf("City 1", "City 2", "City 3")

    fun getListOfWorlds() = listOf("World 1", "World 2", "World 3")

    fun getPairOfWorldsAndCities() = Pair(getListOfWorlds(), getListOfCities())

    fun getListOfSupporters() = listOf(
        SupportModel("name", "imgUrl", "url"),
        SupportModel("name", "imgUrl", "url")
    )
}


