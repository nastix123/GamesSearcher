package by.eapp.thegamesearching.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import by.eapp.thegamesearching.data.remote.models.GameDto
import by.eapp.thegamesearching.domain.model.Game
import by.eapp.thegamesearching.navigation.Screen
import by.eapp.thegamesearching.presentation.home.HomeScreenUiEvents
import by.eapp.thegamesearching.presentation.utils.ShimmerList

@Composable
fun GamesColumn(
    listOfGames: LazyPagingItems<Game>,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 1.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        listOfGames.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { ShimmerList(modifier = Modifier.fillMaxSize()) }
                }
                loadState.refresh is LoadState.Error -> {
                    val error = listOfGames.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            message = error.error.localizedMessage ?: "Error",
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Loading -> {
                    items(listOfGames.itemCount) {
                            index ->
                        listOfGames[index]?.let { game ->
                            GameItem(
                                imageModel = game.backgroundImage,
                                contentDescription = game.name,
                                title = game.name,
                                gameId = game.id,
//                    modifier = Modifier.clickable {
//                        navController.navigate(Screen.GameDetailScreen.wihArgs(game.id))
//                    },
//                    onClick = {navController.navigate(Screen.GameDetailScreen.wihArgs(game.id)},
                                navController = navController
                            )
                        }
                    }
                }
                loadState.append is LoadState.Error -> {
                    val error = listOfGames.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            message = error.error.localizedMessage ?: "Error",
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { retry() }
                        )
                    }
                }
            }
        }
//        items(listOfGames.itemCount)
//        { index ->
//            listOfGames[index]?.let { game ->
//                GameItem(
//                    imageModel = game.background_image!!,
//                    contentDescription = game.name,
//                    title = game.name,
//                    gameId = game.id,
////                    modifier = Modifier.clickable {
////                        navController.navigate(Screen.GameDetailScreen.wihArgs(game.id))
////                    },
////                    onClick = {navController.navigate(Screen.GameDetailScreen.wihArgs(game.id)},
//                    navController = navController
//                )
//            }
//        }
    }
}

@Composable
fun ErrorMessage(message: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = message)
    }
}

