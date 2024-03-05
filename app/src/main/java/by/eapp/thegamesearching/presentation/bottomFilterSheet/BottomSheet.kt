package by.eapp.thegamesearching.presentation.bottomFilterSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import by.eapp.thegamesearching.R
import by.eapp.thegamesearching.presentation.bottomFilterSheet.components.RowOfChips
import by.eapp.thegamesearching.presentation.bottomFilterSheet.components.TextFieldBottomSheet

import by.eapp.thegamesearching.presentation.home.HomeScreenUiState
import by.eapp.thegamesearching.presentation.home.components.MyText
import by.eapp.thegamesearching.ui.theme.primaryBottomFilterSheetColor
import by.eapp.thegamesearching.ui.theme.primaryText
import by.eapp.thegamesearching.ui.theme.secondaryText
import by.eapp.thegamesearching.ui.theme.selectedColorChip
import by.eapp.thegamesearching.ui.theme.unselectedColorChip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomFilterSheet(
    sheetState: SheetState,
    listOfGenres: List<String>,
    filterState: BottomFilterSheetUiState,
    homeState: HomeScreenUiState
) {

    var selectedChip by rememberSaveable {
        mutableStateOf("")
    }
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            filterState.selectedYearOfRelease = ""
            homeState.isSheetOpen = false
        },
        containerColor = primaryBottomFilterSheetColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .padding(top = 10.dp, start = 10.dp,),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            MyText(
                text = stringResource(id = R.string.genre),
                fontSize = 15,
                color = secondaryText,
                modifier = Modifier.padding(5.dp)
            )

            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(listOfGenres) { item ->
                    FilterChip(
                        modifier = Modifier.padding(horizontal = 6.dp),
                        selected = (item == selectedChip),
                        onClick = {
                            selectedChip = item
                        },
                        label = {
                            MyText(
                                text = item,
                                fontSize = 11,
                                color = primaryText
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = selectedColorChip,
                            containerColor = unselectedColorChip,
                        ),
                        border = null
                    )
                }
            }
            MyText(
                text = stringResource(id = R.string.year_of_release),
                fontSize = 15,
                color = secondaryText,
                modifier = Modifier.padding(5.dp)
            )
            TextFieldBottomSheet(
                state = filterState,
                onValueChange = {newValue ->
                    filterState.selectedYearOfRelease = newValue.toString()
                }
            )
            MyText(
                text = stringResource(id = R.string.platforms),
                fontSize = 15,
                color = secondaryText,
                modifier = Modifier.padding(5.dp)
            )
            RowOfChips(
                state = filterState,
                onSelectedChanged = { platform ->
                    val oldList = filterState.selectedPlatforms?.toMutableList() ?: mutableListOf()
                    if (oldList.contains(platform)) {
                        oldList.remove(platform)
                    } else {
                        oldList.add(platform)
                    }
                    filterState.selectedPlatforms = oldList.toList()
                }
            )

            Button(onClick = {}) {

            }
        }

    }

}
