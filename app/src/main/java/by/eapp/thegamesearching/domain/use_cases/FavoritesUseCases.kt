package by.eapp.thegamesearching.domain.use_cases

import by.eapp.thegamesearching.data.local.GameEntity
import by.eapp.thegamesearching.data.mappers.toGameEntity
import by.eapp.thegamesearching.domain.model.Game
import by.eapp.thegamesearching.domain.repository.FavoritesRepository
import by.eapp.thegamesearching.domain.repository.GameDetailRepository
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

class AddFavoriteGameUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(game: Game) = favoritesRepository.addFavorite(game.toGameEntity())
}

