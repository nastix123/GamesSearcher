package by.eapp.thegamesearching.presentation.bottomFilterSheet

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.eapp.thegamesearching.domain.repository.GamesRepository
import by.eapp.thegamesearching.domain.use_cases.GetListOfPlatformsUseCase
import by.eapp.thegamesearching.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BottomFilterSheetViewModel @Inject constructor(
   private val getListOfPlatformsUseCase: GetListOfPlatformsUseCase
): ViewModel() {

    private val _filterSheetUiState = MutableStateFlow(BottomFilterSheetUiState())
    val filterSheetUiState = _filterSheetUiState.asStateFlow()

    init {
        getListOfPlatforms()
    }

    private fun getListOfPlatforms() {
        viewModelScope.launch {
            when (val platforms = getListOfPlatformsUseCase.invoke()) {
                is Resource.Success -> {
                    platforms.data.collect { platformList ->
                        _filterSheetUiState.update { state ->
                            state.copy(
                                allPlatforms = platformList.map {
                                    it.name
                                }
                            )
                        }
                    }
                }
                is Resource.Error -> TODO()
                is Resource.Loading -> TODO()
            }
        }
    }
}