package com.daejol.domain.di

import com.daejol.domain.repository.CatImagesRepository
import com.daejol.domain.repository.DogImagesRepository
import com.daejol.domain.usecase.GetImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}
