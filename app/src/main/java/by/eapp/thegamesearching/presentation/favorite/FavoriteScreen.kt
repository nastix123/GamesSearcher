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
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import by.eapp.thegamesearching.navigation.BottomBar

@Composable
fun FavoriteScreen(
    navController: NavHostController
) {
    Scaffold (
        bottomBar = { BottomBar(navController = navController)}
    ) {paddingValues ->
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(paddingValues)
        ) {
            Text(text = "FavoriteScreen")
        }
    }

}