package by.eapp.thegamesearching.domain.repository

import by.eapp.thegamesearching.data.local.GameEntity
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun addFavorite(game: GameEntity)

    fun getAllFavorites(): Flow<List<GameEntity>>

    fun getFavoriteById(id: Int): GameEntity?

    suspend fun deleteFavorite(game: GameEntity)

    suspend fun deleteAllFavorites()

    fun isFavorite(gameId: Int): Boolean
}