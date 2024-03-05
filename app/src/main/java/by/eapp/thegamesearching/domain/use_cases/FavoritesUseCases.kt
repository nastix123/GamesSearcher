package by.eapp.thegamesearching.domain.use_cases

import by.eapp.thegamesearching.data.local.GameEntity
import by.eapp.thegamesearching.domain.repository.FavoritesRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(id: Int) = favoritesRepository.isFavorite(id)
}

class DeleteAllGamesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke() = favoritesRepository.deleteAllFavorites()
}

class GetAllFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke() = favoritesRepository.getAllFavorites()
}


class DeleteGameUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(game: GameEntity) = favoritesRepository.deleteFavorite(game)
}