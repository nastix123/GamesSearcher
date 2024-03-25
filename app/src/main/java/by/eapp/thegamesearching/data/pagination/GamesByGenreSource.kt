package by.eapp.thegamesearching.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import by.eapp.thegamesearching.data.mappers.toDomain
import by.eapp.thegamesearching.data.remote.ApiService
import by.eapp.thegamesearching.data.remote.models.GameDto
import by.eapp.thegamesearching.domain.model.Game
import retrofit2.HttpException
import javax.inject.Inject

class GamesByGenreSource @Inject constructor(
    private val apiService: ApiService,
    //private val genre: String
): PagingSource<Int, Game>() {

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val page = params.key?: 1
            val per_page = 20
            val response = apiService.getListOfGamesByGenre(
                pageCount = page,
                page = per_page,
                genre = "action"
            ).body()?.results?.map {
                it.toDomain()
            }
            val nextKey = if (response?.isEmpty() == true) null else response?.size?.plus(page)?.plus(1)

            val prevKey = if (page == 1) null else response?.size?.minus(per_page)
            LoadResult.Page(
                data = response!!,
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