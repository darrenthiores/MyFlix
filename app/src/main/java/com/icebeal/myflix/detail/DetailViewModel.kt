package com.icebeal.myflix.detail

import androidx.lifecycle.ViewModel
import com.icebeal.core.domain.MovieUseCase
import com.icebeal.core.model.presenter.Movie
import com.icebeal.core.utils.DataMapper

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setMovieFavorite(movie: Movie, state: Boolean) {

        val movieDomain = DataMapper.mapPresenterToDomain(movie)
        movieUseCase.setMovieFavorite(movieDomain, state)

    }

}