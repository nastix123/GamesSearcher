package by.eapp.thegamesearching.data.remote.models

data class GenreResponse(
    val next: String?,
    val previous: String?,
    val results: List<GenreDto>
)
