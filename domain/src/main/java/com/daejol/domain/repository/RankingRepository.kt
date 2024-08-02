package com.daejol.domain.repository

import com.daejol.domain.DataState
import com.daejol.domain.entity.VotesEntity
import kotlinx.coroutines.flow.Flow

interface RankingRepository {
    suspend fun getRanking(): Flow<DataState<List<VotesEntity>>>

    suspend fun checkIfUserIsSignedIn()
}
