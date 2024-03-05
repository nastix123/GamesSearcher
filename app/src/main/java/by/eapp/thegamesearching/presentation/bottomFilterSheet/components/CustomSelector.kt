package by.eapp.thegamesearching.presentation.bottomFilterSheet.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import by.eapp.thegamesearching.presentation.bottomFilterSheet.BottomFilterSheetUiState
import by.eapp.thegamesearching.presentation.home.components.MyText
import by.eapp.thegamesearching.ui.theme.primaryText
import by.eapp.thegamesearching.ui.theme.secondaryText


@Composable
fun TextFieldBottomSheet(
    state: BottomFilterSheetUiState,
    onValueChange: (Int) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    Column(
        modifier = Modifier.padding(10.dp)
    ) {

        OutlinedTextField(
            placeholder = { MyText(text = "Enter the year", fontSize = 13)},
            value = state.selectedYearOfRelease!!,
            onValueChange = { newValue ->
                if (newValue.isNotBlank()) {
                    val year = newValue.toIntOrNull()
                    if (year != null && year in 1985..2024) {
                        state.selectedYearOfRelease = newValue
                        onValueChange(year)
                    }
                } else {
                    state.selectedYearOfRelease = newValue
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(30.dp),
            //label = { MyText(text = "Select", fontSize = 13)},
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = primaryText,
                focusedLabelColor = Color.White,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = secondaryText
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        
    }
}
