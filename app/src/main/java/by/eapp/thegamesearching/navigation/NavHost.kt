package by.eapp.thegamesearching.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import by.eapp.thegamesearching.presentation.favorite.FavoriteScreen
import by.eapp.thegamesearching.presentation.favorite.FavoritesViewModel
import by.eapp.thegamesearching.presentation.gamedetail.GameDetailScreen

import by.eapp.thegamesearching.presentation.home.HomeScreen
import by.eapp.thegamesearching.presentation.home.HomeScreenViewModel


@Composable
fun RootNavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {

    val viewModel: HomeScreenViewModel = hiltViewModel()
        Log.d("NAvHost", "View model was created")
    val favoritesViewModel: FavoritesViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController, viewModel = viewModel) }
        composable(Screen.Favorites.route) { FavoriteScreen(navController = navController, viewModel = favoritesViewModel)}
        composable(
            Screen.GameDetailScreen.route + "/{gameId}",
            arguments = listOf(navArgument("gameId") {
                type = NavType.IntType
                nullable = false
            })
        ) { navBackStackEntry ->
            GameDetailScreen(navController = navController, gameId = navBackStackEntry.arguments?.getInt("gameId")!!)

        }


    }
}
