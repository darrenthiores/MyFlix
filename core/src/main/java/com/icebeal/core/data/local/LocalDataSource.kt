package com.icebeal.core.data.local

import com.icebeal.core.model.data.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    suspend fun insert(movie: List<MovieEntity>) {
        movieDao.insert(movie)
    }

    fun setMovieFavorite(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        movieDao.setMovieFavorite(movie)
    }

    fun getMovie(): Flow<List<MovieEntity>> = movieDao.getMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    fun searchMovie(searchQuery: String): Flow<List<MovieEntity>> =
        movieDao.searchMovie(searchQuery)

}