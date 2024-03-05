package by.eapp.thegamesearching.presentation.home

import androidx.paging.PagingData
import by.eapp.thegamesearching.data.remote.models.GameDto
import by.eapp.thegamesearching.data.remote.models.GenreDto
import by.eapp.thegamesearching.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeScreenUiState(
    val listOfGamesIsLoading: Boolean = false,
    val listOfGamesError: Boolean = false,
    val listOfGenresLoading: Boolean = false,
    val listOfGenresError: Boolean =  false,
    var selectedGenre: String = "",
    var isSheetOpen: Boolean = false
)
