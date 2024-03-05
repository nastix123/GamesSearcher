package by.eapp.thegamesearching.presentation.bottomFilterSheet

data class BottomFilterSheetUiState(
    var selectedGenre: String? = null,
    var selectedYearOfRelease: String? = "2024",
    var selectedDeveloper: String? = "developer",
    var selectedPlatforms: List<String>? = emptyList(),
    val allPlatforms: List<String> = emptyList()
)
