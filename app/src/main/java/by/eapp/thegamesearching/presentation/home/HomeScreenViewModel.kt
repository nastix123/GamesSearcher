package by.eapp.thegamesearching.presentation.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import by.eapp.thegamesearching.data.remote.models.GameDto
import by.eapp.thegamesearching.data.remote.models.GenreDto
import by.eapp.thegamesearching.domain.model.Game
import by.eapp.thegamesearching.domain.model.Genre
import by.eapp.thegamesearching.domain.use_cases.GetListOfGamesByGenreUseCase
import by.eapp.thegamesearching.domain.use_cases.GetListOfGamesUseCase
import by.eapp.thegamesearching.domain.use_cases.GetListOfGenresUseCase
import by.eapp.thegamesearching.domain.use_cases.GetListOfSearchingGamesUseCase
import by.eapp.thegamesearching.presentation.search.SearchScreenState
import by.eapp.thegamesearching.presentation.search.SearchFieldState
import by.eapp.thegamesearching.utils.Resource.Error
import by.eapp.thegamesearching.utils.Resource.Loading
import by.eapp.thegamesearching.utils.Resource.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getListOfGamesUseCase: GetListOfGamesUseCase,
    private val getListOfGenresUseCase: GetListOfGenresUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val getListOfGamesByGenreUseCase: GetListOfGamesByGenreUseCase,
    private val getListOfSearchingGamesUseCase: GetListOfSearchingGamesUseCase
) : ViewModel() {

    private companion object {
        val TAG: String = HomeScreenViewModel::class.java.name
    }

    private val _homeUiState = MutableStateFlow(HomeScreenUiState())
    val homeUiState = _homeUiState.asStateFlow()

    private val _games: MutableStateFlow<PagingData<Game>> = MutableStateFlow(PagingData.empty())
    val games: MutableStateFlow<PagingData<Game>> get() = _games

    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres: MutableStateFlow<List<Genre>> get() = _genres

    private val _featuredGames: MutableStateFlow<PagingData<Game>> = MutableStateFlow(PagingData.empty())
    val featuredGames: MutableStateFlow<PagingData<Game>> get() = _featuredGames



    private val _searchGamesState: MutableStateFlow<SearchScreenState> = MutableStateFlow(SearchScreenState())
    val searchGamesState: MutableStateFlow<SearchScreenState> get() = _searchGamesState

    private var searchJob: Job? = null

    private val _searchFieldState = MutableStateFlow<SearchFieldState>(SearchFieldState.Idle)
    val searchFieldState = _searchFieldState.asStateFlow()



    private val _inputText: MutableStateFlow<String> = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText.asStateFlow()

    private val isInitialized = AtomicBoolean(false)

    init {

//        getListOfGenres()
//        getListOfGamesByGenre()
    }

    @FlowPreview
    fun initialize() {
        if (isInitialized.compareAndSet(false, true)) {
            viewModelScope.launch {
                inputText.debounce(500).collectLatest { input ->
                    if (input.blankOrEmpty()) {
                        _searchGamesState.update { SearchScreenState(idle = true) }
                        return@collectLatest
                    }

                    when (val result = getListOfSearchingGamesUseCase.invoke(query = input)) {
                        is GetListOfSearchingGamesUseCase.Result.Success -> {
                            result.results.collectLatest {it ->
                                _searchGamesState.update { state ->
                                    state.copy(
                                        result = it
                                    )
                                }
                            }

                        }
                        is GetListOfSearchingGamesUseCase.Result.Error -> {
                            _searchGamesState.update {
                                it.copy(
                                    error = "Error with searching games: ${result.msg}"
                                )
                            }
                        }
                    }
                }
            }
        }
    }


    fun searchFieldActivated() {
        activateSearchField()
    }

    fun clearInput() {
        _inputText.update { "" }
        _searchFieldState.update { SearchFieldState.EmptyActive }
    }

    fun updateInput(inputText: String) {
        _inputText.update { inputText }
        activateSearchField()
    }

    fun revertToInitialState() {
        _inputText.update { "" }
        _searchFieldState.update { SearchFieldState.Idle }
    }

    private fun activateSearchField() {
        if (_inputText.value.blankOrEmpty().not())
        _searchFieldState.update {
            SearchFieldState.WithInputActive
        } else {
            _searchFieldState.update {
                SearchFieldState.EmptyActive
            }
        }
    }


    private fun String.blankOrEmpty() = this.isBlank() || this.isEmpty()

    fun updateHomeUiState(update: (HomeScreenUiState) -> HomeScreenUiState) {
        _homeUiState.value = update(_homeUiState.value)
    }

    private suspend fun getListOfGamesByGenre() {
//        viewModelScope.launch {
//            when (val resourceGames = getListOfGamesByGenreUseCase.invoke(_homeUiState.value.selectedGenre)) {
//                is Flow<PagingData<GameDto>> -> {
//                    resourceGames.data.stateIn(viewModelScope).collectLatest { pagingData ->
//                        _featuredGames.value = pagingData
//                        Log.d(TAG, "games by genre must be updated")
//                    }
//                }
//
//                is Error<*> -> {
//                    _homeUiState.update { state ->
//                        state.copy(
//                            listOfGamesError = true,
//                            listOfGamesIsLoading = false
//                        )
//                    }
//                }
//
//                is Loading<*> -> {
//                    _homeUiState.update { state ->
//                        state.copy(
//                            listOfGamesError = false,
//                            listOfGamesIsLoading = true
//                        )
//                    }
//                }
//
//                else -> {
//                    Log.d(TAG, "games by genres are not updated")
//                }
//            }
//        }
        getListOfGamesByGenreUseCase.invoke(_homeUiState.value.selectedGenre)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect{
                _featuredGames.value = it
            }
    }

    fun getPagingFlow() = getListOfGamesUseCase()
    
    private suspend fun getPagingListOfGames() {
        getListOfGamesUseCase.invoke()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect{
            _games.value = it
        }
    }


    private fun getListOfGenres() {
        viewModelScope.launch {

            when (val genres = getListOfGenresUseCase.invoke()) {
                is Success -> {
                    genres.data.stateIn(viewModelScope).collect { genres ->
                        _genres.value = genres
                    }
                }

                is Error<*> -> {
                    _homeUiState.update { state ->
                        state.copy(
                            listOfGenresError = true,
                            listOfGenresLoading = false
                        )
                    }
                }

                is Loading<*> -> {
                    _homeUiState.update { state ->
                        state.copy(
                            listOfGenresLoading = true,
                            listOfGenresError = false
                        )
                    }
                }


            }
        }

    }

    fun setGenre(genre: String?) {
        _homeUiState.update {
            it.copy(
                selectedGenre = genre!!
            )
        }
    }


}



