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

    @Query("SELECT * FROM favorites_table ORDER BY id DESC")
    fun getAllFavorites(): Flow<List<GameEntity>>

    @Query("SELECT * FROM favorites_table WHERE id =:id")
    fun getFavoriteById(id: Int): GameEntity?

    @Query("SELECT favorite FROM favorites_table WHERE id =:id")
    fun isFavorite(id: Int): Boolean

    @Delete
    suspend fun deleteFavorite(game: GameEntity)

    @Query("DELETE FROM favorites_table")
    suspend fun deleteAllFavorites()


}
