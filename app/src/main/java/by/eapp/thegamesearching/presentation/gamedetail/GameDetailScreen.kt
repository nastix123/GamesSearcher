package by.eapp.thegamesearching.presentation.gamedetail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun GameDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: GameDetailViewModel = hiltViewModel(),
    gameId: Int,
) {
//    LaunchedEffect(key1 = Unit) {
//        viewModel.getGameDetail(gameId)
//    }
//
//    val state by viewModel.gameDetailUiState.collectAsState()
//
//    val isGameFavorite by remember { mutableStateOf(false) }
//    LaunchedEffect(key1 = gameId) {
//        viewModel.checkIfFavorite(gameId)
//        viewModel.getGameDetail(gameId = gameId)
//    }
//
//    GameDetailScreenContent(
//        state = state,
//        isLiked = isGameFavorite,
//        events = { event ->
//            when (event) {
//                is GameDetailUiEvents.AddToFavorites -> viewModel.insertFavorite(event.game)
//                is GameDetailUiEvents.NavigateBack -> navController.popBackStack()
//                is GameDetailUiEvents.ShareGame -> TODO()
//            }
//        },
//        navController = navController
//    )
}

//@Composable
//fun GameDetailScreenContent(
//    state: GameDetailUiState,
//    events: (GameDetailUiEvents) -> Unit,
//    modifier: Modifier = Modifier,
//    isLiked: Boolean,
//    navController: NavController,
//) {
//
//    Column(
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier
//            .fillMaxSize()
//            .background(primaryBackground),
//    ) {
//
//        Box(modifier = Modifier.fillMaxHeight(0.5f)) {
//            AsyncImage(
//                model = state.gameDetails.background_image,
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.align(Alignment.Center)
//            )
//            TransparentBox()
//            Row {
//                Button(
//                    onClick = { navController.popBackStack() },
//                    shape = RoundedCornerShape(3.dp),
//                    modifier = Modifier
//                        .height(40.dp)
//                        .width(40.dp)
//                        .padding(start = 10.dp, top = 10.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = semiTransparentWhite
//                    )
//                ) {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                        contentDescription = "get back",
//                        tint = Color(0xAAFFFFFF)
//                    )
//                }
//            }
//            Button(
//                onClick = {  }) {
//
//            }
//
//        }
//
//        Column(
//            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
//        ) {
//            MyText(
//                text = state.gameDetails.name,
//                fontSize = 22,
//                weight = 800,
//                modifier = Modifier.padding(bottom = 5.dp)
//            )
//            MyText(
//                text = stringResource(id = R.string.developers),
//                fontSize = 10,
//                weight = 300,
//                modifier = Modifier.padding(bottom = 5.dp),
//                color = secondaryText
//            )
//            MyText(
//                text = "CDPR",
//                fontSize = 14,
//                weight = 600,
//                modifier = Modifier.padding(bottom = 5.dp)
//            )
//            MyText(
//                text = stringResource(id = R.string.year_of_release),
//                fontSize = 10,
//                weight = 300,
//                modifier = Modifier.padding(bottom = 5.dp),
//                color = secondaryText
//            )
//            MyText(
//                text = state.gameDetails.released!!.replace("-", "."),
//                fontSize = 14,
//                weight = 600,
//                modifier = Modifier.padding(bottom = 5.dp)
//            )
//            MyText(
//                text = stringResource(id = R.string.description),
//                fontSize = 16,
//                weight = 300,
//                modifier = Modifier.padding(bottom = 5.dp),
//                color = secondaryText
//            )
//
//            MyText(
//                text = state.gameDetails.description.removeTagsHTML(),
//                fontSize = 14,
//                weight = 400,
//                modifier = Modifier.padding(bottom = 5.dp)
//            )
//        }
//    }
//}


@Composable
fun GameDetailMainScreen(
    modifier: Modifier = Modifier,
    state: GameDetailUiState,
    //events: (GameDetailUiEvents) -> Unit,
    //navController: NavController
) {
    val buttonInteractionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier

            .background(primaryBackground)
            .padding(0.dp)
            .fillMaxSize(),


        ) {
        Button(
            onClick = { },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(10.dp)
                .size(40.dp)
                .scaleOnPress(interactionSource = buttonInteractionSource),
            colors = ButtonDefaults.buttonColors(
                containerColor = semiTransparentWhite
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.DarkGray
            )
        }


        Box(modifier = Modifier.fillMaxHeight(0.5f)) {
            AsyncImage(
                model = state.gameDetails.background_image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.align(Alignment.Center)
            )
            TransparentBox()

            Button(
                onClick = { },
                shape = CircleShape,
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 16.dp, end = 16.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
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

fun Modifier.scaleOnPress(
    interactionSource: InteractionSource,
) = composed {
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        if (isPressed) {
            0.95f
        } else {
            1f
        }
    )
    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
}


@Preview
@Composable
fun previewGameDetail() {
    GameDetailMainScreen(
        state = GameDetailUiState(

        )
    )
}