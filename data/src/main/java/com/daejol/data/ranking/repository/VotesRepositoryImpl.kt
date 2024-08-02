package com.daejol.data.ranking.repository

import com.daejol.data.ranking.api.CatVotesApi
import com.daejol.data.ranking.api.DogVotesApi
import com.daejol.data.ranking.api.SignInApi
import com.daejol.domain.DataState
import com.daejol.domain.entity.VotesEntity
import com.daejol.domain.repository.VotesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VotesRepositoryImpl @Inject constructor(
    private val catVotesApi: CatVotesApi,
    private val dogVotesApi: DogVotesApi
) : VotesRepository {
    override suspend fun checkIfUserIsSignedIn() = SignInApi.checkAuth()

    override suspend fun getVotes() = flow<DataState<List<VotesEntity>>> {
        try {
            val catVotes = catVotesApi.getVotes().body()?.map { it.toDomain() }
            val dogVotes = dogVotesApi.getVotes().body()?.map { it.toDomain() }
            val votes = (catVotes ?: listOf()) + (dogVotes ?: listOf())

            println("RankingRepositoryImpl: $votes")
            emit(DataState.Success(data = votes))
        } catch (e: Exception) {
            println("RankingRepositoryImpl: $e")
            emit(DataState.Fail(data = null, exception = e))
        }
    }
}
