package com.icebeal.core.data.repository

import com.icebeal.core.model.domain.MovieDomain
import com.icebeal.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getMovie(): Flow<Resource<List<MovieDomain>>>

    fun getFavoriteMovie(): Flow<List<MovieDomain>>

    fun setMovieFavorite(movie: MovieDomain, state: Boolean)

    fun searchMovie(searchQuery: String): Flow<List<MovieDomain>>

}