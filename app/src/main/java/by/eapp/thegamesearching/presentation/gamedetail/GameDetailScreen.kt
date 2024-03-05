package by.eapp.thegamesearching.presentation.gamedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.eapp.thegamesearching.R
import by.eapp.thegamesearching.presentation.home.components.MyText
import by.eapp.thegamesearching.presentation.home.components.TransparentBox
import by.eapp.thegamesearching.ui.theme.primaryBackground
import by.eapp.thegamesearching.ui.theme.secondaryText
import by.eapp.thegamesearching.ui.theme.semiTransparentWhite
import by.eapp.thegamesearching.utils.removeTagsHTML
import coil.compose.AsyncImage

@Composable
fun GameDetailScreen(
    navController: NavController,
    viewModel: GameDetailScreenViewModel = hiltViewModel(),
    gameId: Int,
    modifier: Modifier = Modifier,

    ) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getGameDetail(gameId)
    }

    val state by viewModel.gameDetailUiState.collectAsState()

    var isGameFavorite by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = gameId) {
        viewModel.checkIfFavorite(gameId)
        viewModel.getGameDetail(gameId = gameId)
    }

    GameDetailScreenContent(
        state = state,
        isLiked = isGameFavorite,
        events = { event ->
            when (event) {
                is GameDetailUiEvents.AddToFavorites -> viewModel.insertFavorite(event.game)
                is GameDetailUiEvents.NavigateBack -> navController.popBackStack()
                is GameDetailUiEvents.ShareGame -> TODO()
            }
        },
        navController = navController
    )
}

@Composable
fun GameDetailScreenContent(
    state: GameDetailUiState,
    events: (GameDetailUiEvents) -> Unit,
    modifier: Modifier = Modifier,
    isLiked: Boolean,
    navController: NavController,
) {

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(primaryBackground),
    ) {

        Box(modifier = Modifier.fillMaxHeight(0.5f)) {
            AsyncImage(
                model = state.gameDetails.background_image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.align(Alignment.Center)
            )
            TransparentBox()
            Row {
                Button(
                    onClick = { navController.popBackStack() },
                    shape = RoundedCornerShape(3.dp),
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .padding(start = 10.dp, top = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = semiTransparentWhite
                    )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "get back",
                        tint = Color(0xAAFFFFFF)
                    )
                }
            }
            Button(
                onClick = {  }) {

            }

        }

        Column(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            MyText(
                text = state.gameDetails.name,
                fontSize = 22,
                weight = 800,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            MyText(
                text = stringResource(id = R.string.developers),
                fontSize = 10,
                weight = 300,
                modifier = Modifier.padding(bottom = 5.dp),
                color = secondaryText
            )
            MyText(
                text = "CDPR",
                fontSize = 14,
                weight = 600,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            MyText(
                text = stringResource(id = R.string.year_of_release),
                fontSize = 10,
                weight = 300,
                modifier = Modifier.padding(bottom = 5.dp),
                color = secondaryText
            )
            MyText(
                text = state.gameDetails.released!!.replace("-", "."),
                fontSize = 14,
                weight = 600,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            MyText(
                text = stringResource(id = R.string.description),
                fontSize = 16,
                weight = 300,
                modifier = Modifier.padding(bottom = 5.dp),
                color = secondaryText
            )

            MyText(
                text = state.gameDetails.description.removeTagsHTML(),
                fontSize = 14,
                weight = 400,
                modifier = Modifier.padding(bottom = 5.dp)
            )
        }
    }
}

