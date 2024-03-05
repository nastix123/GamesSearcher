package by.eapp.thegamesearching.presentation.home.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.eapp.thegamesearching.ui.theme.primaryText

@Composable
fun HeightSpacer(
    height: Dp = 10.dp
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    )
}

@Composable
fun WidthSpacer(
    width: Dp = 10.dp
) {
    Spacer(
        modifier = Modifier
            .width(width)
    )
}
@Composable
fun MyText(
    text: String,
    fontSize: Int,
    weight: Int = 400,
    color: Color = primaryText,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = TextStyle(
            color = primaryText,
            fontWeight = FontWeight(weight),
            fontSize = fontSize.sp
        ),
        modifier = modifier.wrapContentSize(),
        textAlign = TextAlign.Justify
    )
}