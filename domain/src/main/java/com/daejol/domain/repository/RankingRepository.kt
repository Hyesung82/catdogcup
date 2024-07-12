package com.daejol.domain.repository

interface RankingRepository {
    suspend fun getRanking()

    suspend fun checkIfUserIsSignedIn()
}
