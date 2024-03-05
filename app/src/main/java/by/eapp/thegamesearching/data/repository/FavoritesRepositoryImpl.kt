package by.eapp.thegamesearching.data.repository

import by.eapp.thegamesearching.data.local.FavoritesDao
import by.eapp.thegamesearching.data.local.FavoritesDatabase
import by.eapp.thegamesearching.data.local.GameEntity
import by.eapp.thegamesearching.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val db: FavoritesDatabase
): FavoritesRepository {

    override suspend fun addFavorite(game: GameEntity) {
        return db.dao.addFavorite(game)
    }

    override fun getAllFavorites(): Flow<List<GameEntity>> {
        return db.dao.getAllFavorites()
    }

    override fun getFavoriteById(id: Int): GameEntity? {
       return db.dao.getFavoriteById(id)
    }

    override suspend fun deleteFavorite(game: GameEntity) {
        return db.dao.deleteFavorite(game)
    }

    override suspend fun deleteAllFavorites() {
        return db.dao.deleteAllFavorites()
    }

    override fun isFavorite(gameId: Int): Boolean {
        return db.dao.isFavorite(gameId)
    }
}