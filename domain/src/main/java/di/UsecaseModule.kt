package di

import com.daejol.domain.repository.CatImagesRepository
import com.daejol.domain.repository.DogImagesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import usecase.GetImageUsecase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {

    @Singleton
    @Provides
    fun provideGetImageUsecase(
        catImagesRepository: CatImagesRepository,
        dogImagesRepository: DogImagesRepository
    ): GetImageUsecase {
        return GetImageUsecase(catImagesRepository, dogImagesRepository)
    }
}