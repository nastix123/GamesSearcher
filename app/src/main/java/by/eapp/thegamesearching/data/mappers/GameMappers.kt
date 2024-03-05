package by.eapp.thegamesearching.data.mappers

import by.eapp.thegamesearching.data.local.GameEntity
import by.eapp.thegamesearching.data.remote.models.GameDetailDto
import by.eapp.thegamesearching.data.remote.models.GameDto
import by.eapp.thegamesearching.data.remote.models.GamesResponse
import by.eapp.thegamesearching.domain.model.Game

fun GameDetailDto.toGameEntity(): GameEntity {
    return GameEntity(
        id = id,
        name = name,
        released = released,
        backgroundImage = background_image,
        backgroundImageAdditional = background_image_additional,
        description = description,
        //developers = developers,
    )
}

fun GameDto.toDomain(): Game {
    return Game(
        id = this.id,
        name = this.name,
        backgroundImage = this.background_image!!
    )
}


fun GameEntity.toDomain() : Game {
    return Game(
       id = this.id,
        name = this.name,
        backgroundImage = this.backgroundImage!!
    )
}
