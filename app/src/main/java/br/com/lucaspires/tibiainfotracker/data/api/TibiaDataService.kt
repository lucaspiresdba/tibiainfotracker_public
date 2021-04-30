package br.com.lucaspires.tibiainfotracker.data.api

import br.com.lucaspires.tibiainfotracker.data.model.character.CharacterResponse
import br.com.lucaspires.tibiainfotracker.data.model.highscore.HighscoresResponse
import br.com.lucaspires.tibiainfotracker.data.model.houses.HouseDetailsResponse
import br.com.lucaspires.tibiainfotracker.data.model.houses.HousesResponse
import br.com.lucaspires.tibiainfotracker.data.model.news.NewsResponse
import br.com.lucaspires.tibiainfotracker.data.model.worlds.WorldsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

internal interface TibiaDataService {
    companion object {
        const val BASE_URL = "https://api.tibiadata.com/v2/"
    }

    @GET("characters/{name}.json")
    fun getCharacter(@Path("name") name: String): Single<CharacterResponse>

    @GET("latestnews.json")
    fun getNews(): Single<NewsResponse>

    @GET("worlds.json")
    fun getWorlds(): Single<WorldsResponse>

    @GET("houses/{world}/{town}.json")
    fun getHouses(
        @Path("world") world: String,
        @Path("town") town: String
    ): Single<HousesResponse>

    @GET("houses/{world}/{town}/guildhalls.json")
    fun getGuildHouses(
        @Path("world") world: String,
        @Path("town") town: String
    ): Single<HousesResponse>

    @GET("house/{world}/{houseId}.json")
    fun getHouseDetails(
        @Path("world") world: String,
        @Path("houseId") houseId: Int
    ): Single<HouseDetailsResponse>

    @GET("highscores/{world}/{ranktype}/{vocation}.json")
    fun getHighscores(
        @Path("world") world: String,
        @Path("ranktype") rankType: String,
        @Path("vocation") vocation: String
    ): Single<HighscoresResponse>
}