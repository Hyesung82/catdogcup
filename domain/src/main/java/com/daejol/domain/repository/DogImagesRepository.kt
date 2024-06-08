package com.daejol.domain.repository

import DataState
import entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface DogImagesRepository {
    suspend fun getDogRandomImages(imageCount: Int): Flow<DataState<List<ImageEntity>>>
}