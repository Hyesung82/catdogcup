package com.daejol.domain.usecase

import com.daejol.domain.repository.RankingRepository
import javax.inject.Inject

class CheckAuth @Inject constructor(
    private val rankingRepository: RankingRepository
) {
    suspend fun checkIfUserIsSignedIn() = rankingRepository.checkIfUserIsSignedIn()
}
