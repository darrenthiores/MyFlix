package com.icebeal.core.utils

import com.icebeal.core.model.data.entity.MovieEntity
import com.icebeal.core.model.data.response.MovieResponse
import com.icebeal.core.model.domain.MovieDomain
import com.icebeal.core.model.presenter.Movie

object DataMapper {

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<MovieDomain> =
        input.map {
            MovieDomain(
                it.id,
                it.title,
                it.poster,
                it.overview,
                it.releaseDate,
                it.rating,
                it.voter,
                it.isFavorite
            )
        }

    fun mapDomainToEntity(input: MovieDomain): MovieEntity = MovieEntity(
        input.id,
        input.title,
        input.poster,
        input.overview,
        input.releaseDate,
        input.rating,
        input.voter,
        input.isFavorite
    )

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> =
        input.map {
            MovieEntity(
                it.id,
                it.title,
                it.poster,
                it.overview,
                it.releaseDate,
                it.rating,
                it.voter
            )
        }

    fun mapDomainToPresenter(input: List<MovieDomain>): List<Movie> =
        input.map {
            Movie(
                it.id,
                it.title,
                it.poster,
                it.overview,
                it.releaseDate,
                it.rating,
                it.voter,
                it.isFavorite
            )
        }

    fun mapPresenterToDomain(input: Movie): MovieDomain = MovieDomain(
        input.id,
        input.title,
        input.poster,
        input.overview,
        input.releaseDate,
        input.rating,
        input.voter,
        input.isFavorite
    )

}