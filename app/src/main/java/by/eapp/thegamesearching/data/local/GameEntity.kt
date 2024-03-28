package by.eapp.thegamesearching.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.eapp.thegamesearching.data.remote.models.PlatformDto
import by.eapp.thegamesearching.utils.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class GameEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val released: String?,
    val backgroundImage: String?,
    val isFavorite: Boolean = false
)
