package com.icebeal.core.domain

import com.icebeal.core.data.repository.IMovieRepository
import com.icebeal.core.model.domain.MovieDomain
import com.icebeal.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getMovie(): Flow<Resource<List<MovieDomain>>> = movieRepository.getMovie()

    override fun getFavoriteMovie(): Flow<List<MovieDomain>> = movieRepository.getFavoriteMovie()

    override fun setMovieFavorite(movie: MovieDomain, state: Boolean) =
        movieRepository.setMovieFavorite(movie, state)

    override fun searchMovie(searchQuery: String): Flow<List<MovieDomain>> =
        movieRepository.searchMovie(searchQuery)

}