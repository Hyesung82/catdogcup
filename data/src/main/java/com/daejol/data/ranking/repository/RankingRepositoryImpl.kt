package com.daejol.data.ranking.repository

import com.daejol.data.ranking.api.SignInApi
import com.daejol.domain.repository.RankingRepository
import javax.inject.Inject

class RankingRepositoryImpl @Inject constructor() : RankingRepository {
    override suspend fun getRanking() = SignInApi.getPopularAnimals()

    override suspend fun checkIfUserIsSignedIn() = SignInApi.checkAuth()
}
