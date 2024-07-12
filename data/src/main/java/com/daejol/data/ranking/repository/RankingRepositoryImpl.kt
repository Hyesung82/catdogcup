package com.daejol.data.ranking.repository

import com.daejol.data.ranking.api.RankingApi
import com.daejol.domain.repository.RankingRepository
import javax.inject.Inject

class RankingRepositoryImpl @Inject constructor() : RankingRepository {
    override suspend fun getRanking() = RankingApi.getPopularCatsAndDogs()

    override suspend fun checkIfUserIsSignedIn() = RankingApi.checkAuth()
}
