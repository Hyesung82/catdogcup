package com.daejol.data.catdata.repository

import com.daejol.domain.DataState
import com.daejol.data.catdata.api.CatImagesApi
import com.daejol.data.catdata.dto.CatMapper.toDomain
import com.daejol.domain.repository.CatImagesRepository
import com.daejol.domain.entity.ImageEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatImagesRepositoryImpl @Inject constructor(
    private val catImagesApi: CatImagesApi
) : CatImagesRepository {
    override suspend fun getCatRandomImages(imageCount: Int): Flow<DataState<List<ImageEntity>>> =
        flow {

            val catImages =
                catImagesApi.getCatImages(imageCount).body()?.map { it.toDomain() }?.toList()
            println(catImages)
            emit(DataState.Success(data = catImages))

        }


}