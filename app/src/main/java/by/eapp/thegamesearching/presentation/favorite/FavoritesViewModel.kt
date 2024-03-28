package by.eapp.thegamesearching.presentation.favorite

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import by.eapp.thegamesearching.domain.use_cases.GetAllFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase
):  ViewModel(){
    private val _favoriteUiState = MutableStateFlow(FavoritesScreenState())
    val favoriteUiState = _favoriteUiState.asStateFlow()



}