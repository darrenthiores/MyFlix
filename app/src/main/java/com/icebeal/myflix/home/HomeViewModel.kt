package com.icebeal.myflix.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.icebeal.core.domain.MovieUseCase
import com.icebeal.core.model.presenter.Movie
import com.icebeal.core.utils.DataMapper
import kotlinx.coroutines.flow.map

class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    val movies = movieUseCase.getMovie().asLiveData()

    fun searchMovie(searchQuery: String): LiveData<List<Movie>> {
        return movieUseCase.searchMovie(searchQuery).map { DataMapper.mapDomainToPresenter(it) }
            .asLiveData()
    }

}