package com.daejol.data.dogdata.api

import com.daejol.data.dogdata.dto.DogImageDto
import retrofit2.http.GET

public interface DogImagesApi {
    // https://api.thecatapi.com/v1/images/search?limit=10
    @GET("images/search")
    suspend fun getCatImages(): retrofit2.Response<List<DogImageDto>?>
}
