package by.eapp.thegamesearching.presentation.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.eapp.thegamesearching.navigation.BottomBar

@Composable
fun FavoriteScreen(
    navController: NavHostController,
    viewModel: FavoritesViewModel
) {
    val state by viewModel.favoriteUiState.collectAsState()
    Scaffold (
        bottomBar = { BottomBar(navController = navController)}
    ) { paddingValues ->
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            if (state.emptyFavorites) {
                Column (
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "You haven't added anything yet")
                }

            } else if (state.error != null) {
                Text(text = state.error.toString())
            } else if (state.games.isNotEmpty()) {

            }

        }
    }

}

@Preview
@Composable
fun FavoriteScreenPreview() {
    val navController = rememberNavController()
    val favoritesViewModel = hiltViewModel<FavoritesViewModel>()
    FavoriteScreen(navController = navController, viewModel = favoritesViewModel)
}
