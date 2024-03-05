package by.eapp.thegamesearching.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import by.eapp.thegamesearching.R
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
import by.eapp.thegamesearching.domain.repository.GamesRepository
import by.eapp.thegamesearching.utils.Resource
import by.eapp.thegamesearching.utils.Resource.Error
import by.eapp.thegamesearching.utils.Resource.Success
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override fun getPagingListOfGames(): Flow<PagingData<GameDto>> {
        return Pager(
            config = PagingConfig(pageSize = 30, prefetchDistance = 10),
            pagingSourceFactory = { GamesSource(apiService) }
        ).flow
    }

    override suspend fun getListOfGenres(): Resource<Flow<List<GenreDto>>> {
        return try {
            val flowOfGenres = flow {
                val response = apiService.getListOfGenres(page = 1, pageCount = 10)
                val genres = response.body()?.results?:emptyList()
                emit(genres)
            }
            Success(flowOfGenres)
        } catch (e: Exception) {
            Log.d(TAG, genresErr)
            e.printStackTrace()
            Error(throwable = e)
        }
    }


    override suspend fun getListOfGamesByGenre(genre: String): Flow<PagingData<GameDto>> {
        return Pager(
            config = PagingConfig(pageSize = 30, prefetchDistance = 10),
            pagingSourceFactory = { GamesByGenreSource(apiService, genre) }
        ).flow

    }

    override suspend fun getListOfDevelopers(): Resource<Flow<List<DeveloperDto>>> {
        return try {
            val flowOfDevelopers = flow {
                val response = apiService.getListOfDevelopers(page = 1, pageCount = 40)
                val developers = response.body()!!.results
                emit(developers)
            }
            Success(flowOfDevelopers)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Error(throwable = e)
        }
    }

    override suspend fun getListOfPlatforms(): Resource<Flow<List<PlatformDto>>> {
        return try {
            val flowOfPlatforms = flow{
                val response = apiService.getListOfPlatforms(page = 1, pageCount = 20)
                val platforms = response.body()!!.results
                emit(platforms)
            }
            Success(flowOfPlatforms)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Error(throwable = e)
        }
    }

    override suspend fun getListOfSearchingGames(query: String): Flow<PagingData<GameDto>> {
       return Pager(
           pagingSourceFactory = { GamesBySearchingSource(apiService, query) },
           config = PagingConfig(pageSize = 30, prefetchDistance = 10)
       ).flow
    }


}