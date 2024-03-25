package by.eapp.thegamesearching.presentation.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.eapp.thegamesearching.navigation.BottomBar
import by.eapp.thegamesearching.presentation.home.components.GamesColumn

@Composable
fun FavoriteScreen(
    navController: NavHostController,
    state: FavoritesScreenState
) {

    Scaffold (
        bottomBar = { BottomBar(navController = navController)}
    ) {paddingValues ->
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(paddingValues)
        ) {
            if (state.emptyFavorites) {
                Text(text = "Favorite is empty")
            } else if (state.error != null) {
                Text(text = state.error)
            } else if (state.games.isNotEmpty()) {

            }

        }
    }

}

@Preview
@Composable
fun FavoriteScreenPreview() {
    val navController = rememberNavController()
    FavoriteScreen(navController = navController,
        state = FavoritesScreenState(

        ))
}
