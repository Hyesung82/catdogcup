package com.daejol.domain.usecase

import com.daejol.domain.repository.RankingRepository
import javax.inject.Inject

class GetRankingUseCase @Inject constructor(
    private val rankingRepository: RankingRepository
) {
    suspend fun getPopularCatsAndDogs() = rankingRepository.getRanking()
}
