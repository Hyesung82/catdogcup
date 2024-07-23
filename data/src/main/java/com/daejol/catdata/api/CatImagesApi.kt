package com.daejol.catdata.api

import com.daejol.catdata.dto.CatImageDto
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface CatImagesApi {
    // https://api.thecatapi.com/v1/images/search?limit=10
    @GET("images/search?format=json&has_breeds=true")
    suspend fun getCatImages(
        @Query("limit") limit: Int
    ): retrofit2.Response<List<CatImageDto>?>


}