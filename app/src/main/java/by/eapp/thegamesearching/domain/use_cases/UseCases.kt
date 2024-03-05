package by.eapp.thegamesearching.domain.use_cases

data class UseCases (
    val gamesByGenre: GetListOfGamesByGenreUseCase,
    val allGames: GetListOfGamesUseCase,
    val genres: GetListOfGenresUseCase,
    val platformsUseCase: GetListOfPlatformsUseCase,
    val isFavoriteUseCase: IsFavoriteUseCase,
    val deleteAllGamesUseCase: DeleteAllGamesUseCase,
    val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    val deleteGameUseCase: DeleteGameUseCase,
    val searchingGames: GetListOfSearchingGamesUseCase
)