package usecase

import com.daejol.domain.repository.CatImagesRepository
import com.daejol.domain.repository.DogImagesRepository
import entity.ImageEntity
import kotlinx.coroutines.flow.*
import javax.inject.Inject

enum class WorldCupType(value: String) {
    CAT("CAT"), DOG("DOG"), COMBINED("COMBINED")
}

class GetImageUseCase @Inject constructor(
    private val catImagesRepository: CatImagesRepository,
    private val dogImagesRepository: DogImagesRepository
) {
    suspend fun getAnimalList(
        type: WorldCupType,
        randomImageCount: Int
    ): Flow<List<ImageEntity>> {
        println("[keykat] randomImageCount:: $randomImageCount")
        return when (type) {
            WorldCupType.CAT -> getRandomCatImages(randomImageCount)
                ?: flow { listOf<ImageEntity>() }

            WorldCupType.DOG -> getRandomCatImages(randomImageCount)
                ?: flow { listOf<ImageEntity>() }

            WorldCupType.COMBINED -> merge(
                getRandomCatImages(randomImageCount / 2)?.map { it } ?: flow { },
                getRandomDogImages(randomImageCount / 2)?.map { it } ?: flow { }
            )
        }
    }

    private suspend fun getRandomCatImages(randomImageCount: Int): Flow<List<ImageEntity>>? {
        try {
            val flow = catImagesRepository.getCatRandomImages(randomImageCount).map {
                return@map it.on(
                    // TODO: Entity 자체를 넘겨주는 것이 좋을까? 아니면 정말 필요한 것만 한 번 더 정제해서
                    // TODO: 주는 것이 나을까?
                    onSuccess = { it },
                    // TODO: onError일 때 어떤 데이터를 던져주는 것이 좋을까?
                    onError = { throw Exception() }
                ) ?: listOf<ImageEntity>()
            }

            return flow
        } catch (e: Exception) {
            return null
        }
    }

    private suspend fun getRandomDogImages(randomImageCount: Int): Flow<List<ImageEntity>>? {
        try {
            val flow = dogImagesRepository.getDogRandomImages(randomImageCount).map {
                return@map it.on(
                    // TODO: Entity 자체를 넘겨주는 것이 좋을까? 아니면 정말 필요한 것만 한 번 더 정제해서
                    // TODO: 주는 것이 나을까?
                    onSuccess = { list ->
                        list
                    },
                    // TODO: onError일 때 어떤 데이터를 던져주는 것이 좋을까?
                    onError = {
                        throw Exception()
                    }
                ) ?: listOf<ImageEntity>()
            }

            return flow
        } catch (e: Exception) {
            return null
        }
    }
}