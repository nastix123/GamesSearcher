package by.eapp.thegamesearching.data.remote

import by.eapp.thegamesearching.data.remote.models.DeveloperResponse
import by.eapp.thegamesearching.data.remote.models.GameDetailDto
import by.eapp.thegamesearching.data.remote.models.GamesResponse
import by.eapp.thegamesearching.data.remote.models.GenreResponse
import by.eapp.thegamesearching.data.remote.models.PlatformResponse
import by.eapp.thegamesearching.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("games")
    suspend fun getPagingListOfGames(
        @Query("key") key: String = API_KEY,
        @Query("page") page: Int = 1 ,
        @Query("page_size") pageCount: Int = 30,
    ): Response<GamesResponse>

    @GET("games")
    suspend fun getListOfSearchingGames(
        @Query("key") key: String = API_KEY,
        @Query("page") page: Int ,
        @Query("page_size") pageCount: Int = 30,
        @Query("search") searchText: String,
    ): Response<GamesResponse>

    @GET("games")
    suspend fun getListOfGamesWithFilter(
        @Query("key") key: String = API_KEY,
        @Query("page") page: Int,
        @Query("page_size") pageCount: Int = 30,
        @Query("genres") genres: List<String>?,
        @Query("parent_platforms") parentPlatforms: List<String>
    ): Response<GamesResponse>


    @GET("genres")
    suspend fun getListOfGenres(
        @Query("key") key: String = API_KEY,
        @Query("page") page: Int  = 1,
        @Query("page_size") pageCount: Int = 10
    ): Response<GenreResponse>

    @GET("games")
    suspend fun getListOfGamesByGenre(
        @Query("key") key: String = API_KEY,
        @Query("page") page: Int  = 1,
        @Query("page_size") pageCount: Int = 10,
        @Query("genre") genre: String?
    ): Response<GamesResponse>

    @GET("platforms")
    suspend fun getListOfPlatforms(
        @Query("key") key: String = API_KEY,
        @Query("page") page: Int ,
        @Query("page_size") pageCount: Int = 20
    ): Response<PlatformResponse>

    @GET("developers")
    suspend fun getListOfDevelopers(
        @Query("page") page: Int ,
        @Query("page_size") pageCount: Int = 40
    ): Response<DeveloperResponse>


    @GET("games/{id}")
    suspend fun getGameById(
        @Path("id") id: Int,
        @Query("key") key: String = API_KEY,
    ): Response<GameDetailDto>

}