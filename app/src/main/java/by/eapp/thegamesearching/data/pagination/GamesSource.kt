package by.eapp.thegamesearching.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import by.eapp.thegamesearching.data.mappers.toDomain
import by.eapp.thegamesearching.data.remote.ApiService
import by.eapp.thegamesearching.data.remote.models.GameDto
import by.eapp.thegamesearching.domain.model.Game
import retrofit2.HttpException
import javax.inject.Inject

class GamesSource @Inject constructor(
    private val apiService: ApiService
): PagingSource<Int, Game>() {

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
//        val anchorPosition = state.anchorPosition?:return null
//        val page = state.closestPageToPosition(anchorPosition)?: return null
//        return page.prevKey?.plus(1)?: page.nextKey?.minus(1)
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val page = params.key?: 1
            val pageSize = 30
            val response = checkNotNull(apiService.getPagingListOfGames(
                pageCount = page,
                page = pageSize,
            ).body()).results.map {
                it.toDomain()
            }


           val nextKey = if (response.isEmpty()) null else response.size.plus(page).plus(1)
            //val nextKey = if (response.results.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else response.size.minus(pageSize)
            //val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(
                data = response,
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