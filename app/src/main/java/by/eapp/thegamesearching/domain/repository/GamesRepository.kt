package by.eapp.thegamesearching.domain.repository

import androidx.paging.PagingData
import by.eapp.thegamesearching.data.remote.models.DeveloperDto
import by.eapp.thegamesearching.data.remote.models.DeveloperResponse
import by.eapp.thegamesearching.data.remote.models.GameDto
import by.eapp.thegamesearching.data.remote.models.GamesResponse
import by.eapp.thegamesearching.data.remote.models.GenreDto
import by.eapp.thegamesearching.data.remote.models.PlatformDto
import by.eapp.thegamesearching.domain.model.Developer
import by.eapp.thegamesearching.domain.model.Game
import by.eapp.thegamesearching.domain.model.Genre
import by.eapp.thegamesearching.domain.model.Platform
import by.eapp.thegamesearching.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface GamesRepository {

    fun getPagingListOfGames(): Flow<PagingData<Game>>

    suspend fun getListOfGenres(): Resource<Flow<List<Genre>>>

    suspend fun getListOfGamesByGenre(genre: String): Flow<PagingData<Game>>

    suspend fun getListOfDevelopers(): Resource<Flow<List<Developer>>>

    suspend fun getListOfPlatforms(): Resource<Flow<List<Platform>>>

    suspend fun getListOfSearchingGames(query: String): Flow<PagingData<Game>>
}