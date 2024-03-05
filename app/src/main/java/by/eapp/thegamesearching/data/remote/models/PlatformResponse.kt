package by.eapp.thegamesearching.data.remote.models

data class PlatformResponse(
    val count: Int = 10,
    val results: List<PlatformDto>
)
