package by.eapp.thegamesearching.presentation.gamedetail

import by.eapp.thegamesearching.data.local.GameEntity

sealed interface GameDetailUiEvents {
    data object NavigateBack: GameDetailUiEvents
    data class AddToFavorites(val game: GameEntity): GameDetailUiEvents
    data class ShareGame(val gameId: Int): GameDetailUiEvents
}