package com.daejol.domain.repository

import com.daejol.domain.DataState
import com.daejol.domain.entity.VotesEntity
import kotlinx.coroutines.flow.Flow

interface VotesRepository {
    suspend fun checkIfUserIsSignedIn()

    suspend fun getVotes(): Flow<DataState<List<VotesEntity>>>
}
