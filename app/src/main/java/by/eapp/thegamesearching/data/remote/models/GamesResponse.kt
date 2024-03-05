package by.eapp.thegamesearching.data.remote.models

data class GamesResponse(
    val count: Int,
    val results: List<GameDto>,
    val next: String,
    val previous: String,
)
