package by.eapp.thegamesearching.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import by.eapp.thegamesearching.R
import by.eapp.thegamesearching.presentation.home.HomeScreenUiEvents
import by.eapp.thegamesearching.presentation.home.HomeScreenViewModel
import by.eapp.thegamesearching.presentation.home.components.GamesColumn
import by.eapp.thegamesearching.presentation.home.components.GenreRow
import by.eapp.thegamesearching.presentation.home.components.HeightSpacer
import by.eapp.thegamesearching.presentation.home.components.MyText
import by.eapp.thegamesearching.presentation.home.components.WidthSpacer
import by.eapp.thegamesearching.ui.theme.BackgroundForNavBar
import by.eapp.thegamesearching.ui.theme.primaryBackground
import by.eapp.thegamesearching.ui.theme.secondaryText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onSearchInputChanged: (String) -> Unit,
    onClicked: () -> Unit,
    searchFieldState: SearchFieldState,
    searchingText: String,

    ) {

    //val searchingText = remember { mutableStateOf("") }
    TextField(
        value = searchingText,
        shape = RoundedCornerShape(15.dp),
        onValueChange = {
            onSearchInputChanged(searchingText)
        },

        leadingIcon = {
            when (searchFieldState) {
                SearchFieldState.EmptyActive -> {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }

                SearchFieldState.WithInputActive -> {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }

                else -> {}
            }
            /* Icon(
                 imageVector = Icons.Default.Search,
                 contentDescription = null,
                 tint = Color.Black
             )*/
        },
        placeholder = {
            MyText(
                fontSize = 14,
                text = stringResource(id = R.string.search),
                color = Color.DarkGray
            )
        },
        modifier = Modifier
            .padding(0.5.dp)
            .fillMaxWidth(0.8f)
            .height(50.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = BackgroundForNavBar,
            disabledPlaceholderColor = Color.DarkGray,
            focusedPlaceholderColor = Color.DarkGray
        ),
        interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
            LaunchedEffect(key1 = interactionSource) {
                interactionSource.interactions.collect { interaction ->
                    if (interaction is PressInteraction.Release) {
                        onClicked.invoke()
                    }
                }
            }
        },
    )
}
