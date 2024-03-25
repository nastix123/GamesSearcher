package by.eapp.thegamesearching.data.pagination

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import by.eapp.thegamesearching.data.mappers.toDomain
import by.eapp.thegamesearching.data.remote.ApiService
import by.eapp.thegamesearching.data.remote.models.GameDto
import by.eapp.thegamesearching.domain.model.Game
import javax.inject.Inject

class GamesBySearchingSource @Inject constructor(
    private val apiService: ApiService,
    //private val query: String,
) : PagingSource<Int, Game>() {
    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val page = params.key ?: 1
            val pageSize = 30
            val response = checkNotNull(
                apiService.getListOfSearchingGames(
                    page = page,
                    pageCount = pageSize,
                    searchText = ""
                ).body()
            ).results.map {
                it.toDomain()
            }
            val nextKey = if (response.isEmpty()) null else response.size.plus(page).plus(1)
            val prevKey = if (page == 1) null else response.size.minus(pageSize)
            LoadResult.Page(
                data = response,
                nextKey = nextKey,
                prevKey = prevKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

}

