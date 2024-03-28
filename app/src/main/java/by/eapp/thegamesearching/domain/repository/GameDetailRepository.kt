package by.eapp.thegamesearching.domain.repository

import by.eapp.thegamesearching.data.remote.models.GameDetailDto

interface GameDetailRepository {
    suspend fun getGameDetails(gameId: Int): GameDetailDto

}