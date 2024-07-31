package com.daejol.data.dogdata.repository

import com.daejol.domain.DataState
import com.daejol.data.dogdata.api.DogImagesApi
import com.daejol.data.dogdata.dto.DogMapper.toDomain
import com.daejol.domain.repository.DogImagesRepository
import com.daejol.domain.entity.ImageEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogImagesRepositoryImpl @Inject constructor(
    private val dogImagesApi: DogImagesApi,
) : DogImagesRepository {
    override suspend fun getDogRandomImages(imageCount: Int): Flow<DataState<List<ImageEntity>>> = flow {
        try {
            val dogImages =
                dogImagesApi.getCatImages().body()?.map { it.toDomain() }?.toList()
            println(dogImages)
            emit(DataState.Success(data = dogImages))
        } catch (e: Exception) {
            emit(DataState.Fail(data = null, exception = e))
        }
    }
}
