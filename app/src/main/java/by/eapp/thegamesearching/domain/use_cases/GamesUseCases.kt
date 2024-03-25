package by.eapp.thegamesearching.domain.use_cases

import androidx.paging.PagingData
import by.eapp.thegamesearching.data.remote.models.GameDto
import by.eapp.thegamesearching.domain.model.Game
import by.eapp.thegamesearching.domain.repository.GamesRepository
import by.eapp.thegamesearching.utils.Resource
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class GetListOfGamesByGenreUseCase @Inject constructor(
    private val repository: GamesRepository,
) {
    suspend operator fun invoke(genre: String) = repository.getListOfGamesByGenre(genre)
}

class GetListOfGamesUseCase @Inject constructor (
    private val gamesRepository: GamesRepository
) {
   operator fun invoke(): Flow<PagingData<Game>> = gamesRepository.getPagingListOfGames()
}
class GetListOfGenresUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    suspend operator fun invoke() = repository.getListOfGenres()
}

class GetListOfPlatformsUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    suspend operator fun invoke() = repository.getListOfPlatforms()
}

class GetListOfSearchingGamesUseCase @Inject constructor(
    private val repository: GamesRepository,
) {
    sealed interface Result{
        data class Error(val msg: String): Result
        data class Success(val results: Flow<PagingData<Game>>): Result
    }
    suspend operator fun invoke(query: String) : Result = try {
        val results = repository.getListOfSearchingGames(query)
        Result.Success(results)
    } catch (e: IOException) {
        Result.Error(e.toString())
    }
}