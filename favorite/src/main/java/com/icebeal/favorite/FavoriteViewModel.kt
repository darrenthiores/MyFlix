package com.icebeal.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.icebeal.core.domain.MovieUseCase
import com.icebeal.core.utils.DataMapper
import kotlinx.coroutines.flow.map

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val favoriteMovies =
        movieUseCase.getFavoriteMovie().map { DataMapper.mapDomainToPresenter(it) }.asLiveData()

}