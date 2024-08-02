package com.daejol.domain.usecase

import com.daejol.domain.DataState
import com.daejol.domain.entity.VotesEntity
import com.daejol.domain.repository.VotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRankingUseCase @Inject constructor(
    private val votesRepository: VotesRepository
) {
    suspend fun getPopularAnimals(): Flow<DataState<List<VotesEntity>>> {
        return votesRepository.getVotes()
    }
}
