package by.eapp.thegamesearching.presentation.favorite

import by.eapp.thegamesearching.domain.model.Game

data class FavoritesScreenState(
    val games: List<Game> = emptyList(),
    val emptyFavorites: Boolean = true,
    val error: String? = null
)
