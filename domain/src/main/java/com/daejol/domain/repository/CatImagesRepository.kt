package com.daejol.domain.repository

import com.daejol.domain.DataState
import com.daejol.domain.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface CatImagesRepository {
    suspend fun getCatRandomImages(imageCount: Int): Flow<DataState<List<ImageEntity>>>
}