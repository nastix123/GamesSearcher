package by.eapp.thegamesearching.presentation.search

import androidx.paging.PagingData
import by.eapp.thegamesearching.data.remote.models.GameDto

data class SearchScreenState (
    val error: String = "",
    val isLoading: Boolean = false,
    val result: PagingData<GameDto> = PagingData.empty(),
    val idle: Boolean = false
)

sealed interface SearchFieldState {
    data object Idle: SearchFieldState
    data object EmptyActive : SearchFieldState
    data object WithInputActive : SearchFieldState
}