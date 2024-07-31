package com.daejol.data.dogdata.repository

import com.daejol.domain.DataState
import com.daejol.data.dogdata.dto.DogMapper.toDomain
import com.daejol.domain.repository.DogBreedsRepository
import com.daejol.data.dogdata.api.DogBreedsApi
import com.daejol.domain.entity.BreedInfoEntity
import com.daejol.domain.entity.BreedTypeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogBreedsRepositoryImpl @Inject constructor(
    private val dogBreedsApi: DogBreedsApi,
): DogBreedsRepository {

    override suspend fun getDogBreeds(): Flow<DataState<List<BreedInfoEntity>>> = flow {
        try {
            val dogBreeds =
                dogBreedsApi.getDogBreeds().body()?.map { it.toDomain() }?.toList()
            emit(DataState.Success(data = dogBreeds))
        } catch (e: Exception) {
            emit(DataState.Fail(data = null, exception = e))
        }
    }

    override suspend fun getDogBreed(id: String): Flow<DataState<BreedTypeEntity>> = flow {
        try {
            val dogBreedType =
                dogBreedsApi.getDogBreed(id = id).body()
            emit(DataState.Success(data = dogBreedType?.toDomain()))
        } catch (e: Exception) {
            emit(DataState.Fail(data = null, exception = e))
        }
    }
}
