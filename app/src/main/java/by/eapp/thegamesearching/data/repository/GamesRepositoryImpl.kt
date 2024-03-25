package by.eapp.thegamesearching.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import by.eapp.thegamesearching.R
import by.eapp.thegamesearching.data.mappers.toDeveloper
import by.eapp.thegamesearching.data.mappers.toGenre
import by.eapp.thegamesearching.data.mappers.toPlatform
import by.eapp.thegamesearching.data.pagination.GamesByGenreSource
import by.eapp.thegamesearching.data.pagination.GamesBySearchingSource
import by.eapp.thegamesearching.data.pagination.GamesSource
import by.eapp.thegamesearching.data.pagination.RemoteDataSource
import by.eapp.thegamesearching.data.remote.ApiService
import by.eapp.thegamesearching.data.remote.models.DeveloperDto
import by.eapp.thegamesearching.data.remote.models.DeveloperResponse
import by.eapp.thegamesearching.data.remote.models.GameDto
import by.eapp.thegamesearching.data.remote.models.GenreDto
import by.eapp.thegamesearching.data.remote.models.PlatformDto
import by.eapp.thegamesearching.data.utils.Constants.genresErr
import by.eapp.thegamesearching.domain.model.Developer
import by.eapp.thegamesearching.domain.model.Game
import by.eapp.thegamesearching.domain.model.Genre
import by.eapp.thegamesearching.domain.model.Platform
import by.eapp.thegamesearching.domain.repository.GamesRepository
import by.eapp.thegamesearching.utils.Resource
import by.eapp.thegamesearching.utils.Resource.Error
import by.eapp.thegamesearching.utils.Resource.Success
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.util.concurrent.CancellationException
import javax.inject.Inject



class GamesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val gamesSource: GamesSource,
    private val gamesBySearchingSource: GamesBySearchingSource,
    private val gamesByGenreSource: GamesByGenreSource
) : GamesRepository {

    private companion object {
        val TAG: String = GamesRepositoryImpl::class.java.name
    }

    override fun getPagingListOfGames(): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(pageSize = 30, prefetchDistance = 10),
            pagingSourceFactory = { GamesSource(apiService) }
        ).flow
    }

    override suspend fun getListOfGenres(): Resource<Flow<List<Genre>>> {
        return try {
            val flowOfGenres = flow {
                val response = apiService.getListOfGenres(page = 1, pageCount = 10)
                if (response.isSuccessful) {
                    val genres = response.body()?.results?.map { it.toGenre() } ?: emptyList()
                    emit(genres)
                } else {
                    throw HttpException(response)
                }
            }
            Success(flowOfGenres)
        } catch (e: Exception) {
            Log.e(TAG, genresErr, e)
            Error(throwable = e)
        }
    }



    override suspend fun getListOfGamesByGenre(genre: String): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(pageSize = 30, prefetchDistance = 10),
            pagingSourceFactory = { GamesByGenreSource(apiService, /*genre*/) }
        ).flow

    }

    override suspend fun getListOfDevelopers(): Resource<Flow<List<Developer>>> {
        return try {
            val flowOfDevelopers = flow {
                val response = apiService.getListOfDevelopers(page = 1, pageCount = 40)
                if (response.isSuccessful) {
                    val developers = response.body()?.results?.map {
                        it.toDeveloper()
                    } ?: emptyList()
                    emit(developers)
                } else {
                    throw HttpException(response)
                }
            }
            Success(flowOfDevelopers)
        } catch (e: Exception) {
            if (e !is CancellationException) {
                Log.e(TAG, "Error getting list of developers", e)
                Error(throwable = e)
            } else {
                throw e
            }
        }
    }
    override suspend fun getListOfPlatforms(): Resource<Flow<List<Platform>>> {
        return try {
            val flowOfPlatforms = flow {
                val response = apiService.getListOfPlatforms(page = 1, pageCount = 20)
                if (response.isSuccessful) {
                    val platforms = response.body()?.results?.map { it.toPlatform() } ?: emptyList()
                    emit(platforms)
                } else {
                    throw HttpException(response)
                }
            }
            Success(flowOfPlatforms)
        } catch (e: Exception) {
            if (e !is CancellationException) {
                Log.e(TAG, "Error getting list of platforms", e)
                Error(throwable = e)
            } else {
                throw e
            }
        }
    }

    override suspend fun getListOfSearchingGames(query: String): Flow<PagingData<Game>> {
        return Pager(
            pagingSourceFactory = { GamesBySearchingSource(apiService, /*query*/) },
            config = PagingConfig(pageSize = 30, prefetchDistance = 10)
        ).flow
    }

}