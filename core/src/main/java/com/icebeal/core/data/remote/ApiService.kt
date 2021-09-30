package com.icebeal.core.data.remote

import com.icebeal.core.BuildConfig
import com.icebeal.core.model.data.response.MovieApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getMovie(
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("region") region: String = "id"
    ): MovieApiResponse

}