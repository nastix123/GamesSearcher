package by.eapp.thegamesearching.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import by.eapp.thegamesearching.data.remote.ApiService
import by.eapp.thegamesearching.data.remote.models.GameDto
import retrofit2.HttpException

class GamesByGenreSource(

    private val apiService: ApiService,
    private val genre: String

): PagingSource<Int, GameDto>() {

    override fun getRefreshKey(state: PagingState<Int, GameDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameDto> {
        return try {
            val page = params.key?: 1
            val per_page = 20
            val response = apiService.getListOfGamesByGenre(
                pageCount = page,
                page = per_page,
                genre = genre
            ).body()!!
            val nextKey = if (response.results.isEmpty()) null else response.results.size.plus(page).plus(1)

            val prevKey = if (page == 1) null else response.results.size.minus(per_page)
            LoadResult.Page(
                data = response.results,
                nextKey = nextKey,
                prevKey = prevKey
            )

        } catch (e:Exception) {
            return LoadResult.Error(e)
        }
        catch(e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}