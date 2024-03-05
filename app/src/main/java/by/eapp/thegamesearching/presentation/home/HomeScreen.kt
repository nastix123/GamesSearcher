package by.eapp.thegamesearching.presentation.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import by.eapp.thegamesearching.navigation.BottomBar
import by.eapp.thegamesearching.navigation.Screen
import by.eapp.thegamesearching.presentation.bottomFilterSheet.BottomFilterSheet
import by.eapp.thegamesearching.presentation.bottomFilterSheet.BottomFilterSheetViewModel
import by.eapp.thegamesearching.presentation.home.components.GamesColumn
import by.eapp.thegamesearching.presentation.home.components.GenreRow
import by.eapp.thegamesearching.presentation.home.components.HeightSpacer
import by.eapp.thegamesearching.presentation.home.components.WidthSpacer
import by.eapp.thegamesearching.presentation.search.SearchBar
import by.eapp.thegamesearching.ui.theme.primaryBackground


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val viewModelBottomFilterSheet = hiltViewModel<BottomFilterSheetViewModel>()

    val sheetState = rememberModalBottomSheetState()
    val stateOfFilter by viewModelBottomFilterSheet.filterSheetUiState.collectAsState()
    val state by viewModel.homeUiState.collectAsState()
    val searchFieldState by viewModel.searchFieldState.collectAsState()
    val searchingText by viewModel.inputText.collectAsState()

    val allGames = viewModel.games.collectAsLazyPagingItems()
    val allGenres = viewModel.genres.collectAsState(initial = emptyList()).value.map {
        it.name
    }


    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        containerColor = primaryBackground
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ) {
            HeightSpacer(20.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar(
                    onSearchInputChanged = { viewModel.updateInput(it) },
                    onClicked = { viewModel.updateHomeUiState { state -> state.copy(isSheetOpen = true) } },
                    searchFieldState = searchFieldState,
                    searchingText = searchingText
                )
                WidthSpacer(10.dp)
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable(
                        onClick = {
                            viewModel.updateHomeUiState { state ->
                                state.copy(
                                    isSheetOpen = true
                                )

                            }
                            Log.d("HomeScreen", "isSheetOpen updates")
                        }
                    )
                )
                WidthSpacer(15.dp)
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            HeightSpacer(15.dp)
            GenreRow(allGenres, state, viewModel)
            HeightSpacer(5.dp)
            if (state.listOfGamesIsLoading) {
                CircularProgressIndicator(
                    modifier = modifier.align(
                        Alignment.CenterHorizontally
                    )
                )
            }
            if (state.listOfGamesError) {
                Text(text = "Error", modifier = modifier.align(Alignment.CenterHorizontally))
            }
                GamesColumn(
                    listOfGames = allGames,
                    navController = navController
                )
//            if (state.isSheetOpen) {
//                BottomFilterSheet(
//                    sheetState = sheetState,
//                    listOfGenres = allGenres,
//                    filterState = stateOfFilter,
//                    homeState = state
//                )
//            }
        }
    }
}

