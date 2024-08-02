package com.daejol.domain.usecase

import com.daejol.domain.repository.VotesRepository
import javax.inject.Inject

class CheckAuthUseCase @Inject constructor(
    private val votesRepository: VotesRepository
) {
    suspend fun checkIfUserIsSignedIn() = votesRepository.checkIfUserIsSignedIn()
}
