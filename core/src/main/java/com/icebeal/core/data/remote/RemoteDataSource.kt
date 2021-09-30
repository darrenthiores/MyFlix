package com.icebeal.core.data.remote

import android.util.Log
import com.icebeal.core.model.data.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovie(): Flow<ApiResponse<List<MovieResponse>>> = flow {

        try {

            val response = apiService.getMovie()
            val movie = response.movies

            if (movie.isNotEmpty()) {

                emit(ApiResponse.Success(movie))

            } else {

                emit(ApiResponse.Empty)

            }

        } catch (e: Exception) {

            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())

        }

    }.flowOn(Dispatchers.IO)

}