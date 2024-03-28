package by.eapp.thegamesearching.data.mappers

import by.eapp.thegamesearching.data.local.GameEntity
import by.eapp.thegamesearching.data.remote.models.DeveloperDto
import by.eapp.thegamesearching.data.remote.models.GameDetailDto
import by.eapp.thegamesearching.data.remote.models.GameDto
import by.eapp.thegamesearching.data.remote.models.GamesResponse
import by.eapp.thegamesearching.data.remote.models.GenreDto
import by.eapp.thegamesearching.data.remote.models.PlatformDto
import by.eapp.thegamesearching.domain.model.Developer
import by.eapp.thegamesearching.domain.model.Game
import by.eapp.thegamesearching.domain.model.Genre
import by.eapp.thegamesearching.domain.model.Platform

fun Game.toGameEntity(): GameEntity {
    return GameEntity(
        id = id,
        name = name,
        backgroundImage = backgroundImage,
        released = ""
    )
}
fun GameDetailDto.toGameEntity(): GameEntity {
    return GameEntity(
        id = id,
        name = name,
        released = released,
        backgroundImage = background_image,
    )
}

fun GameDto.toDomain(): Game {
    return Game(
        id = this.id,
        name = this.name,
        backgroundImage = this.background_image?:""
    )
}


fun GameEntity.toDomain() : Game {
    return Game(
       id = this.id,
        name = this.name,
        backgroundImage = this.backgroundImage?:""
    )
}

fun GenreDto.toGenre() : Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}

fun PlatformDto.toPlatform() : Platform {
    return Platform(
        id = this.id,
        name = this.name
    )
}

fun DeveloperDto.toDeveloper() : Developer {
    return Developer(
        id = this.id,
        name = this.name,
        slug = this.slug
    )
}

