package by.eapp.thegamesearching.domain.use_cases

import by.eapp.thegamesearching.domain.repository.GameDetailRepository
import javax.inject.Inject

class GetGameDetailUseCase @Inject constructor(
    private val gameDetailRepository: GameDetailRepository
){
    suspend operator fun invoke(gameId: Int) = gameDetailRepository.getGameDetails(gameId)
}