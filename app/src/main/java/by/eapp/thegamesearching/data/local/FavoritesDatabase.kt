package by.eapp.thegamesearching.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import by.eapp.thegamesearching.domain.model.Game


@Database(
    entities = [GameEntity::class],
    version = 1
)
abstract class FavoritesDatabase(): RoomDatabase() {
    abstract val dao: FavoritesDao
}