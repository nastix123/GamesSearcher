package by.eapp.thegamesearching.data.repository

import by.eapp.thegamesearching.data.remote.ApiService
import by.eapp.thegamesearching.data.remote.models.GameDetailDto
import by.eapp.thegamesearching.domain.repository.GameDetailRepository
import javax.inject.Inject

class GameDetailRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : GameDetailRepository {
    override suspend fun getGameDetails(gameId: Int): GameDetailDto {
        return apiService.getGameById(id = gameId).body()!!
    }

}