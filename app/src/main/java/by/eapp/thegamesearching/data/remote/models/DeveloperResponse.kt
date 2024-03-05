package by.eapp.thegamesearching.data.remote.models

data class DeveloperResponse(
    val count: Int = 50,
    val results: List<DeveloperDto>
)
