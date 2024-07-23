package di

import com.daejol.domain.repository.CatImagesRepository
import com.daejol.domain.repository.DogImagesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import usecase.GetImageUseCase
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