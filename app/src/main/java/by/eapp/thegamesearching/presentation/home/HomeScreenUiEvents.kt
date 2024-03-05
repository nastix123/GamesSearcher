package by.eapp.thegamesearching.presentation.home

sealed class HomeScreenUiEvents {
    data object OnSearchClick: HomeScreenUiEvents()
    data object OnFilterClick: HomeScreenUiEvents()
    //data object OnClearClick: HomeScreenUiEvents возможно это лучше отнести к модели Search, либюо сделать одну модель для HomeScreen и SearchScreen
    data class NavigateToGameDetails(
        val gameId: Int
    ): HomeScreenUiEvents()
    data object OnSelectedGenreClick: HomeScreenUiEvents()
    data object GetHome: HomeScreenUiEvents()
}