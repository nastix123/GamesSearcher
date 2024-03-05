package by.eapp.thegamesearching.data.pagination

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import by.eapp.thegamesearching.data.remote.ApiService
import by.eapp.thegamesearching.data.remote.models.GameDto
import javax.inject.Inject

class GamesBySearchingSource @Inject constructor(
    private val apiService: ApiService,
    private val query: String,
) : PagingSource<Int, GameDto>() {
    override fun getRefreshKey(state: PagingState<Int, GameDto>): Int? {
        return state.anchorPosition
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameDto> {
        return try {
            val page = params.key ?: 1
            val pageSize = 30
            val response = checkNotNull(
                apiService.getListOfSearchingGames(
                    page = page,
                    pageCount = pageSize,
                    searchText = query
                ).body()
            )
            val nextKey =
                if (response.results.isEmpty()) null else response.results.size.plus(page).plus(1)
            //val nextKey = if (response.results.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else response.results.size.minus(pageSize)
            //val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(
                data = response.results,
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

