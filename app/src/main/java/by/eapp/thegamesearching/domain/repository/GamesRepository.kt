package by.eapp.thegamesearching.domain.repository

import androidx.paging.PagingData
import by.eapp.thegamesearching.data.remote.models.DeveloperDto
import by.eapp.thegamesearching.data.remote.models.DeveloperResponse
import by.eapp.thegamesearching.data.remote.models.GameDto
import by.eapp.thegamesearching.data.remote.models.GamesResponse
import by.eapp.thegamesearching.data.remote.models.GenreDto
import by.eapp.thegamesearching.data.remote.models.PlatformDto
import by.eapp.thegamesearching.domain.model.Game
import by.eapp.thegamesearching.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface GamesRepository {

    fun getPagingListOfGames(): Flow<PagingData<GameDto>>

    suspend fun getListOfGenres(): Resource<Flow<List<GenreDto>>>

    suspend fun getListOfGamesByGenre(genre: String): Flow<PagingData<GameDto>>

    suspend fun getListOfDevelopers(): Resource<Flow<List<DeveloperDto>>>

    suspend fun getListOfPlatforms(): Resource<Flow<List<PlatformDto>>>

    suspend fun getListOfSearchingGames(query: String): Flow<PagingData<GameDto>>
}