package com.daejol.domain.di

import com.daejol.domain.repository.CatImagesRepository
import com.daejol.domain.repository.DogImagesRepository
import com.daejol.domain.repository.RankingRepository
import com.daejol.domain.usecase.CheckAuth
import com.daejol.domain.usecase.GetImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.daejol.domain.usecase.GetRankingUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetImageUseCase(
        catImagesRepository: CatImagesRepository,
        dogImagesRepository: DogImagesRepository
    ): GetImageUseCase {
        return GetImageUseCase(catImagesRepository, dogImagesRepository)
    }

    @Singleton
    @Provides
    fun provideGetRankingUseCase(
        rankingRepository: RankingRepository
    ): GetRankingUseCase = GetRankingUseCase(rankingRepository)

    @Singleton
    @Provides
    fun provideCheckAuthUseCase(
        rankingRepository: RankingRepository
    ): CheckAuth = CheckAuth(rankingRepository)
}
