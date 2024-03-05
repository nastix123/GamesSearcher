package by.eapp.thegamesearching.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val icon: ImageVector?,
) {
    object Home : Screen(route = "home", icon = Icons.Default.Home)
    object Favorites : Screen(route = "favorites", icon = Icons.Default.Favorite)
    object GameDetailScreen : Screen(route = "detail",icon = null)
    object Search: Screen(route = "search", icon = null)
    object Filter: Screen(route = "filter", icon = null)
    object Splash: Screen(route = "splash", icon = null)

    fun wihArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}