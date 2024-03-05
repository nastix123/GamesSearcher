package by.eapp.thegamesearching.presentation.home.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import by.eapp.thegamesearching.data.remote.models.GameDto
import by.eapp.thegamesearching.navigation.Screen
import by.eapp.thegamesearching.presentation.home.HomeScreenUiEvents
import by.eapp.thegamesearching.presentation.utils.shimmerBrush
import coil.compose.SubcomposeAsyncImage

@Composable
fun GameItem(
    imageModel: String,
    contentDescription: String?,
    title: String,
    modifier: Modifier = Modifier,
    gameId: Int,
    onClick: () -> Unit = {},
    navController: NavController
) {
    val showShimmer = remember { mutableStateOf(true) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.GameDetailScreen.wihArgs(gameId))
            },
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            SubcomposeAsyncImage(
                model = imageModel,
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(
                        shimmerBrush(targetValue = 1300f, showShimmer = showShimmer.value)
                    ),
                onSuccess = {
                    showShimmer.value = false
                }
            )
            TransparentBox()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                MyText(
                    text = title,
                    fontSize = 11,
                    weight = 600
                )
            }
        }
    }

}

@Composable
fun TransparentBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    ),
                    startY = 300f
                )
            )
    ) {}
}