package by.eapp.thegamesearching.data.remote.models

data class GameDetailDto(
    val id: Int,
    val name: String,
    val description: String,
    val released: String? = null,
    val background_image: String?,
    val background_image_additional: String? = null,
    val platforms: List<PlatformDto>,
)
