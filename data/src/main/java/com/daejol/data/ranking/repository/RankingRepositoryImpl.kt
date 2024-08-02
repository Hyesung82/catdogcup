package com.daejol.data.ranking.repository

import com.daejol.data.ranking.api.RankingApi
import com.daejol.data.ranking.api.SignInApi
import com.daejol.domain.DataState
import com.daejol.domain.entity.VotesEntity
import com.daejol.domain.repository.RankingRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RankingRepositoryImpl @Inject constructor(
    private val rankingApi: RankingApi
) : RankingRepository {
    override suspend fun getRanking() = flow<DataState<List<VotesEntity>>> {
        try {
            val votes = rankingApi.getVotes().body()?.map { it.toDomain() }
            println("RankingRepositoryImpl: $votes")
            emit(DataState.Success(data = votes))
        } catch (e: Exception) {
            println("RankingRepositoryImpl: $e")
            emit(DataState.Fail(data = null, exception = e))
        }
    }

    override suspend fun checkIfUserIsSignedIn() = SignInApi.checkAuth()
}
