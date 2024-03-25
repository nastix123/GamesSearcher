package by.eapp.thegamesearching.data.pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import by.eapp.thegamesearching.data.remote.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) {
    fun getPagingListOfGames() = Pager(
        config = PagingConfig(
            pageSize = 30,
            maxSize = 100),
        pagingSourceFactory = { GamesSource(apiService) }
    ).flow

    fun getPagingListOfGamesByGenre(genre: String) = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { GamesByGenreSource(apiService,/*genre*/)}
    ).flow

    fun getListOfSearchingGames(searchText: String) = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { GamesBySearchingSource(apiService, /*searchText*/) }
    ).flow
}

fun String.isValid() = this.isNotBlank()