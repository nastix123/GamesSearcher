package by.eapp.thegamesearching.presentation.search

import androidx.paging.PagingData
import by.eapp.thegamesearching.data.remote.models.GameDto
import by.eapp.thegamesearching.domain.model.Game

data class SearchScreenState (
    val error: String = "",
    val isLoading: Boolean = false,
    val result: PagingData<Game> = PagingData.empty(),
    val idle: Boolean = false
)

sealed interface SearchFieldState {
    data object Idle: SearchFieldState
    data object EmptyActive : SearchFieldState
    data object WithInputActive : SearchFieldState
}