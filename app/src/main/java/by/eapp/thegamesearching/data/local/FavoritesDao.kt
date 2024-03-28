package by.eapp.thegamesearching.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Upsert
    suspend fun addFavorite(game: GameEntity)

    @Query("SELECT * FROM favorites ORDER BY id DESC")
    fun getAllFavorites(): Flow<List<GameEntity>>

    @Query("SELECT * FROM favorites WHERE id =:id")
    fun getFavoriteById(id: Int): GameEntity?

    @Query("SELECT isFavorite FROM favorites WHERE id =:id")
    fun isFavorite(id: Int): Boolean

    @Delete
    suspend fun deleteFavorite(game: GameEntity)

    @Query("DELETE FROM favorites")
    suspend fun deleteAllFavorites()


}
