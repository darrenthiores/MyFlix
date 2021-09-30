package com.icebeal.core.domain

import com.icebeal.core.model.domain.MovieDomain
import com.icebeal.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getMovie(): Flow<Resource<List<MovieDomain>>>

    fun getFavoriteMovie(): Flow<List<MovieDomain>>

    fun setMovieFavorite(movie: MovieDomain, state: Boolean)

    fun searchMovie(searchQuery: String): Flow<List<MovieDomain>>

}