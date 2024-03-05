package by.eapp.thegamesearching.presentation.bottomFilterSheet.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.eapp.thegamesearching.presentation.bottomFilterSheet.BottomFilterSheetUiState
import by.eapp.thegamesearching.ui.theme.primaryText
import by.eapp.thegamesearching.ui.theme.selectedColorChip
import by.eapp.thegamesearching.ui.theme.unselectedColorChip

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RowOfChips(
    state: BottomFilterSheetUiState,
    onSelectedChanged: (String) -> Unit = {},
) {

    val selectedChips: MutableState<List<String>> = remember {
        mutableStateOf(listOf())
    }

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.padding(6.dp)
    ) {
        state.selectedPlatforms?.forEach { item ->
            FilterChip(
                modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp),
                selected = state.selectedPlatforms!!.contains(item),
                onClick = {
                    onSelectedChanged(item)
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
