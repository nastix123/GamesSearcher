package by.eapp.thegamesearching.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import by.eapp.thegamesearching.presentation.home.HomeScreenUiState
import by.eapp.thegamesearching.presentation.home.HomeScreenViewModel
import by.eapp.thegamesearching.ui.theme.primaryText
import by.eapp.thegamesearching.ui.theme.selectedColorChip
import by.eapp.thegamesearching.ui.theme.unselectedColorChip

@Composable
fun GenreRow(
    genreNames: List<String>,
    state: HomeScreenUiState,
    viewModel: HomeScreenViewModel
) {

    var selectedChip by rememberSaveable {
        mutableStateOf("")
    }

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(genreNames) { item ->
            FilterChip(
                modifier = Modifier.padding(horizontal = 6.dp),
                selected = (item == selectedChip),
                onClick = {
                    selectedChip = item
                    viewModel.setGenre(selectedChip)


                },
                label = {
                    Text(text = item, color = primaryText)
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = selectedColorChip,
                    containerColor = unselectedColorChip,
                ),
                border = null
            )
        }
    }
}

