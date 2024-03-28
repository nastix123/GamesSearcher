package by.eapp.thegamesearching.presentation.gamedetail

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import by.eapp.thegamesearching.data.local.GameEntity
//import by.eapp.thegamesearching.data.mappers.toDomain
//import by.eapp.thegamesearching.data.mappers.toGameEntity
//import by.eapp.thegamesearching.domain.model.Game
//import by.eapp.thegamesearching.domain.repository.FavoritesRepository
//import by.eapp.thegamesearching.domain.repository.GameDetailRepository
//import by.eapp.thegamesearching.domain.repository.GamesRepository
//import by.eapp.thegamesearching.domain.use_cases.AddFavoriteGameUseCase
//import by.eapp.thegamesearching.domain.use_cases.GetGameDetailUseCase
//import by.eapp.thegamesearching.domain.use_cases.IsFavoriteUseCase
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.async
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import javax.inject.Inject
//
//
//@HiltViewModel
//class GameDetailScreenViewModel @Inject constructor(
//    private val isFavoriteUseCase: IsFavoriteUseCase,
//    private val getGameDetailUseCase: GetGameDetailUseCase,
//    private val addNewFavoriteGameUseCase: AddFavoriteGameUseCase
//) : ViewModel() {
//
//    private val _gameDetailUiState = MutableStateFlow(GameDetailUiState())
//    val gameDetailUiState = _gameDetailUiState.asStateFlow()
//
//    private val _isFavorite: MutableStateFlow<Boolean> = MutableStateFlow(false)
//    val isFavorite: StateFlow<Boolean> get() = _isFavorite
//
//    fun getGameDetail(gameId: Int) {
//        viewModelScope.launch {
//            val gameDetails = getGameDetailUseCase(gameId)
//            _gameDetailUiState.update { state ->
//                state.copy(
//                    gameDetails = gameDetails
//                )
//            }
//        }
//
//    }
//
//    /*fun isFavorite() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val isFavorite = favoritesRepository.isFavorite()
//        }
//    }*/
//
//    fun checkIfFavorite(gameId: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val isFavorite = isFavoriteUseCase(gameId)
//            withContext(Dispatchers.Main) {
//                _isFavorite.value = isFavorite
//            }
//        }
//    }
//
//    private fun insertFavorite(game: Game) {
//        viewModelScope.launch {
//            addNewFavoriteGameUseCase.invoke(game)
//        }
//    }
//
//    fun onEvent(event: GameDetailUiEvents) {
//        when (event) {
//            is GameDetailUiEvents.AddToFavorites -> {
//                insertFavorite(event.game.toDomain())
//            }
//
//            else -> {}
//        }
//    }
//
//
//}


