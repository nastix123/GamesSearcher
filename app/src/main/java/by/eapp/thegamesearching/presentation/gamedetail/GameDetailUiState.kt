package by.eapp.thegamesearching.presentation.gamedetail

import by.eapp.thegamesearching.data.remote.models.GameDetailDto

data class GameDetailUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val gameDetails: GameDetailDto = GameDetailDto(
        id = -1,
        name = "Unknown",
        background_image = "",
        background_image_additional = "",
        description = "No description available",
        released = "Unknown",
        platforms = emptyList()
    ),
    val isFavorite: Boolean = false
)
