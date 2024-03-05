package by.eapp.thegamesearching.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import by.eapp.thegamesearching.ui.theme.BackgroundForNavBar


@Composable
fun BottomBar(
    navController: NavHostController,
) {

    val sheetState by rememberSaveable {
        mutableStateOf(false)
    }
    val items = listOf(
        Screen.Home,
        Screen.Favorites
    )


    val showBottomBar =
        navController.currentBackStackEntryAsState().value?.destination?.route in items.map { it.route }

    if (showBottomBar) {
        BottomNavigation(
            backgroundColor = BackgroundForNavBar
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = screen.icon!!,
                            contentDescription = screen.route,
                            tint = Color.Black
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}





